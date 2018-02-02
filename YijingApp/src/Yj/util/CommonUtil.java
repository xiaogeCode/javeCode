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
	  
	  //������ֵ���ɶ�Ӧ��س
	  public YaoModel getYaoByValue(int value) {
		//����س�����ͣ�����
		//0�� ����Ϊ��:һ    			1�� ż��Ϊ����--
		  YaoModel yao= new YaoModel();
		  yao.setValue(value);
		  int va = yao.getValue()%2;
			switch(va){
			case 1:
			{
				yao.setName("��");
				yao.setType(0);
			}
				break;
			case 0:
			{
				yao.setName("��");
				yao.setType(1);
			}
			break;
			default:
				break;
			}
		  
		return yao;
	}
	  //��ȡ64�Ե�ֵ
	  public int getLiuSiGuaValue(LiuSiGuaModel lsgua) {
		  int sgValue = BaGuaNames.getIndex(lsgua.getShangGuaName());
		  int xgValue = BaGuaNames.getIndex(lsgua.getXiaGuaName());
		  return sgValue+xgValue*8;
	  }
	  //����س����һ������
	  public BaGuaModel getBagua(YaoModel yan1,YaoModel yan2,YaoModel yan3) {
		BaGuaModel bg = new BaGuaModel();
		System.out.println("value:"+yan1.getType()+yan2.getType()+yan3.getType());
		//yan1,yan2,yan3  �Ӹ�λ����λ
		bg.setValue(yan3.getType()*1+yan2.getType()*2+yan1.getType()*4);
		bg.setName(BaGuaNames.getName(bg.getValue()));
		if (bg.getName().equals("��")) {
			bg.setName("��");
		}
		if (bg.getName().equals("��")) {
			bg.setName("��");
		}
		return bg;
	}
	  //�������Ժϳ�һ��64��
	  public LiuSiGuaModel getLiuSigua(BaGuaModel xiagua,BaGuaModel shanggua) {
		LiuSiGuaModel ls = new 	LiuSiGuaModel();
		ls.setValue(xiagua.getValue()*8 + shanggua.getValue());
		ls.setShangGuaName(shanggua.getName());
		ls.setXiaGuaName(xiagua.getName());
		return ls;
	}

}
