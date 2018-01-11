package xiaoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import xiaoge.db.DBUtil;
import xiaoge.model.Goddess;
 
/**
 * ���ݲ㴦����
 * 
 * @author AlanLee
 * 
 */
public class GoddessDao
{
  /**
   * ��ѯȫ��Ů��
   * 
   * @return
   * @throws SQLException
   */
  public List<Goddess> query() throws SQLException
  {
    List<Goddess> goddessList = new ArrayList<Goddess>();
 
    // ������ݿ�����
    Connection conn = DBUtil.getConnection();
 
    StringBuilder sb = new StringBuilder();
    sb.append("select id,name,mobie,email,address from goddess");
 
    // ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ�
    PreparedStatement ptmt = conn.prepareStatement(sb.toString());
 
    ResultSet rs = ptmt.executeQuery();
 
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
    return goddessList;
  }
 
  /**
   * ��ѯ����Ů��
   * 
   * @return
   * @throws SQLException
   */
  public Goddess queryById(Integer id) throws SQLException
  {
    Goddess g = null;
 
    Connection conn = DBUtil.getConnection();
 
    String sql = "" + " select * from imooc_goddess " + " where id=? ";
 
    PreparedStatement ptmt = conn.prepareStatement(sql);
 
    ptmt.setInt(1, id);
 
    ResultSet rs = ptmt.executeQuery();
 
    while (rs.next())
    {
      g = new Goddess();
      g.setId(rs.getInt("id"));
      g.setName(rs.getString("name"));
      g.setMobie(rs.getString("mobie"));
      g.setEmail(rs.getString("email"));
      g.setAddress(rs.getString("address"));
    }
 
    return g;
  }
 
  /**
   * ���Ů��
   * 
   * @throws SQLException
   */
  public void addGoddess(Goddess goddess) throws SQLException
  {
    // ������ݿ�����
    Connection conn = DBUtil.getConnection();
 
    String sql = "insert into goddess(name,mobie,email,address) values(?,?,?,?)";
 
    PreparedStatement ptmt = conn.prepareStatement(sql);
 
    ptmt.setString(1, goddess.getName());
    ptmt.setString(2, goddess.getMobie());
    ptmt.setString(3, goddess.getEmail());
    ptmt.setString(4, goddess.getAddress());
 
    ptmt.execute();
  }
 
  /**
   * �޸�Ů������
   * 
   * @throws SQLException
   */
  public void updateGoddess(Goddess goddess) throws SQLException
  {
    Connection conn = DBUtil.getConnection();
 
    String sql = "update goddess set name=?,mobie=?,email=?,address=? where id=?";
 
    PreparedStatement ptmt = conn.prepareStatement(sql);
 
    ptmt.setString(1, goddess.getName());
    ptmt.setString(2, goddess.getMobie());
    ptmt.setString(3, goddess.getEmail());
    ptmt.setString(4, goddess.getAddress());
 
    ptmt.execute();
  }
 
  /**
   * ɾ��Ů��
   * 
   * @throws SQLException
   */
  public void deleteGoddess(Integer id) throws SQLException
  {
    Connection conn = DBUtil.getConnection();
 
    String sql = "delete from goddess where id=?";
 
    PreparedStatement ptmt = conn.prepareStatement(sql);
 
    ptmt.setInt(1, id);
 
    ptmt.execute();
  }
}
