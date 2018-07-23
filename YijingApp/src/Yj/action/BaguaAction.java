package Yj.action;

import java.sql.SQLException;
import java.util.List;
 
import Yj.dao.BaguaDao;
import Yj.model.*;


public class BaguaAction
{
    /**
     * 功能描述: <br>
     * 〈添加表〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:05
     */
	public void addTable() throws Exception
	  {
	    BaguaDao dao = new BaguaDao();
	    dao.createBaguaTable();
	    System.out.print("create table");


	 }

  
  /**
   * 功能描述: <br>
   * 〈〉
     参数         [gua]
   * 返回 @return:void
   * 作者 @Author:xiaoge
   * 时间 @Date: 2018/7/23 20:04
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
   * 功能描述: <br>
   * 〈〉
     参数         [id]
   * 返回 @return:Yj.model.LiuSiGuaModel
   * 作者 @Author:xiaoge
   * 时间 @Date: 2018/7/23 20:06
   */
  public LiuSiGuaModel get(Integer id) throws SQLException
  {
    BaguaDao dao = new BaguaDao();
    return dao.queryById(id);
  }
  /**
   * 功能描述: <br>
   * 〈〉
     参数         [gua]
   * 返回 @return:Yj.model.LiuSiGuaModel
   * 作者 @Author:xiaoge
   * 时间 @Date: 2018/7/23 20:06
   */
  public LiuSiGuaModel getByGuaXiang(LiuSiGuaModel gua) throws SQLException
  {
    BaguaDao dao = new BaguaDao();
    return dao.queryByGuaXiang(gua);
  }

  /**
   * 功能描述: <br>
   * 〈〉
     参数         [gua]
   * 返回 @return:void
   * 作者 @Author:xiaoge
   * 时间 @Date: 2018/7/23 20:17
   */
  public void edit(LiuSiGuaModel gua) throws Exception
  {
    BaguaDao dao = new BaguaDao();
    dao.updateGua(gua);
  }

  /**
   * 功能描述: <br>
   * 〈〉
     参数         [id]
   * 返回 @return:void
   * 作者 @Author:xiaoge
   * 时间 @Date: 2018/7/23 20:06
   */
  public void del(Integer id) throws SQLException
  {
    BaguaDao dao = new BaguaDao();
    dao.deleteGua(id);
  }
 
  /**
   * 功能描述: <br>
   * 〈〉
     参数         []
   * 返回 @return:java.util.List<Yj.model.LiuSiGuaModel>
   * 作者 @Author:xiaoge
   * 时间 @Date: 2018/7/23 20:17
   */
  public List<LiuSiGuaModel> query() throws Exception
  {
    BaguaDao dao = new BaguaDao();
    return dao.query();
  }
 
  
}