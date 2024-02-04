package com.zks.sas.dao;

import com.zks.sas.entity.CourseArrangement;

import java.util.List;

public interface ICourseArrangementDAO {
    //新增
    public boolean save(CourseArrangement ca);
    public boolean update(CourseArrangement ca);
    public boolean deleteById(int id);
    public CourseArrangement findById(int id);
    public List<CourseArrangement> findAll();

    public int getRowCount();
    public int getPageCount(int pageSize);
    public List<CourseArrangement> getNowPageData(int pageSize, int nowPage);
}
