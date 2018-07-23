package Yj.ui;

import java.awt.*;  
import javax.swing.*;  

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import Yj.ui.ManageView;

public class LoginView extends JFrame implements ActionListener {  
    //
    JPanel jp1,jp2,jp3;//
    JLabel jlb1,jlb2;//
    JButton jb1,jb2;//
    JTextField jtf;//
    JPasswordField jpf;//
      
    //
    public LoginView(){  
        //
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        //
        jlb1=new JLabel("名字");
        jlb2=new JLabel("密码");
        //
        jb1=new JButton("登陆");
        jb2=new JButton("重置");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        //
        jtf=new JTextField(10);  
        //
        jpf=new JPasswordField(10);  
          
        //
        this.setLayout(new GridLayout(3, 1));//
          
        //
        jp1.add(jlb1);  
        jp1.add(jtf);  
          
        jp2.add(jlb2);  
        jp2.add(jpf);  
          
        jp3.add(jb1);  
        jp3.add(jb2);  
          
        //���뵽JFrame  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
          
        //
        this.setTitle("登陆");//
        this.setSize(300, 150);//
        this.setLocationRelativeTo(null);//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
        this.setVisible(true);//
          
        //
        this.setResizable(false);  
    }  
    @Override
    public void actionPerformed(ActionEvent e) {  
        
        if(e.getActionCommand()=="登陆")
        {  
              login();
        }else if(e.getActionCommand()=="重置")
        {  
               
        }      reset();       
          
    }  
    public void login() {
    	new ManageView();
    	dispose();
	}
    public void reset() {
    	jtf.setText("");
    	jpf.setText("");
		
	}
}  
