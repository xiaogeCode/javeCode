package rs.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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

	JFrame frame = new JFrame();
	public Container container  = frame.getContentPane();
    
    JTable jt=null;
	int rowMoused=-1;
	int columnMoused= -1;
	
	MyTextArea myTextArea = null;//用于传递参数
	
	//List<ChapterModel> list= null;
	
	public  MyMenu() {
		setFrame();
	}
	public void setFrame(){

		container.setLayout(null);
		frame.setTitle("目录");
		//container.setBackground(Color.orange);
		frame.setSize(200, 350);
		frame.setLocation(350, 300);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		frame.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		frame.dispose();
			}
		});
	}
	public void setTableUI(List<ChapterModel> list){
		//此处的JTable显示的为数据库模型，同时对JTable实现了鼠标事假监听
		if (list.size()<1) {
			list=new ArrayList<ChapterModel>();
			for (int i = 0; i < 2; i++) {
				ChapterModel model = new ChapterModel();
				model.setTitle("title"+i);
				model.setUrl("url"+i);
				list.add(model);
			}
		}
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
					int rowI  = jt.rowAtPoint(arg0.getPoint());// 得到table的行号
					if (rowI > -1)
						myTextArea.tableDidSelect((Vector)((MyTableModel)jt.getModel()).getValueAt(rowI, 0));
				        //System.out.println("双击鼠标 "+((MyTableModel)jt.getModel()).getValueAt(rowI, 0));
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
		
		//为表头设置一个 CellRenderer, 这个 CellRenderer 的预选高度为 0. 把JTable的表头也就是标题行给隐藏掉
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        jt.getTableHeader().setDefaultRenderer(renderer);
        
		JScrollPane jsp = new JScrollPane(jt);
		//jsp.add(jt);
		
		container.add(jsp);
		container.setLayout(new GridLayout(1, 1));  
		container.repaint();
		/*
		
        
        //为表头设置一个 CellRenderer, 这个 CellRenderer 的预选高度为 0. 把JTable的表头也就是标题行给隐藏掉
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        table.getTableHeader().setDefaultRenderer(renderer);  
        
      //设置某个单元格的值,这个值是一个对象
        table.setValueAt ("第一章", 0, 0);
        table.setValueAt ("第二章", 1, 0);
        
		* 
		 */
	}
}
