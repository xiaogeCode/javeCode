package action;

import dao.CodedInfoDbDao;
import model.CodedModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CodedInfoDbAction
 * Author:   xiaoge
 * Date:     2018/7/29 15:09
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CodedInfoDbAction {

    public static List<CodedModel> getCodedList(){
        List<CodedModel> list = null;
        try {
            list = CodedInfoDbDao.getCodedList();
            for (int i = 0;i<list.size();i++) {
                System.out.println("id: "+list.get(i).getId()+"name: "+list.get(i).getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static CodedModel getCodeInfoByName(String name){
        CodedModel codedModel = null;
        try {
            codedModel = CodedInfoDbDao.getCodedInfoByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codedModel;
    }
    public static CodedModel getCodeInfoByCodedName(String codedName){
        CodedModel codedModel = null;
        try {
            codedModel = CodedInfoDbDao.getCodedInfoByCodedName(codedName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codedModel;
    }
    public static void addCodeInfo(CodedModel model){
/*        CodedModel codedModel = new CodedModel();
        codedModel.setName("00");
        codedModel.setCodeName("眼镜");
        codedModel.setPicUrl("aa");*/
        try {
            CodedInfoDbDao.addCodedInfo(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void updataCodeInfo(CodedModel code){
        try {
            CodedInfoDbDao.updataCoded(code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteCodeInfo(CodedModel code){
        try {
            CodedInfoDbDao.deleteCoded(code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteAll(){
        try {
            CodedInfoDbDao.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
