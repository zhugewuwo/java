package com.zks.sas.dao;

import com.zks.sas.db.DBManager;
import com.zks.sas.entity.SASClassInfo;
import com.zks.sas.entity.SASTeacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SASDAO {
    public List<SASClassInfo> sasClassInfoByLesName(String lesName) {
        List<SASClassInfo> list = new ArrayList<>();
        String sql =
                "select " +
                        "  stu.clsId as 'clsId', ci.clsName 'clsName',  count(s.score) as 'stuCount', avg(s.score) as 'scoreAvg'\n" +
                        "from score s\n" +
                        "inner join student stu on (stu.stuNum = s.stuNum)\n" +
                        "inner join classinfo ci on (ci.clsId = stu.clsId)\n" +
                        "where s.lesId=(select lesId from lesson where lesName='" + lesName + "')\n" +
                        "group by stu.clsId\n" +
                        "order by avg(s.score);";
        //DBM
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                SASClassInfo info = new SASClassInfo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SASTeacher> sasTeacherByLesName(String lesName) {
        List<SASTeacher> list = new ArrayList<>();
        String sql =
                "select " +
                        "  tea.teaNum as 'teaNum', tea.teaName 'teaName',  count(s.score) as 'stuCount', avg(s.score) as 'scoreAvg'\n" +
                        "from score s\n" +
                        "inner join coursearrangement cou on (cou.lesId = s.lesId)\n" +
                        "inner join teacher tea on (tea.teaNum = cou.teaNum)\n" +
                        "where s.lesId=(select lesId from lesson where lesName='" + lesName + "')\n" +
                        "group by tea.teaNum\n" +
                        "order by avg(s.score);";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while (rs.next()) {
                SASTeacher info = new SASTeacher(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
