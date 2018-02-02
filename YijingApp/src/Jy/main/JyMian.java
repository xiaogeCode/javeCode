package Jy.main;

import java.util.List;

import Yj.ui.*;
import Yj.util.*;
import Yj.model.*;
import Yj.action.*;
public class JyMian {

	public static void main(String[] args) throws Exception {
		
		//new LoginView();
		new ManageView();
		/*
		CommonUtil comUtil = CommonUtil.getCommonUtil();
		//comUtil.setYaoType(new YaoModel());	
		LiuSiGuaModel gua = new LiuSiGuaModel();
		BaguaAction action= new BaguaAction();
		//action.addTable();
		//action.add(gua);
		List<LiuSiGuaModel> godList = null;
		godList = action.query();
		System.out.println("count :"+godList.size());
		//Goddess god = null;
		for(int i=0;i<godList.size();i++){
			gua = godList.get(i);
			int value = comUtil.getLiuSiGuaValue(gua);
			//gua.setValue(value);
			//action.edit(gua);
			System.out.println("name:"+gua.getName()+"  shanggua:"+gua.getShangGuaName()+"   xiagua:"+gua.getXiaGuaName()+" value:"+value);
		}
		*/
	}
}
