package util;

import javax.swing.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CommUtil
 * Author:   xiaoge
 * Date:     2018/7/29 23:13
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CommUtil implements CommInterface{
    public static String getImageUrl(String str){
        //URL url = CommUtil.class.getResource("../pic/"+str);
        return CommUtil.class.getResource("/pic/"+str).getPath();
    }
    public static String getImageUrl2(String str) {
        // 获得真实路径
        String userDir = System.getProperty("user.dir");
        // 获得文件分隔符（在 UNIX 系统中是"/"）
        String se = System.getProperty("file.separator");
        String url = userDir+se+"src"+se+"pic"+se+str;
        ImageIcon icon = new ImageIcon(url);
        if(icon.getImage().getWidth(null)<0) {
            //如果未找到图片则返回空
            return null;
        }
//        url.replaceAll("\\\\", "/");
        return url;
    }
    public static String getDbUrl(String str){
        URL url = CommUtil.class.getResource("/db/"+str);
        return url.getPath();
    }
    public static String getDbUrl2(String str){
        // 获得真实路径
        String userDir = System.getProperty("user.dir");
        // 获得文件分隔符（在 UNIX 系统中是"/"）
        String se = System.getProperty("file.separator");
        return userDir+se+"src"+se+"db"+se+str;
    }
    /**
     * 功能描述: <br>
     * 〈语言国际化〉
     参数         [str, type]
     * 返回 @return:java.lang.String
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/28 12:26
     */
    public static String getLanguageString(String str, int type){
        ResourceBundle localResource;
        switch (type) {
            case language_type_en:{
                Locale local=new Locale("en","US");
                localResource = ResourceBundle.getBundle("language",local);
                break;
            }
            case language_type_zh:
            default:{
                Locale local=new Locale("zh","CN");
                localResource = ResourceBundle.getBundle("language",local);
                break;
            }

        }
        return localResource.getString(str);
    }
}
