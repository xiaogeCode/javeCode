package mario.model;

import java.awt.Image;

import javax.swing.ImageIcon;

import mario_main.GameFrame;

public class Mario extends Thread {

	public GameFrame gf;  
	public boolean jumpFlag=true;  
    
    //����µ�����  
    public int x=60,y=358;  
    //����µ��ٶ�  
    public int xspeed=5,yspeed=1;  
    //����µĿ��  
    public int width=30,height=32;  
    //����µ�ͼƬ  
    public Image img = new ImageIcon("src/Image/mario.png").getImage();  
      
    public boolean left=false,right=false,down=false,up=false;  
      
    public String Dir_Up="Up",Dir_Left="Left",Dir_Right="Right",Dir_Down="Down"; 
    
	public Mario (GameFrame gf) {  
        this.gf=gf;  
    } 
	public void run(){  
		while(true){  
			//System.out.println("mario run");
            //������  
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
	//�������ĺ���  
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
         this.yspeed=1;//��ԭ�ٶ�  
    }
  //�����ײ  
    public boolean hit(String dir){  
    	return false;
    }
    
}
