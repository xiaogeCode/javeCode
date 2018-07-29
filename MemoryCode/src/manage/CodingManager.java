package manage;

import action.CodedInfoDbAction;
import model.CodedModel;

import javax.swing.*;
import java.util.Random;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CodingManager
 * Author:   xiaoge
 * Date:     2018/7/29 21:32
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CodingManager {
    public static String getStrCoding(String str){
        CodedModel model = CodedInfoDbAction.getCodeInfoByName(str);
        if (model != null){
            return model.getCodeName();
        }
        return "not found";
    }
    public static String getRandomStr(){
        String string;
        Random random = new Random();
/*
        0-109
*/
        int i =random.nextInt(110);
        if (i<10){
/*
            00-09
*/
            string ="0"+i;
        }else if (i<100){
/*
            10-99
*/
            string = Integer.toString(i);
        }else {
/*
            0-9
*/
            string = Integer.toString(i-100);
        }

        return string;
    }
}
