package Manager;


import model.*;
import util.CommStringInterface;

import java.util.List;
import java.util.Random;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: ZobristHashMgr
 * Author:   xiaoge
 * Date:     2018/8/7 14:41
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ZobristHashMgr implements CommStringInterface{
/**
 * 功能描述: <br>
 * 〈生成ZobristHasn基本数组〉
   参数         []
 * 返回 @return:model.ZobristHasn
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/12 9:27
 */
    public static ZobristHasn MakebaseZobHash(){
        ZobristHasn hash = new ZobristHasn();
        for (int i=0;i<hash.getKey().length;i++){
            for (int j=0;j<hash.getKey()[0].length;j++){
                CellState tmp = hash.getKey()[i][j];
                for (int k = 0;k<5;k++){
                    Random rand = new Random();
                    long result = rand.nextLong();

//                    int[] para =new int[9];
//                    for (int p=0;p<9;p++){
//                        int randNum = rand.nextInt(9)+1;
//                        para[p]=randNum;
//                    }
//                    for (int index = 0; index < 9; index++) {
//                        result = result * 10 + para[index];
//                    }

                    tmp.getValue()[k]=result;
                }
            }
        }
        System.out.println("hash base create");

        return hash;
    }
/**
 * 功能描述: <br>
 * 〈获取指定地图的ZobristHasn编码〉
   参数         [zobhash, state]
 * 返回 @return:int
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/12 9:27
 */
    public static int getZobrishHash(ZobristHasn zobhash, GameState state){
        int hash = 0;
         List<Warrior> heros = state.getHeros();
         for (int i=1;i<HRD_WIDTH-1;i++){
             for (int j=1;j<HRD_HEIGHT-1;j++){
//                 int index = state.getMap()[i][j];
//                 int type = heros.get(index).getType();
                 int type = state.getMap()[i][j];
                 hash ^=zobhash.getKey()[i-1][j-1].getValue()[type];
             }
         }
         return hash;
     }
     /**
      * 功能描述: <br>
      * 〈获取指定地图的ZobristHasn镜像编码〉
        参数         [zobhash, state]
      * 返回 @return:int
      * 作者 @Author:xiaoge
      * 时间 @Date: 2018/8/12 9:28
      */
    int getMirrorZobrishHash(ZobristHasn zobhash, GameState state){
        int hash = 0;
        List<Warrior> heros = state.getHeros();
        for (int i=1;i<HRD_WIDTH-1;i++){
            for (int j=1;j<HRD_HEIGHT-1;j++){
                int index = state.getMap()[i][j];
                int type = heros.get(index).getType();
                //hash ^=zobhash.getKey()[i-1][j-1].getValue()[type];
                hash ^=zobhash.getKey()[i-1][HRD_HEIGHT-j].getValue()[type];
            }
        }
        return hash;
    }
    /**
     * 功能描述: <br>
     * 〈更新ZobristHasn编码〉
       参数         [zobhash, state, heroIdx, dirIdx]
     * 返回 @return:int
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/12 9:29
     */
    int getZobrishHashUpdate(ZobristHasn zobhash, GameState state,int heroIdx,int dirIdx){
        int hash = 0;
        Warrior hero = state.getHeros().get(heroIdx);
        Direction direction = DIRECTIONS[dirIdx];

        switch (hero.getType()){
            case HERO_TYPE_ZHANGFEI:{
                //原位置的处理
                hash ^= zobhash.getKey()[hero.getLeft()][hero.getTop()].getValue()[hero.getType()];
                hash ^= zobhash.getKey()[hero.getLeft()][hero.getTop()+1].getValue()[hero.getType()];
                hash ^= zobhash.getKey()[hero.getLeft()][hero.getTop()].getValue()[0];//0是空状态
                hash ^= zobhash.getKey()[hero.getLeft()][hero.getTop()+1].getValue()[0];

                //新位置的处理
                hash ^= zobhash.getKey()[hero.getLeft()+direction.getHd()][hero.getTop()+direction.getVd()].getValue()[0];//0是空状态
                hash ^= zobhash.getKey()[hero.getLeft()+direction.getHd()][hero.getTop()+direction.getVd()+1].getValue()[0];//0是空状态
                hash ^= zobhash.getKey()[hero.getLeft()+direction.getHd()][hero.getTop()+direction.getVd()].getValue()[hero.getType()];
                hash ^= zobhash.getKey()[hero.getLeft()+direction.getHd()][hero.getTop()+direction.getVd()+1].getValue()[hero.getType()];
                break;
            }
            case HERO_TYPE_CAOCAO:{
                break;
            }
            case HERO_TYPE_GUANYU:{
                break;
            }
            case HERO_TYPE_XIAOBING:{
                break;
            }
            default:{
                break;
            }

        }
        return hash;
    }
}
