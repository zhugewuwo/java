package com.zks.sas.view;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentMainFrame extends JFrame {
    public StudentMainFrame() throws HeadlessException {

        setTitle("校务通 - 学生登录,欢迎您:" + DataTool.s.getStuName());
        setSize(1200, 700);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        getContentPane().add(panel, BorderLayout.NORTH);
        JLabel lblMsg = new JLabel("");
        panel.add(lblMsg);

        lblMsg.setText("欢迎您:" + DataTool.s.getStuName() + " 时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        JMenuBar menuBar = new JMenuBar();//菜单栏
        setJMenuBar(menuBar);

        //4 学生管理
        JMenu menu1 = new JMenu("个人信息维护");
        menuBar.add(menu1);
        JMenuItem menuItem11 = new JMenuItem("修改个人信息");
        menu1.add(menuItem11);
        menuItem11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentUpdateFrame(DataTool.s.getStuNum()).setVisible(true);
            }
        });
        JMenuItem menuItem13 = new JMenuItem("密码找回问题");
        menu1.add(menuItem13);
        menuItem13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentFindPwdFrame(DataTool.s.getStuNum()).setVisible(true);
            }
        });

        JMenu menu2 = new JMenu("信息查询");
        menuBar.add(menu2);
        JMenuItem menuItem21 = new JMenuItem("课程查询");//查询  没有新增修改删除
        menu2.add(menuItem21);
        menuItem21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LessonQueryFrame(DataTool.s.getStuNum()).setVisible(true);
            }
        });

        JMenuItem menuItem22 = new JMenuItem("成绩查询");//查询  没有新增修改删除
        menu2.add(menuItem22);
        menuItem22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseQueryFrame(DataTool.s.getStuNum()).setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        new StudentMainFrame().setVisible(true);
    }
}
