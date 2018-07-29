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
        0-135
*/
        int i =random.nextInt(136);
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
        }else if(i<110){
/*
            0-9
*/
            string = Integer.toString(i-100);
        }else if (i<136){
            char ch='a';
            int count = i-110;
            for (int k=0;k<count;k++){
                ch++;
            }
            string =String.valueOf(ch);

        }else{
            string = Integer.toString(i);
        }

        return string;
    }
}
