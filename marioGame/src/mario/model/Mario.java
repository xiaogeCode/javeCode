package mario.model;

import java.awt.Image;

import javax.swing.ImageIcon;

import mario_main.GameFrame;

public class Mario extends Thread {

	public GameFrame gf;  
	public boolean jumpFlag=true;  
    
    //马里奥的坐标  
    public int x=60,y=358;  
    //马里奥的速度  
    public int xspeed=5,yspeed=1;  
    //马里奥的宽高  
    public int width=30,height=32;  
    //马里奥的图片  
    public Image img = new ImageIcon("src/Image/mario.png").getImage();  
      
    public boolean left=false,right=false,down=false,up=false;  
      
    public String Dir_Up="Up",Dir_Left="Left",Dir_Right="Right",Dir_Down="Down"; 
    
	public Mario (GameFrame gf) {  
        this.gf=gf;  
    } 
	public void run(){  
		while(true){  
			//System.out.println("mario run");
            //向左走  
            if(left){  
            }
            if (right) {
				
			}
            //System.out.println("up"+up);
            if(!!up){  
            	if(jumpFlag){  
                    jumpFlag=false;  
                    new Thread(){  
                        public void run(){  
                            jump();  
                            jumpFlag=true;  
                        }  
                    }.start();  
                }  
            }
            
        }
		
	}
	//向上跳的函数  
    public void jump(){
    	System.out.println("jump");
    	 int jumpHeigh=0;  
         for (int i = 0; i < 150; i++) {  
             gf.mario.y-=this.yspeed;  
             jumpHeigh++;  
             if(hit(Dir_Up)){  
                 break;  
             }  
             try {  
                 Thread.sleep(5);  
             } catch (InterruptedException e) {  
                 e.printStackTrace();  
             }  
         }  
         for (int i = 0; i <jumpHeigh; i++) {  
             gf.mario.y+=this.yspeed;  
             if(hit(Dir_Down)){  
                 this.yspeed=0;  
             }  
             try {  
                 Thread.sleep(5);  
             } catch (InterruptedException e) {  
                 e.printStackTrace();  
             }  
               
               
         }  
         this.yspeed=1;//还原速度  
    }
  //检测碰撞  
    public boolean hit(String dir){  
    	return false;
    }
    
}
