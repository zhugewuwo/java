package com.zks.sas.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMainFrame extends JFrame {
    public TeacherMainFrame() throws HeadlessException {
        setSize(1200, 700);
        setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();//菜单栏
        setJMenuBar(menuBar);
        //1 老师管理
        JMenu menu1 = new JMenu("老师管理");
        menuBar.add(menu1);
        JMenuItem menuItem21 = new JMenuItem("老师信息列表");
        menu1.add(menuItem21);
        menuItem21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherListFrame().setVisible(true);
            }
        });
        JMenuItem menuItem22 = new JMenuItem("新增老师信息");
        menu1.add(menuItem22);
        menuItem22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherAddFrame().setVisible(true);
            }
        });

        //2 班级管理
        JMenu menu2 = new JMenu("班级管理");
        menuBar.add(menu2);
        JMenuItem menuItem31 = new JMenuItem("班级信息列表");
        menu2.add(menuItem31);
        menuItem31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassInfoListFrame().setVisible(true);
            }
        });
        JMenuItem menuItem32 = new JMenuItem("新增班级信息");
        menu2.add(menuItem32);
        menuItem32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassInfoAddFrame().setVisible(true);
            }
        });

        //3 学生管理
        JMenu menu3 = new JMenu("学生管理");
        menuBar.add(menu3);
        JMenuItem menuItem41 = new JMenuItem("学生信息列表");
        menu3.add(menuItem41);

        JMenuItem menuItem42 = new JMenuItem("新增学生信息");
        menu3.add(menuItem42);
        menuItem41.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentListFrame().setVisible(true);
            }
        });

        menuItem41.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentAddFrame().setVisible(true);
            }
        });


        //4 成绩分析
        JMenu menu4 = new JMenu("成绩分析");
        menuBar.add(menu4);
        JMenuItem menuItem51 = new JMenuItem("按班级分析");
        menu4.add(menuItem51);
        menuItem51.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScoreSASClassInfoFrame().setVisible(true);
            }
        });

        JMenuItem menuItem52 = new JMenuItem("按老师分析");
        menu4.add(menuItem52);
        menuItem52.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScoreSASTeacherFrame().setVisible(true);
            }
        });


    }

    public static void main(String[] args) {
        new TeacherMainFrame().setVisible(true);
    }
}
