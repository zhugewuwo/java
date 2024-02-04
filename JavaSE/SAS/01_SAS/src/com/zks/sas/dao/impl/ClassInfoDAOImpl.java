package com.zks.sas.dao.impl;

import com.zks.sas.dao.IClassInfoDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.ClassInfo;
import com.zks.sas.entity.Teacher;
import com.zks.sas.tool.DateConvert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassInfoDAOImpl implements IClassInfoDAO {
    @Override
    public boolean save(ClassInfo classInfo) {
        String dateStr = DateConvert.convertToString(classInfo.getGrade());
        String sql = "insert into classinfo " +
                "(clsId, clsName, grade, stuCount, teaNum, key1, key2)" +
                "values " +
                "(" + classInfo.getClsId() + ", '" + classInfo.getClsName() + "', " +
                "'" + dateStr + "', " + classInfo.getStuCount() + "," +
                "'" + classInfo.getTeaNum() + "', '" + classInfo.getKey1() + "', " + classInfo.getKey2() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(ClassInfo classInfo) {
        String sql = "update classinfo set clsName = '软工5班', grade = '2020-10-01', teaNum = '19890403' where clsId = 49";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from classInfo where clsId = '"  + id + "'";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public ClassInfo findById(int id) {
        ClassInfo classInfo = null;
        String sql = "select clsId, clsName, grade, stuCount, teaNum, key1, key2 from classinfo " +
                "where clsId = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int clsId = rs.getInt(i++);
                String clsName = rs.getString(i++);
                Date grade = rs.getDate(i++);
                int stuCount = rs.getInt(i++);
                String teaNum = rs.getString(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);

                classInfo = new ClassInfo(clsId, clsName, grade, stuCount, teaNum, key1, key2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return classInfo;
    }
//mainpanel msgPanel
    @Override
    public List<ClassInfo> findAll() {
        List<ClassInfo> classInfos = new ArrayList<>();
        String sql = "select clsId, clsName, grade, stuCount, teaNum, key1, key2 from classinfo";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int clsId = rs.getInt(i++);
                String clsName = rs.getString(i++);
                Date grade = rs.getDate(i++);
                int stuCount = rs.getInt(i++);
                String teaNum = rs.getString(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);

                ClassInfo classInfo = new ClassInfo(clsId, clsName, grade, stuCount, teaNum, key1, key2);
                classInfos.add(classInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            dbManager.close();
        }
        return classInfos;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        String sql = "select count(*) from classInfo";
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
    public List<ClassInfo> getNowPageData(int pageSize, int nowPage) {
        List<ClassInfo> cls = new ArrayList<>();
        String sql = "select clsId, clsName,grade,stuCount,teaNum,key1,key2 from classinfo " +
                "limit " + (nowPage - 1) * pageSize + ", " + pageSize;
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                int clsId = rs.getInt(i++);
                String clsName = rs.getString(i++);
                Date grade = rs.getDate(i++);
                int stuCount = rs.getInt(i++);
                String  teaNum = rs.getString(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);
                ClassInfo c = new ClassInfo(clsId, clsName,grade,stuCount,teaNum,key1,key2);
                cls.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return cls;
    }
}
