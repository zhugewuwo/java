package com.zks.sas.dao;

import com.zks.sas.entity.Lesson;

import java.sql.ResultSet;
import java.util.List;

public interface ILessonDAO {
    //新增
    public boolean save(Lesson l);
    public boolean update(Lesson l);
    public boolean deleteById(int id);
    public Lesson findById(int id);
    public ResultSet findByStuId(String id);
    public List<Lesson> findAll();
}
