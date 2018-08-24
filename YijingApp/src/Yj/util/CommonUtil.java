package Yj.util;
import Yj.model.*;

import java.awt.datatransfer.StringSelection;
import java.util.Locale;
import java.util.ResourceBundle;

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
	/**
	 * 功能描述: <br>
	 * 〈语言国际化〉
	   参数         [str, type]
	 * 返回 @return:java.lang.String
	 * 作者 @Author:xiaoge
	 * 时间 @Date: 2018/7/28 12:26
	 */
	public String getLanguageString(String str, int type){
        ResourceBundle localResource;
        switch (type) {
            case 0:{
                Locale local=new Locale("en","US");
                //Locale local  = Locale.getDefault();
                localResource = ResourceBundle.getBundle("language",local);
                break;
            }
            case 1:
            default:{
                Locale local=new Locale("zh","CN");
                localResource = ResourceBundle.getBundle("language",local);
                break;
            }

        }
        String val = localResource.getString(str);

	  	return val;
	}
	public String getDbUrl(String str){
		String userDir = System.getProperty("user.dir");// 获得真实路径
		String se = System.getProperty("file.separator"); // 获得文件分隔符（在 UNIX 系统中是"/"）
		String url = userDir+se+"src"+se+"db"+se+str;
		return url;
	}

    public String getImagUrl(String str) {
        String userDir = System.getProperty("user.dir");// 获得真实路径
        String se = System.getProperty("file.separator"); // 获得文件分隔符（在 UNIX 系统中是"/"）
        String url = userDir+se+"src"+se+"image"+se+str;
        return url;
    }
}
