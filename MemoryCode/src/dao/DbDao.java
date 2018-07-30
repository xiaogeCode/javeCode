package dao;

import util.CommUtil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: DbDao
 * Author:   xiaoge
 * Date:     2018/7/29 13:55
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class DbDao {
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:src\\db\\yj.db";
    private static final String UNAME = "root";
    private static final String PWD = "root";
    private final String url = CommUtil.getDbUrl("mc.db");//"src/db/mc.db"; //this.getClass().getResource("/db/yj.db").getPath();

    private static Connection conn = null;
    private static DbDao dbDao = new DbDao();
    public static DbDao getDbDao(){
        return dbDao;
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
