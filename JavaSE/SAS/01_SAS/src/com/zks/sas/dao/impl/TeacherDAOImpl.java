package com.zks.sas.dao.impl;


import com.zks.sas.dao.ITeacherDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.Teacher;
import com.zks.sas.tool.DateConvert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public boolean save(Teacher t) {
        String dateStr = DateConvert.convertToString(t.getTeaBtd());
        String sql = "insert into teacher" +
                "(teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states) " +
                "VALUES" +
                "('" + t.getTeaNum() + "', '" + t.getTeaName() + "', '" + t.getTeaTitle() + "', " + t.getTeaType() + ", " +
                "'" + dateStr + "', '" + t.getPwd() + "', " + t.getStates() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(Teacher t) {
//        , teaName = '陈泽', teaTitle, teaType, teaBtd, pwd, states
        String sql = "update teacher set " +
                "teaNum = '" + t.getTeaNum() + "', " +
                "teaName = '" + t.getTeaName() + "', " +
                "teaTitle = '" + t.getTeaTitle() + "', " +
                "teaType = " + t.getTeaType() + ", " +
                "teaBtd = '" + DateConvert.convertToString(t.getTeaBtd()) + "', " +
                "pwd = '" + t.getPwd() + "', " +
                "states = " + t.getStates() + " " +
                "where " +
                "teaNum = 20230003";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(String id) {
        String sql = "delete from teacher where teaNum = '"  + id + "'";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public Teacher findById(String id) {
        Teacher teacher = null;
        String sql = "select teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2 from teacher " +
                "where teaNum = '" + id + "'";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String teaNum = rs.getString(i++);
                String teaName = rs.getString(i++);
                String teaTitle = rs.getString(i++);
                int teaType = rs.getInt(i++);
                Date teaBtd = rs.getDate(i++);
                String pwd = rs.getString(i++);
                int states = rs.getInt(i++);
                int roleId = rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);

                teacher = new Teacher(teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2);
            }
        } catch (SQLException i) {
            i.printStackTrace();
        } finally {
            dbManager.close();
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teas = new ArrayList<>();
        String sql = "select teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2 from teacher";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String teaNum = rs.getString(i++);
                String teaName = rs.getString(i++);
                String teaTitle = rs.getString(i++);
                int teaType = rs.getInt(i++);
                Date teaBtd = rs.getDate(i++);
                String pwd = rs.getString(i++);
                int states = rs.getInt(i++);
                int roleId = rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);


                Teacher teacher = new Teacher(teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2);
                teas.add(teacher);
            }
        } catch (SQLException i) {
            i.printStackTrace();
        } finally {
            dbManager.close();
        }
        return teas;
    }

    @Override
    public Teacher findByNameAndPwd(String num, String pwd) {
        Teacher t = null;
        String sql = "select teaNum, teaName, teaTitle, teaType, teaBtd, states,roleId, key1, key2 from teacher " +
                "where teaNum= ? and pwd = ?";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql, num, pwd);
        try {
            if (rs.next()) {
                int i = 1;
                String teaNum = rs.getString(i++);
                String teaName = rs.getString(i++);
                String teaTitle = rs.getString(i++);
                int teaType = rs.getInt(i++);
                Date teaBtd = rs.getDate(i++);
                int states = rs.getInt(i++);
                int roleId = rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);
                t = new Teacher(teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return t;
    }



    @Override
    public int getRowCount() {
        int rowCount = 0;
        String sql = "select count(*) from teacher";
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
    public List<Teacher> getNowPageData(int pageSize, int nowPage) {
        List<Teacher> teas = new ArrayList<>();
        String sql = "select teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states,roleId, key1, key2 from teacher " +
                "limit " + (nowPage - 1) * pageSize + ", " + pageSize;
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String teaNum = rs.getString(i++);
                String teaName = rs.getString(i++);
                String teaTitle = rs.getString(i++);
                int teaType = rs.getInt(i++);

                Date teaBtd = rs.getDate(i++);
                String pwd = rs.getString(i++);
                int states = rs.getInt(i++);
                int roleId = rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);
                Teacher tea = new Teacher(teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2);
                teas.add(tea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return teas;
    }


}
