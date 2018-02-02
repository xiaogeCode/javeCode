package Yj.action;

import java.sql.SQLException;
import java.util.List;
 
import Yj.dao.BaguaDao;
import Yj.model.*;
import Yj.util.*;
 
/**
 * ���Ʋ㣬ֱ�������ﹹ�����ݣ������������ͨ�����󴫵ݽ��ռ��ɣ�����ͬ��
 * 
 * @author AlanLee
 * 
 */
public class BaguaAction
{
	public void addTable() throws Exception
	  {
	    BaguaDao dao = new BaguaDao();
	    dao.createBaguaTable();
	    System.out.print("create table");

	    
	  }
  /**
   * ������
   * 
   * @param goddess
   * @throws Exception
   */
  public void add(LiuSiGuaModel gua) throws Exception
  {
    BaguaDao dao = new BaguaDao();
    gua.setName("Ǭ");
    gua.setDescrible("52220000");
    gua.setValue(0);
    gua.setShangGuaName("Ǭ");
    gua.setXiaGuaName("Ǭ");
    dao.addGua(gua);
  }
 
  /**
   * ��ѯ��������
   * 
   * @param id
   * @return
   * @throws SQLException
   */
  public LiuSiGuaModel get(Integer id) throws SQLException
  {
    BaguaDao dao = new BaguaDao();
    return dao.queryById(id);
  }
  /**
   * ��ѯ��������
   * 
   * @param id
   * @return
   * @throws SQLException
   */
  public LiuSiGuaModel getByGuaXiang(LiuSiGuaModel gua) throws SQLException
  {
    BaguaDao dao = new BaguaDao();
    return dao.queryByGuaXiang(gua);
  }
 
  /**
   * �޸��ض���
   * 
   * @param goddess
   * @throws Exception
   */
  public void edit(LiuSiGuaModel gua) throws Exception
  {
    BaguaDao dao = new BaguaDao();
    dao.updateGua(gua);
  }
 
  /**
   * ɾ������
   * 
   * @param id
   * @throws SQLException
   */
  public void del(Integer id) throws SQLException
  {
    BaguaDao dao = new BaguaDao();
    dao.deleteGua(id);
  }
 
  /**
   * ��ѯȫ������
   * 
   * @return
   * @throws Exception
   */
  public List<LiuSiGuaModel> query() throws Exception
  {
    BaguaDao dao = new BaguaDao();
    return dao.query();
  }
 
  
}