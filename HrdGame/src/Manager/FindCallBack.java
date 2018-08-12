package Manager;

import model.GameState;

import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: FindCallBack
 * Author:   xiaoge
 * Date:     2018/8/10 23:39
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface FindCallBack {
/*
    搜索到解决方案接口
*/
    public void findPath(List<GameState> list);
}
