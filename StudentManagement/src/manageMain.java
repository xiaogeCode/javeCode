import java.util.List;

import xiaoge.dao.GoddessDao;
import xiaoge.model.Goddess;

import xiaoge.action.*;
import xiaoge.model.*;
import java.sql.SQLException;

public class manageMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub

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
