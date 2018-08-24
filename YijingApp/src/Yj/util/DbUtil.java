package Yj.util;
import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
 
public class DbUtil
{
  private static final String CLASS_NAME = "org.sqlite.JDBC";
  private static final String DB_URL = "jdbc:sqlite:src\\db\\yj.db";
  private static final String UNAME = "root";
  private static final String PWD = "root";
  private final String url =this.getClass().getResource("/db/yj.db").getPath();//"src/db/yj.db";//CommonUtil.getCommonUtil().getDbUrl("yj.db");

  private static Connection conn = null;
  private static DbUtil dbUtil = new DbUtil();
  public static DbUtil getDbUtil(){
      return dbUtil;
  }
  public Connection getConnection(){
      try
      {

          Class.forName(CLASS_NAME);
/*
          使用内部资源饮用，打包jar时不会找不到资源
*/
          System.out.println("db url: "+url);
          conn = DriverManager.getConnection("jdbc:sqlite:"+url);
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
  public static Connection getConnections()
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

