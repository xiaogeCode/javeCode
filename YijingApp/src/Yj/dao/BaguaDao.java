package Yj.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import Yj.util.*;
import Yj.model.*;
 
/**
 *
 * 
 * @author XIAOGE
 * 
 */ 

public class BaguaDao
{
    /**
     * 功能描述: <br>
     * 〈〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:21
     */
    public void createBaguaTable() throws SQLException
    {
      Connection conn = DBUtil.getConnection();
      Statement statement = conn.createStatement();
      statement.setQueryTimeout(30);


      String sql = "CREATE TABLE IF NOT EXISTS BaGuaInfo" +
              "(ID INTEGER PRIMARY KEY  AUTOINCREMENT,"+
              "NAME   TEXT  NOT NULL,"+
              "ABOVEGUA   TEXT  NOT NULL,"+
              "BELLOWGUA   TEXT  NOT NULL,"+
              "DESCRIBLE   TEXT  NOT NULL,"+
              "VALUE 	 INT   NOT NULL)";


      statement.executeUpdate(sql);

      conn.commit();
      statement.close();
      conn.close();
    }
    /**
     * 功能描述: <br>
     * 〈〉
       参数         []
     * 返回 @return:java.util.List<Yj.model.LiuSiGuaModel>
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:20
     */
    public List<LiuSiGuaModel> query() throws SQLException
    {
      List<LiuSiGuaModel> guaList = new ArrayList<LiuSiGuaModel>();

      Connection conn = DBUtil.getConnection();
      Statement statement=conn.createStatement();

      String sql = "SELECT * FROM BaGuaInfo;";
      ResultSet rs = statement.executeQuery(sql);

      LiuSiGuaModel gua = null;

      while (rs.next())
      {
        gua= new LiuSiGuaModel();
        gua.setId(rs.getInt("ID"));
        gua.setValue(rs.getInt("VALUE"));
        gua.setName(rs.getString("name"));
        gua.setDescrible(rs.getString("DESCRIBLE"));
        gua.setShangGuaName(rs.getString("ABOVEGUA"));
        gua.setXiaGuaName(rs.getString("BELLOWGUA"));

        guaList.add(gua);
      }
      rs.close();
      statement.close();
      conn.close();
      System.out.println("find all done successfully");
      return guaList;
    }

    /**
     * 功能描述: <br>
     * 〈〉
       参数         [id]
     * 返回 @return:Yj.model.LiuSiGuaModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:21
     */
  public LiuSiGuaModel queryById(Integer id) throws SQLException
  {
	  LiuSiGuaModel g = null;

    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    String sql = "SELECT * FROM BaGuaInfo where ID="+id+";";
    ResultSet rs = statement.executeQuery(sql);
    

    while (rs.next())
    {
      g = new LiuSiGuaModel();
      g.setId(rs.getInt("ID"));
      g.setValue(rs.getInt("VALUE"));
      g.setName(rs.getString("name"));
      g.setDescrible(rs.getString("DESCRIBLE"));
      g.setShangGuaName(rs.getString("ABOVEGUA"));
      g.setXiaGuaName(rs.getString("BELLOWGUA"));
    }
 
    rs.close();
    statement.close();
    conn.close();
    System.out.println("find done successfully");
    return g;
  }
    /**
     * 功能描述: <br>
     * 〈〉
       参数         [gua]
     * 返回 @return:Yj.model.LiuSiGuaModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:22
     */
    public LiuSiGuaModel queryByGuaXiang(LiuSiGuaModel gua) throws SQLException
    {
        LiuSiGuaModel g = null;

      Connection conn = DBUtil.getConnection();
      Statement statement=conn.createStatement();
      String sql = "SELECT * FROM BaGuaInfo where ABOVEGUA='"+gua.getShangGuaName()+"'"+"and BELLOWGUA='"+gua.getXiaGuaName()+"';";

      ResultSet rs = statement.executeQuery(sql);

      while (rs.next())
      {
        g = new LiuSiGuaModel();
        g.setId(rs.getInt("ID"));
        g.setValue(rs.getInt("VALUE"));
        g.setName(rs.getString("name"));
        g.setDescrible(rs.getString("DESCRIBLE"));
        g.setShangGuaName(rs.getString("ABOVEGUA"));
        g.setXiaGuaName(rs.getString("BELLOWGUA"));
      }

      rs.close();
      statement.close();
      conn.close();
      System.out.println("find done successfully");
      return g;
    }

    /**
     * 功能描述: <br>
     * 〈〉
       参数         [gua]
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:22
     */
  public void addGua(LiuSiGuaModel gua) throws SQLException
  {
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    
    String sql = "insert into BaGuaInfo(id,name,DESCRIBLE,ABOVEGUA,BELLOWGUA,VALUE) values(null,'"+gua.getName()+"','"+gua.getDescrible()+"','"+gua.getShangGuaName()+"','"+gua.getXiaGuaName()+"',"+gua.getValue()+")";
    statement.executeUpdate(sql);
    statement.close();
    conn.commit();
    conn.close();
    
  }

    /**
     * 功能描述: <br>
     * 〈〉
       参数         [gua]
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:22
     */
    public void updateGua(LiuSiGuaModel gua) throws SQLException
    {

      Connection conn = DBUtil.getConnection();
      Statement statement=conn.createStatement();
      int id = gua.getId();
      String sql = "UPDATE BaGuaInfo set VALUE = "+"'"+gua.getValue()+"'"+" where ID="+id+";";
      statement.executeUpdate(sql);
      conn.commit();
      statement.close();
      conn.close();

    }

    /**
     * 功能描述: <br>
     * 〈〉
       参数         [id]
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/23 20:22
     */
  public void deleteGua(Integer id) throws SQLException
  {
    Connection conn = DBUtil.getConnection();
	Statement statement=conn.createStatement(); 
 
	String sql = "DELETE from BaGuaInfo where ID="+id+";";
	statement.executeUpdate(sql);
	conn.commit();
	statement.close();
	conn.close();
	System.out.println("delete done successfully");

  }
}
