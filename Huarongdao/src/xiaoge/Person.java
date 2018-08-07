package xiaoge;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

public class Person extends JButton implements FocusListener{

	Color c = new Color(255,245,170);
	String names[] = {"曹操","关羽","赵云","张飞","马超","黄忠","兵","兵","兵","兵"};
	int location_x;
	int location_y;
	int lenth = 80;
	int person_type;
	int person_width;
	int person_height;
	public Person(String s){
		super(s);
		
		for(int i=0 ;i<7;i++){
			if(names[i].equals(s)){
				person_type = i;
				continue;
			}
		}
		
		
		setBackground(c);
		
		int tmp_width = 100;
		int tmp_height = 100;
		switch(person_type){
		case 0:
		{
			person_width=2;
			person_height=2;
			tmp_width 	= lenth *2;
			tmp_height	= lenth *2;
		}
			break;
		case 1:
		{
			person_width=2;
			person_height=1;
			tmp_width 	= lenth *2;
			tmp_height	= lenth *1;
		}
		break;
		case 2:
		case 3:
		case 4:
		case 5:
		{
			person_width=1;
			person_height=2;
			tmp_width 	= lenth *1;
			tmp_height	= lenth *2;
			
		}
			break;
		case 6:
		{
			person_width=1;
			person_height=1;
			tmp_width 	= lenth *1;
			tmp_height	= lenth *1;
			
		}
			break;
			default:
				break;
		}
		
		
		setBounds(0,0,tmp_width,tmp_height);
		addFocusListener(this);
		
	}
	public void setPersonLocation(int x,int y ){
		location_x= x;
		location_y= y;
		int tmp_x = x*lenth;
		int tmp_y = y*lenth;
		setLocation(tmp_x,tmp_y);
	}
	public void focusGained(FocusEvent e)
    {
     setBackground(Color.red);
    }
    public void focusLost(FocusEvent e)
    {
     setBackground(c);
    }
}
