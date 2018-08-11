package model;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MoveAction
 * Author:   xiaoge
 * Date:     2018/8/7 14:17
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MoveAction {
/*
    移动的人物编号 和 方向编号
*/
    int heroIndex;
    int dirIndex;

    public int getHeroIndex() {
        return heroIndex;
    }

    public void setHeroIndex(int heroIndex) {
        this.heroIndex = heroIndex;
    }

    public int getDirIndex() {
        return dirIndex;
    }

    public void setDirIndex(int dirIndex) {
        this.dirIndex = dirIndex;
    }

}
