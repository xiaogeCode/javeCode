package Yj.ui;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Yj.model.LiuSiGuaModel;
import Yj.action.*;
public class LiuSiGuaView extends JFrame implements ActionListener {

    private JPanel jp3;

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane jsp;

    public LiuSiGuaView () throws Exception {
        String[] colnames = {"", "乾", "兑", "离", "震", "巽", "坎" ,"艮","坤"};
        model = new DefaultTableModel(colnames, 8);
        table = new JTable(model);
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setPreferredScrollableViewportSize(new Dimension(360, 320));
        table.setEnabled(false);

        jsp = new JScrollPane(table);
        jp3 = new JPanel();

        jp3.add(jsp);
        this.add(jp3);

        this.setLayout(new GridLayout(1, 1));
        this.setTitle("卦象");
        this.setSize(500, 500);
        this.setLocation(150, 150);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
        this.setResizable(false);

        readGuaInfo();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    private void readGuaInfo() throws Exception {
        table.setValueAt ("乾", 0, 0);
        table.setValueAt ("兑", 1, 0);
        table.setValueAt ("离", 2, 0);
        table.setValueAt ("震", 3, 0);
        table.setValueAt ("巽", 4, 0);
        table.setValueAt ("坎", 5, 0);
        table.setValueAt ("艮", 6, 0);
        table.setValueAt ("坤", 7, 0);
        DefaultTableCellRenderer d = new DefaultTableCellRenderer();

        BaguaAction action = new BaguaAction();
        List<LiuSiGuaModel> godList;
        godList = action.query();
        System.out.println("count :"+godList.size());
        LiuSiGuaModel gua;
        for(int i=0;i<godList.size();i++){
            gua = godList.get(i);
            System.out.println("name"+gua.getName());
        }

        d.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i< table.getColumnCount();i++)
        {
            TableColumn col = table.getColumn(table.getColumnName(i));
            col.setCellRenderer(d);
        }
        for (int k = 0; k < 8; k++) {
            for (int j = 1; j < 9; j++) {
                gua = godList.get(k*8+(j-1));
                table.setValueAt (gua.getName(), k, j);
            }
        }


    }

}
