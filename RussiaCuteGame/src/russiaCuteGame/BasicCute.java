package russiaCuteGame;
import java.awt.*;
import javax.swing.*;

public class BasicCute extends JButton{

	int currentX;			//Œª÷√_x
	int currentY;			//Œª÷√_y
	int lenth;				//±ﬂ≥§
	
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
		
		//ImageIcon image = new ImageIcon(getClass().getResource("/image/—©.jpg"));//new ImageIcon("E:\\pic\\Õº∆¨\\—©.jpg");
		//ImageIcon image = new ImageIcon(this.getClass().getClassLoader().getResource("/image/—©.jpg"));
		//this.setIcon(image);
		//this.setBackground(new Color(255,245,170));
		this.setBackground(Color.blue);
		
	}
}
