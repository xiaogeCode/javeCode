package Yj.ui;

import java.awt.Graphics; 
import java.awt.Image; 
import javax.swing.JPanel; 
public class YaoImagePanel extends JPanel {

	 
	/***
	* 背景图片
	*/
	private Image image= null; 
	
	 
	/* * 
	是否自适应 
	 */
	private boolean	autoResize	 = 	false; 	  
	/** * 构造方法 
	 */  
	 
	public	 YaoImagePanel() { 
		super(null,false);
	}
	public YaoImagePanel (boolean autoResize) {
		super(null, autoResize);
	}
	public YaoImagePanel (Image image) {
		super();
		this.image = image;
		repaint();
	}
	public  YaoImagePanel(Image image,boolean autoResize) {
		super();
		this.autoResize = autoResize;
		this.image= image;
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			if (autoResize) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			}else {
				g.drawImage(image, 0, 0, null);
			}
		}
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image img) {
		this.image = img;
		repaint();
	}
	public boolean getAutoResize() {
		return autoResize;
	}
	public void setAutoResize(boolean auto) {
		this.autoResize = auto;
		repaint();
	}
}
