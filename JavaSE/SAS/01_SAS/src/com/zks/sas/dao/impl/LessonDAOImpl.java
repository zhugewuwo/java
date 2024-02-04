package com.zks.sas.dao.impl;

import com.zks.sas.dao.ILessonDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAOImpl implements ILessonDAO {
    @Override
    public boolean save(Lesson l) {
        String sql = "insert into lesson(lesId, lesName, context, score, hours)" +
                "values (" + l.getLesId() + ", '" + l.getLesName() + "', '" + l.getContext() + "', " +
                "" + l.getScore() + ", " + l.getHours() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(Lesson l) {
        String sql = "update lesson set lesId = " + l.getLesId() + ", lesName = '" + l.getLesName() + "', " +
                "context = '" + l.getContext() + "', score = " + l.getScore() + ", " +
                "hours = " + l.getHours() + " where lesId = " + l.getLesId() + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from lesson where lesId = " + id + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public Lesson findById(int id) {
        Lesson lesson = null;
        String sql = "select lesId, lesName, context, score, hours from lesson where lesId = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int lesId = rs.getInt(i++);
                String lesName = rs.getString(i++);
                String context = rs.getString(i++);
                int score = rs.getInt(i++);
                int hours = rs.getInt(i++);

                lesson = new Lesson(lesId, lesName, context, score, hours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return lesson;
    }

    @Override
    public ResultSet findByStuId(String id) {
        String sql = "select l.lesId, l.lesName, l.score, l.hours from score s inner join lesson l on (s.lesId = l.lesId) where stuNum = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        return rs;
    }

    @Override
    public List<Lesson> findAll() {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "select lesId, lesName, context, score, hours from lesson";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int lesId = rs.getInt(i++);
                String lesName = rs.getString(i++);
                String context = rs.getString(i++);
                int score = rs.getInt(i++);
                int hours = rs.getInt(i++);

                Lesson lesson = new Lesson(lesId, lesName, context, score, hours);
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return lessons;
    }
}
