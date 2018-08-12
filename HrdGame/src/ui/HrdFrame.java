package ui;

import Manager.FindCallBack;
import Manager.HrdGmaeMgr;
import model.GameState;
import model.MoveAction;
import model.Warrior;
import util.CommStringInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: HrdFrame
 * Author:   xiaoge
 * Date:     2018/8/7 19:13
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class HrdFrame extends JFrame implements KeyListener, CommStringInterface, MouseListener,ActionListener {
    HrdGmaeMgr hrdGmaeMgr;
    GameState state;
    Point preLocation;


    public HrdFrame() {
        hrdGmaeMgr = new HrdGmaeMgr(new FindCallBack() {
            @Override
            public void findPath(List<GameState> list) {
                System.out.println("frame update");
                System.out.println("size: "+list.size());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i= list.size()-1;i>-1;i--){
                            System.out.println("find repaint");
                            GameState tmpState= list.get(i);

                            state= tmpState;

                            getContentPane().removeAll();
                            setView();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }){
                }.start();


            }
        });
        state = hrdGmaeMgr.makeNewMap();
        //state =hrdGmaeMgr.makeNewMapByRobot();

        setFrame();
        setView();


    }

    public void setFrame(){
        this.setTitle("华容道");
        this.setSize(frame_cute_size*(HRD_WIDTH-2), frame_cute_size*(HRD_HEIGHT-1));
        this.setLocation(400, 50);
        this.setResizable(false);
        this.setLayout(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });
    }
    public void setView(){
        HrdPlane gamePane = new HrdPlane();
        gamePane.setSize(frame_cute_size*(HRD_WIDTH-2), frame_cute_size*(HRD_HEIGHT-2));
        gamePane.setLocation(0,0);

        gamePane.setState(state);
        gamePane.repaint();

        JButton solveBtn = new JButton();
        solveBtn.setText("Solve");
        solveBtn.setSize(100, 50);
        solveBtn.setLocation(0, frame_cute_size*(HRD_HEIGHT-2));
        solveBtn.addActionListener(this);

        JButton mapMakeBtn = new JButton();
        mapMakeBtn.setText("MakeMap");
        mapMakeBtn.setSize(100, 50);
        mapMakeBtn.setLocation(120, frame_cute_size*(HRD_HEIGHT-2));
        mapMakeBtn.addActionListener(this);

        this.getContentPane().add(gamePane);
        this.getContentPane().add(solveBtn);
        this.getContentPane().add(mapMakeBtn);
        this.getContentPane().repaint();
//        this.pack();
    }

/**
 * 功能描述: <br>
 * 〈根据位置获取英雄〉
   参数         [x, y]
 * 返回 @return:int
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/12 9:33
 */
    public int getHeroIdxbyLocation(int x,int y){
        int type = state.getMap()[x+1][y+1];
        switch (type){
            case HERO_TYPE_ZHANGFEI:{
                //up
/*                int cv1 = state.getMap()[x+1][y];
                //down
                int cv2 = state.getMap()[x+1][y+2];
                //俩张飞连一起
                if (cv1==cv2){

                }
                if(cv1==HERO_TYPE_ZHANGFEI){
                    y=y-1;
                }*/
                break;
            }
            case HERO_TYPE_CAOCAO:{
                if(state.getMap()[x+1][y]==HERO_TYPE_CAOCAO){
                    y=y-1;
                }
                if(state.getMap()[x][y+1]==HERO_TYPE_CAOCAO){
                    x=x-1;
                }
                break;
            }
            case HERO_TYPE_GUANYU:{
                if(state.getMap()[x][y+1]==HERO_TYPE_GUANYU){
                    x=x-1;
                }
                break;
            }
            case HERO_TYPE_XIAOBING:{
                break;
            }
            default:{
                break;
            }
        }
        int index = -1;
        for (int i=0;i<state.getHeros().size();i++){
            Warrior hero = state.getHeros().get(i);
            if ((hero.getLeft() == x) && (hero.getTop() == y)){
                return i;
            }
        }
        return index;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(KeyEvent.getKeyText(e.getKeyCode())){
            case "A":{
                GameState tmpState = hrdGmaeMgr.moveToNewStata(state,6,2);
                tmpState = hrdGmaeMgr.moveToNewStata(tmpState,7,2);
                tmpState = hrdGmaeMgr.moveToNewStata(tmpState,1,2);
                state = tmpState;
                getContentPane().removeAll();
                setView();

                //System.out.println("a press");
                break;
            }

            case "D":
                GameState tmpState = hrdGmaeMgr.moveToNewStata(state,7,3);
                break;
            case "S":{
            }

            break;
            case " ":
                break;
            case "Enter": {
                hrdGmaeMgr.search(state);
                break;
            }
            default:{
                break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        preLocation = new Point(e.getX(),e.getY());

        //System.out.println("localx: "+locaX+"  localY: "+locaY);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int locaX = preLocation.x/frame_cute_size;
        int locaY = preLocation.y/frame_cute_size;

        Point curLocation = new Point(e.getX(),e.getY());
        System.out.println("preX:"+preLocation.x+"  preY:"+preLocation.y);
        System.out.println("curX:"+curLocation.x+"  curY:"+curLocation.y);
        System.out.println("absX:"+Math.abs(curLocation.x -preLocation.x)+"  absY:"+Math.abs(curLocation.y-preLocation.y));
        if (Math.abs(curLocation.x -preLocation.x)>Math.abs(curLocation.y-preLocation.y)){
            if (curLocation.x -preLocation.x<0){
                System.out.println(" move left");
                //left
                int heroIdx = getHeroIdxbyLocation(locaX,locaY);
                System.out.println("hero Index: "+heroIdx);
                if (heroIdx >-1){
                    GameState tmp = hrdGmaeMgr.moveToNewStata(state,heroIdx,3);
                    if (tmp!= null){
                        state = tmp;
                    }
                }
            }else{
                System.out.println(" move left");
                //right
                int heroIdx = getHeroIdxbyLocation(locaX,locaY);
                if (heroIdx >-1){
                    GameState tmp = hrdGmaeMgr.moveToNewStata(state,heroIdx,1);
                    if (tmp!= null){
                        state = tmp;
                    }
                }

            }
        }else{
            if (curLocation.y -preLocation.y<0){
                System.out.println(" move up");
                //up
                int heroIdx = getHeroIdxbyLocation(locaX,locaY);
                if (heroIdx >-1){
                    GameState tmp = hrdGmaeMgr.moveToNewStata(state,heroIdx,0);
                    if (tmp!= null){
                        state = tmp;
                    }
                }
            }else{
                System.out.println(" move down");
                //up
                int heroIdx = getHeroIdxbyLocation(locaX,locaY);
                System.out.println("heroIdx: "+heroIdx);
                if (heroIdx >-1){
                    GameState tmp = hrdGmaeMgr.moveToNewStata(state,heroIdx,2);
                    if (tmp!= null){
                        System.out.println("state != null");
                        state = tmp;
                    }

                }
            }
        }
        this.getContentPane().removeAll();
        setView();


        System.out.println("localx: "+locaX+"  localY: "+locaY);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Solve"))
        {
            hrdGmaeMgr.search(state);
        }
        if(e.getActionCommand().equals("MakeMap"))
        {
            state =hrdGmaeMgr.makeNewMapByRobot();
            this.getContentPane().removeAll();
            setView();
        }
    }
}
