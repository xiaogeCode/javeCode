package xiaoge;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Copyright (C), 2021, xiaoge
 * FileName: Main
 * Author:   lin_yunhua
 * Date:     2021/3/23 3:56
 * Description: 斗地主
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class Main {
    public static void main(String[] args) {
        //定义一副牌
        ArrayList<String> poker = new ArrayList<String>();
        //定义花色
        //String[] colors = {"黑桃♠", "红桃v", "草花♣", "方片♦"};
        String[] colors = {"♠", "v", "♣", "♦"};
        //定义牌面
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        //形成一副牌
        poker.add("大王");
        poker.add("小王");
        for (String color : colors) {
            for (String number : numbers) {
                poker.add(color + number);
            }
        }
        System.out.println("洗牌前： "+poker);

        //定义三个玩家和底牌
        ArrayList<String> gameer01 = new ArrayList<>();
        ArrayList<String> gameer02 = new ArrayList<>();
        ArrayList<String> gameer03 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();

        //洗牌
        Collections.shuffle(poker);
        System.out.println("洗牌后： "+poker);
        //发牌
        for (int i = 0; i < 54; i++) {
            //3张底牌
            if (i>=51){
                dipai.add(poker.get(i));
            }else{
                int k = i%3;
                switch (k){
                    //玩家1 发牌
                    case 0:{
                        gameer01.add(poker.get(i));
                        break;
                    }
                    //玩家2发牌
                    case 1:{
                        gameer02.add(poker.get(i));
                        break;
                    }
                    //玩家3发牌
                    default:{
                        gameer03.add(poker.get(i));
                        break;
                    }
                }
            }
        }

        //打印最终信息
        System.out.println("玩家1： "+gameer01);
        System.out.println("玩家2： "+gameer02);
        System.out.println("玩家3： "+gameer03);
        System.out.println("底牌 ： "+dipai);

    }
}
