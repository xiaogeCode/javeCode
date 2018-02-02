package Yj.ui;

import java.awt.*;  
import javax.swing.*;  

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import Yj.ui.ManageView;

public class LoginView extends JFrame implements ActionListener {  
    //�������  
    JPanel jp1,jp2,jp3;//���  
    JLabel jlb1,jlb2;//��ǩ  
    JButton jb1,jb2;//��ť  
    JTextField jtf;//�ı�  
    JPasswordField jpf;//����  
      
    //���캯��  
    public LoginView(){  
        //�������  
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        //������ǩ  
        jlb1=new JLabel("�û���");  
        jlb2=new JLabel("��    ��");  
        //������ť  
        jb1=new JButton("��¼");  
        jb2=new JButton("����");  
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        //�����ı���  
        jtf=new JTextField(10);  
        //���������  
        jpf=new JPasswordField(10);  
          
        //���ò��ֹ���  
        this.setLayout(new GridLayout(3, 1));//����ʽ����  
          
        //����������  
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
          
        //���ô���  
        this.setTitle("�û���¼");//�����ǩ  
        this.setSize(300, 150);//�����С  
        this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳��ر�JFrame  
        this.setVisible(true);//��ʾ����  
          
        //��������  
        this.setResizable(false);  
    }  
    
    public void actionPerformed(ActionEvent e) {  
        
        if(e.getActionCommand()=="��¼")  
        {  
              login();
        }else if(e.getActionCommand()=="����")  
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
