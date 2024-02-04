package com.zks.sas.db;

import java.sql.*;

public class DBManager {
    //属性的声明, 还没创建
    private Connection con = null;
    private Statement sta = null;
    private ResultSet rs = null;
    private PreparedStatement psta = null;
    /**
     * 用来执行insert update delete的SQL语句
     * @param sql 要执行的SQL
     * @return SQL语句影响的行数
     */
    public int update(String sql) {
        int row = 0;
        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sas_21";
            con = DriverManager.getConnection(url, "root", "root");
            sta = con.createStatement();
            row = sta.executeUpdate(sql);//.executeUpdate(sql)
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 用来执行select的SQL语句
     * @param sql 要执行的SQL
     * @return 结果集
     */
    public ResultSet query(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sas_21";
            con = DriverManager.getConnection(url, "root", "root");
            sta = con.createStatement();
            rs = sta.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet query(String sql, Object... values) {
        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sas_21";
            con = DriverManager.getConnection(url, "root", "root");
            psta = con.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                psta.setObject((i + 1), values[i]);
            }
            rs = psta.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭资源
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (sta != null) {
                sta.close();
                sta = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


