package com.zks.sas.view;

import com.zks.sas.dao.IStudentDAO;
import com.zks.sas.dao.ITeacherDAO;
import com.zks.sas.dao.impl.StudentDAOImpl;
import com.zks.sas.dao.impl.TeacherDAOImpl;
import com.zks.sas.entity.Student;
import com.zks.sas.entity.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtName;
    private JPasswordField txtPwd;
    public LoginFrame() {
        setTitle("校务通 - 登录");
        setSize(458, 275);
        setResizable(false);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JLabel lblMsg = new JLabel("");
        panel.add(lblMsg);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(null);

        JLabel label = new JLabel("类型:", SwingConstants.RIGHT);
        label.setBounds(46, 92, 100, 23);
        panel_1.add(label);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("老师登录");
        comboBox.addItem("学生登录");
        comboBox.setBounds(146, 92, 140, 23);
        panel_1.add(comboBox);

        txtName = new JTextField();
        txtName.setBounds(146, 122, 140, 23);
        panel_1.add(txtName);

        JLabel label_1 = new JLabel("用户:", new ImageIcon("imgs/base/user.png"), SwingConstants.RIGHT);
        label_1.setBounds(46, 122, 100, 23);
        panel_1.add(label_1);

        JLabel label_2 = new JLabel("密码:", new ImageIcon("imgs/base/pass.png"), SwingConstants.RIGHT);
        label_2.setBounds(46, 152, 100, 23);
        panel_1.add(label_2);

        txtPwd = new JPasswordField();
        txtPwd.setBounds(146, 152, 140, 23);
        panel_1.add(txtPwd);

        JButton btnCs = new JButton("退出", new ImageIcon("imgs/base/exit.png"));
        btnCs.addActionListener(e -> {
            System.exit(0);
        });
        btnCs.setBounds(296, 152, 93, 23);
        panel_1.add(btnCs);

        JButton btnLogin = new JButton("登录", new ImageIcon("imgs/base/login.png"));
        btnLogin.setBounds(296, 122, 93, 23);
        panel_1.add(btnLogin);

        JButton btnPwd = new JButton("找回密码");
        btnPwd.setBounds(296, 92, 93, 23);
        panel_1.add(btnPwd);
        btnPwd.addActionListener(e -> {
            lblMsg.setText("");
            lblMsg.setForeground(Color.BLACK);
            int index = comboBox.getSelectedIndex();
            if (index == 0) {
                JOptionPane.showMessageDialog(null, "只有学生才可以找回密码,老师请联系管理员!", "错误", JOptionPane.ERROR_MESSAGE);
                lblMsg.setText("只有学生才可以找回密码,老师请联系管理员!");
                lblMsg.setForeground(Color.RED);
                return;
            }
            new StudentFindPwdFrame(DataTool.s.getStuNum()).setVisible(true);
        });

        JLabel lblNewLabel = new JLabel("系统登录");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(170, 27, 134, 40);
        panel_1.add(lblNewLabel);
        btnCs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnLogin.addActionListener( e-> {
            if (!checkData()) return;
            String userName = txtName.getText();
            String pwd = new String(txtPwd.getPassword());
            if (comboBox.getSelectedIndex() == 0) {//老师登录
                ITeacherDAO teacherDAO = new TeacherDAOImpl();
                Teacher t = teacherDAO.findByNameAndPwd(userName, pwd);
                if (t == null) {
                    JOptionPane.showMessageDialog(null, "用户名密码错误!", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    DataTool.t = t;
                    TeacherMainFrame frameTeacher = new TeacherMainFrame();
                    frameTeacher.setVisible(true);
                    dispose();
                }
            } else {//学生登录// if(comboBox.getSelectedIndex() == 1)
                IStudentDAO studentDAO = new StudentDAOImpl();
                Student s = studentDAO.findByNameAndPwd(userName, pwd);
                if (s == null) {
                    JOptionPane.showMessageDialog(null, "用户名密码错误!", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    DataTool.s = s;
                    new StudentMainFrame().setVisible(true);
                    dispose();
                }

            }

        });
    }
    private boolean checkData() {
        String userName = txtName.getText();
        if (userName.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "用户名不能为空!", "错误", JOptionPane.ERROR_MESSAGE);
            txtName.requestFocus();
            return false;
        }
        String pwd = new String(txtPwd.getPassword());
        if (pwd.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "密码不能为空!", "错误", JOptionPane.ERROR_MESSAGE);
            txtPwd.requestFocus();
            return false;
        }
        return true;
    }


}
