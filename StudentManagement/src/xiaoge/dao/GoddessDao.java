package xiaoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import xiaoge.db.DBUtil;
import xiaoge.model.Goddess;
 
/**
 * 数据层处理类
 * 
 * @author XIAOGE
 * 
 */ 

public class GoddessDao
{
	/**
	 * 添加女神数据库表格
	 * 
	 * @throws SQLException
	 */
	public void createGoddessTable() throws SQLException
	{
	  // 获得数据库连接
	  Connection conn = DBUtil.getConnection();
	  Statement statement = conn.createStatement();
	  
	  //不存在表就创建表
	  String sql = "CREATE TABLE IF NOT EXISTS Godness" + 
 			 "(ID INTEGER PRIMARY KEY  AUTOINCREMENT,"+ 
 			 "NAME   TEXT  NOT NULL,"+
 			 "MOBIE TEXT  NOT NULL,"+
 			 "EMAIL   TEXT  NOT NULL,"+
 			 "ADDRESS   TEXT  NOT NULL,"+
 			 "AGE 	 INT   NOT NULL)";
	  statement.executeUpdate(sql);
	  statement.close();
	  conn.close();
	}
  /**
   * 查询全部女神
   * 
   * @return
   * @throws SQLException
   */
  public List<Goddess> query() throws SQLException
  {
    List<Goddess> goddessList = new ArrayList<Goddess>();
 
 // 获得数据库连接
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
 
    String sql = "SELECT * FROM Godness;";
    ResultSet rs = statement.executeQuery(sql);
    
    Goddess goddess = null;
 
    while (rs.next())
    {
      goddess = new Goddess();
      goddess.setId(rs.getInt("id"));
      goddess.setName(rs.getString("name"));
      goddess.setMobie(rs.getString("mobie"));
      goddess.setEmail(rs.getString("email"));
      goddess.setAddress(rs.getString("address"));
 
      goddessList.add(goddess);
    }
    rs.close();
    statement.close();
    conn.close();
    System.out.println("find all done successfully");
    return goddessList;
  }
 
  /**
   * 查询单个女神
   * 
   * @return
   * @throws SQLException
   */
  public Goddess queryById(Integer id) throws SQLException
  {
    Goddess g = null;
 
    // 获得数据库连接
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    String sql = "SELECT * FROM Godness where ID="+id+";";
    ResultSet rs = statement.executeQuery(sql);
    

    while (rs.next())
    {
      g = new Goddess();
      g.setId(rs.getInt("id"));
      g.setName(rs.getString("name"));
      g.setMobie(rs.getString("mobie"));
      g.setEmail(rs.getString("email"));
      g.setAddress(rs.getString("address"));
    }
 
    rs.close();
    statement.close();
    conn.close();
    System.out.println("find done successfully");
    return g;
  }
 
  /**
   * 添加女神
   * 
   * @throws SQLException
   */
  public void addGoddess(Goddess goddess) throws SQLException
  {
    // 获得数据库连接
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
 
    String sql = "insert into Godness(id,name,mobie,email,address,age) values(null,'"+goddess.getName()+"','"+goddess.getMobie()+"','"+goddess.getEmail()+"','"+goddess.getAddress()+"',18)";
    statement.executeUpdate(sql);
    statement.close();
    conn.commit();
    conn.close();
    
  }
 
  /**
   * 修改女神资料
   * 
   * @throws SQLException
   */
  public void updateGoddess(Goddess goddess) throws SQLException
  {
	// 获得数据库连接
    Connection conn = DBUtil.getConnection();
	Statement statement=conn.createStatement(); 
	int id = goddess.getId();
	String sql = "UPDATE Godness set name = '麻生希' where ID="+id+";";
    statement.executeUpdate(sql);
    conn.commit();
    statement.close();
    conn.close();
 
  }
 
  /**
   * 删除女神
   * 
   * @throws SQLException
   */
  public void deleteGoddess(Integer id) throws SQLException
  {
    // 获得数据库连接
    Connection conn = DBUtil.getConnection();
	Statement statement=conn.createStatement(); 
 
	String sql = "DELETE from Godness where ID="+id+";";
	statement.executeUpdate(sql);
	conn.commit();
	statement.close();
	conn.close();
	System.out.println("delete done successfully");

  }
}
