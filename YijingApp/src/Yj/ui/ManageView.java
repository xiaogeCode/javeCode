package Yj.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class ManageView extends JFrame implements ActionListener{

	//�������  
    JButton jb1,jb2=null;  
    JPanel jp1,jp2,jp3=null;  
    JLabel jlb1,jlb2,jlb3,jlb4=null;  
    
	public ManageView () {
		//�������  
        jb1=new JButton("�����ѯ");  
        jb2=new JButton("��سռ��");  
        jb1.addActionListener(this);
        jb2.addActionListener(this);
          
        //jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
          
        //jlb1=new JLabel("����");  
        //jlb2=new JLabel("ѧ��");  
        //jlb3=new JLabel("���¹��棺");  
        //jlb4=new JLabel("��У������ʮ����У���֪ͨ");  
          
        //jp1.add(jlb1);  
        //jp1.add(jlb2);  
          
        jp2.add(jb1);  
        //jp2.add(jlb3);  
          
        jp3.add(jb2);  
        //jp3.add(jlb4);  
          
          
        //this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
          
        //���ò��ֹ�����  
        this.setLayout(new GridLayout(2,1,50,50));  
        this.setTitle("�׾�ʹ��ϵͳ");  
        this.setSize(400,300);  
        //this.setLocation(200, 200);   
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        if(e.getActionCommand()=="�����ѯ")  
        {  
        	try {
				new LiuSiGuaView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand()=="��سռ��")  
        {  
               new CeSuanView();
        } 
	}
	

}
