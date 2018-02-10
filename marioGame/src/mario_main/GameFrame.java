package mario_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mario.model.*;
public class GameFrame extends JFrame{

	final public int frameWidth = 800;
	final public int frameHeight = 450;
	public Mario mario;  
	
	//背景图片  
    public BackgroundImage bg= new BackgroundImage() ;  
    
  //容器装敌人  
    public ArrayList<Enery> eneryList = new ArrayList<Enery>();  
      
    //子弹容器  
    public ArrayList<Boom> boomList = new ArrayList<Boom>();  
      
    //子弹的速度  
    public int bspeed=0;
    
    
    public  GameFrame() {
    	mario = new Mario(this);
    	mario.start();  
    	
		//窗体重绘线程  
        new Thread(){  
            public void run(){  
                while(true){  
                	moveBg(0);
                    //重绘窗体  
                    repaint();  
                    //检查子弹是否出界  
                    //checkBoom();  
                    try {  
                        Thread.sleep(10);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }.start(); 
	}
    public void initFrame(){  
        //设置窗体相关属性  
        this.setSize(frameWidth,frameHeight);  
        this.setTitle("超级玛丽");  
        this.setResizable(false);  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(3);  
        this.setVisible(true);  
          
        //该窗体添加键盘监听  
        KeyListener kl = new KeyListener(this);  
        this.addKeyListener(kl);  
    }  
    public void paint(Graphics g) {  
        //利用双缓冲画背景图片和马里奥  
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics();  
        //big.drawImage(bg.img, bg.x, bg.y, null);  
        big.drawImage(bg.img, 0, 0, frameWidth, frameHeight, bg.ox, 0,bg.ox+frameWidth,frameHeight, null);
        //末尾补上头部图像，实现循环播放
        if ((bg.ox +frameWidth>bg.img.getWidth(null))&&(bg.ox<bg.img.getWidth(null))) {
        	int leakWidth = bg.ox+frameWidth-bg.img.getWidth(null);
            big.drawImage(bg.img, frameWidth-leakWidth, 0, frameWidth, frameHeight, 0, 0,leakWidth,frameHeight, null);
		}
        
        for (int i = 0; i <eneryList.size(); i++) {  
            Enery e =eneryList.get(i);  
            big.drawImage(e.img, e.x, e.y, e.width, e.height,null);  
        }  
        
        //画子弹  
        for (int i = 0; i < boomList.size(); i++) {  
            Boom b =boomList.get(i);  
            Color c =big.getColor();  
            big.setColor(Color.red);  
            big.fillOval(b.x+=b.speed, b.y, b.width, b.width);  
            big.setColor(c);  
        }  
        
        //big.setColor(Color.red);
        //big.fillOval(90,50,80,80);  
      //画人物  
        big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height,null);  
        
        g.drawImage(bi,0,0,null);    
    }  
    public void moveBg(int i) {
		switch (i) {
		case 0://左
		{
			bg.ox+=5;
			if (bg.ox >= bg.img.getWidth(null)) {
				bg.ox = 0;
			}
		}
			break;
		case 1://右
		{}
			break;

		default:
			break;
		}
	}
}
