package model;

import java.awt.*;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CellNode
 * Author:   xiaoge
 * Date:     2018/8/16 18:01
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CellNode {
/*
    节点id ,方便查找节点
*/
    int id;

/*
    每个方块的和值（我们将它称为F，等于G+H)
*/
    int fValue;

/*
    G是从开始点A到当前方块的移动量。
    所以从开始点A到相邻小方块的移动量为1，该值会随着离开始点越来越远而增大
*/
    int gValue;

/*
    H是从当前方块到目标点（我们把它称为点B，代表骨头！）的移动量估算值。
    这个常被称为探视，因为我们不确定移动量是多少 – 仅仅是一个估算值。
*/
    int hValur;
/*
    节点位置
*/
    Point location;
/*
    节点父类
*/
    CellNode parrent;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getfValue() {
        return fValue;
    }

    public void setfValue(int fValue) {
        this.fValue = fValue;
    }

    public int getgValue() {
        return gValue;
    }

    public void setgValue(int gValue) {
        this.gValue = gValue;
    }

    public int gethValur() {
        return hValur;
    }

    public void sethValur(int hValur) {
        this.hValur = hValur;
    }

    public CellNode getParrent() {
        return parrent;
    }

    public void setParrent(CellNode parrent) {
        this.parrent = parrent;
    }
}
