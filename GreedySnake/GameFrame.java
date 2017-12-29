package GreedySnake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.util.Random;
import java.util.ArrayList;

import java.util.TimerTask;
import java.util.Timer;

public class GameFrame implements KeyListener {

	JFrame frame = new JFrame();
	public Container container  = frame.getContentPane();

	int map_width,map_height,cute_len=30; 								//��ͼ��С������ı߳�
	ArrayList<Cute> current_cuteArray=new ArrayList<Cute>();	  	//��ǰ�ķ�����
	Cute randomCute = new Cute(1,1,cute_len);						//�������
	int map_data[][];												//��ǰ��ͼ��Ϣ
	
	Timer timer=new Timer(); 										//��ʱ�ƶ�
	int time_interver=1000;											//�����ƶ����
	
	int direction=2;												//�ƶ�����0�ϣ�1�£�2��3��
	
	public GameFrame(int width,int heigh){
		this.map_width = width;
		this.map_height = heigh;
		setFrame();
		reSetView();
		
	}
	//���õ�ͼ
	public void reSetView(){
		//setFrame();
		
		//��ʼ����ͼ����
		this.map_data = new int[this.map_width][this.map_height];
		//��ʼ��һ������
		map_data[this.map_width/2][this.map_height/2]=1;
		
		//��յ�ǰ����
		if(!this.current_cuteArray.isEmpty()){
			current_cuteArray.clear();
		}
		//����һ���������������
		current_cuteArray.add(new Cute(map_width/2,map_height/2,this.cute_len));
		
		//������ɷ�����                                
		createCute();
		
		setView();
		
		//��ʼ����ʱ��
      	timer();
      	
		
	}
	public void setFrame(){

		System.out.println("setFrame");
		container.setLayout(null);
		container.setBackground(Color.yellow);
		frame.setSize((cute_len+1)*(this.map_width+1) , (cute_len+1)*(this.map_height+1));
		frame.setLocation(400, 50);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		frame.addKeyListener(this);

		frame.addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent arg0) {  
				System.exit(1);// ϵͳ�˳�  
			}  
		    });
				
	}
	//������ͼ
	public void setView(){
		//�����ͼ
		container.removeAll();
		//����
		for(int i = current_cuteArray.size()-1;i>-1;i--){
			Cute cute = this.current_cuteArray.get(i);
			container.add(cute);
		}
		//�������
		container.add(randomCute);
		container.repaint();
		/*
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				if(map_data[i][j]==1){
					Cute cute=new Cute(i,j,this.cute_len );
						container.add(cute);
				}	
			}
		}
		*/
	}
	//������ɷ���
	public void createCute(){
		Random random = new Random();
		int x =random.nextInt(this.map_width);
		int y =random.nextInt(this.map_height);
		if(this.map_data[x][y]!=1){
			this.map_data[x][y]=1;
			randomCute.location_X = x;
			randomCute.location_y = y;
			randomCute.reLocation();
		}else{
			createCute();
		}
		
		
	}
	//���ݷ��������ƶ�
	public void move(int dir){
		//0��   1��   2��   3 ��
		Cute firstCute = this.current_cuteArray.get(0);
		Cute newCute = new Cute(1,1,this.cute_len);
		int nextX=1;
		int nextY=1;
		switch(dir){
		case 0:
			//newCute.setLocationX(firstCute.location_X);
			nextX =firstCute.location_X;
			nextY =firstCute.location_y-1;
			break;
		case 1:
			nextX =firstCute.location_X;
			nextY =firstCute.location_y+1;
			break;
		case 2:
			nextX =firstCute.location_X-1;
			nextY =firstCute.location_y;
			break;
		case 3:
			nextX =firstCute.location_X+1;
			nextY =firstCute.location_y;
			break;
		default:
			break;
		
		}
		newCute.location_X = nextX;
		newCute.location_y = nextY;
		
		newCute.reLocation();
		Cute lastCute = current_cuteArray.get(current_cuteArray.size()-1 );
		
		//�ƶ�����
		if(this.map_data[nextX][nextY]==1){//��������
			//�����������
			if((nextX == randomCute.location_X )&&(nextY == randomCute.location_y)){
				//ͷ����һ��
				current_cuteArray.add(0,newCute);
				//ˢ�µ�ͼ		
				this.map_data[newCute.location_X][newCute.location_y] =1;
				//���������������
				createCute();
				
			}
			//������ײ
			else{
			}
					
		}else{//û�ͷ�����ײ
			//ͷ����һ����β����һ��
			current_cuteArray.add(0,newCute);
			current_cuteArray.remove(current_cuteArray.size()-1);
			
			//ˢ�µ�ͼ		
			this.map_data[newCute.location_X][newCute.location_y] =1;
			this.map_data[lastCute.location_X][lastCute.location_y] =0;
		}
		
	}

	// �趨ָ������task��ָ���ӳ�delay����й̶��ӳ�peroid��ִ��
	// schedule(TimerTask task, long delay, long period)
	 public  void timer() {
		 TimerTask task = new TimerTask() {  
			 @Override  
			 public void run() { 
				 // task to run goes here
				 move(direction);
				 //ˢ�½���
				 setView();
				 }  
			 };
			 timer = new Timer();
			 long delay = 0;  
			 long intevalPeriod = time_interver;  
			 // schedules the task to be run in an interval 
			 timer.scheduleAtFixedRate(task, delay, intevalPeriod);  

			 

	 }	
	 public void keyPressed(KeyEvent e) {  

	        //text.append("����" + KeyEvent.getKeyText(e.getKeyCode()) + "������\n"); 

		 System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		 switch(KeyEvent.getKeyText(e.getKeyCode())){
		 case "W":
			 this.direction = 0;
			 //this.move(0);
				break;
		 case "S":
			 this.direction = 1;
			 //this.move(1);
			 break;
		 case "A":
			 this.direction = 2;
			 //this.move(2);
			 break;
		case "D":
			this.direction = 3;
			 //this.move(3);
			 break;
		case "�ո�":
			break;
		case "Enter":
			break;
		default:
		break;
		}
		 //move(direction);
		//ˢ�½���
		 setView();
	}  

	 public void keyReleased(KeyEvent e) {  

	    //text.append("����" + KeyEvent.getKeyText(e.getKeyCode()) + "���ɿ�\n");  

	 }  

	 

	 public void keyTyped(KeyEvent e) {  

	     //text.append("�����������" + e.getKeyChar() + "\n");  

	 }  	 	
	 
}
 