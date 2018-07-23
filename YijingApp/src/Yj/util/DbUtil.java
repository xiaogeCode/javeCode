package Yj.util;
import java.sql.Connection;
import java.sql.DriverManager;
 
public class DbUtil
{
  private static final String CLASS_NAME = "org.sqlite.JDBC";
  private static final String DB_URL = "jdbc:sqlite:src\\db\\yj.db";
  private static final String UNAME = "root";
  private static final String PWD = "root";
 
  private static Connection conn = null;
 
  public static Connection getConnection()
  {
	  try
	    {
	      Class.forName(CLASS_NAME);
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

