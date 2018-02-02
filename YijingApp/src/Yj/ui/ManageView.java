package Yj.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class ManageView extends JFrame implements ActionListener{

	//定义组件  
    JButton jb1,jb2=null;  
    JPanel jp1,jp2,jp3=null;  
    JLabel jlb1,jlb2,jlb3,jlb4=null;  
    
	public ManageView () {
		//创建组件  
        jb1=new JButton("卦象查询");  
        jb2=new JButton("六爻占卦");  
        jb1.addActionListener(this);
        jb2.addActionListener(this);
          
        //jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
          
        //jlb1=new JLabel("姓名");  
        //jlb2=new JLabel("学号");  
        //jlb3=new JLabel("最新公告：");  
        //jlb4=new JLabel("我校举行六十周年校庆的通知");  
          
        //jp1.add(jlb1);  
        //jp1.add(jlb2);  
          
        jp2.add(jb1);  
        //jp2.add(jlb3);  
          
        jp3.add(jb2);  
        //jp3.add(jlb4);  
          
          
        //this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
          
        //设置布局管理器  
        this.setLayout(new GridLayout(2,1,50,50));  
        this.setTitle("易经使用系统");  
        this.setSize(400,300);  
        //this.setLocation(200, 200);   
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        if(e.getActionCommand()=="卦象查询")  
        {  
        	try {
				new LiuSiGuaView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand()=="六爻占卦")  
        {  
               new CeSuanView();
        } 
	}
	

}
