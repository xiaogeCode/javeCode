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

	int map_width,map_height,cute_len=30; 								//地图大小，方块的边长
	ArrayList<Cute> current_cuteArray=new ArrayList<Cute>();	  	//当前的方块组
	Cute randomCute = new Cute(1,1,cute_len);						//随机方块
	int map_data[][];												//当前地图信息
	
	Timer timer=new Timer(); 										//定时移动
	int time_interver=1000;											//方块移动间隔
	
	int direction=2;												//移动方向0上，1下，2左，3右
	
	public GameFrame(int width,int heigh){
		this.map_width = width;
		this.map_height = heigh;
		setFrame();
		reSetView();
		
	}
	//重置地图
	public void reSetView(){
		//setFrame();
		
		//初始化地图数据
		this.map_data = new int[this.map_width][this.map_height];
		//初始第一个方块
		map_data[this.map_width/2][this.map_height/2]=1;
		
		//清空当前数组
		if(!this.current_cuteArray.isEmpty()){
			current_cuteArray.clear();
		}
		//将第一个方块加入数组中
		current_cuteArray.add(new Cute(map_width/2,map_height/2,this.cute_len));
		
		//随机生成方块组                                
		createCute();
		
		setView();
		
		//初始化定时器
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
				System.exit(1);// 系统退出  
			}  
		    });
				
	}
	//设置视图
	public void setView(){
		//清空视图
		container.removeAll();
		//蛇体
		for(int i = current_cuteArray.size()-1;i>-1;i--){
			Cute cute = this.current_cuteArray.get(i);
			container.add(cute);
		}
		//随机方块
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
	//随机生成方块
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
	//根据方向整体移动
	public void move(int dir){
		//0上   1下   2左   3 右
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
		
		//移动数组
		if(this.map_data[nextX][nextY]==1){//碰到方块
			//碰到随机方块
			if((nextX == randomCute.location_X )&&(nextY == randomCute.location_y)){
				//头部加一个
				current_cuteArray.add(0,newCute);
				//刷新地图		
				this.map_data[newCute.location_X][newCute.location_y] =1;
				//重新生成随机方块
				createCute();
				
			}
			//自身碰撞
			else{
			}
					
		}else{//没和方块相撞
			//头部加一个，尾部减一个
			current_cuteArray.add(0,newCute);
			current_cuteArray.remove(current_cuteArray.size()-1);
			
			//刷新地图		
			this.map_data[newCute.location_X][newCute.location_y] =1;
			this.map_data[lastCute.location_X][lastCute.location_y] =0;
		}
		
	}

	// 设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
	// schedule(TimerTask task, long delay, long period)
	 public  void timer() {
		 TimerTask task = new TimerTask() {  
			 @Override  
			 public void run() { 
				 // task to run goes here
				 move(direction);
				 //刷新界面
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

	        //text.append("键盘" + KeyEvent.getKeyText(e.getKeyCode()) + "键向下\n"); 

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
		case "空格":
			break;
		case "Enter":
			break;
		default:
		break;
		}
		 //move(direction);
		//刷新界面
		 setView();
	}  

	 public void keyReleased(KeyEvent e) {  

	    //text.append("键盘" + KeyEvent.getKeyText(e.getKeyCode()) + "键松开\n");  

	 }  

	 

	 public void keyTyped(KeyEvent e) {  

	     //text.append("输入的内容是" + e.getKeyChar() + "\n");  

	 }  	 	
	 
}
 