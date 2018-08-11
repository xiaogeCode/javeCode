package util;

import model.Direction;


/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CommStringInterface
 * Author:   xiaoge
 * Date:     2018/8/7 14:06
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface CommStringInterface {
    public final int HRD_WIDTH = 6;
    public final int HRD_HEIGHT = 7;
    public final int Max_MOVE_DIR = 4;
    /*up ritht down left*/
    public final Direction[] DIRECTIONS = {new Direction(0,-1),new Direction(1,0),new Direction(0,1),new Direction(-1,0)};
    public final int HERO_TYPE_CAOCAO = 1;
    public final int HERO_TYPE_GUANYU = 2;
    public final int HERO_TYPE_ZHANGFEI = 3;
    public final int HERO_TYPE_XIAOBING = 4;

    public final int frame_cute_size = 100;

    public final int hash_type_alreay_handle = 0;
    public final int hash_type_prepare_handle =1;
}

