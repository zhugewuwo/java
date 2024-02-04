package com.zks.sas.entity;

public class SASClassInfo {
    private int clsId;
    private String clsName;
    private int stuCount;
    private float scoreAvg;

    public SASClassInfo() {

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

    public SASClassInfo(int clsId, String clsName, int stuCount, float scoreAvg) {
        this.clsId = clsId;
        this.clsName = clsName;
        this.stuCount = stuCount;
        this.scoreAvg = scoreAvg;
    }
}
