package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: StateNode
 * Author:   xiaoge
 * Date:     2018/8/6 12:08
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class StateNode {
    StateModel state = null;
    List<StateNode> childs;
    StateNode parrent = null;

    public StateNode(StateModel state) {
        this.state = state;
        childs = new ArrayList<StateNode>();
        setParrent(null);
    }

    public List<StateNode> getChilds() {
        return childs;
    }

    public void setChilds(List<StateNode> childs) {
        this.childs = childs;
    }


    public StateModel getState() {
        return state;
    }

    public void setState(StateModel state) {
        this.state = state;
    }


    public StateNode getParrent() {
        return parrent;
    }

    public void setParrent(StateNode parrent) {
        this.parrent = parrent;
    }



}
