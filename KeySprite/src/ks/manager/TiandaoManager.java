package ks.manager;

import ks.source.AsyUtil;
import ks.source.CommonUtil;
import ks.source.KeyMap;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * Created By Xiaoge
 * Date: 2018/7/19
 * Time: 23:16
 */
public class TiandaoManager {

    public  static TiandaoManager tiandaoManager;
    static {
        tiandaoManager = new TiandaoManager();
    }


    public void fire() {
        try{
            System.out.println("fire");
            Robot robot= new Robot();
            robot.setAutoDelay(1000);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);




            for (int i = 0; i <1000; i++) {
                //移动鼠标
                //robot.mouseMove(1200, 400);

                //点击鼠标
                //鼠标左键
                System.out.println("单击");
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                //获得暂停时间
                int time = 5000;
                //暂停
                Thread.sleep(time);

            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  void shili() {
        try{
            System.out.println("shili");
            Robot robot= new Robot();
            robot.setAutoDelay(1000);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);




            for (int i = 0; i <1000; i++) {
                //移动鼠标
                //robot.mouseMove(1200, 400);

                robot.keyRelease(KeyEvent.VK_G);
                robot.keyPress(KeyEvent.VK_G);

                //获得暂停时间
                int time = 5000;
                //暂停
                Thread.sleep(time);

            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void fish() {
        try{


            System.out.println("shili");
            Robot robot= new Robot();
            robot.setAutoDelay(1000);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);

            for (int i = 0; i <1000; i++) {
                //移动鼠标
                robot.mouseMove(1200, 400);

                robot.keyRelease(KeyEvent.VK_G);
                robot.keyPress(KeyEvent.VK_G);


                //点击鼠标
                //鼠标左键
                System.out.println("单击");
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                //获得暂停时间
                int time = 5000;
                //暂停
                Thread.sleep(time);

            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void autoSpk() {
        try{
            System.out.println("autoSpk");
            Robot robot= new Robot();
            robot.setAutoDelay(1000);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);

            //移动鼠标
            robot.mouseMove(1200, 400);

            for (int i = 0; i <1000; i++) {


                //按下Alt+TAB键（切换桌面窗口）
                robot.keyPress(KeyEvent.VK_CONTROL);
                for(int j=1;j<=1;j++)
                {
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                }
                robot.keyRelease(KeyEvent.VK_CONTROL);

                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.keyPress(KeyEvent.VK_ENTER);



                //获得暂停时间
                int time = 5000;
                //暂停
                Thread.sleep(time);

            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //固定谱演奏
    public  void classicSing() {
        try{
            System.out.println("classicSing");
            int delay ;
            String pu;
            Robot robot= new Robot();
            robot.setAutoDelay(300);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);
            for (int k = 5; k < CommonUtil.QuPuList.size(); k++) {
                delay = CommonUtil.QuPuList.get(k).getDelayTime();
                pu = CommonUtil.QuPuList.get(k).getQuickKey();

                for (int i = 0; i < 50; i++) {
                    for (String c : pu.split("")){
                        robot.keyPress(KeyMap.keyMap.get(c.toUpperCase()));
                        robot.keyRelease(KeyMap.keyMap.get(c.toUpperCase()));

                    }
                    //获得暂停时间
                    int time = delay*1000;
                    //暂停
                    Thread.sleep(time);
                }
            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //自由演奏
    public  void freeSing() {
        try{
            System.out.println("freeSing");
            String pu = CommonUtil.readFromFile("f:/pu.txt");
            Robot robot= new Robot();
            robot.setAutoDelay(300);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);

            for (String c : pu.split("")){
                robot.keyPress(KeyMap.keyMap.get(c.toUpperCase()));
                robot.keyRelease(KeyMap.keyMap.get(c.toUpperCase()));

            }
            //获得暂停时间
            //int time = delay*1000;
            //暂停
            //Thread.sleep(time);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //自由演奏 有和声（同时按住多个键）
    //eg.  EYU(1H)D(2H)3(2G)S(UG)(YH)DHJQ 括号表示要同时按住的键
    public  void freeMutiSing() {
        try{
            System.out.println("freeMutiSing");
            String pu = CommonUtil.readFromFile("f:/pu.txt");
            Robot robot= new Robot();
            robot.setAutoDelay(300);

            //按下Alt+TAB键（切换桌面窗口）
            robot.keyPress(KeyEvent.VK_ALT);
            for(int i=1;i<=1;i++)
            {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            robot.keyRelease(KeyEvent.VK_ALT);

            ArrayList<String> mutiKeyList = new ArrayList<String>();
            boolean needMutiClick = false;
            for (String c : pu.split("")){
                if (needMutiClick) {//对括号内的处理
                    if (c.equals(")")) {//同时按键
                        for (int i = 0; i < mutiKeyList.size(); i++) {
                            asyClick(robot, mutiKeyList.get(i));
                        }
	            			/*
		            		for (int i = 0; i < mutiKeyList.size(); i++) {
		            			robot.keyPress(KeyMap.keyMap.get(mutiKeyList.get(i).toUpperCase()));
							}
		            		for (int i = mutiKeyList.size()-1; i > -1; i--) {
		            			robot.keyRelease(KeyMap.keyMap.get(mutiKeyList.get(i).toUpperCase()));
							}
							*/
                        needMutiClick = false;
                    }else {
                        mutiKeyList.add(c);
                    }
                }else {
                    if (c.equals("(")) {//遇到有括号
                        if (!mutiKeyList.isEmpty()) {
                            mutiKeyList.clear();
                        }
                        //mutiKeyList.add(c);
                        needMutiClick = true;
                    }else {//直接按键
                        robot.keyPress(KeyMap.keyMap.get(c.toUpperCase()));
                        robot.keyRelease(KeyMap.keyMap.get(c.toUpperCase()));
                    }
                }




            }
            //获得暂停时间
            //int time = delay*1000;
            //暂停
            //Thread.sleep(time);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //开线程按键
    public  void asyClick(final Robot robot,final String s) {
        AsyUtil.getThreadPool().execute(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                robot.keyPress(KeyMap.keyMap.get(s.toUpperCase()));
                robot.keyRelease(KeyMap.keyMap.get(s.toUpperCase()));
            }
        });
    }
}
