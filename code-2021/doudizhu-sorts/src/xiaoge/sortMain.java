package xiaoge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Copyright (C), 2021, xiaoge
 * FileName: sortMain
 * Author:   lin_yunhua
 * Date:     2021/3/23 6:38
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class sortMain {
    public static void main(String[] args) {
        //定义一幅牌的map
        HashMap<Integer,String> pokerMap = new HashMap<>();
        //定义一幅牌的索引list
        ArrayList<Integer> pokerIndexs = new ArrayList<>();
        //定义花色和数值
        String[] colors = {"♠", "v", "♣", "♦"};
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};

        //将牌放入map里
        Integer index = 0;
        //放入大王
        pokerMap.put(index,"大王");
        pokerIndexs.add(index);
        index++;

        //放入小王
        pokerMap.put(index,"小王");
        pokerIndexs.add(index);
        index++;

        //其他牌按顺序放入map
        for (String number : numbers) {
            for (String color : colors) {
                pokerMap.put(index,color+number);
                pokerIndexs.add(index);
                index++;
            }
        }
        System.out.println(pokerMap);

        //洗牌
        Collections.shuffle(pokerIndexs);
        System.out.println(pokerIndexs);

        ArrayList<Integer> player01= new ArrayList<>();
        ArrayList<Integer> player02= new ArrayList<>();
        ArrayList<Integer> player03= new ArrayList<>();
        ArrayList<Integer> dipai= new ArrayList<>();

        //发牌
        for (Integer i=0;i<pokerIndexs.size();i++){
            Integer in = pokerIndexs.get(i);
            Integer k = i%3;
            if (i>=51){
                dipai.add(in);
            }else if (k==0){
                //给玩家1发牌
                player01.add(in);
            }

            else if (k==1){
                //给玩家1发牌
                player02.add(in);
            }
            else if (k==2){
                //给玩家2发牌
                player03.add(in);
            }
        }
        //玩家牌排序
        Collections.sort(player01);
        Collections.sort(player02);
        Collections.sort(player03);
        Collections.sort(dipai);

        //展示牌
        showPoker("老王",pokerMap,player01);
        showPoker("老李",pokerMap,player02);
        showPoker("老孙",pokerMap,player03);
        showPoker("底牌",pokerMap,dipai);

    }
    /*
     * 函数名:〈showPoker〉
     * 功能简述:打印玩家的牌
     * name:玩家名 map：扑克索引表 list:玩家的牌
     * 参数: [name, map, list]
     * 返回: void
     * 作者: lin_yunhua
     * 时间: 2021/3/23  7:18
     */
    public static void showPoker(String name,HashMap<Integer,String> map,ArrayList list){
        System.out.print(name+": ");
        for (Object key : list) {
            System.out.print(map.get(key)+" ");
        }
        System.out.println();
    }
}
