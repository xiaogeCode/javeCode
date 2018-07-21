package pk.ui;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import pk.model.PukeModel;
public class CardPanel extends JPanel {

	public interface SelectCallBack{
		void onResponse(int tag,boolean state);
	}
	
	
	Image image = null;
	private boolean selected = false;	//当前卡牌是否选中
	public PukeModel pukeModel;
	public int tag;						//当前位置
	SelectCallBack callBack;			//当前卡牌选中回调
	
	public  CardPanel() {
		super();
		selected = false;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				boolean currSelected = selected;
				setSelected(!currSelected);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public  CardPanel(Image im) {
		super();
		selected = false;
		this.image = im;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				boolean currSelected = selected;
				setSelected(!currSelected);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	public void setImage(Image im) {
		this.image = im;
		repaint();
	}
	public void setSelected(boolean sltd) {
		if (selected) {
			if (sltd == false) {//从选中状态到未选中
				//this.setLocation(this.location().x, this.location().y+50);
				callBack.onResponse(tag, false);
			}
		}else {
			if (sltd == true) {//从未选中状态到选中
				//this.setLocation(this.location().x, this.location().y-50);
				callBack.onResponse(tag, true);
			}
		}
		selected = sltd;
		//repaint();
		
	}
	public boolean getSelected() {
		return this.selected;
	}
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		/*int rotate = 1;
		
		Graphics2D g2d = (Graphics2D) g; 
		if (rotate == 1) {
			g2d.rotate(-Math.PI/2, this.getWidth()/2, this.getHeight()/2);
			int wide = 150;//this.size().width;
			int hei = 105;//this.size().height;
			g2d.drawImage(image, 0, 0,hei, wide, this);
			this.setSize(hei, wide);
		}else {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		
		g2d.dispose();*/
    }

}
