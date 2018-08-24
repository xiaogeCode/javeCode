package russiaCuteGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: GamePanel
 * Author:   xiaoge
 * Date:     2018/8/25 0:37
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class GamePanel extends JPanel {
    private int mapWidth, mapHeight, cuteLen;

    private int currentX;
    private int currentY;
    private int[][] currentCuteArray;
    private int[][] nextCuteArray;
    private int[][] mapArray;
    private int score=0;

    public GamePanel(int width,int heigh) {
        mapWidth = width;
        mapHeight = heigh;
        cuteLen = 30;

        currentX =(width-4)/2;
        currentY =0;

        currentCuteArray = new int[4][4];
        nextCuteArray = new int[4][4];
    }
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
    public void setCurrentCuteArray(int[][] currentCuteArray) {
        this.currentCuteArray = currentCuteArray;
    }

    public void setNextCuteArray(int[][] nextCuteArray) {
        this.nextCuteArray = nextCuteArray;
    }

    public void setMapArray(int[][] mapArray) {
        this.mapArray = mapArray;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
        Graphics big =bi.getGraphics();

        for(int i = 0; i< mapWidth; i++){
            for(int j = 0; j< mapHeight; j++){
                if(mapArray[i][j]==1){

                    Color c =big.getColor();
                    big.setColor(Color.red);
                    big.fillRect(i*(cuteLen +1), j*(cuteLen +1), cuteLen, cuteLen);
                    big.setColor(c);
                }

            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(currentCuteArray[i][j]==1){
                    Color c =big.getColor();
                    big.setColor(Color.red);
                    big.fillRect((i+ currentX)*(cuteLen +1), (j+ currentY)*(cuteLen +1), cuteLen, cuteLen);
                    big.setColor(c);
                }

            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(nextCuteArray[i][j]==1){

                    Color c =big.getColor();
                    big.setColor(Color.blue);
                    big.fillRect((i+ mapWidth +1)*(cuteLen +1), (j+1)*(cuteLen +1)+10, cuteLen, cuteLen);
                    big.setColor(c);
                }

            }
        }

        Color c =big.getColor();
        big.setColor(Color.green);
        big.fillRect((mapWidth)*(cuteLen +1), 0, 2, (mapHeight +1)*(cuteLen +1));
        big.setColor(c);


        big.drawString("score:   "+score, (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*0);
        big.drawString("ENTER：重新开始ʼ" , (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*1);
        big.drawString("	A：左移 ", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*2);
        big.drawString("	D：右移动", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*3);
        big.drawString("	S：快速向下", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*4);
        big.drawString("SPACE：翻转 ", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*5);
        big.drawString("	W：暂停/开始" , (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*6);

        g.drawImage(bi,0,0,null);
    }
}
