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
    int id;
    int fValue;
    int gValue;
    int hValur;
    Point location;
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
