package cmd;

import manager.MinheapList;
import model.CellNode;
import ui.GameFrame;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MainFunc
 * Author:   xiaoge
 * Date:     2018/8/16 17:59
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MainFunc {
    public static void main(String[] args) {
        System.out.println("text");
        MinheapList list = new MinheapList();
        int i=0;
        while (i <5) {
            CellNode tmp = new CellNode();
            tmp.setfValue(i);
            list.addItem(tmp);
            i++;
        }
        for (CellNode tmp:list.getList()) {
            System.out.println(tmp.getfValue());
        }
        CellNode last = list.getLastItem();
        list.deleteLastItem();
        System.out.println(last);
        System.out.println(last.getfValue());
        new GameFrame();
    }
}

