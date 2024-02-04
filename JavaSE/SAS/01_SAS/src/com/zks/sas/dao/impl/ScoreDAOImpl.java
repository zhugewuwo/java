package com.zks.sas.dao.impl;

import com.zks.sas.dao.IScoreDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.CourseArrangement;
import com.zks.sas.entity.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAOImpl implements IScoreDAO {
    @Override
    public boolean save(Score s) {
        String sql = "insert into score(lesId, stuNum, score) " +
                "VALUES (" + s.getLesId() + ", '" + s.getStuNum() + "', " + s.getScore() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(Score s) {
        String sql = "update score set scoreId = " + s.getScoreId() + ", lesId = " + s.getLesId() + ", " +
                "stuNum = '" + s.getStuNum() + "', score = " + s.getScore() + " " +
                "where scoreId = " + s.getScoreId() + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(int id) {
        //2, "202007030222", 99
        String sql = "delete from score where scoreId = " + id + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public Score findById(int id) {
        Score s = null;
        String sql = "select scoreId, lesId, stuNum, score from score where scoreId = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int scoreId = rs.getInt(i++);
                int lesId = rs.getInt(i++);
                String stuNum = rs.getString(i++);
                int score = rs.getInt(i++);

                s = new Score(scoreId, lesId, stuNum, score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return s;
    }

    @Override
    public ResultSet findByStuId(String stuId) {
        String sql = "select s.scoreId, l.lesName,  s.score from score s inner join lesson l on (s.lesId = l.lesId) where stuNum = " + stuId + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        return rs;
    }

    @Override
    public List<Score> findAll() {
        List<Score> scores = new ArrayList<>();
        String sql = "select scoreId, lesId, stuNum, score from score";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int scoreId = rs.getInt(i++);
                int lesId = rs.getInt(i++);
                String stuNum = rs.getString(i++);
                int score = rs.getInt(i++);

                Score s = new Score(scoreId, lesId, stuNum, score);
                scores.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return scores;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        String sql = "select count(*) from score";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return rowCount;
    }

    @Override
    public int getPageCount(int pageSize) {
        int rowCount = getRowCount();
        int pageCount = rowCount / pageSize;
        if (rowCount % pageSize != 0) pageCount++;
        return pageCount;
    }

    @Override
    public List<Score> getNowPageData(int pageSize, int nowPage) {
        List<Score> scores = new ArrayList<>();
        String sql = "select scoreId, lesId, stuNum, score from score " +
                "limit " + (nowPage - 1) * pageSize + ", " + pageSize;
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int scoreId = rs.getInt(i++);
                int lesId  = rs.getInt(i++);
                String stuNum = rs.getString(i++);
                int score = rs.getInt(i++);

                Score s = new Score(scoreId,lesId,stuNum,score);
                scores.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return scores;
    }
}
