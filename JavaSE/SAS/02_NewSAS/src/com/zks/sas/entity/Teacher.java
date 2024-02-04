package com.zks.sas.entity;

import java.util.Date;

public class Teacher {
    private String teaNum;//老师编号
    private String teaName;//老师姓名
    private String teaTitle;//老师职称
    private int teaType;//老师类型
    private Date teaBtd;//生日
    private String pwd;//密码
    private int states;//状态
    private int roleId;//角色编号
    private String key1;//备用字段1
    private int key2;//备用字段1

    public Teacher() {
    }

    public Teacher(String teaName, String teaTitle, int teaType, Date teaBtd, String pwd, int states, int roleId, String key1, int key2) {
        this.teaName = teaName;
        this.teaTitle = teaTitle;
        this.teaType = teaType;
        this.teaBtd = teaBtd;
        this.pwd = pwd;
        this.states = states;
        this.roleId = roleId;
        this.key1 = key1;
        this.key2 = key2;
    }

    public Teacher(String teaNum, String teaName, String teaTitle, int teaType, Date teaBtd, String pwd, int states, int roleId, String key1, int key2) {
        this.teaNum = teaNum;
        this.teaName = teaName;
        this.teaTitle = teaTitle;
        this.teaType = teaType;
        this.teaBtd = teaBtd;
        this.pwd = pwd;
        this.states = states;
        this.roleId = roleId;
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaTitle() {
        return teaTitle;
    }

    public void setTeaTitle(String teaTitle) {
        this.teaTitle = teaTitle;
    }

    public int getTeaType() {
        return teaType;
    }

    public void setTeaType(int teaType) {
        this.teaType = teaType;
    }

    public Date getTeaBtd() {
        return teaBtd;
    }

    public void setTeaBtd(Date teaBtd) {
        this.teaBtd = teaBtd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaNum=" + teaNum +
                ", teaName='" + teaName + '\'' +
                ", teaTitle='" + teaTitle + '\'' +
                ", teaType=" + teaType +
                ", teaBtd=" + teaBtd +
                ", pwd='" + pwd + '\'' +
                ", states=" + states +
                ", roleId=" + roleId +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }
}
