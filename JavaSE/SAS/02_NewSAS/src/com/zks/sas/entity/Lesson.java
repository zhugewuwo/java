package com.zks.sas.entity;

public class Lesson {
    private int lesId;          //课程编号
    private String lesName;     //课程名称
    private String context;     //内容
    private int score;          //学分
    private int hours;          //课时量

    public Lesson() {
    }

    public Lesson(String lesName, String context, int score, int hours) {
        this.lesName = lesName;
        this.context = context;
        this.score = score;
        this.hours = hours;
    }

    public Lesson(int lesId, String lesName, String context, int score, int hours) {
        this.lesId = lesId;
        this.lesName = lesName;
        this.context = context;
        this.score = score;
        this.hours = hours;
    }

    public int getLesId() {
        return lesId;
    }

    public void setLesId(int lesId) {
        this.lesId = lesId;
    }

    public String getLesName() {
        return lesName;
    }

    public void setLesName(String lesName) {
        this.lesName = lesName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lesId=" + lesId +
                ", lesName='" + lesName + '\'' +
                ", context='" + context + '\'' +
                ", score=" + score +
                ", hours=" + hours +
                '}';
    }
}
