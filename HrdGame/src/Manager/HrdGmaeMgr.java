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
/*
    用于搜索解决方案
*/
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
/*
    用于生成新地图算法
*/
    //用来计数生成的地图数目
    private  static int mapCreateCount=0;
    List<Point> heroPointList;
    List<List<Point>> mapList;

    int type[] = new int[]{HERO_TYPE_CAOCAO,HERO_TYPE_GUANYU,
            HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI,HERO_TYPE_ZHANGFEI, HERO_TYPE_ZHANGFEI,
            HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING,HERO_TYPE_XIAOBING};

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
    }
    /**
     * 功能描述: <br>
     * 〈生成特定地图〉
       参数         []
     * 返回 @return:model.GameState
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:15
     */
        public GameState makeNewMap(){
            int a[][]= new int[][]{{1,0},{1,2},{0,0},{0,2},{3,0},{3,2},{1,3},{2,3},{0,4},{3,4}};
            //int a[][]= new int[][]{{0,0},{0,2},{1,3},{2,0},{3,0},{3,2},{0,4},{2,2},{0,3},{3,4}};

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
            GameState state = new GameState();
            int k=0;
            for (Point pt:list) {
                Warrior hero = new Warrior();
                hero.setLeft(pt.x);
                hero.setTop(pt.y);
                hero.setType(type[k]);

    //            System.out.println("x-y: "+pt.x+" "+pt.y);

                state.getHeros().add(hero);
                k++;
            }

            state.setMap(getMapFromHeroList(state.getHeros()));
            return state;
        }
        /**
         * 功能描述: <br>
         * 〈寻找地图的解决方案〉
           参数         [state]
         * 返回 @return:void
         * 作者 @Author:xiaoge
         * 时间 @Date: 2018/8/12 9:14
         */
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
    /**
     * 功能描述: <br>
     * 〈根据地图节点回溯整个路径 并 调用接口刷新界面〉
       参数         [newStateNode]
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:13
     */
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
        /**
         * 功能描述: <br>
         * 〈判断地图是否存在相应的地图hash表中〉
           参数         [type, state]
         * 返回 @return:boolean
         * 作者 @Author:xiaoge
         * 时间 @Date: 2018/8/12 9:12
         */
        public boolean existInHashTable(int type,GameState state){
            boolean exist = false;
            int hash = ZobristHashMgr.getZobrishHash(zbHash,state);
            if (type == hash_type_alreay_handle){
                //exist=alreadySearchHashMap.containsKey(hash);
                exist=(alreadySearchHashMap.get(hash)!=null);
            }else{
                exist=(readyToHandleHashMap.get(hash)!=null);
            }
            return exist;
        }
        /**
         * 功能描述: <br>
         * 〈判断英雄是否可移动〉
           参数         [state, heroIdx, dirIdx]
         * 返回 @return:boolean
         * 作者 @Author:xiaoge
         * 时间 @Date: 2018/8/12 9:12
         */
    public boolean canHeroMove(GameState state,int heroIdx,int dirIdx){
        int cv1,cv2,cv3,cv4;
        boolean canMove = false;
        Warrior hero = state.getHeros().get(heroIdx);
        Direction dir = DIRECTIONS[dirIdx] ;
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

                canMove= (((cv1 == 0)||(cv1 == hero.getType())) && ((cv2 == 0)||(cv2 == hero.getType())) && ((cv3 == 0)||(cv3 == hero.getType()))&& ((cv4 == 0)||(cv4 == hero.getType())));
//                tmp[x][y] = HERO_TYPE_CAOCAO;
//                tmp[x][y+1] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y] =HERO_TYPE_CAOCAO;
//                tmp[x+1][y+1] =HERO_TYPE_CAOCAO;
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

    /**
     * 功能描述: <br>
     * 〈移动编号heroIdx的英雄方向dirIdx 生成新地图〉
       参数         [state, heroIdx, dirIdx]
     * 返回 @return:model.GameState
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:11
     */
    public GameState moveToNewStata(GameState state,int heroIdx,int dirIdx){
        GameState newState = null;
        if (canHeroMove(state,heroIdx,dirIdx)){
            newState = new GameState();

            newState.setMap(updateNewstateMap(state,heroIdx,dirIdx));
            newState.setHeros(updateNewstateHerolist(state,heroIdx,dirIdx));
        }

        return newState;
    }
    /**
     * 功能描述: <br>
     * 〈生成移动后新地图的英雄列表信息（state.getHeros()）〉
       参数         [state, heroIdx, dirIdx]
     * 返回 @return:java.util.List<model.Warrior>
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:20
     */
    public List<Warrior> updateNewstateHerolist(GameState state,int heroIdx,int dirIdx){
        List<Warrior> tmpHeroList = new ArrayList<>();
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

/**
 * 功能描述: <br>
 * 〈生成移动后新地图的GameState里的map〉
   参数         [state, heroIdx, dirIdx]
 * 返回 @return:int[][]
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/12 9:22
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
/**
 * 功能描述: <br>
 * 〈打印gamestate 信息〉
   参数         [state]
 * 返回 @return:void
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/12 9:23
 */
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
    /**
     * 功能描述: <br>
     * 〈自动生成新地图〉
       参数         []
     * 返回 @return:model.GameState
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:07
     */
    public GameState makeNewMapByRobot(){
        if (mapList.size()>0){
            mapList.clear();
        }
        mapCreateCount = 0;

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
/*
        随机安放第一个英雄曹操
*/
        Random random = new Random();
        int caoX =random.nextInt(3);
        int caoY =random.nextInt(4);
/*
        安放除曹操外的其他英雄，生成地图
*/
        placeHero(1,placeHeroToMap(0,tmp,caoX,caoY));
/*
        地图列表中随机取一个地图
*/
        int index = random.nextInt(mapList.size());
/*
        给地图中的曹操赋值
*/
        mapList.get(index).get(0).x = caoX;
        mapList.get(index).get(0).y = caoY;
/*
        生成地图信息 gameState
*/
        GameState state = getGamestateFromHeroPointList(mapList.get(index));

        return state;
    }
    /**
     * 功能描述: <br>
     * 〈向地图递归放入英雄〉
       参数         [index, map]
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 8:59
     */
    public void placeHero(int index,int[][] map){
/*
        超出10个英雄 或 地图生成个数大于9 退出递归
*/
        if ((index>9) || (mapCreateCount>9)){
            return;
        }
/*
        地图的每一个点尝试放入英雄
*/
        for (int i=0;i<HRD_WIDTH-2;i++) {
            for (int j=0;j<HRD_HEIGHT-2;j++) {
/*
                如果能放入英雄
*/
                if (canPlace(index,map,i,j)){
/*
                    放入英雄 并 更新当前英雄已放列表
*/
                    int[][] newMap = placeHeroToMap(index,map,i,j);
                    heroPointList.get(index).x=i;
                    heroPointList.get(index).y=j;
/*
                    10个英雄已经放完
*/
                    if (index ==9){
                        mapCreateCount++;
/*
                        生成英雄放置表，加入地图列表
*/
                        List<Point> tmpList = new ArrayList<>();
                        for (Point pt:heroPointList) {
                            Point newPt= new Point(pt.x,pt.y);
                            tmpList.add(newPt);
                        }

                        mapList.add(tmpList);
                        System.out.println("count: "+mapCreateCount);
                        System.out.println("size c:"+mapList.size());
                    }else {
/*
                        递归放入下一个英雄
*/
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
    /**
     * 功能描述: <br>
     * 〈向地图放入英雄〉
       参数         [index, map, locX, locY]
     * 返回 @return:int[][]
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:24
     */
    private int[][] placeHeroToMap(int index,int[][] map,int locX,int locY){
        int[][] tmpMap = new int[HRD_WIDTH][HRD_HEIGHT];
        for (int i=0;i<HRD_WIDTH;i++){
            for (int j=0;j<HRD_HEIGHT;j++){
                tmpMap[i][j]=map[i][j];
            }
        }

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
    /**
     * 功能描述: <br>
     * 〈英雄能否放入地图中〉
       参数         [index, map, locX, locY]
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:25
     */
    private boolean canPlace(int index,int[][] map,int locX,int locY) {
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
