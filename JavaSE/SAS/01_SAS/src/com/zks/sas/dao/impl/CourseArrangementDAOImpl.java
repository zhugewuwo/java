package com.zks.sas.dao.impl;

import com.zks.sas.dao.ICourseArrangementDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.CourseArrangement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseArrangementDAOImpl implements ICourseArrangementDAO {

    @Override
    public boolean save(CourseArrangement ca) {
        String sql = "insert into coursearrangement" +
                "(caId, lesId, teaNum, " +
                "clsId, year, semester)" +
                "values " +
                "(" + ca.getCaId() + ", " + ca.getLesId() + ", '" + ca.getTeaNum() + "', " +
                "" + ca.getClsId() + ", " + ca.getYear() + ", " + ca.getSemester() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(CourseArrangement ca) {
        String sql = "update coursearrangement set caId = " + ca.getCaId() + ", " +
                "lesId = " + ca.getLesId() + ", teaNum = '" + ca.getTeaNum() + "', " +
                "clsId = " + ca.getClsId() + ", year = " + ca.getYear() + ", " +
                "semester = " + ca.getSemester() + " where caId = " + ca.getCaId() + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from coursearrangement where caId = " + id + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public CourseArrangement findById(int id) {
        CourseArrangement courseArrangement = null;
        String sql = "select caId, clsId, lesId, teaNum, year, semester from coursearrangement " +
                "where caId = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int caId = rs.getInt(i++);
                int clsId = rs.getInt(i++);
                int lesId = rs.getInt(i++);
                String teaNum = rs.getString(i++);
                int year = rs.getInt(i++);
                int semester = rs.getInt(i++);

                courseArrangement = new CourseArrangement(caId, clsId, lesId, teaNum, year, semester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            dbManager.close();
        }
        return courseArrangement;
    }

    @Override
    public List<CourseArrangement> findAll() {
        List<CourseArrangement> courseArrangements = new ArrayList<>();
        String sql = "select caId, clsId, lesId, teaNum, year, semester from coursearrangement";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int caId = rs.getInt(i++);
                int clsId = rs.getInt(i++);
                int lesId = rs.getInt(i++);
                String teaNum = rs.getString(i++);
                int year = rs.getInt(i++);
                int semester = rs.getInt(i++);

                CourseArrangement courseArrangement = new CourseArrangement(caId, clsId, lesId, teaNum, year, semester);
                courseArrangements.add(courseArrangement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            dbManager.close();
        }
        return courseArrangements;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        String sql = "select count(*) from coursearrangement";
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
    public List<CourseArrangement> getNowPageData(int pageSize, int nowPage) {
        List<CourseArrangement> courseArrangements = new ArrayList<>();
        String sql = "select caId, clsId, lesId, teaNum, year, semester from coursearrangement " +
                "limit " + (nowPage - 1) * pageSize + ", " + pageSize;
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int caId = rs.getInt(i++);
                int clsId = rs.getInt(i++);
                int lesId = rs.getInt(i++);
                String teaNum = rs.getString(i++);
                int year = rs.getInt(i++);
                int semester = rs.getInt(i++);

                CourseArrangement courseArrangement = new CourseArrangement(caId, clsId, lesId, teaNum, year, semester);
                courseArrangements.add(courseArrangement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return courseArrangements;
    }
}

