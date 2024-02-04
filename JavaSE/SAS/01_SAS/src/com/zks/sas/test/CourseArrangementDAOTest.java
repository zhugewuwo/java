package com.zks.sas.test;

import com.zks.sas.dao.ICourseArrangementDAO;
import com.zks.sas.dao.impl.CourseArrangementDAOImpl;
import com.zks.sas.entity.CourseArrangement;

import java.util.Date;

public class CourseArrangementDAOTest {

    static void updateTest() {
        CourseArrangement ca = new CourseArrangement(44, 48, 2, "19890403", 2023, 2);
        ICourseArrangementDAO caDAO = new CourseArrangementDAOImpl();
        boolean ok = caDAO.update(ca);
        if (ok) {
            System.out.println("修改课程安排成功");
        } else {
            System.out.println("修改课程安排失败");
        }
    }

    static void deleteTest() {
        ICourseArrangementDAO caDAO = new CourseArrangementDAOImpl();
        if(caDAO.deleteById(44)) {
            System.out.println("删除课程信息成功");
        }else {
            System.out.println("删除课程信息失败");
        }
    }

    static void saveTest() {
        //新增测试
        Date bth = new Date();
        CourseArrangement ca = new CourseArrangement(44, 48, 2, "19890403", 2023, 1);
        ICourseArrangementDAO courseArrangementDAO = (ICourseArrangementDAO) new CourseArrangementDAOImpl();
        boolean ok = courseArrangementDAO.deleteById(44);
        if (ok) {
            System.out.println("新增课程安排成功");
        } else {
            System.out.println("新增课程安排失败");
        }
}

    public static void main(String[] args) {
        //saveTest();
        //updateTest();
        deleteTest();
    }

}
