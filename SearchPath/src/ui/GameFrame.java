package ui;

import manager.FindCallBack;
import manager.SearchPathManager;
import model.CellNode;
import util.CommStringInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: GameFrame
 * Author:   xiaoge
 * Date:     2018/8/16 19:54
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class GameFrame extends JFrame implements CommStringInterface,MouseListener{
    int[][] map;
    List<Point> path = new ArrayList<>();
    Point curPoint  = new Point(1,1);
    Point goalPoint;

    public GameFrame(){
        SearchPathManager searchPathManager = new SearchPathManager();
        map=searchPathManager.makeMap();
        setFrame();
        setMapView();
    }

    public void setFrame(){
        this.setTitle("go");
        this.setSize(frame_cute_size*(MAP_WIDTH-2), frame_cute_size*(MAP_HEIGHT-1));
        this.setLocation(400, 50);
        this.setResizable(false);
        this.setLayout(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.addKeyListener(this);
        this.addMouseListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });
    }
    public void setMapView(){
        MapPanel gamePane = new MapPanel();
        if (path.size()>0){
            gamePane.setPath(path);
        }
        gamePane.setCurPoint(curPoint);
        gamePane.setGoalPoint(goalPoint);

        gamePane.setSize(frame_cute_size*(MAP_WIDTH-2), frame_cute_size*(MAP_HEIGHT-2));
        gamePane.setLocation(0,0);
        gamePane.setMap(map);

        gamePane.repaint();

        this.getContentPane().add(gamePane);
        this.getContentPane().repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //frame标题栏高度
        Insets set=this.getInsets();
        int titlebarH=set.top;

        Point curLocation = new Point(e.getX(),e.getY()-titlebarH);
        int locaX = curLocation.x/frame_cute_size;
        int locaY = curLocation.y/frame_cute_size;

        System.out.println("x,y: "+locaX+","+locaY);
        goalPoint = new Point(locaX+1,locaY+1);

        SearchPathManager searchPathManager = new SearchPathManager();
        CellNode beginNode = new CellNode();
        beginNode.setLocation(curPoint);
        CellNode goalNode = new CellNode();
        goalNode.setLocation(new Point(goalPoint));

        searchPathManager.setFindCallBack(new FindCallBack() {
            @Override
            public void findPath(List<CellNode> list) {
                System.out.println("步数: "+list.size());

                if (path.size() > 0) {
                    path.clear();
                }

                for (int i= list.size()-1;i>-1;i--){
                    path.add(list.get(i).getLocation());
                    getContentPane().removeAll();
                }
                setMapView();

                /*new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }){
                }.start();*/
            }
        });
        searchPathManager.search(map,beginNode,goalNode);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
