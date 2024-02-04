package com.zks.sas.dao;

import com.zks.sas.entity.Student;

import java.util.List;

public interface IStudentDAO {
    //新增
    public boolean save(Student s);
    public boolean update(Student s);
    public boolean deleteById(String id);
    public Student findById(String id);
    public List<Student> findAll();
    public Student findByNameAndPwd(String stuNum, String pwd);

    public int getRowCount();
    public int getPageCount(int pageSize);
    public List<Student> getNowPageData(int pageSize, int nowPage);
}
