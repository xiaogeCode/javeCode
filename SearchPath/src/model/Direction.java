package model;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: Direction
 * Author:   xiaoge
 * Date:     2018/8/16 19:01
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class Direction {
    /*
    方向偏移量 横偏移 hd 竖偏移 vd
*/
    int hd;
    int vd;

    public Direction(int hd, int vd) {
        this.hd = hd;
        this.vd = vd;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public int getVd() {
        return vd;
    }

    public void setVd(int vd) {
        this.vd = vd;
    }
}
