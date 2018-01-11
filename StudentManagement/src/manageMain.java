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
			ac.add(gd);
		}catch (Exception e) {
			   e.printStackTrace();
		  }
		
	}

}
