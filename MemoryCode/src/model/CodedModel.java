package model;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CodedModel
 * Author:   xiaoge
 * Date:     2018/7/29 13:32
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CodedModel {
    /*
        数据库中的id
        */
    int id;
    /*
        被编码的名字
    */
    private String name;
    /*
    编码名
    */
    private String codeName;
    /*
        图片地址
    */
    private String picUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}
