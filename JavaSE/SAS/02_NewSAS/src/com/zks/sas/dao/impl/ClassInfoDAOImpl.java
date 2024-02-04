package com.zks.sas.dao.impl;

import com.zks.sas.dao.IClassInfoDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.ClassInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClassInfoDAOImpl implements IClassInfoDAO {

    @Override
    public boolean save(ClassInfo classInfo) {
        String sql = "insert into classinfo (clsName, grade, stuCount, teaNum, key1, key2) VALUES ('"+classInfo.getClsName()+"',"+classInfo.getGrade()+","+classInfo.getStuCount()+",'"+classInfo.getTeaNum()+"','"+classInfo.getKey1()+"',"+classInfo.getKey2()+")";
        DBManager dbManager = new DBManager();

        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(ClassInfo classInfo) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public ClassInfo findById(int id) {
        ClassInfo classInfo = null;
        String sql = "select clsId,clsName, grade, stuCount, teaNum, key1, key2 from classinfo where interval clsId = "+id+"";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()){
                int i = 1;
                classInfo = new ClassInfo(rs.getString(i++),rs.getDate(i++),rs.getInt(i++), rs.getString(i++),rs.getString(i++),rs.getInt(i++));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classInfo;
    }

    @Override
    public List<ClassInfo> findAll() {
        return null;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int etPageCount(int pageSize) {
        return 0;
    }

    @Override
    public List<ClassInfo> etNowPageData(int pageSize, int nowPage) {
        return null;
    }
}
