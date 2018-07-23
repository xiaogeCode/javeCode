package Yj.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class ManageView extends JFrame implements ActionListener{

	//
    JButton jb1,jb2=null;  
    JPanel jp1,jp2,jp3=null;  
    JLabel jlb1,jlb2,jlb3,jlb4=null;  
    
	public ManageView () {
		//
        jb1=new JButton("卦象信息");
        jb2=new JButton("测算");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
          
        jp2=new JPanel();
        jp3=new JPanel();  
          
        jp2.add(jb1);
        jp3.add(jb2);

        //this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
          
        //
        this.setLayout(new GridLayout(2,1,50,50));  
        this.setTitle("八卦");
        this.setSize(400,300);  
        //this.setLocation(200, 200);   
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        if(e.getActionCommand()=="卦象信息")
        {  
        	try {
				new LiuSiGuaView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand()=="测算")
        {  
               new CeSuanView();
        } 
	}
	

}
