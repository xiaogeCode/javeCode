package xiaoge.action;

import java.sql.SQLException;
import java.util.List;
 
import xiaoge.dao.GoddessDao;
import xiaoge.model.Goddess;
 
/**
 * ���Ʋ㣬ֱ�������ﹹ�����ݣ������������ͨ�����󴫵ݽ��ռ��ɣ�����ͬ��
 * 
 * @author AlanLee
 * 
 */
public class GoddessAction
{
	public void addTable() throws Exception
	  {
	    GoddessDao dao = new GoddessDao();
	    //if(!dao.TableIsExist("Godness")){
	    	System.out.print("create table");
	    	dao.createGoddessTable();
	   // }
	    
	  }
  /**
   * ����Ů��
   * 
   * @param goddess
   * @throws Exception
   */
  public void add(Goddess goddess) throws Exception
  {
    GoddessDao dao = new GoddessDao();
    goddess.setName("��ԲԲ");
    goddess.setMobie("52220000");
    goddess.setEmail("52220000@qq.com");
    goddess.setAddress("�����찲��");
    dao.addGoddess(goddess);
  }
 
  /**
   * ��ѯ����Ů��
   * 
   * @param id
   * @return
   * @throws SQLException
   */
  public Goddess get(Integer id) throws SQLException
  {
    GoddessDao dao = new GoddessDao();
    return dao.queryById(id);
  }
 
  /**
   * �޸�Ů��
   * 
   * @param goddess
   * @throws Exception
   */
  public void edit(Goddess goddess) throws Exception
  {
    GoddessDao dao = new GoddessDao();
    dao.updateGoddess(goddess);
  }
 
  /**
   * ɾ��Ů��
   * 
   * @param id
   * @throws SQLException
   */
  public void del(Integer id) throws SQLException
  {
    GoddessDao dao = new GoddessDao();
    dao.deleteGoddess(id);
  }
 
  /**
   * ��ѯȫ��Ů��
   * 
   * @return
   * @throws Exception
   */
  public List<Goddess> query() throws Exception
  {
    GoddessDao dao = new GoddessDao();
    return dao.query();
  }
 
  /**
   * �����Ƿ�ɹ�
   * 
   * @param args
   * @throws SQLException
   */
  /*
  public static void main(String[] args) throws SQLException
  {
    GoddessDao goddessDao = new GoddessDao();
 
    List<Goddess> goddessList = goddessDao.query();
 
    for (Goddess goddess : goddessList)
    {
      System.out.println(goddess.getName() + "," + goddess.getMobie() + "," + goddess.getEmail());
    }
  }
  */
}