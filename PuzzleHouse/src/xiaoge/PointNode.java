package xiaoge;

import java.awt.*;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: PointNode
 * Author:   xiaoge
 * Date:     2018/8/6 16:49
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class PointNode {
    Point item;
    PointNode parrent;

    public PointNode(Point item) {
        this.item = item;
        this.parrent = null;
    }

    public Point getItem() {
        return item;
    }

    public void setItem(Point item) {
        this.item = item;
    }

    public PointNode getParrent() {
        return parrent;
    }

    public void setParrent(PointNode parrent) {
        this.parrent = parrent;
    }
}
