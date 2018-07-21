package pk.model;

import java.awt.Image;

public class PukeModel {
	public int number;				//牌面数字
	public PukeTypeEnum type;		//牌花色
	public Image pukeImage;			//牌图片
	public int value;				//牌对应的数值
	public int tag;					//1-54 代表一副牌
	public final int blackJokerNum 	= 53; //小鬼数值
	public final int redJokerNum 	= 54; //大鬼数值
}
