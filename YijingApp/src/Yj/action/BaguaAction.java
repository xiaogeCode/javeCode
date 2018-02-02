package Yj.action;

import java.sql.SQLException;
import java.util.List;
 
import Yj.dao.BaguaDao;
import Yj.model.*;
import Yj.util.*;
 
/**
 * 控制层，直接在这里构建数据，界面的数据则通过请求传递接收即可，亦是同理
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
   * 新增卦
   * 
   * @param goddess
   * @throws Exception
   */
  public void add(LiuSiGuaModel gua) throws Exception
  {
    BaguaDao dao = new BaguaDao();
    gua.setName("乾");
    gua.setDescrible("52220000");
    gua.setValue(0);
    gua.setShangGuaName("乾");
    gua.setXiaGuaName("乾");
    dao.addGua(gua);
  }
 
  /**
   * 查询单个卦象
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
   * 查询单个卦象
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
   * 修改特定卦
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
   * 删除卦象
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
   * 查询全部卦象
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