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
	  

	  public YaoModel getYaoByValue(int value) {
		  YaoModel yao= new YaoModel();
		  yao.setValue(value);
		  int va = yao.getValue()%2;
			switch(va){
			case 1:
			{
				yao.setName("阴");
				yao.setType(0);
			}
				break;
			case 0:
			{
				yao.setName("阳");
				yao.setType(1);
			}
			break;
			default:
				break;
			}
		  
		return yao;
	}

	  public int getLiuSiGuaValue(LiuSiGuaModel lsgua) {
		  int sgValue = BaGuaNames.getIndex(lsgua.getShangGuaName());
		  int xgValue = BaGuaNames.getIndex(lsgua.getXiaGuaName());
		  return sgValue+xgValue*8;
	  }

	  public BaGuaModel getBagua(YaoModel yan1,YaoModel yan2,YaoModel yan3) {
		BaGuaModel bg = new BaGuaModel();
		System.out.println("value:"+yan1.getType()+yan2.getType()+yan3.getType());
		//yan1,yan2,yan3
		bg.setValue(yan3.getType()*1+yan2.getType()*2+yan1.getType()*4);
		bg.setName(BaGuaNames.getName(bg.getValue()));

		return bg;
	}

	  public LiuSiGuaModel getLiuSigua(BaGuaModel xiagua,BaGuaModel shanggua) {
		LiuSiGuaModel ls = new 	LiuSiGuaModel();
		ls.setValue(xiagua.getValue()*8 + shanggua.getValue());
		ls.setShangGuaName(shanggua.getName());
		ls.setXiaGuaName(xiagua.getName());
		return ls;
	}

}
