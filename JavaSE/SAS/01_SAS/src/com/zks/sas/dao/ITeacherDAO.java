package com.zks.sas.dao;

import com.zks.sas.entity.Teacher;

import java.util.List;

public interface ITeacherDAO {
    //新增
    public boolean save(Teacher t);
    public boolean update(Teacher t);
    public boolean deleteById(String id);
    public Teacher findById(String id);
    public List<Teacher> findAll();
    public Teacher findByNameAndPwd(String num, String pwd);

    public int getRowCount();
    public int getPageCount(int pageSize);
    public List<Teacher> getNowPageData(int pageSize, int nowPage);
}
