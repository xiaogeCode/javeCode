package Yj.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LiuSiGuaImagePanel extends JPanel{
	private int[] yao={0,0,0,0,0,0};
	private YaoImagePanel[] yaoPanel = new YaoImagePanel[6];
	private String[] guaTypes = {"±æÿ‘","¥Ìÿ‘","◊Ÿÿ‘","ª•ÿ‘","±‰ÿ‘"};
	private int guaType=0;
	private String guaName="";
	private JLabel nameLabel=new JLabel();
	public  LiuSiGuaImagePanel() {
		//super();
		super(null,false);
		for (int k = 0; k < 6; k++) {
			YaoImagePanel tmpImagePanel = new YaoImagePanel();
			yaoPanel[k] = tmpImagePanel;
		}
		//this.setBackground(Color.green);
		repaint();
	}
	public  LiuSiGuaImagePanel(int y1,int y2,int y3,int y4,int y5,int y6) {
		super();
		yao[0]=y1;
		yao[1]=y2;
		yao[2]=y3;
		yao[3]=y4;
		yao[4]=y5;
		yao[5]=y6;
		repaint();
		
	}
	public void setYao(int y1,int y2,int y3,int y4,int y5,int y6) {
		yao[0]=y1;
		yao[1]=y2;
		yao[2]=y3;
		yao[3]=y4;
		yao[4]=y5;
		yao[5]=y6;
		repaint();
	}
	public int[] getYao() {
		return yao;
	}
	public void setGuaName(String name) {
		this.guaName = name;
		repaint();
	}
	public String getGuaName() {
		return guaName;
	}
	public void setGuaType(int type) {
		this.guaType = type;
		repaint();
		
	}
	public int getGuaType() {
		return guaType;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.removeAll();
		
		int hgt = this.getHeight();
		nameLabel.setText(guaTypes[guaType]+":"+guaName);
		nameLabel.setLocation(0,hgt-20);
		nameLabel.setSize(100, 20);
		this.add(nameLabel);
		
		for (int i = 0; i < 6; i++) {
			if (yao[i] == 0) {//—Ù
				try {
					yaoPanel[i].setImage(ImageIO.read(new File("src/image/0.jpg")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {//“ı
				try {
					yaoPanel[i].setImage(ImageIO.read(new File("src/image/1.jpg")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			yaoPanel[i].setLocation(0,hgt-40-10*i);
			yaoPanel[i].setSize(44, 4);
			yaoPanel[i].setLayout(null);
			yaoPanel[i].setAutoResize(true);
			yaoPanel[i].setAutoResize(true);
			this.add(yaoPanel[i]);
			
		}
		
	}
	
}
