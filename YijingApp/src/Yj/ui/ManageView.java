package Yj.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageView extends JFrame implements ActionListener{

	//
    JButton jb1,jb2;

	public ManageView () {
	    setFrame();
	    setView();

	}
    private void setFrame(){
        this.setTitle("八卦");
        this.setSize(400, 300);
        this.setLocation(400, 50);
        this.setResizable(false);
        this.setLayout(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });
    }
    private void setView(){
        //
        jb1=new JButton("卦象信息");
        jb1.addActionListener(this);
        jb1.setSize(100, 50);
        jb1.setLocation(20, 20);


        jb2=new JButton("测算");
        jb2.addActionListener(this);
        jb2.setSize(100, 50);
        jb2.setLocation(20, 120);


        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);

        this.getContentPane().repaint();

    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        String infoStr = "卦象信息";
        String calStr = "测算";
        if(e.getActionCommand().equals(infoStr))
        {  
        	try {
				new LiuSiGuaView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand().equals(calStr))
        {  
               new CeSuanView();
        } 
	}
	

}
