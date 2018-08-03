package pk.model;

import java.awt.Image;

public class PukeModel {
    //牌面数字
	public int number;
    //牌花色
	public PukeTypeEnum type;
    //牌图片
	public Image pukeImage;
    //牌对应的数值
	public int value;
    //1-54 代表一副牌
	public int tag;
    //小鬼数值
	public final int blackJokerNum 	= 53;
    //大鬼数值
	public final int redJokerNum 	= 54;
}
