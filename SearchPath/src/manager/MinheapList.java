package manager;

import model.CellNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MinheapList
 * Author:   xiaoge
 * Date:     2018/8/16 18:00
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MinheapList {
    List<CellNode> list;

    public MinheapList() {
        list= new ArrayList<>();
    }

    public List<CellNode> getList() {
        return list;
    }

    public void addItem(CellNode node) {
        list.add(node);
        if (list.size() > 1) {
            for (int i = list.size()-1;i>0;i--) {
                //大数往前压
                if (list.get(i).getfValue() > list.get(i - 1).getfValue()) {
                    CellNode tmp = list.get(i-1);
                    list.set(i-1,list.get(i));
                    list.set(i,tmp);
                }
            }
        }
    }
    public void deleteLastItem() {
        if (list.size() > 0) {
            list.remove(list.size()-1);
        }

    }
    public void deleteItemById(int id) {
        Iterator<CellNode> it = list.iterator();
        while(it.hasNext()){
            CellNode node = it.next();
            if (node.getId() == id) {
                it.remove();
                return;
            }
        }

    }
    public CellNode getLastItem(){
        if (list.size() > 0) {
            return list.get(list.size()-1);
        }
        return null;
    }
    public CellNode getItemById(int id) {
        for (CellNode tmp:list) {
            if (tmp.getId() == id) {
                return tmp;
            }
        }
        return null;
    }
}
