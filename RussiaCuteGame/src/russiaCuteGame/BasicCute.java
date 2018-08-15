package russiaCuteGame;
import java.awt.*;
import javax.swing.*;

public class BasicCute extends JButton{

	int currentX;			//
	int currentY;			//
	int lenth;				//
	
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

		this.setBackground(Color.blue);
		
	}
}
