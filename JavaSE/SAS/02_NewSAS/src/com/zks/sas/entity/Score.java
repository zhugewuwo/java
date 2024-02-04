package com.zks.sas.entity;

public class Score {
    private int scoreId;    //成绩编号
    private int lesId;      //课程编号
    private String stuNum;  //学生学号
    private int score;      //成绩

    public Score() {
    }

    public Score(int lesId, String stuNum, int score) {
        this.lesId = lesId;
        this.stuNum = stuNum;
        this.score = score;
    }

    public Score(int scoreId, int lesId, String stuNum, int score) {
        this.scoreId = scoreId;
        this.lesId = lesId;
        this.stuNum = stuNum;
        this.score = score;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getLesId() {
        return lesId;
    }

    public void setLesId(int lesId) {
        this.lesId = lesId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", lesId=" + lesId +
                ", stuNum='" + stuNum + '\'' +
                ", score=" + score +
                '}';
    }
}
