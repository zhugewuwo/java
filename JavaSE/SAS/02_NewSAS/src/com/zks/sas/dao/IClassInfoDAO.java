package com.zks.sas.dao;

import com.zks.sas.entity.ClassInfo;

import java.util.List;

public interface IClassInfoDAO {
    public boolean save(ClassInfo classInfo);
    public boolean update(ClassInfo classInfo);
    public boolean deleteById(int id);
    public ClassInfo findById(int id);
    public List<ClassInfo> findAll();
    public int getRowCount();
    public int etPageCount(int pageSize);
    public List<ClassInfo> etNowPageData(int pageSize, int nowPage);
}
