package com.zks.sas.test;

import com.zks.sas.dao.IClassInfoDAO;
import com.zks.sas.dao.impl.ClassInfoDAOImpl;
import com.zks.sas.entity.ClassInfo;


import java.util.Date;

public class ClassInfoDAOTest {
    static void saveTest() {
        //新增测试
        Date bth = new Date();
        ClassInfo classInfo = new ClassInfo("软工5班", bth, 44, "19890403", "",1);
        IClassInfoDAO classInfoDAO = (IClassInfoDAO) new ClassInfoDAOImpl();
        boolean ok = classInfoDAO.save(classInfo);
        if(ok) {
            System.out.println("新增班级成功");
        }else {
            System.out.println("新增班级失败");
        }
    }

    static void updateTest() {
        Date bth = new Date();
        ClassInfo classInfo = new ClassInfo("软工5班", bth, 44, "19890403", "", 1);
        IClassInfoDAO classInfoDAO = (IClassInfoDAO) new ClassInfoDAOImpl();
        boolean ok = classInfoDAO.update(classInfo);
        if (ok) {
            System.out.println("修改班级信息成功");
        } else {
            System.out.println("修改班级信息失败");
        }
    }

    static void deleteTest() {
        IClassInfoDAO ciDAO = new ClassInfoDAOImpl();
        if(ciDAO.deleteById(49)) {
            System.out.println("删除班级信息成功");
        }else {
            System.out.println("删除班级信息失败");
        }
    }

    public static void main(String[] args) {
        //saveTest();
        updateTest();
    }
}
