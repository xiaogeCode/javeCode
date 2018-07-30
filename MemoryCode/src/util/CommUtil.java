package util;

import javax.swing.*;
import java.net.URL;

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
public class CommUtil {
    public static String getImageUrl(String str){
        URL url = CommUtil.class.getResource("../pic/"+str);
        return url.getPath();
    }
    public static String getImageUrl2(String str) {
        String userDir = System.getProperty("user.dir");// 获得真实路径
        String se = System.getProperty("file.separator"); // 获得文件分隔符（在 UNIX 系统中是"/"）
        String url = userDir+se+"src"+se+"pic"+se+str;
        ImageIcon icon = new ImageIcon(url);
        if(icon.getImage().getWidth(null)<0) {
            return null; //如果未找到图片则返回空
        }
        return url;
    }
    public static String getDbUrl(String str){
        String userDir = System.getProperty("user.dir");// 获得真实路径
        String se = System.getProperty("file.separator"); // 获得文件分隔符（在 UNIX 系统中是"/"）
        String url = userDir+se+"src"+se+"db"+se+str;
        return url;
    }
}
