package xiaoge;

import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StateNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: PuzzleSolveManager
 * Author:   xiaoge
 * Date:     2018/8/6 16:36
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class PuzzleSolveManager implements CommonStringInterface{
    Queue<PointNode> locationQue=null;
    List<PointNode> alreadySearchList = null;

    Point goalPoint = new Point(Map_Width-2,Map_Height-2);
    PointNode restNode = null;
    PathFindCallBack callBack = null;


    public PuzzleSolveManager(PathFindCallBack phCallback) {
        locationQue = new LinkedList<PointNode>();
        alreadySearchList = new ArrayList<PointNode>();
        locationQue.offer(new PointNode(new Point(1,1)));
        callBack = phCallback;
    }

    public void searchPath(int[][] map){
        /*for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                System.out.println("manager: "+map[i][j]);
            }
        }*/
        int k = 0;
        while (locationQue.size()>0){
            //从队列中取出一个节点
            System.out.println("size before:"+locationQue.size());
            PointNode curPtNode = locationQue.poll();
            System.out.println("size after :"+locationQue.size());
            //对队列进行四种方向操作 生成的新状态保存到队列中
            for (int i=0;i<4;i++){
                k++;

                System.out.println("第"+k+"次");
                //System.out.println("cur"+curPtNode.item.x+"  "+curPtNode.item.y);

                Point tmpPoint = moveWithDir(curPtNode.item,i,map);
                PointNode tmpNode = new PointNode(tmpPoint);


                //生成的新状态不在状态队列中 并不在已经搜索过节点表中
                if(!existInAlreadySearchListPoint(tmpNode)){
                    if (!existPoint(tmpNode)){

                        //System.out.println("tmp"+tmpNode.item.x+"  "+tmpNode.item.y);

                        //构建新节点 加入到队列中
                        tmpNode.setParrent(curPtNode);
                        locationQue.offer(tmpNode);
                        //System.out.println("tmpp"+tmpPoint.x+"  "+tmpPoint.y);
                        //System.out.println("goal"+goalPoint.x+"  "+goalPoint.y);
                        //找到解决方案，倒序输出
                        //判断目标点值是否相等
                        System.out.println("add a new: "+tmpNode.item.x+"  "+tmpNode.item.y);
                        if ((tmpPoint.x == goalPoint.x) && (tmpPoint.y == goalPoint.y)){
                            restNode = tmpNode;
                            System.out.println("solve: ");
                            printPatn(restNode);
                            return;
                        }
                    }
                }

            }
            alreadySearchList.add(curPtNode);
        }

    }
    public void printPatn(PointNode node){
        List<Point> list = new ArrayList<Point>();
        PointNode tmp = node;
        while (tmp!=null){
            System.out.println("tmp: "+tmp.item.x+"  "+ tmp.item.y);
            list.add(tmp.item);
            tmp = tmp.getParrent();
        }
        callBack.find(list);
    }

    public Point moveWithDir(Point currPt,int dir,int[][] map) {
        int a[]=new int[]{-1,1,0,0};
        int b[]=new int[]{0,0,1,-1};

        int newX = currPt.x+a[dir];
        int newY = currPt.y+b[dir];
        if ((newX>map.length-1) || (newX < 0) || (newY > map[0].length-1) || (newY <0)){
            //越界
            return currPt;
        }
        if (map[newX][newY] !=0){
            //撞墙
            return currPt;
        }
        return new Point(newX,newY);
    }
    public boolean existPoint(PointNode pt){
        for (PointNode tmp:locationQue) {
            if ((tmp.item.x == pt.item.x) && (tmp.item.y == pt.item.y)){
                return true;
            }
        }
        return false;
    }
    public boolean existInAlreadySearchListPoint(PointNode pt){
        for (PointNode tmp:alreadySearchList) {
            if ((tmp.item.x == pt.item.x) && (tmp.item.y == pt.item.y)){
                return true;
            }
        }
        return false;
    }
}
