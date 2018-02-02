package Yj.util;
import Yj.model.*;
public class CommonUtil {
	  private static CommonUtil comUtil = new CommonUtil();
	  public static CommonUtil getCommonUtil()
	  {
		  try
		    {
		    }
		    catch (Exception e)
		    {
		      System.exit(0);
		    }
	    return comUtil;
	  }
	  
	  //根据数值生成对应的爻
	  public YaoModel getYaoByValue(int value) {
		//设置爻的类型，名称
		//0阳 奇数为阳:一    			1阴 偶数为阴：--
		  YaoModel yao= new YaoModel();
		  yao.setValue(value);
		  int va = yao.getValue()%2;
			switch(va){
			case 1:
			{
				yao.setName("阳");
				yao.setType(0);
			}
				break;
			case 0:
			{
				yao.setName("阴");
				yao.setType(1);
			}
			break;
			default:
				break;
			}
		  
		return yao;
	}
	  //获取64卦的值
	  public int getLiuSiGuaValue(LiuSiGuaModel lsgua) {
		  int sgValue = BaGuaNames.getIndex(lsgua.getShangGuaName());
		  int xgValue = BaGuaNames.getIndex(lsgua.getXiaGuaName());
		  return sgValue+xgValue*8;
	  }
	  //三个爻生成一个八卦
	  public BaGuaModel getBagua(YaoModel yan1,YaoModel yan2,YaoModel yan3) {
		BaGuaModel bg = new BaGuaModel();
		System.out.println("value:"+yan1.getType()+yan2.getType()+yan3.getType());
		//yan1,yan2,yan3  从高位到低位
		bg.setValue(yan3.getType()*1+yan2.getType()*2+yan1.getType()*4);
		bg.setName(BaGuaNames.getName(bg.getValue()));
		if (bg.getName().equals("坎")) {
			bg.setName("坎");
		}
		if (bg.getName().equals("震")) {
			bg.setName("震");
		}
		return bg;
	}
	  //俩个八卦合成一个64卦
	  public LiuSiGuaModel getLiuSigua(BaGuaModel xiagua,BaGuaModel shanggua) {
		LiuSiGuaModel ls = new 	LiuSiGuaModel();
		ls.setValue(xiagua.getValue()*8 + shanggua.getValue());
		ls.setShangGuaName(shanggua.getName());
		ls.setXiaGuaName(xiagua.getName());
		return ls;
	}

}
