package tiaoyue.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import tiaoyue.model.*;;

public class GameFrame extends JFrame implements KeyListener{

	int map_width = 16;
	int map_height =25;
	int cute_width = 20;
	int[][] map;
	List<Enery>eneryList = new ArrayList<Enery>();
	
	BackgroundImage bg = null;
	Jumper jump = null;
	public  GameFrame() {
		bg= new BackgroundImage();
		jump= new Jumper();
		initMap();
		repaint();
		new Thread(){
			public void run(){
				while(true){
					jump();
					repaint();
					try {  
                        Thread.sleep(10);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
				}
			}

			
		}.start();
	}
	protected void jump() {
		// TODO Auto-generated method stub
		int jumpHeight =0;
		for (int i = 0; i < 90; i++) {//上跳
			jump.y -=1;
			jumpHeight++;
			moveBg();
			moveEnery(1);
			repaint();
			//System.out.println("Y:"+jump.y);
			//if (hit(2)) {
			//	break;
			//}
			try {  
                Thread.sleep(5);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
		}
		
		for (int i = 0; i < jumpHeight+map_height*cute_width; i++) {//下跳
			jump.y++;
			if (hit(3)) {
				System.out.println("pengzhuang");
				break;
			}
			repaint();
			try {  
                Thread.sleep(5);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
			
		}
		
	}
	protected void moveEnery(int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j < eneryList.size(); j++) {
			Enery tmp= eneryList.get(j);
			tmp.y+=i;
		}
		
	}
	private void moveBg() {
		// TODO Auto-generated method stub
		bg.oy-=10;
		if (bg.oy<=0) {
			bg.oy =bg.img.getHeight(null);
		}
	}
	protected boolean hit(int i) {
		// TODO Auto-generated method stub
		switch (i) {
		case 0:
		case 1:
		case 2:
			break;
		case 3:{
			Rectangle jumperRect= new Rectangle(jump.x,jump.y,jump.width,jump.height);
			for (int j = 0; j < eneryList.size(); j++) {
				Enery ee= eneryList.get(j);
				if (ee.height<jump.height) {//
					Rectangle eeRectangle=new Rectangle(ee.x,ee.y,ee.width,ee.height);
					if (jumperRect.intersects(eeRectangle)) {
						return true;
					}
				}
				
			}	
		}

		default:
			break;
		}
		
		return false;
	}
	protected void initMap() {
		// TODO Auto-generated method stub

		map = new int[map_width][map_height];
		jump = null;
		jump = new Jumper();
		if (eneryList.size()>0) {
			eneryList.clear();
		}
		for(int i=map_height-1;i>-1;i--){
			//每一行随机设立一块挡板
			Random random = new Random();
			int k =random.nextInt(map_width-3)+2;
			for(int j=0;j<this.map_width;){
				if(j==k){
					map[j][i]=1;
					Bricks brick=new Bricks(j*cute_width, i*cute_width, cute_width*2, cute_width, null);
					eneryList.add(brick);
					
					if(i==map_height-1){//设置人物位置
						jump.x = j*cute_width;
						jump.y = i*cute_width-cute_width;
					}
					j=j+1;
				}else{
					map[j][i]=0;
				}
				j++;
			}
		}
	}
	public void initFrame(){  
        //设置窗体相关属性  
        this.setSize(320,500);  
        this.setTitle("山寨涂鸦跳跃");  
        this.setResizable(false);  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(3);  
        this.setVisible(true);  
          
        //该窗体添加键盘监听
        this.addKeyListener(this);
		//this.addMouseListener(this);
		addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        } );
        
    }  
	public void paint(Graphics g) {  
        //利用双缓冲画背景图片和马里奥  
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics();  
        //big.drawImage(bg.img, bg.x, bg.y, null); 
        int frameWidth= this.getSize().width;
        int frameHeight= this.getSize().height;

        big.drawImage(bg.img, 0, 0, frameWidth, frameHeight, bg.ox, bg.oy,bg.ox+frameWidth,bg.oy+frameHeight, null);
          
        
      //画地图
        for(int i=0;i<eneryList.size();i++){
        	Enery enery= eneryList.get(i);
        	Color c =big.getColor();
        	big.setColor(Color.black);
			big.fillRect(enery.x, enery.y, enery.width, enery.height);
			big.setColor(c);	
  		}	  
       
          
        //画人物  
        big.drawImage(jump.img, jump.x, jump.y, jump.width, jump.height,null);  
        g.drawImage(bi,0,0,null);  
          
    }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		switch(KeyEvent.getKeyText(e.getKeyCode())){
		case "A":{
			{
				 new Thread(){  
                     public void run(){  
                    	System.out.println("a press");
             			moveJumper(0);                     }  
                 }.start();  
			}
			
			break;
		}
	
		case "D":
		{
			new Thread(){  
                public void run(){  
               	System.out.println("a press");
        			moveJumper(1);                     }  
            }.start(); 

		}
			break;
		case "S":{
			//move(2);
		}
	
		break;
		case "空格":
			break;
		case "Enter":
		{
			initMap();
		break;
		}
		case "W":
		{
		}
		break;
		default:
			break;
		}
		
	}
	protected void moveJumper(int i) {
		// TODO Auto-generated method stub
		switch (i){
		case 0:{
			if(jump.x-10>-1){
				jump.x -=10;
			}
			break;
		}
		case 1:{
			System.out.println("case qcase1");
			if(jump.x+10<map_width*cute_width){
				jump.x+=10;
				System.out.println("movoe right");
			}
			break;
		}
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}  
}
