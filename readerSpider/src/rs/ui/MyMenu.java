package rs.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import rs.model.ChapterModel;

public class MyMenu extends JFrame {

	public Container container  = this.getContentPane();
    
    JTable jt=null;
	int rowMoused=-1;
	int columnMoused= -1;
	
	MyTextArea myTextArea = null;//���ڴ��ݲ���
	
	//List<ChapterModel> list= null;
	
	public  MyMenu() {
		setFrame();
	}
	public void setFrame(){

		container.setLayout(null);
		this.setTitle("目录");
		//container.setBackground(Color.orange);
		this.setSize(200, 350);
		this.setLocation(350, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		dispose();
        		myTextArea.menuViewDispose();
        		
			}
		});
	}
	public void setTableUI(List<ChapterModel> list){
		//�˴���JTable��ʾ��Ϊ���ݿ�ģ�ͣ�ͬʱ��JTableʵ��������¼ټ���
		if (list.size()<1) {
			list=new ArrayList<ChapterModel>();
			for (int i = 0; i < 2; i++) {
				ChapterModel model = new ChapterModel();
				model.setTitle("title"+i);
				model.setUrl("url"+i);
				list.add(model);
			}
		}
		Collections.reverse(list);// ��ArrayLista�е�Ԫ�ؽ��е���
		MyTableModel model = new MyTableModel(list);
		jt=new JTable(model);
		jt.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
        rowMoused= jt.getSelectedRow();
        columnMoused= jt.getSelectedColumn();     
              }
       });  
		 jt.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					int rowI  = jt.rowAtPoint(arg0.getPoint());// �õ�table���к�
					if (rowI > -1)
						myTextArea.tableDidSelect((Vector)((MyTableModel)jt.getModel()).getValueAt(rowI, 0));
				        //System.out.println("˫����� "+((MyTableModel)jt.getModel()).getValueAt(rowI, 0));
				     }

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			     
			  });
		//����Ĭ��ѡ�����һ�У�ˢ��textarea����
		 jt.setRowSelectionInterval( 0,  0);
		 myTextArea.tableDidSelect((Vector)((MyTableModel)jt.getModel()).getValueAt(0, 0));
		//Ϊ��ͷ����һ�� CellRenderer, ��� CellRenderer ��Ԥѡ�߶�Ϊ 0. ��JTable�ı�ͷҲ���Ǳ����и����ص�
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        jt.getTableHeader().setDefaultRenderer(renderer);
        
		JScrollPane jsp = new JScrollPane(jt);
		//jsp.add(jt);
		
		container.add(jsp);
		container.setLayout(new GridLayout(1, 1));  
		container.repaint();
		/*
		
        
        //Ϊ��ͷ����һ�� CellRenderer, ��� CellRenderer ��Ԥѡ�߶�Ϊ 0. ��JTable�ı�ͷҲ���Ǳ����и����ص�
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        table.getTableHeader().setDefaultRenderer(renderer);  
        
      //����ĳ����Ԫ���ֵ,���ֵ��һ������
        table.setValueAt ("��һ��", 0, 0);
        table.setValueAt ("�ڶ���", 1, 0);
        
		* 
		 */
	}
}
