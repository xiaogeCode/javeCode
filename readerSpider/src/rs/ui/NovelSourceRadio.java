package rs.ui;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//小说来源网站列表
public class NovelSourceRadio extends JFrame implements ItemListener{

	JRadioButton jr1 = null;
	JRadioButton jr2 =null;
	JRadioButton jr3 = null;
	JRadioButton jr4 =null;
	MyTextArea txarea = null;
	
	public  NovelSourceRadio() {
		this.setTitle("单选按钮");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setBounds(100, 100, 250, 100);  
        JPanel jp=new JPanel();  
        //jp.setBorder(new EmptyBorder(5,5,5,5));  
        this.setContentPane(jp);  
        jp.setLayout(new GridLayout(1,3));
        //jp.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));  
        
        jr1 = new JRadioButton("八零txt");
		jr2 = new JRadioButton("笔趣阁");
		jr3 = new JRadioButton("妙趣阁");
		jp.add(jr1);
		jp.add(jr2);
		jp.add(jr3);
		
		ButtonGroup btnGpButtonGroup = new ButtonGroup();
		btnGpButtonGroup.add(jr1);
		btnGpButtonGroup.add(jr2);
		btnGpButtonGroup.add(jr3);
			
		jr1.addItemListener(this);
		jr2.addItemListener(this);
		jr3.addItemListener(this);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		dispose();
			}
		});
        this.setVisible(true);  
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		int index = 0;
		System.out.println("select");
		if (arg0.getSource() == jr1) {
			index =0;
			System.out.println("80s");
		}else if (arg0.getSource() == jr2) {
			index =1;
			System.out.println("biqu");
		}
		else if (arg0.getSource() == jr3) {
			index =2;
			System.out.println("miaoqu");
		}
		txarea.itemChanged(index);
		dispose();
	}
	 
}
