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
 * 数据层处理类
 * 
 * @author XIAOGE
 * 
 */ 

public class BaguaDao
{
	/**
	 * 添加八卦信息数据库表格
	 * 
	 * @throws SQLException
	 */
	public void createBaguaTable() throws SQLException
	{
	  // 获得数据库连接
	  Connection conn = DBUtil.getConnection();
	  Statement statement = conn.createStatement();
	  statement.setQueryTimeout(30); 
	  
	  //不存在表就创建表
	  
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
   * 查询全部64卦
   * 
   * @return
   * @throws SQLException
   */
  public List<LiuSiGuaModel> query() throws SQLException
  {
    List<LiuSiGuaModel> guaList = new ArrayList<LiuSiGuaModel>();
 
 // 获得数据库连接
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
   * 查询单个卦象
   * 
   * @return
   * @throws SQLException
   */
  public LiuSiGuaModel queryById(Integer id) throws SQLException
  {
	  LiuSiGuaModel g = null;
 
    // 获得数据库连接
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
   * 查询单个卦象
   * 
   * @return
   * @throws SQLException
   */
  public LiuSiGuaModel queryByGuaXiang(LiuSiGuaModel gua) throws SQLException
  {
	  LiuSiGuaModel g = null;
 
    // 获得数据库连接
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    String sql = "SELECT * FROM BaGuaInfo where ABOVEGUA='"+gua.getShangGuaName()+"'"+"and BELLOWGUA='"+gua.getXiaGuaName()+"';";
    //if (gua.getXiaGuaName().equals("坎") && gua.getShangGuaName().equals("震")){
    //     sql = "SELECT * FROM BaGuaInfo where ABOVEGUA='震"+"'"+"and BELLOWGUA='坎"+"';";

	//}
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
   * 添加卦象
   * 
   * @throws SQLException
   */
  public void addGua(LiuSiGuaModel gua) throws SQLException
  {
    // 获得数据库连接
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    
    String sql = "insert into BaGuaInfo(id,name,DESCRIBLE,ABOVEGUA,BELLOWGUA,VALUE) values(null,'"+gua.getName()+"','"+gua.getDescrible()+"','"+gua.getShangGuaName()+"','"+gua.getXiaGuaName()+"',"+gua.getValue()+")";
    statement.executeUpdate(sql);
    statement.close();
    conn.commit();
    conn.close();
    
  }
 
  /**
   * 修改卦资料
   * 
   * @throws SQLException
   */
  public void updateGua(LiuSiGuaModel gua) throws SQLException
  {
	// 获得数据库连接
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
   * 删除卦
   * 
   * @throws SQLException
   */
  public void deleteGua(Integer id) throws SQLException
  {
    // 获得数据库连接
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
