package com.zks.sas.dao.impl;

import com.zks.sas.dao.IRestPwdDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.RestPwd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestPwdDAOImpl implements IRestPwdDAO {
    @Override
    public boolean save(RestPwd r) {
        String sql = "insert into restpwd(stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount)" +
                "VALUES ('" + r.getStuNum() + "', '" + r.getQuestion1() + "', '" + r.getAnswer1() + "', '" + r.getQuestion2() + "', " +
                "'" + r.getAnswer2() + "', '" + r.getQuestion3() + "', '" + r.getAnswer3() + "', " + r.getRequestCount() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(RestPwd r) {
        String sql = "update restpwd set stuNum = '" + r.getStuNum() + "', question1 = '" + r.getQuestion1() + "', " +
                "answer1 = '" + r.getAnswer1() + "', question2 = '" + r.getQuestion2() + "', " +
                "answer2 = '" + r.getAnswer2() + "', question3 = '" + r.getQuestion3() + "', " +
                "answer3 = '" + r.getAnswer3() + "', requestCount = " + r.getRequestCount() + " " +
                "where stuNum = '" + r.getStuNum() + "'";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(String id) {
        String sql = "delete from restpwd where stuNum = " + id + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public RestPwd findById(String id) {
        RestPwd resetpwd = null;
        String sql = "select stuNum, question1, answer1, " +
                "question2, answer2, question3, answer3, " +
                "requestCount from restpwd " +
                "where stuNum = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String stuNum = rs.getString(i++);
                String question1 = rs.getString(i++);
                String answer1 = rs.getString(i++);
                String question2 = rs.getString(i++);
                String answer2 = rs.getString(i++);
                String question3 = rs.getString(i++);
                String answer3 = rs.getString(i++);
                int requestCount = rs.getInt(i++);

                resetpwd = new RestPwd(stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return resetpwd;
    }

    @Override
    public List<RestPwd> findAll() {
        List<RestPwd> resetpwds = new ArrayList<>();
        String sql = "select stuNum, question1, answer1, " +
                "question2, answer2, question3, answer3, " +
                "requestCount from restpwd";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String stuNum = rs.getString(i++);
                String question1 = rs.getString(i++);
                String answer1 = rs.getString(i++);
                String question2 = rs.getString(i++);
                String answer2 = rs.getString(i++);
                String question3 = rs.getString(i++);
                String answer3 = rs.getString(i++);
                int requestCount = rs.getInt(i++);

                RestPwd resetpwd = new RestPwd(stuNum, question1, answer1, question2, answer2, question3, answer3, requestCount);
                resetpwds.add(resetpwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return resetpwds;
    }
}
