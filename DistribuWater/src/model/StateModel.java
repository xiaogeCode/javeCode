package model;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: StateModel
 * Author:   xiaoge
 * Date:     2018/8/6 11:57
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class StateModel {
    private int aVol;
    private int bVol;

    public StateModel(int aVol, int bVol) {
        this.aVol = aVol;
        this.bVol = bVol;
    }

    public int getaVol() {
        return aVol;
    }

    public void setaVol(int aVol) {
        this.aVol = aVol;
    }

    public int getbVol() {
        return bVol;
    }

    public void setbVol(int bVol) {
        this.bVol = bVol;
    }

}
