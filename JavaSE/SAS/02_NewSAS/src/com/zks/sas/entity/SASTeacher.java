package com.zks.sas.entity;
public class SASTeacher  {
    private String  teaNum;
    private String teaName;
    private int stuCount;
    private float scoreAvg;

    public SASTeacher() {

    }
    public String  getTeaNum() {
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

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public float getScoreAvg() {
        return scoreAvg;
    }

    public void setScoreAvg(float scoreAvg) {
        this.scoreAvg = scoreAvg;
    }

    public SASTeacher(String teaNum, String teaName, int stuCount, float scoreAvg) {
        this.teaNum = teaNum;
        this.teaName = teaName;
        this.stuCount = stuCount;
        this.scoreAvg = scoreAvg;
    }
}
