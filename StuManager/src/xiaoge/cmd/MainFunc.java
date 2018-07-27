package xiaoge.cmd;

import xiaoge.action.GoddessAction;
import xiaoge.model.Goddess;

import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MainFunc
 * Author:   xiaoge
 * Date:     2018/7/27 15:49
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MainFunc {
    public static void main(String[] args) {
        /*
		GoddessDao goddessDao = new GoddessDao();

	    List<Goddess> goddessList = goddessDao.query();

	    for (Goddess goddess : goddessList)
	    {
	      System.out.println(goddess.getName() + "," + goddess.getMobie() + "," + goddess.getEmail());
	    }
	    */
        Goddess gd = new Goddess();
        GoddessAction ac = new GoddessAction();
        try{
            //ac.addTable();
            //ac.add(gd);
            List<Goddess> godList = null;
            godList = ac.query();
            System.out.println("count :"+godList.size());
            //Goddess god = null;
            for(int i=0;i<godList.size();i++){
                gd = godList.get(i);
                System.out.println("name"+gd.getName());
            }
            ac.edit(gd);
            gd = ac.get(1);
            if(gd!=null){
                System.out.println("name"+gd.getName());
            }


            //ac.del(1);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
