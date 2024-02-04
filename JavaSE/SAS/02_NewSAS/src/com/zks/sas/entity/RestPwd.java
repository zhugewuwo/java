package com.zks.sas.entity;

public class RestPwd {
    private String stuNum;      //学生编号
    private String question1;   //问题1
    private String answer1;     //答案1
    private String question2;   //问题2
    private String answer2;     //答案2
    private String question3;   //问题3
    private String answer3;     //答案3
    private int requestCount;   //回答次数

    public RestPwd() {
    }

    public RestPwd(String question1, String answer1, String question2, String answer2, String question3, String answer3, int requestCount) {
        this.question1 = question1;
        this.answer1 = answer1;
        this.question2 = question2;
        this.answer2 = answer2;
        this.question3 = question3;
        this.answer3 = answer3;
        this.requestCount = requestCount;
    }

    public RestPwd(String stuNum, String question1, String answer1, String question2, String answer2, String question3, String answer3, int requestCount) {
        this.stuNum = stuNum;
        this.question1 = question1;
        this.answer1 = answer1;
        this.question2 = question2;
        this.answer2 = answer2;
        this.question3 = question3;
        this.answer3 = answer3;
        this.requestCount = requestCount;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public String toString() {
        return "Resetpwd{" +
                "stuNum='" + stuNum + '\'' +
                ", question1='" + question1 + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", question2='" + question2 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", question3='" + question3 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
