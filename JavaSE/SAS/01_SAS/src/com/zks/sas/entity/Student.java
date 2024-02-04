package com.zks.sas.entity;

import java.util.Date;

public class Student {
    private String stuNum;  //学生编号
    private int clsId;      //班级编号
    private String stuName; //学生姓名
    private Date stuBtd;    //学生生日
    private String pwd;     //密码
    private int states;     //状态
    private int sex;        //性别
    private String key1;    //备用字段1
    private int key2;       //备用字段2

    public Student() {
    }

    public Student(int clsId, String stuName, Date stuBtd, String pwd, int states, int sex, String key1, int key2) {
        this.clsId = clsId;
        this.stuName = stuName;
        this.stuBtd = stuBtd;
        this.pwd = pwd;
        this.states = states;
        this.sex = sex;
        this.key1 = key1;
        this.key2 = key2;
    }

    public Student(String stuNum, int clsId, String stuName, Date stuBtd, String pwd, int states, int sex, String key1, int key2) {
        this.stuNum = stuNum;
        this.clsId = clsId;
        this.stuName = stuName;
        this.stuBtd = stuBtd;
        this.pwd = pwd;
        this.states = states;
        this.sex = sex;
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Date getStuBtd() {
        return stuBtd;
    }

    public void setStuBtd(Date stuBtd) {
        this.stuBtd = stuBtd;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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
        return "Student{" +
                "stuNum='" + stuNum + '\'' +
                ", clsId=" + clsId +
                ", stuName='" + stuName + '\'' +
                ", stuBtd=" + stuBtd +
                ", pwd='" + pwd + '\'' +
                ", states=" + states +
                ", sex=" + sex +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }
}
