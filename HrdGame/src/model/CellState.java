package model;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CellState
 * Author:   xiaoge
 * Date:     2018/8/7 14:28
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CellState {
    /*
        五种 ： 空 大正方形  横长方形 竖长方形 小方格
    */
    long value[];

    public CellState() {
        value= new long[5];
        for (int i=0;i<5;i++){
            value[i] = 0;
        }
    }

    public long[] getValue() {
        return value;
    }

    public void setValue(long[] value) {
        this.value = value;
    }
}
