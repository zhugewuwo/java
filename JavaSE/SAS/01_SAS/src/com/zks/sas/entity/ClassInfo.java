package com.zks.sas.entity;

import java.util.Date;

public class ClassInfo {
    private int clsId;//班级编号
    private String clsName;//班级名称
    private Date grade;//入学年份
    private int stuCount;//班级人数
    // 外键约束 REFERENCES 主键表 主键
    private String teaNum;//导员编号
    private String key1;//备用字段1
    private int key2;//备用字段1

    public ClassInfo() {
    }

    public ClassInfo(String clsName, Date grade, int stuCount, String teaNum, String key1, int key2) {
        this.clsName = clsName;
        this.grade = grade;
        this.stuCount = stuCount;
        this.teaNum = teaNum;
        this.key1 = key1;
        this.key2 = key2;
    }

    public ClassInfo(int clsId, String clsName, Date grade, int stuCount, String teaNum, String key1, int key2) {
        this.clsId = clsId;
        this.clsName = clsName;
        this.grade = grade;
        this.stuCount = stuCount;
        this.teaNum = teaNum;
        this.key1 = key1;
        this.key2 = key2;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public Date getGrade() {
        return grade;
    }

    public void setGrade(Date grade) {
        this.grade = grade;
    }

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
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
        return "ClassInfo{" +
                "clsId=" + clsId +
                ", clsName='" + clsName + '\'' +
                ", grade=" + grade +
                ", stuCount=" + stuCount +
                ", teaNum='" + teaNum + '\'' +
                ", key1='" + key1 + '\'' +
                ", key2=" + key2 +
                '}';
    }
}
