package com.zks.sas.dao;

import com.zks.sas.entity.Score;
import com.zks.sas.entity.Teacher;

import java.sql.ResultSet;
import java.util.List;

public interface IScoreDAO {
    //新增
    public boolean save(Score s);
    public boolean update(Score s);
    public boolean deleteById(int id);
    public Score findById(int id);
    public ResultSet findByStuId(String stuId);
    public List<Score> findAll();

    public int getRowCount();
    public int getPageCount(int pageSize);
    public List<Score> getNowPageData(int pageSize, int nowPage);
}
