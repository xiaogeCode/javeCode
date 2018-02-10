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
	
	//����ͼƬ  
    public BackgroundImage bg= new BackgroundImage() ;  
    
  //����װ����  
    public ArrayList<Enery> eneryList = new ArrayList<Enery>();  
      
    //�ӵ�����  
    public ArrayList<Boom> boomList = new ArrayList<Boom>();  
      
    //�ӵ����ٶ�  
    public int bspeed=0;
    
    
    public  GameFrame() {
    	mario = new Mario(this);
    	mario.start();  
    	
		//�����ػ��߳�  
        new Thread(){  
            public void run(){  
                while(true){  
                	moveBg(0);
                    //�ػ洰��  
                    repaint();  
                    //����ӵ��Ƿ����  
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
        //���ô����������  
        this.setSize(frameWidth,frameHeight);  
        this.setTitle("��������");  
        this.setResizable(false);  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(3);  
        this.setVisible(true);  
          
        //�ô�����Ӽ��̼���  
        KeyListener kl = new KeyListener(this);  
        this.addKeyListener(kl);  
    }  
    public void paint(Graphics g) {  
        //����˫���廭����ͼƬ�������  
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics();  
        //big.drawImage(bg.img, bg.x, bg.y, null);  
        big.drawImage(bg.img, 0, 0, frameWidth, frameHeight, bg.ox, 0,bg.ox+frameWidth,frameHeight, null);
        //ĩβ����ͷ��ͼ��ʵ��ѭ������
        if ((bg.ox +frameWidth>bg.img.getWidth(null))&&(bg.ox<bg.img.getWidth(null))) {
        	int leakWidth = bg.ox+frameWidth-bg.img.getWidth(null);
            big.drawImage(bg.img, frameWidth-leakWidth, 0, frameWidth, frameHeight, 0, 0,leakWidth,frameHeight, null);
		}
        
        for (int i = 0; i <eneryList.size(); i++) {  
            Enery e =eneryList.get(i);  
            big.drawImage(e.img, e.x, e.y, e.width, e.height,null);  
        }  
        
        //���ӵ�  
        for (int i = 0; i < boomList.size(); i++) {  
            Boom b =boomList.get(i);  
            Color c =big.getColor();  
            big.setColor(Color.red);  
            big.fillOval(b.x+=b.speed, b.y, b.width, b.width);  
            big.setColor(c);  
        }  
        
        //big.setColor(Color.red);
        //big.fillOval(90,50,80,80);  
      //������  
        big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height,null);  
        
        g.drawImage(bi,0,0,null);    
    }  
    public void moveBg(int i) {
		switch (i) {
		case 0://��
		{
			bg.ox+=5;
			if (bg.ox >= bg.img.getWidth(null)) {
				bg.ox = 0;
			}
		}
			break;
		case 1://��
		{}
			break;

		default:
			break;
		}
	}
}
