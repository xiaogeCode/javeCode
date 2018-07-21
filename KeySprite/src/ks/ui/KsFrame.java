package ks.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KsFrame extends JFrame{
	public KsFrame () {
		initFrame();
	}

	public void initFrame() {
		this.getLayeredPane().setLayout(null);
		this.setTitle("pk");
		this.setSize(800 , 500);
		this.setLocation(400, 50);
		this.setResizable(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {  
		public void windowClosing(WindowEvent arg0) {  
			System.exit(1);//  
		}  
	    }); 
	}
}
