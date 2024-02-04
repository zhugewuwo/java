package com.zks.sas.db;

import java.sql.*;

public class DBManager {
    private Connection con = null;
    private Statement sta = null;
    private ResultSet rs = null;
    private PreparedStatement psta = null;

    /**
     * 增删改
     * @param sql
     * @return
     */
    public int update(String sql){
        int row = 0;
        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc");
            String url = "jdbc:mysql://localhost:3306/sas_21";
            con = DriverManager.getConnection(url, "root","root");
            sta = con.createStatement();
            row = sta.executeUpdate(sql);

        } catch (Exception e) {;
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 查
     * @param sql
     * @return
     */
    public ResultSet query(String sql){
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc");
            String url = "jdbc:mysql://localhost:3306/sas_21";
            con = DriverManager.getConnection(url, "root","root");
            sta = con.createStatement();
            rs = sta.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }


}
