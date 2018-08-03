package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CardListPanel
 * Author:   xiaoge
 * Date:     2018/8/3 12:50
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CardListPanel extends JLayeredPane {
    private List<CardPanel> panelList = new ArrayList<CardPanel>();

    public List<CardPanel> getPanelList() {
        return panelList;
    }

    public void setPanelList(List<CardPanel> panelList) {
        this.panelList = panelList;
    }
    public void showPanel(){
        for (CardPanel cp:panelList) {
            this.add(cp,new Integer(cp.getLoaction_index()));
        }
        this.repaint();
    }

}
