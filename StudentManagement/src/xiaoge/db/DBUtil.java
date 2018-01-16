package xiaoge.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBUtil
{
  private static final String Class_Name = "org.sqlite.JDBC";
  private static final String DB_URL = "jdbc:sqlite:F:\\studentdb.db";
  private static final String UNAME = "root";
  private static final String PWD = "root";
 
  private static Connection conn = null;
 /*
  static
  {
    try
    {
      // 1.加载驱动程序
      Class.forName(Class_Name);
      // 2.获得数据库的连接
      conn = DriverManager.getConnection(DB_URL);
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    catch (Exception e)
    {
      System.out.println(e.getClass().getName()+":"+e.getMessage());
      System.exit(0);
    }
    System.out.println("Opened database successfully");
    

  }
 */
  public static Connection getConnection()
  {
	  try
	    {
	      // 1.加载驱动程序
	      Class.forName(Class_Name);
	      // 2.获得数据库的连接
	      conn = DriverManager.getConnection(DB_URL);
	      conn.setAutoCommit(false);
	    }
	    catch (Exception e)
	    {
	      System.out.println(e.getClass().getName()+":"+e.getMessage());
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
    return conn;
  }
  
}
