package model;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: TagWarrior
 * Author:   xiaoge
 * Date:     2018/8/7 14:12
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class Warrior {
/*
    type四种，大正方形 竖长方形 横长方形 小方形
*/
    int type;
/*
    左上角坐标 x:left y:top
*/
    int left;
    int top;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }


}
