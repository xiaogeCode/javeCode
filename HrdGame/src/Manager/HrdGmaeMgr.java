package Manager;

import model.*;
import util.CommStringInterface;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: HrdGmaeMgr
 * Author:   xiaoge
 * Date:     2018/8/7 15:51
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class HrdGmaeMgr implements CommStringInterface{
    //保存状态队列，每次遍历从中取一个节点
    Queue<GameState> stateExistList= null;
    //在队列中待处理的状态转成的hash表，进行快速判断是否存在该状态
    HashMap readyToHandleHashMap;
    //已经搜索过状态转成的hash表，防止重复搜索,可快速判断
    HashMap alreadySearchHashMap;
    //ZobristHasn初始编码
    ZobristHasn zbHash;
    //找到后返回接口，用来展示搜索到的结果
    FindCallBack findCallBack = null;
    //解决方案
    List<GameState>resultList = null;
    //用来计数生成的地图数目
    private  static int mapCreateCount=0;
    List<Point> heroPointList;
    List<List<Point>> mapList;

    public HrdGmaeMgr(FindCallBack callBack) {
        stateExistList = new LinkedList<GameState>();
        alreadySearchHashMap = new HashMap();
        readyToHandleHashMap = new HashMap();
        zbHash = ZobristHashMgr.MakebaseZobHash();
        findCallBack = callBack;
        resultList = new ArrayList<GameState>();
        heroPointList = new ArrayList<Point>();
        mapList =new ArrayList<List<Point>>();
        for (int i=0;i<10;i++){
            Point pt= new Point(0,0);
            heroPointList.add(pt);

        }
//        for (int i=0;i<HRD_WIDTH-2;i++) {
//            for (int j = 0; j < HRD_HEIGHT - 2; j++) {
//                CellState cellState = zbHash.getKey()[i][j];
//                for (int k = 0;k<5;k++){
//                    System.out.print(cellState.getValue()[k]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

    }

    public GameState makeNewMap(){
        int a[][]= new int[][]{{1,0},{1,2},{0,0},{0,2},{3,0},{3,2},{1,3},{2,3},{0,4},{3,4}};
        //int a[][]= new int[][]{{0,0},{0,2},{1,3},{2,0},{3,0},{3,2},{0,4},{2,2},{0,3},{3,4}};
        int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
                HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
                HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};
        GameState state = new GameState();
        int k=0;
        for (int[] tmp:a) {
            Warrior hero = new Warrior();
            hero.setLeft(tmp[0]);
            hero.setTop(tmp[1]);
            hero.setType(type[k]);

            state.getHeros().add(hero);
            k++;
        }
        state.setMap(getMapFromHeroList(state.getHeros()));

        return state;
    }

    public int[][] getMapFromHeroList(List<Warrior>list){
        int[][] tmp = new int[HRD_WIDTH][HRD_HEIGHT];
        for (int i=0;i<HRD_WIDTH;i++){
            for (int j=0;j<HRD_HEIGHT;j++){
                //地图周围围一圈 方便以后做移动操作 不用考虑边界
                if ((i==0) ||(i==HRD_WIDTH-1) ||(j==0)||(j==HRD_HEIGHT-1)){
                    tmp[i][j] = -1;
                }else{
                    //位置没有人物用0表示
                    tmp[i][j] = 0;
                }

            }
        }
        for (Warrior hero:list) {

            int x = hero.getLeft()+1;
            int y = hero.getTop()+1;

            switch (hero.getType()){
                case HERO_TYPE_ZHANGFEI:{
                    tmp[x][y] = HERO_TYPE_ZHANGFEI;
                    tmp[x][y+1] =HERO_TYPE_ZHANGFEI;
                    break;
                }
                case HERO_TYPE_CAOCAO:{
                    tmp[x][y] = HERO_TYPE_CAOCAO;
                    tmp[x][y+1] =HERO_TYPE_CAOCAO;
                    tmp[x+1][y] =HERO_TYPE_CAOCAO;
                    tmp[x+1][y+1] =HERO_TYPE_CAOCAO;
                    break;
                }
                case HERO_TYPE_GUANYU:{
                    tmp[x][y] = HERO_TYPE_GUANYU;
                    tmp[x+1][y] = HERO_TYPE_GUANYU;
                    break;
                }
                case HERO_TYPE_XIAOBING:{
                    tmp[x][y] = HERO_TYPE_XIAOBING;
                    break;
                }
                default:{
                    break;
                }
            }
        }
        return tmp;

    }
    public List<Warrior> getHeroListFromMap(int[][] map){
        int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
                HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
                HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};

        List<Warrior> list=new ArrayList<Warrior>();
        for (int i=0;i<2;i++) {
            for (int a1=1;a1<HRD_WIDTH;a1++) {
                for (int a2=1;a2<HRD_HEIGHT;a2++) {
                    if (map[a1][a2] == type[i]){
                        Warrior hero = new Warrior();
                        hero.setType(type[i]);
                        hero.setLeft(a1);
                        hero.setTop(a2);
                        list.add(hero);
                        //跳出二重循环
                        a1 =HRD_WIDTH;
                        a2 =HRD_HEIGHT;
                        break;
                    }
                }
            }
        }
        return list;
    }
    public GameState getGamestateFromHeroPointList(List<Point>list){
        int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
                HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
                HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};
        GameState state = new GameState();
        int k=0;
        for (Point pt:list) {
            Warrior hero = new Warrior();
            hero.setLeft(pt.x);
            hero.setTop(pt.y);
            hero.setType(type[k]);

            System.out.println("x-y: "+pt.x+" "+pt.y);

            state.getHeros().add(hero);
            k++;
        }

        state.setMap(getMapFromHeroList(state.getHeros()));
        return state;
    }
    public void search(GameState state){
        if (readyToHandleHashMap.size()>0){
            readyToHandleHashMap.clear();
        }
        if (alreadySearchHashMap.size()>0){
            alreadySearchHashMap.clear();
        }
        if (stateExistList.size()>0){
            stateExistList.clear();
        }
        if (resultList.size()>0){
            resultList.clear();
        }
        //加入到hash表中
        int hashFist = ZobristHashMgr.getZobrishHash(zbHash,state);
        readyToHandleHashMap.put(hashFist,1);
        stateExistList.offer(state);

        int k = 0;
        while (stateExistList.size() > 0) {
            k++;
            //从队列中取出一个节点
            GameState curStateNode = stateExistList.poll();
            int curHash = ZobristHashMgr.getZobrishHash(zbHash,state);
            System.out.println("第" + k + "次");
            for (int i = 0; i < curStateNode.getHeros().size(); i++) {
                GameState newStateNode=null;
                for (int j = 0; j < 4; j++) {
                    //移动生成新的状态
                    newStateNode = moveToNewStata(curStateNode,i,j);
                    //不为null就是能移动
                    if (newStateNode != null){
                        //生成的新状态不在状态队列中
                        if ((!existInHashTable(hash_type_alreay_handle,newStateNode))
                                && (!existInHashTable(hash_type_prepare_handle,newStateNode))){

                            //新节点 加入到队列中
                            newStateNode.setParrent(curStateNode);
                            stateExistList.offer(newStateNode);

                            //加入到hash表中
                            int hash = ZobristHashMgr.getZobrishHash(zbHash,newStateNode);
//                            System.out.println("hash  "+hash);
                            readyToHandleHashMap.put(hash,1);

                            //找到解决方案
                            //判断目标点值是否相等
                            if ((newStateNode.getHeros().get(0).getLeft() == 1) &&
                                    (newStateNode.getHeros().get(0).getTop() == 3)){
                                //
                                System.out.println("find it");
                                findPath(newStateNode);
                                return;
                            }

                        }
                    }
                }

            }
            /*if (k==1){
                break;
            }*/
            //加入到已处理列表
            alreadySearchHashMap.put(curHash,1);
            readyToHandleHashMap.remove(curHash);
        }
        System.out.println("search end");
    }

    private void findPath(GameState newStateNode) {
        if (resultList.size()>0){
            resultList.clear();
        }
        GameState tmpState = newStateNode;
        while (tmpState.getParrent()!=null){
            resultList.add(tmpState);
            tmpState = tmpState.getParrent();
        }
        findCallBack.findPath(resultList);
    }

    public boolean existInHashTable(int type,GameState state){
        boolean exist = false;
        int hash = ZobristHashMgr.getZobrishHash(zbHash,state);
        if (type == hash_type_alreay_handle){
            //exist=alreadySearchHashMap.containsKey(hash);
            exist=(alreadySearchHashMap.get(hash)!=null);
        }else{
            exist=(readyToHandleHashMap.get(hash)!=null);
        }
        if (exist){
//            System.out.println("exist");
        }else {
//            System.out.println("not exist");
        }


        return exist;
    }
    public boolean canHeroMove(GameState state,int heroIdx,int dirIdx){
        int cv1,cv2,cv3,cv4;
        boolean canMove = false;
        Warrior hero = state.getHeros().get(heroIdx);
        Direction dir = DIRECTIONS[dirIdx] ;
//        System.out.println("x: "+hero.getLeft()+" y: "+hero.getTop()+" type: "+hero.getType());
        switch (hero.getType()){
            case HERO_TYPE_ZHANGFEI:{
                cv1 = state.getMap()[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1];
                cv2 = state.getMap()[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+2];
/*
                如果位置是空或者重叠，表示可以移动
*/
                if (dirIdx == 0){
                    //up
                    canMove= ((cv1 == 0)) && ((cv2 == 0)||(cv2 == hero.getType()));
                }else if (dirIdx == 2){
                    //down
                    canMove= (((cv1 == 0)||(cv1 == hero.getType())) && (cv2 == 0));
                } else{
                    //left right
                    canMove= ((cv1 == 0) && (cv2 == 0));
                }

//                tmp[x][y] = HERO_TYPE_ZHANGFEI;
//                tmp[x][y+1] =HERO_TYPE_ZHANGFEI;
                break;
            }
            case HERO_TYPE_CAOCAO:{
                cv1 = state.getMap()[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1];
                cv2 = state.getMap()[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+2];
                cv3 = state.getMap()[hero.getLeft()+dir.getHd()+2][hero.getTop()+dir.getVd()+1];
                cv4 = state.getMap()[hero.getLeft()+dir.getHd()+2][hero.getTop()+dir.getVd()+2];
                /*System.out.println("c1: "+cv1+" c2: "+cv2+" c3: "+cv3+" c4: "+cv4);
                System.out.println("gettype: "+hero.getType());
                if ((cv1 == 0)||(cv1 == hero.getType())){
                    System.out.println("equal");
                }*/
                canMove= (((cv1 == 0)||(cv1 == hero.getType())) && ((cv2 == 0)||(cv2 == hero.getType())) && ((cv3 == 0)||(cv3 == hero.getType()))&& ((cv4 == 0)||(cv4 == hero.getType())));
//                tmp[x][y] = HERO_TYPE_CAOCAO;
//                tmp[x][y+1] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y+1] =HERO_TYPE_CAOCAO;
                /*if (canMove){
                    System.out.println("canmove");
                }*/
                break;
            }
            case HERO_TYPE_GUANYU:{
                cv1 = state.getMap()[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1];
                cv2 = state.getMap()[hero.getLeft()+dir.getHd()+2][hero.getTop()+dir.getVd()+1];
                canMove= (((cv1 == 0)||(cv1 == hero.getType())) && ((cv2 == 0)||(cv2 == hero.getType())));
//                tmp[x][y] = HERO_TYPE_GUANYU;
//                tmp[x+1][y] = HERO_TYPE_GUANYU;
                break;
            }
            case HERO_TYPE_XIAOBING:{
                cv1 = state.getMap()[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1];
                canMove= (cv1 == 0);
//                tmp[x][y] = HERO_TYPE_XIAOBING;
                break;
            }
            default:{
                break;
            }
        }

        return canMove;
    }
    public GameState moveToNewStata(GameState state,int heroIdx,int dirIdx){
        GameState newState = null;
        if (canHeroMove(state,heroIdx,dirIdx)){
//            System.out.println("can move "+" heroIdex  "+heroIdx+" dirIdex "+dirIdx);
            newState = new GameState();
            //newState.setParrent(state);

//            MoveAction moveAction = new MoveAction();
//            moveAction.setHeroIndex(heroIdx);
//            moveAction.setDirIndex(dirIdx);
//            newState.setMove(moveAction);

            newState.setMap(updateNewstateMap(state,heroIdx,dirIdx));

            newState.setHeros(updateNewstateHerolist(state,heroIdx,dirIdx));
//            newState.setHeros(state.getHeros());
//            int x = state.getHeros().get(heroIdx).getLeft()+DIRECTIONS[dirIdx].getHd();
//            int y = state.getHeros().get(heroIdx).getTop()+DIRECTIONS[dirIdx].getVd();
//            newState.getHeros().get(heroIdx).setLeft(x);
//            newState.getHeros().get(heroIdx).setTop(y);

        }

        return newState;
    }
    public List<Warrior> updateNewstateHerolist(GameState state,int heroIdx,int dirIdx){
        List<Warrior> tmpHeroList = new ArrayList<Warrior>();
        for (int i=0;i<state.getHeros().size();i++) {
            Warrior hero = new Warrior();
            hero.setLeft(state.getHeros().get(i).getLeft());
            hero.setTop(state.getHeros().get(i).getTop());
            hero.setType(state.getHeros().get(i).getType());

            if (i==heroIdx){
                hero.setLeft(hero.getLeft()+DIRECTIONS[dirIdx].getHd());
                hero.setTop(hero.getTop()+  DIRECTIONS[dirIdx].getVd());
            }
            tmpHeroList.add(hero);
        }
        return tmpHeroList;
    }
/*
    移动后更新GameState里的map
*/
    public int[][] updateNewstateMap(GameState state,int heroIdx,int dirIdx){
        int[][] tmpMap = new int[HRD_WIDTH][HRD_HEIGHT];
        Warrior hero = state.getHeros().get(heroIdx);
        Direction dir = DIRECTIONS[dirIdx];
        for (int i=0;i<HRD_WIDTH;i++){
            for (int j=0;j<HRD_HEIGHT;j++){
                tmpMap[i][j]=state.getMap()[i][j];
            }
        }
        switch (state.getHeros().get(heroIdx).getType()){
            case HERO_TYPE_ZHANGFEI:{
/*
                原位置置0
*/
                tmpMap[hero.getLeft()+1][hero.getTop()+1] =0;
                tmpMap[hero.getLeft()+1][hero.getTop()+2] =0;
/*
                新位置赋值
*/
                tmpMap[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1] =HERO_TYPE_ZHANGFEI;
                tmpMap[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+2] =HERO_TYPE_ZHANGFEI;
                break;
            }
            case HERO_TYPE_CAOCAO:{
                tmpMap[hero.getLeft()+1][hero.getTop()+1]=0;
                tmpMap[hero.getLeft()+1][hero.getTop()+2]=0;
                tmpMap[hero.getLeft()+2][hero.getTop()+1]=0;
                tmpMap[hero.getLeft()+2][hero.getTop()+2]=0;

                tmpMap[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1]=HERO_TYPE_CAOCAO;
                tmpMap[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+2]=HERO_TYPE_CAOCAO;
                tmpMap[hero.getLeft()+dir.getHd()+2][hero.getTop()+dir.getVd()+1]=HERO_TYPE_CAOCAO;
                tmpMap[hero.getLeft()+dir.getHd()+2][hero.getTop()+dir.getVd()+2]=HERO_TYPE_CAOCAO;
//
                break;
            }
            case HERO_TYPE_GUANYU:{
                tmpMap[hero.getLeft()+1][hero.getTop()+1]=0;
                tmpMap[hero.getLeft()+2][hero.getTop()+1]=0;

                tmpMap[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1]=HERO_TYPE_GUANYU;
                tmpMap[hero.getLeft()+dir.getHd()+2][hero.getTop()+dir.getVd()+1]=HERO_TYPE_GUANYU;
                break;
            }
            case HERO_TYPE_XIAOBING:{
                tmpMap[hero.getLeft()+1][hero.getTop()+1]=0;

                tmpMap[hero.getLeft()+dir.getHd()+1][hero.getTop()+dir.getVd()+1]=HERO_TYPE_XIAOBING;
                break;
            }
            default:{
                break;
            }
        }
        return tmpMap;

    }

    public void printMap(GameState state){
/*
        旋转输出
*/
        for (int i=0;i<HRD_HEIGHT;i++){
            for (int j=0;j<HRD_WIDTH;j++){
                System.out.print(state.getMap()[j][i]);
            }
            System.out.println();
        }
    }
    public GameState makeNewMapByRobot(){
        int[][] tmp = new int[HRD_WIDTH][HRD_HEIGHT];
        for (int i=0;i<HRD_WIDTH;i++){
            for (int j=0;j<HRD_HEIGHT;j++){
                //地图周围围一圈 方便以后做移动操作 不用考虑边界
                if ((i==0) ||(i==HRD_WIDTH-1) ||(j==0)||(j==HRD_HEIGHT-1)){
                    tmp[i][j] = -1;
                }else{
                    //位置没有人物用0表示
                    tmp[i][j] = 0;
                }

            }
        }
        placeHero(0,tmp);
        System.out.println("to state");
        GameState state = getGamestateFromHeroPointList(mapList.get(1));
        return state;
    }
    public void placeHero(int index,int[][] map){
        int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
                HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
                HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};

        if ((index>9) || (mapCreateCount>9)){
            return;
        }
        for (int i=0;i<HRD_WIDTH-2;i++) {
            for (int j=0;j<HRD_HEIGHT-2;j++) {
                if (canPlace(index,map,i,j)){
                    int[][] newMap = placeHeroToMap(index,map,i,j);
                    heroPointList.get(index).x=i;
                    heroPointList.get(index).y=j;
                    if (index ==9){
                        mapCreateCount++;
                        List<Point> tmpList = new ArrayList<Point>();
                        for (Point pt:heroPointList) {
                            Point newPt= new Point(pt.x,pt.y);
                            tmpList.add(newPt);
                        }
                        mapList.add(tmpList);
                        System.out.println("count: "+mapCreateCount);
                        printHeroListByPointList(heroPointList);

                        //printMapWithMap(newMap);
                    }else {
                        placeHero(index+1,newMap);
                    }
                }
            }
        }

    }
    public void printHeroListByPointList(List<Point>list){
        for (Point pt:list) {
            System.out.println(pt.x+" "+pt.y);
        }
    }
    public void printMapWithMap(int[][] map){
        for (int i=0;i<HRD_HEIGHT;i++) {
            for (int j=0;j<HRD_WIDTH;j++) {
                System.out.print(map[j][i]+"  ");
            }
            System.out.println();
        }
    }
    private int[][] placeHeroToMap(int index,int[][] map,int locX,int locY){
        int[][] tmpMap = new int[HRD_WIDTH][HRD_HEIGHT];
        for (int i=0;i<HRD_WIDTH;i++){
            for (int j=0;j<HRD_HEIGHT;j++){
                tmpMap[i][j]=map[i][j];
            }
        }
        int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
                HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
                HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};

        int curType = type[index];
        switch (curType){
            case HERO_TYPE_ZHANGFEI:{
                tmpMap[locX+1][locY+1]=HERO_TYPE_ZHANGFEI;
                tmpMap[locX+1][locY+2]=HERO_TYPE_ZHANGFEI;

//                tmp[x][y] = HERO_TYPE_ZHANGFEI;
//                tmp[x][y+1] =HERO_TYPE_ZHANGFEI;

                break;
            }
            case HERO_TYPE_CAOCAO:{
                tmpMap[locX+1][locY+1]=HERO_TYPE_CAOCAO;
                tmpMap[locX+1][locY+2]=HERO_TYPE_CAOCAO;
                tmpMap[locX+2][locY+1]=HERO_TYPE_CAOCAO;
                tmpMap[locX+2][locY+2]=HERO_TYPE_CAOCAO;


//                tmp[x][y] = HERO_TYPE_CAOCAO;
//                tmp[x][y+1] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y+1] =HERO_TYPE_CAOCAO;

                break;
            }
            case HERO_TYPE_GUANYU:{
                tmpMap[locX+1][locY+1]=HERO_TYPE_GUANYU;
                tmpMap[locX+2][locY+1]=HERO_TYPE_GUANYU;
//                tmp[x][y] = HERO_TYPE_GUANYU;
//                tmp[x+1][y] = HERO_TYPE_GUANYU;
                break;
            }
            case HERO_TYPE_XIAOBING:{
                tmpMap[locX+1][locY+1]=HERO_TYPE_XIAOBING;
                break;
            }
            default:{
                break;
            }
        }
        return tmpMap;
    }
    private boolean canPlace(int index,int[][] map,int locX,int locY) {
        int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
                HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
                HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};
        int curType = type[index];
        int cv1,cv2,cv3,cv4;
        boolean canMove = false;
        switch (curType){
            case HERO_TYPE_ZHANGFEI:{
                cv1 = map[locX+1][locY+1];
                cv2 = map[locX+1][locY+2];

                canMove= ((cv1 == 0) && (cv2 == 0));

//                tmp[x][y] = HERO_TYPE_ZHANGFEI;
//                tmp[x][y+1] =HERO_TYPE_ZHANGFEI;

                break;
            }
            case HERO_TYPE_CAOCAO:{
                cv1 = map[locX+1][locY+1];
                cv2 = map[locX+1][locY+2];
                cv3 = map[locX+2][locY+1];
                cv4 = map[locX+2][locY+2];

                canMove= ((cv1 == 0) && (cv2 == 0) && (cv3 == 0)&& (cv4 == 0));

//                tmp[x][y] = HERO_TYPE_CAOCAO;
//                tmp[x][y+1] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y+1] =HERO_TYPE_CAOCAO;

                break;
            }
            case HERO_TYPE_GUANYU:{
                cv1 = map[locX+1][locY+1];
                cv2 = map[locX+2][locY+1];
                canMove= ((cv1 == 0) && (cv2 == 0));
//                tmp[x][y] = HERO_TYPE_GUANYU;
//                tmp[x+1][y] = HERO_TYPE_GUANYU;
                break;
            }
            case HERO_TYPE_XIAOBING:{
                cv1 = map[locX+1][locY+1];
                canMove= (cv1 == 0);
                break;
            }
            default:{
                break;
            }
        }
        return canMove;
    }
}
