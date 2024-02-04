package com.zks.sas.dao.impl;

import com.zks.sas.dao.IStudentDAO;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.Student;
import com.zks.sas.tool.DateConvert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public boolean save(Student s) {
        String dateStr = DateConvert.convertToString(s.getStuBtd());
        String sql = "insert into student" +
                "(stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2)" +
                "VALUES " +
                "('" + s.getStuNum() + "', " + s.getClsId() + ", '" + s.getStuName() + "', " +
                "'" + dateStr + "', '" + s.getPwd() + "', " + s.getStates() + ", " +
                "" + s.getSex() + ",  '" + s.getKey1() + "', " + s.getKey2() + ")";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean update(Student s) {
        String sql = "update student set stuNum = '" + s.getStuNum() + "', clsId = " + s.getClsId() + ", " +
                "stuName = '" + s.getStuName() + "', stuBtd = '" + DateConvert.convertToString(s.getStuBtd()) + "', " +
                "pwd = '" + s.getPwd() + "', states = " + s.getStates() + ", sex = " + s.getSex() + ", " +
                "key1 = '" + s.getKey1() + "', key2 = " + s.getKey2() + " " +
                "where stuNum = '" + s.getStuNum() + "'";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public boolean deleteById(String id) {
        String sql = "delete from student where stuNum = " + id + "";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public Student findById(String id) {
        Student student = null;
        String sql = "select stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2 " +
                "from student " +
                "where stuNum = " + id + "";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String stuNum = rs.getString(i++);
                int clsId = rs.getInt(i++);
                String stuName = rs.getString(i++);
                Date stuBtd = rs.getDate(i++);
                String pwd = rs.getString(i++);
                int states = rs.getInt(i++);
                int sex = rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);

                student = new Student(stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2 from student";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String stuNum = rs.getString(i++);
                int clsId = rs.getInt(i++);
                String stuName = rs.getString(i++);
                Date stuBtd = rs.getDate(i++);
                String pwd = rs.getString(i++);
                int states = rs.getInt(i++);
                int sex = rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);

                Student student = new Student(stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return students;
    }

    @Override
    public Student findByNameAndPwd(String stuNum, String pwd) {
        Student s = null;
        String sql = "select clsId, stuName, stuBtd, states, sex, key1, key2 from student " +
                " where stuNum = '" + stuNum + "' and pwd ='" + pwd + "'";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            if (rs.next()){
                int i = 1;
                int clsId = rs.getInt(i++);
                String stuName = rs.getString(i++);
                Date stuBtd = rs.getDate(i++);
                int states = rs.getInt(i++);
                int sex= rs.getInt(i++);
                String key1 = rs.getString(i++);
                int key2 = rs.getInt(i++);
                s = new Student(stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbManager.close();
        }
        return s;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        String sql = "select count(*) from student";
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
    public List<Student> getNowPageData(int pageSize, int nowPage) {
        List<Student> stus = new ArrayList<>();
        String sql = "select stuNum, stuName, clsId, stuBtd from student " +
                "limit " + (nowPage - 1) * pageSize + ", " + pageSize;
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                int i = 1;
                String teaNum = rs.getString(i++);
                String teaName = rs.getString(i++);
                int clsId = rs.getInt(i++);
                Date stuBtd = rs.getDate(i++);

                Student stu = new Student(teaNum, clsId,teaName, stuBtd,"",0,0,"",0);
                stus.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
        return stus;
    }
}
