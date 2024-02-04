package com.zks.sas.dao;

import com.zks.sas.entity.ClassInfo;

import java.util.List;

public interface IClassInfoDAO {
    //新增
    public boolean save(ClassInfo classInfo);
    public boolean update(ClassInfo classInfo);
    public boolean deleteById(int id);
    public ClassInfo findById(int id);
    public List<ClassInfo> findAll();
    //  分页相关方法
    public int getRowCount();
    public int getPageCount(int pageSize);
    public List<ClassInfo> getNowPageData(int pageSize, int nowPage);
}
