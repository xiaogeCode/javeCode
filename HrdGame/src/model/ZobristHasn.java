package model;

import com.sun.deploy.panel.ITreeNode;
import util.CommStringInterface;

import java.security.KeyPair;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: ZobristHasn
 * Author:   xiaoge
 * Date:     2018/8/7 14:31
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ZobristHasn implements CommStringInterface{
    CellState key[][];
    public ZobristHasn() {
        key=new CellState[HRD_WIDTH-2][HRD_HEIGHT-2];
        for (int i=0;i<HRD_WIDTH-2;i++){
            for (int k=0;k<HRD_HEIGHT-2;k++){
                CellState cellState = new CellState();
                key[i][k] = cellState;
            }
        }
    }

    public CellState[][] getKey() {
        return key;
    }

    public void setKey(CellState[][] key) {
        this.key = key;
    }

}
