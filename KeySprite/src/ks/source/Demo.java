package ks.source;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Demo {

	public void funcDemo() {
		try{
			System.out.println("helo");
			Robot robot= new Robot();
			robot.setAutoDelay(1000);
			
			//获取屏幕分辨率
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            System.out.println(d);
            //以屏幕的尺寸创建个矩形
            Rectangle screenRect = new Rectangle(d);
            //截图（截取整个屏幕图片）
            BufferedImage bufferedImage =  robot.createScreenCapture(screenRect);
            //保存截图
            File file = new File("screenRect.png");
            ImageIO.write(bufferedImage, "png", file);
			
			robot.mouseMove(1200, 400);
			
			 //点击鼠标
            //鼠标左键
			System.out.println("单击");
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            
          //鼠标右键
            System.out.println("右击");
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            
            //按下ESC，退出右键状态
            System.out.println("按下ESC");
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            //滚动鼠标滚轴
            System.out.println("滚轴");
            robot.mouseWheel(5);
			
            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=2;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);
            
            robot.keyPress(KeyEvent.VK_G);
            
			//获得暂停时间
		    //int time = 1000;
		    //暂停
		    //Thread.sleep(time);
		    
			//robot.mousePress(0);

		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
}
