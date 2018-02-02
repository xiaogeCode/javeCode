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

	// �������  
    JLabel jl2,jl1,jl3,jl4,jl5,jl6,jl7,jl8 = null;  
    JTextField jtf,jtf2 = null;  
    JButton jb,jb2 = null;  
    JPanel jp1, jp2,jp3,jp4,jp5,jp6 = null;  
  
    DefaultTableModel model,model2 = null;  
    JTable table,table2 = null;  
    JScrollPane jsp,jsp2 = null;
    		
	public LiuSiGuaView () throws Exception {
		// �������       
		/*
        jl1 = new JLabel("Ǭ");  
        jl2	= new JLabel("��");    
        jl3 = new JLabel("��");  
        jl4 = new JLabel("��");  
        jl5 = new JLabel("��");  
        jl6	= new JLabel("��");    
        jl7 = new JLabel("��");  
        jl8 = new JLabel("��"); 
        */
        
     // ���ñ��1  
        String[] colnames = {"", "Ǭ", "��", "��", "��", "��", "��" ,"��","��"};  
        model = new DefaultTableModel(colnames, 8);  
        table = new JTable(model);  
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setPreferredScrollableViewportSize(new Dimension(360, 320));
        table.setEnabled(false);
        

        jsp = new JScrollPane(table);
        
        
		jp1 = new JPanel();  
        jp2 = new JPanel();  
        jp3 = new JPanel(); 
        /*
        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);
        jp1.add(jl6);
        jp1.add(jl7);
        jp1.add(jl8);
         */
        
        jp3.add(jsp);  



        //jp1.setLayout(new GridLayout(6,1));
		
        
        //this.add(jp1);  
        //this.add(jp5);  
        //this.add(jp2);  
        this.add(jp3);  
        //this.add(jp6);  
        //this.add(jp4);  
          
        this.setLayout(new GridLayout(1, 1));  
        this.setTitle("��ʮ���Բ�ѯ");  
        this.setSize(500, 500);  
        this.setLocation(150, 150);  
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
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
	public void readGuaInfo() throws Exception {
		//����ĳ����Ԫ���ֵ,���ֵ��һ������
        table.setValueAt ("Ǭ", 0, 0);
        table.setValueAt ("��", 1, 0);
        table.setValueAt ("��", 2, 0);
        table.setValueAt ("��", 3, 0);
        table.setValueAt ("��", 4, 0);
        table.setValueAt ("��", 5, 0);
        table.setValueAt ("��", 6, 0);
        table.setValueAt ("��", 7, 0);
      //���䷽ʽ����  
        DefaultTableCellRenderer d = new DefaultTableCellRenderer();  
        
        BaguaAction action = new BaguaAction();
        List<LiuSiGuaModel> godList = null;
		godList = action.query();
		System.out.println("count :"+godList.size());
		LiuSiGuaModel gua = null;
		for(int i=0;i<godList.size();i++){
			gua = godList.get(i);
			System.out.println("name"+gua.getName());
		}
        
        //���ñ��Ԫ��Ķ��뷽ʽΪ���ж��뷽ʽ  
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
