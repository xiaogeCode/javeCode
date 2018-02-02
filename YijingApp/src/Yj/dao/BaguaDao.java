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
 * ���ݲ㴦����
 * 
 * @author XIAOGE
 * 
 */ 

public class BaguaDao
{
	/**
	 * ��Ӱ�����Ϣ���ݿ���
	 * 
	 * @throws SQLException
	 */
	public void createBaguaTable() throws SQLException
	{
	  // ������ݿ�����
	  Connection conn = DBUtil.getConnection();
	  Statement statement = conn.createStatement();
	  statement.setQueryTimeout(30); 
	  
	  //�����ڱ�ʹ�����
	  
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
   * ��ѯȫ��64��
   * 
   * @return
   * @throws SQLException
   */
  public List<LiuSiGuaModel> query() throws SQLException
  {
    List<LiuSiGuaModel> guaList = new ArrayList<LiuSiGuaModel>();
 
 // ������ݿ�����
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
   * ��ѯ��������
   * 
   * @return
   * @throws SQLException
   */
  public LiuSiGuaModel queryById(Integer id) throws SQLException
  {
	  LiuSiGuaModel g = null;
 
    // ������ݿ�����
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
   * ��ѯ��������
   * 
   * @return
   * @throws SQLException
   */
  public LiuSiGuaModel queryByGuaXiang(LiuSiGuaModel gua) throws SQLException
  {
	  LiuSiGuaModel g = null;
 
    // ������ݿ�����
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    String sql = "SELECT * FROM BaGuaInfo where ABOVEGUA='"+gua.getShangGuaName()+"'"+"and BELLOWGUA='"+gua.getXiaGuaName()+"';";
    //if (gua.getXiaGuaName().equals("��") && gua.getShangGuaName().equals("��")){
    //     sql = "SELECT * FROM BaGuaInfo where ABOVEGUA='��"+"'"+"and BELLOWGUA='��"+"';";

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
   * �������
   * 
   * @throws SQLException
   */
  public void addGua(LiuSiGuaModel gua) throws SQLException
  {
    // ������ݿ�����
    Connection conn = DBUtil.getConnection();
    Statement statement=conn.createStatement(); 
    
    String sql = "insert into BaGuaInfo(id,name,DESCRIBLE,ABOVEGUA,BELLOWGUA,VALUE) values(null,'"+gua.getName()+"','"+gua.getDescrible()+"','"+gua.getShangGuaName()+"','"+gua.getXiaGuaName()+"',"+gua.getValue()+")";
    statement.executeUpdate(sql);
    statement.close();
    conn.commit();
    conn.close();
    
  }
 
  /**
   * �޸�������
   * 
   * @throws SQLException
   */
  public void updateGua(LiuSiGuaModel gua) throws SQLException
  {
	// ������ݿ�����
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
   * ɾ����
   * 
   * @throws SQLException
   */
  public void deleteGua(Integer id) throws SQLException
  {
    // ������ݿ�����
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
