package manager;

import model.CellNode;
import model.Direction;
import util.CommStringInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: SearchPathManager
 * Author:   xiaoge
 * Date:     2018/8/16 18:25
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class SearchPathManager implements CommStringInterface{
    MinheapList openList;
    List<CellNode> closeList;

    //找到后返回接口，用来展示搜索到的结果
    FindCallBack findCallBack;
    //解决方案
    List<CellNode>resultList;

    public SearchPathManager() {
        openList = new MinheapList();
        closeList = new ArrayList<>();
        resultList = new ArrayList<>();
    }
    public void setFindCallBack(FindCallBack findCallBack) {
        this.findCallBack = findCallBack;
    }
    public int[][] makeMap() {
        int[][] tmp = new int[MAP_WIDTH][MAP_HEIGHT];
        for (int i=0;i<MAP_WIDTH;i++){
            for (int j=0;j<MAP_HEIGHT;j++){
                //地图周围围一圈 方便以后做移动操作 不用考虑边界
                if ((i==0) ||(i==MAP_WIDTH-1) ||(j==0)||(j==MAP_HEIGHT-1)){
                    tmp[i][j] = -1;
                }else{
                    //位置没有人物用0表示
                    tmp[i][j] = 0;
                    Random random = new Random();
                    int k = random.nextInt(5);
                    if (k == 2) {
                        tmp[i][j] = 1;
                    }
                }

            }
        }
        //tmp[2][1]=1;
        //tmp[2][2]=1;

        //tmp[3][4]=1;
        //tmp[3][5]=1;
        tmp[1][1]=0;
        return tmp;
    }


    public void search(int[][]map, CellNode node,CellNode goalNode) {
        node.setgValue(0);
        node.setId(node.getLocation().x+node.getLocation().y*MAP_WIDTH);
        openList.addItem(node);

        boolean find =false;
        int k = 0;
        while ((openList.getList().size() > 0)&&(!find)) {
            //System.out.println("第"+k+"次");
            k++;
            //每次获取f值最小的节点
            CellNode curNode = openList.getLastItem();

            //对节点进行四个方向的尝试
            for (int i=0;i<4;i++) {
                //节点可以向指定方向i 行走
                if (canMove(map,curNode,i)) {
                    //CellNode newNode = moveToNewNode(map,curNode,i);
                    //生成新节点
                    Direction dir = DIRECTIONS[i] ;
                    int x = curNode.getLocation().x+dir.getHd();
                    int y = curNode.getLocation().y+dir.getVd();
                    CellNode newNode = new CellNode();
                    newNode.setLocation(new Point(x,y));
                    newNode.setId(newNode.getLocation().x+newNode.getLocation().y*MAP_WIDTH);
                    //设置 g,h,f的值
                    newNode.sethValur(getHvalue(newNode,goalNode));
                    newNode.setgValue(curNode.getgValue()+1);
                    newNode.setfValue(newNode.gethValur()+newNode.getgValue());

                    //新节点不在待处理列表中
                    if ((openList.getItemById(newNode.getId()) == null)) {
                        // 也不在已经处理过的列表中
                        if (!existInClosedList(newNode.getId())) {
                            //将当前节点设置为新节点的父节点
                            newNode.setParrent(curNode);
                            //加入待处理列表
                            openList.addItem(newNode);
                            //已经找到目标节点
                            if ((newNode.getLocation().x == goalNode.getLocation().x) &&
                                    (newNode.getLocation().y == goalNode.getLocation().y)) {
                                find = true;
                                System.out.println("find the path");
                                findPath(newNode);
                            }
                        }
                    }else{
                        //更新 f g h 的值
//                        CellNode openNode = openList.getItemById(newNode.getId());
//                        if (newNode.getgValue()<openNode.getgValue()){
//                            openNode.setgValue(newNode.getgValue());
//                            openNode.setfValue(openNode.gethValur()+openNode.getgValue());
//                            //更新在openlist中的位置
//                            openList.deleteItemById(openNode.getId());
//                            openList.addItem(openNode);
//                            System.out.println("update");
//                        }
                    }
                }
            }
            //从待搜寻列表中删除
            openList.deleteItemById(curNode.getId());
            //加入到已经搜寻列表
            closeList.add(curNode);
        }


    }

    private int getHvalue(CellNode curNode,CellNode goalNode) {
        return Math.abs(goalNode.getLocation().x-curNode.getLocation().x)+
                Math.abs(goalNode.getLocation().y-curNode.getLocation().y);
    }

    private boolean canMove(int[][]map, CellNode node,int dirIdx) {
        Direction dir = DIRECTIONS[dirIdx] ;
        int x = node.getLocation().x+dir.getHd();
        int y = node.getLocation().y+dir.getVd();
        if (map[x][y] == 0){
            return true;
        }
        return false;
    }

    private CellNode moveToNewNode(int[][]map, CellNode node,int dir) {
        return null;
    }
    public boolean existInClosedList(int id){
        for (CellNode tmp:closeList) {
            if (tmp.getId() == id) {
                return true;
            }
        }
        return false;
    }
    private void findPath(CellNode newNode) {
        if (resultList.size()>0){
            resultList.clear();
        }
        CellNode tmpState = newNode;
        while (tmpState.getParrent()!=null){
            resultList.add(tmpState);
            tmpState = tmpState.getParrent();
        }
        findCallBack.findPath(resultList);
    }

}
