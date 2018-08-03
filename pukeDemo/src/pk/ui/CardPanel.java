package pk.ui;

import pk.model.CardModel;
import pk.model.PukeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CardPanel1
 * Author:   xiaoge
 * Date:     2018/8/3 15:11
 * Description: ${卡片容器}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CardPanel extends JPanel implements MouseListener {
    //当前卡牌展示的位置
    private int loaction_index;

    CardModel cardInfo = null;

    public CardPanel() {
        super();
        this.addMouseListener(this);
    }

    public int getLoaction_index() {
        return loaction_index;
    }

    public void setLoaction_index(int loaction_index) {
        this.loaction_index = loaction_index;
    }


    public CardModel getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardModel cardInfo) {
        this.cardInfo = cardInfo;
        resetCard();
    }

    private void resetCard() {
        if (this.cardInfo.selected){
            //this.setLocation(this.location().x, this.location().y+50);
            this.setLocation(this.getX(), 0);
        }else{
            //this.setLocation(this.location().x, this.location().y-50);
            this.setLocation(this.getX(), 50);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.cardInfo.pukeInfo.pukeImage, 0, 0, this.getWidth(), this.getHeight(), this);
        resetCard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*if (this.cardInfo.selected){
//            this.setLocation(this.location().x, this.location().y+50);
            this.setLocation(this.location().x, 100);
        }else{
//            this.setLocation(this.location().x, this.location().y-50);
            this.setLocation(this.location().x, 0);
        }*/
        this.cardInfo.selected = !this.cardInfo.selected;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
