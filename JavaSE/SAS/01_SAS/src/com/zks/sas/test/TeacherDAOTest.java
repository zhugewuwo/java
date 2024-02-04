package com.zks.sas.test;


import com.zks.sas.dao.ITeacherDAO;
import com.zks.sas.dao.impl.TeacherDAOImpl;
import com.zks.sas.entity.Teacher;
import com.zks.sas.tool.DateConvert;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TeacherDAOTest {

    static void saveTest() {
        String dateStr = "1980-1-1";
        Date bth = new Date();
        //? String -> Date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            bth = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //新增测试
        Teacher t = new Teacher("20230004", "k", "教授", 1, bth, "1", 1, 1, "1", 1);
        ITeacherDAO teacherDAO = (ITeacherDAO) new TeacherDAOImpl();
        boolean ok = teacherDAO.save(t);
        if(ok) {
            System.out.println("新增老师成功");
        }else {
            System.out.println("新增老师失败");
        }
    }

    static void updateTest() {
        String dateStr = "1980-1-1";
        Date btd = DateConvert.convertToDate(dateStr);
        //修改测试
        Teacher t = new Teacher("20230004", "孔明", "教授", 1, btd, "1", 1, 1, "1", 1);
        ITeacherDAO teacherDAO = (ITeacherDAO) new TeacherDAOImpl();
        boolean ok = teacherDAO.update(t);
        if(ok) {
            System.out.println("修改老师信息成功");
        }else {
            System.out.println("修改老师信息失败");
        }
    }
    static void deleteTest() {
        ITeacherDAO teacherDAO = new TeacherDAOImpl();
        if(teacherDAO.deleteById(null)) {
            System.out.println("删除老师成功");
        }else {
            System.out.println("删除老师失败");
        }
    }

    public static void findAll() {
        ITeacherDAO teacherDAO = new TeacherDAOImpl();
        List<Teacher> teas = teacherDAO.findAll();
        for (Teacher tea: teas) {
            System.out.println(tea);
        }
    }

    public static void findById() {
        ITeacherDAO teacherDAO = new TeacherDAOImpl();
        Teacher tea = teacherDAO.findById("20230003");
        if (tea == null) {
            System.out.println("不存在");
        } else {
            System.out.println(tea);
        }
    }

    public static void main(String[] args) {
        //saveTest();
        //updateTest();
        //deleteTest();
        //findAll();
        findById();

    }

}
