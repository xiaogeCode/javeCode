package russiaCuteGame;
import java.awt.*;
import javax.swing.*;

public class BasicCute extends JButton{

	int currentX;			//λ��_x
	int currentY;			//λ��_y
	int lenth;				//�߳�
	
	public BasicCute(){
		super();
	}
	public BasicCute(int x,int y ,int len){
		super();
		this.currentX  = x;
		this.currentY  = y;
		this.lenth = len;
		
		this.setLocation(x*(len+1), y*(len+1));
		this.setSize(len, len);
		
		//ImageIcon image = new ImageIcon(getClass().getResource("/image/ѩ.jpg"));//new ImageIcon("E:\\pic\\ͼƬ\\ѩ.jpg");
		//ImageIcon image = new ImageIcon(this.getClass().getClassLoader().getResource("/image/ѩ.jpg"));
		//this.setIcon(image);
		//this.setBackground(new Color(255,245,170));
		this.setBackground(Color.blue);
		
	}
}
