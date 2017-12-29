package GreedySnake;
import java.awt.*;
import javax.swing.*;

public class Cute extends JButton{
	public int location_X,location_y,lenth;

	public Cute(int x,int y,int len){
		super();
		this.location_X = x;
		this.location_y = y;
		this.lenth = len;
		
		this.setLocation(location_X*(lenth+1), location_y*(lenth+1));
		this.setSize(lenth, lenth);
		this.setBackground(Color.blue);
		this.setFocusable(false);
	}
	public void setLocationX(int x){
		location_X=x;
		this.setLocation(location_X*(lenth+1), location_y*(lenth+1));
	}
	public void setLocationY(int y){
		location_y=y;
		this.setLocation(location_X*(lenth+1), location_y*(lenth+1));
	}
	public void reLocation(){
		this.setLocation(location_X*(lenth+1), location_y*(lenth+1));
	}
	
}
