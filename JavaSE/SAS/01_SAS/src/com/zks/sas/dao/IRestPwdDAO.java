package com.zks.sas.dao;

import com.zks.sas.entity.RestPwd;

import java.util.List;

public interface IRestPwdDAO {
    //新增
    public boolean save(RestPwd r);
    public boolean update(RestPwd r);
    public boolean deleteById(String id);
    public RestPwd findById(String id);
    public List<RestPwd> findAll();
}
