package util;

import model.Direction;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CommStringInterface
 * Author:   xiaoge
 * Date:     2018/8/16 19:00
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface CommStringInterface {
    public final int MAP_WIDTH = 20;
    public final int MAP_HEIGHT = 15;
    public final int frame_cute_size = 50;

    /*up ritht down left*/
    public final Direction[] DIRECTIONS = {new Direction(0,-1),new Direction(1,0),new Direction(0,1),new Direction(-1,0)};
    public enum spaceType{
        space,
        block
    }
}
