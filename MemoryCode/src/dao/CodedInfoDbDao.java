package dao;

import model.CodedModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CodedInfoDbDao
 * Author:   xiaoge
 * Date:     2018/7/29 13:45
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CodedInfoDbDao {
    /**
     * 功能描述: <br>
     * 〈获取数据库中的编码列表〉
       参数         []
     * 返回 @return:java.util.List<model.CodedModel>
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:51
     */
    public static List<CodedModel> getCodedList() throws SQLException {
        List<CodedModel> list = new ArrayList<CodedModel>();
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM CodedInfo;";
        ResultSet rs = statement.executeQuery(sql);

        CodedModel codedModel = null;

        while (rs.next()){
            codedModel = new CodedModel();
            codedModel.setName(rs.getString("name"));
            codedModel.setCodeName(rs.getString("codedName"));
            codedModel.setPicUrl(rs.getString("picUrl"));
            codedModel.setId(rs.getInt("id"));

            list.add(codedModel);
        }
        rs.close();
        statement.close();
        conn.close();
        return list;
    }
    /**
     * 功能描述: <br>
     * 〈根据名字获取编码信息〉
       参数         [name]
     * 返回 @return:model.CodedModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:51
     */
    public static CodedModel getCodedInfoByName(String name) throws SQLException {
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM CodedInfo where name='"+name+"';";
        ResultSet rs = statement.executeQuery(sql);

        CodedModel codedModel = null;

        while (rs.next()){
            codedModel = new CodedModel();
            codedModel.setName(rs.getString("name"));
            codedModel.setCodeName(rs.getString("codedName"));
            codedModel.setPicUrl(rs.getString("picUrl"));
            codedModel.setId(rs.getInt("id"));
        }
        rs.close();
        statement.close();
        conn.close();
        return codedModel;
    }
    /**
     * 功能描述: <br>
     * 〈根据编码获得编码信息〉
       参数         [codedName]
     * 返回 @return:model.CodedModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:51
     */
    public static CodedModel getCodedInfoByCodedName(String codedName) throws SQLException {
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM CodedInfo where codedName='"+codedName+"';";
        ResultSet rs = statement.executeQuery(sql);

        CodedModel codedModel = null;

        while (rs.next()){
            codedModel = new CodedModel();
            codedModel.setName(rs.getString("name"));
            codedModel.setCodeName(rs.getString("codedName"));
            codedModel.setPicUrl(rs.getString("picUrl"));
            codedModel.setId(rs.getInt("id"));
        }
        rs.close();
        statement.close();
        conn.close();
        return codedModel;

    }
    /**
     * 功能描述: <br>
     * 〈往数据库中添加编码数据〉
       参数         [code]
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:50
     */
    public static boolean addCodedInfo(CodedModel code) throws SQLException {
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();

        String sql = "insert into CodedInfo(id,name,codedName,picUrl) values(null,'"+code.getName()+"','"+code.getCodeName()+"','"+code.getPicUrl()+"'"+")";
        statement.executeUpdate(sql);
        conn.commit();

        statement.close();
        conn.close();

        return true;
    }
    /**
     * 功能描述: <br>
     * 〈更新数据库的编码数据〉
       参数         [code]
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:50
     */
    public static boolean updataCoded(CodedModel code) throws SQLException {
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();
        int id = code.getId();
        //String sql = "UPDATE CodedInfo set codedName = "+"'"+code.getCodeName()+"'"+" where ID="+id+";";

        String sql = "UPDATE CodedInfo set codedName = "+"'"+code.getCodeName()+"',"
                + "name =" +"'"+code.getName()+"',"
                + "picUrl =" +"'"+code.getPicUrl()+"'"
                +" where ID="+id+";";
        statement.executeUpdate(sql);
        conn.commit();

        statement.close();
        conn.close();
        return true;
    }
    /**
     * 功能描述: <br>
     * 〈删除数据库中编码数据〉
       参数         [code]
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:49
     */
    public static boolean deleteCoded(CodedModel code) throws SQLException {
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();

        String sql = "DELETE from CodedInfo where ID="+code.getId()+";";
        statement.executeUpdate(sql);
        conn.commit();

        statement.close();
        conn.close();
        return true;
    }
    /**
     * 功能描述: <br>
     * 〈删除数据库全部记录〉
       参数         []
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/7/29 15:54
     */
    public static boolean deleteAll() throws SQLException {
        Connection conn = DbDao.getDbDao().getConnection();
        Statement statement = conn.createStatement();

        String sql = "DELETE from CodedInfo;";
        statement.executeUpdate(sql);
        conn.commit();

        statement.close();
        conn.close();
        return true;
    }
}
