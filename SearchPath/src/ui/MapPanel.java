package ui;

import util.CommStringInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MapPanel
 * Author:   xiaoge
 * Date:     2018/8/16 19:58
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MapPanel extends JPanel implements CommStringInterface{
    int map[][];
    List<Point> path = new ArrayList<>();
    Point curPoint ;
    Point goalPoint ;

    public MapPanel() {

    }
    public void setGoalPoint(Point goalPoint) {
        this.goalPoint = goalPoint;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }
    public void setCurPoint(Point curPoint) {
        this.curPoint = curPoint;
    }


    public void setMap(int[][] map) {
        this.map = map;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
        Graphics big =bi.getGraphics();

        Color c =big.getColor();
        //绘画地图
        for (int i=1;i<MAP_WIDTH-1;i++){
            for (int j=1;j<MAP_HEIGHT-1;j++){
                switch (map[i][j]){
                    case 0:{
                        big.setColor(Color.green);
                        big.fillRect((i-1)*frame_cute_size, (j-1)*frame_cute_size, frame_cute_size, frame_cute_size);
                        break;
                    }
                    default:{
                        break;
                    }
                }
                big.setColor(c);
            }
        }

        //绘画路径
        for (Point pt:path) {
            big.setColor(Color.red);
            big.fillRect((pt.x-1)*frame_cute_size, (pt.y-1)*frame_cute_size, frame_cute_size-1, frame_cute_size-1);
            big.setColor(c);
        }

        //起始点
        big.setColor(Color.black);
        big.fillOval((curPoint.x-1) *frame_cute_size, (curPoint.y-1)*frame_cute_size, frame_cute_size, frame_cute_size);
        big.setColor(c);

        //目标点
        if (goalPoint != null) {
            big.setColor(Color.blue);
            big.fillOval((goalPoint.x-1) *frame_cute_size, (goalPoint.y-1)*frame_cute_size, frame_cute_size, frame_cute_size);
            big.setColor(c);
        }

        g.drawImage(bi,0,0,null);

    }
}
