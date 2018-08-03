package cmd;

import model.CodedModel;
import ui.MainFrame;
import util.CommUtil;

import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MainFunc
 * Author:   xiaoge
 * Date:     2018/7/29 13:29
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MainFunc {
    public static void main(String[] args) {
//        List<CodedModel> list = CodedInfoDbAction.getCodedList();
        new MainFrame();
        System.out.println("aa");
    }
    public static void addToDatabase(){
        String astr = "仪器，要发，药酒，" +
                "耳饰，鳄鱼，鸳鸯，耳塞，耳屎，二胡，二流子，阿嚏，恶霸，阿胶，" +
                "森林，鲨鱼，扇娥，二把伞，绅士，珊瑚，山鹿，山鸡，妇女，三角尺，" +
                "司令，司仪，柿儿，死火山，石狮子，师傅，石榴，司机，石板，死狗，" +
                "武林，工人，普洱，武僧，巫师，火车，蜗牛，武器，尾巴，无药可救，" +
                "榴莲，儿童，卤鹅，庐山，硫酸，锣鼓，辘轳，楼梯，喇叭，漏斗，" +
                "麒麟，奇异果，企鹅，青山，骑士，起雾，气流，七夕，青蛙，气球，" +
                "巴黎，白蚁，叭儿狗，爬山，巴士，白虎，八路，白旗，拜拜，芭蕉，" +
                "精灵，旧衣，揪耳朵，救生圈，救世主，酒壶，酒楼，香港，酒吧，救护车";
        String astr2 = "鸡蛋，铅笔，鸭子，耳朵，旗子，钩子，哨子，镰刀，麻花，蝌蚪" ;
        String astr3 = "火箭，屁股，小锅，弓，梳子，扳手，大镰刀，梯子，骨头，鱼钩，机枪，锄头，山峰，门，" +
                "球，旗帜，牵气球，鸟，蛇，手杖，杯子，领子，灯丝，剪刀，叉子，楼梯";
        char as = 'a';
        String[] sArray=astr3.split("，");
        int index = 0;
        for (String aSArray : sArray) {
            CodedModel model = new CodedModel();
//            model.setName(Integer.toString(index));
            model.setName(String.valueOf(as));
            model.setCodeName(aSArray);
            model.setPicUrl("www.baidu.com");
            System.out.println("name: "+model.getName()+" codeName: "+model.getCodeName());
//            CodedInfoDbAction.addCodeInfo(model);
            as++;
        }
    }


}
