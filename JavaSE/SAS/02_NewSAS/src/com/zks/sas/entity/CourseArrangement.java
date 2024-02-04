package com.zks.sas.entity;

public class CourseArrangement {
    private int caId;
    private int clsId;
    private int lesId;
    private String teaNum;
    private int year;
    private int semester;

    public CourseArrangement() {
    }

    public CourseArrangement(int clsId, int lesId, String teaNum, int year, int semester) {
        this.clsId = clsId;
        this.lesId = lesId;
        this.teaNum = teaNum;
        this.year = year;
        this.semester = semester;
    }

    public CourseArrangement(int caId, int clsId, int lesId, String teaNum, int year, int semester) {
        this.caId = caId;
        this.clsId = clsId;
        this.lesId = lesId;
        this.teaNum = teaNum;
        this.year = year;
        this.semester = semester;
    }

    public int getCaId() {
        return caId;
    }

    public void setCaId(int caId) {
        this.caId = caId;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    public int getLesId() {
        return lesId;
    }

    public void setLesId(int lesId) {
        this.lesId = lesId;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "CourseArrangement{" +
                "caId=" + caId +
                ", clsId=" + clsId +
                ", lesId=" + lesId +
                ", teaNum='" + teaNum + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }
}
