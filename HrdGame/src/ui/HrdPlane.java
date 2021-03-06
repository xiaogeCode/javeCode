package ui;

import model.GameState;
import util.CommStringInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: HrdPlane
 * Author:   xiaoge
 * Date:     2018/8/11 1:10
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class HrdPlane extends JPanel implements CommStringInterface{
    GameState state;

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
        this.setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
        Graphics big =bi.getGraphics();

        Color c =big.getColor();

        for (int k=0;k<state.getHeros().size();k++){
            int x = state.getHeros().get(k).getLeft();
            int y = state.getHeros().get(k).getTop();
            int type = state.getHeros().get(k).getType();
            switch (type){
                case HERO_TYPE_ZHANGFEI:{
                    big.setColor(Color.black);
                    big.fillRect(x*frame_cute_size, y*frame_cute_size, frame_cute_size-1, frame_cute_size*2-1);
                    break;
                }
                case HERO_TYPE_CAOCAO:{
                    big.setColor(Color.green);
                    big.fillRect(x*frame_cute_size, y*frame_cute_size, frame_cute_size*2-1, frame_cute_size*2-1);
                    break;
                }
                case HERO_TYPE_GUANYU:{
                    big.setColor(Color.blue);
                    big.fillRect(x*frame_cute_size, y*frame_cute_size, frame_cute_size*2-1, frame_cute_size-1);
                    break;
                }
                case HERO_TYPE_XIAOBING:{
                    big.setColor(Color.red);
                    big.fillRect(x*frame_cute_size, y*frame_cute_size, frame_cute_size-1, frame_cute_size-1);
                    break;
                }
                default:{
                    break;
                }
            }
            big.setColor(c);
        }

        /*
        旋转输出
*/
        /*for (int i=0;i<HRD_HEIGHT;i++){
            for (int j=0;j<HRD_WIDTH;j++){
                switch (state.getMap()[j][i]){
                    case HERO_TYPE_ZHANGFEI:{
                        big.setColor(Color.green);
                        big.fillRect((j-1)*frame_cute_size, (i-1)*frame_cute_size, frame_cute_size, frame_cute_size);
                        break;
                    }
                    case HERO_TYPE_CAOCAO:{
                        big.setColor(Color.red);
                        break;
                    }
                    case HERO_TYPE_GUANYU:{
                        big.setColor(Color.blue);
                        break;
                    }
                    case HERO_TYPE_XIAOBING:{
                        big.setColor(Color.yellow);
                        break;
                    }
                    default:{
                        break;
                    }
                }
                big.fillRect((j-1)*frame_cute_size, (i-1)*frame_cute_size, frame_cute_size, frame_cute_size);
                big.setColor(c);

            }
        }*/

        g.drawImage(bi,0,0,null);
    }
}
