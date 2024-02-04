package com.zks.sas.view;

import com.zks.sas.dao.IRestPwdDAO;
import com.zks.sas.dao.IStudentDAO;
import com.zks.sas.dao.impl.RestPwdDAOImpl;
import com.zks.sas.dao.impl.StudentDAOImpl;
import com.zks.sas.entity.RestPwd;
import com.zks.sas.entity.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFindPwdFrame extends JFrame {
    private JTextField txtStuNum;
    private JTextField txtQ1;
    private JTextField txtA1;
    private JTextField txtQ2;
    private JTextField txtA2;
    private JTextField txtQ3;
    private JTextField txtA3;
    private JTextField txtPwd1;
    private JTextField txtPwd2;
    private String id;
    public StudentFindPwdFrame(String id) {
        this.id = id;
        setSize(480, 556);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        getContentPane().add(panel, BorderLayout.SOUTH);

        JLabel lblMsg = new JLabel("");
        panel.add(lblMsg);
        lblMsg.setText("提示:");

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(null);

        JLabel label = new JLabel("找回密码");
        label.setBounds(193, 25, 114, 29);
        panel_1.add(label);

        JLabel label_1 = new JLabel("学生编号:");
        label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_1.setBounds(76, 60, 74, 27);
        panel_1.add(label_1);

        txtStuNum = new JTextField();
        txtStuNum.setColumns(10);
        txtStuNum.setBounds(161, 64, 146, 21);
        panel_1.add(txtStuNum);
        txtStuNum.setText(id);

        JButton btnLoadQ = new JButton("加载问题");
        btnLoadQ.setBounds(304, 64, 93, 21);
        panel_1.add(btnLoadQ);

        JLabel label_2 = new JLabel("问题一    :");
        label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_2.setBounds(76, 105, 74, 27);
        panel_1.add(label_2);

        txtQ1 = new JTextField();
        txtQ1.setEditable(false);
        txtQ1.setColumns(10);
        txtQ1.setBounds(161, 105, 236, 21);
        panel_1.add(txtQ1);

        JLabel label_3 = new JLabel("答案一    :");
        label_3.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_3.setBounds(76, 142, 74, 27);
        panel_1.add(label_3);

        txtA1 = new JTextField();
        txtA1.setColumns(10);
        txtA1.setBounds(161, 146, 236, 21);
        panel_1.add(txtA1);

        JLabel label_4 = new JLabel("问题二    :");
        label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_4.setBounds(76, 195, 74, 27);
        panel_1.add(label_4);

        txtQ2 = new JTextField();
        txtQ2.setEditable(false);
        txtQ2.setColumns(10);
        txtQ2.setBounds(161, 195, 236, 21);
        panel_1.add(txtQ2);

        JLabel label_5 = new JLabel("答案二    :");
        label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_5.setBounds(76, 232, 74, 27);
        panel_1.add(label_5);

        txtA2 = new JTextField();
        txtA2.setColumns(10);
        txtA2.setBounds(161, 236, 236, 21);
        panel_1.add(txtA2);

        JLabel label_6 = new JLabel("问题三    :");
        label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_6.setBounds(76, 279, 74, 27);
        panel_1.add(label_6);

        txtQ3 = new JTextField();
        txtQ3.setEditable(false);
        txtQ3.setColumns(10);
        txtQ3.setBounds(161, 279, 236, 21);
        panel_1.add(txtQ3);

        JLabel label_7 = new JLabel("答案三    :");
        label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_7.setBounds(76, 316, 74, 27);
        panel_1.add(label_7);

        txtA3 = new JTextField();
        txtA3.setColumns(10);
        txtA3.setBounds(161, 320, 236, 21);
        panel_1.add(txtA3);

        JButton btnCheckA = new JButton("验证答案");
        btnCheckA.setBounds(304, 351, 93, 23);
        btnCheckA.setEnabled(false);
        panel_1.add(btnCheckA);

        txtPwd1 = new JTextField();
        txtPwd1.setEnabled(false);
        txtPwd1.setColumns(10);
        txtPwd1.setBounds(161, 380, 236, 21);
        panel_1.add(txtPwd1);

        JLabel label_8 = new JLabel("新密码   :");
        label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_8.setBounds(76, 380, 74, 27);
        panel_1.add(label_8);

        JLabel label_9 = new JLabel("确认密码 :");
        label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_9.setBounds(76, 417, 74, 27);
        panel_1.add(label_9);

        txtPwd2 = new JTextField();
        txtPwd2.setEnabled(false);
        txtPwd2.setColumns(10);
        txtPwd2.setBounds(161, 421, 236, 21);
        panel_1.add(txtPwd2);

        JButton btnSavePwd = new JButton("保存密码");
        btnSavePwd.setEnabled(false);
        btnSavePwd.setBounds(304, 454, 93, 23);
        panel_1.add(btnSavePwd);
        btnLoadQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMsg.setText("提示:");
                lblMsg.setForeground(Color.BLACK);
                String stuNum = txtStuNum.getText();
                if (stuNum.trim().length() == 0) {
                    lblMsg.setText("提示:学生编号不能为空!");
                    lblMsg.setForeground(Color.RED);
                    return;
                }
                if (stuNum.trim().length() != 12) {
                    lblMsg.setText("提示:学生编号必须是12位数字!");
                    lblMsg.setForeground(Color.RED);
                    return;
                }
                IRestPwdDAO restPwdDAO = new RestPwdDAOImpl();
                RestPwd restPwd = restPwdDAO.findById(stuNum);
                if (restPwd == null) {
                    lblMsg.setText("提示:你还没有设置密码找回问题,此功能无法使用!");
                    lblMsg.setForeground(Color.RED);
                    return;
                }
                if (restPwd.getRequestCount() == 0) {
                    lblMsg.setText("提示:你的回答次数已达三次,无法使用!");
                    lblMsg.setForeground(Color.RED);
                    return;
                }
                //显示问题
                txtQ1.setText(restPwd.getQuestion1());
                txtQ2.setText(restPwd.getQuestion2());
                txtQ3.setText(restPwd.getQuestion3());
                btnCheckA.setEnabled(true);
                lblMsg.setText("提示:你有" + restPwd.getRequestCount()+ "次机会!");
            }
        });

        btnCheckA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMsg.setText("提示:");
                lblMsg.setForeground(Color.BLACK);
                String a1 = txtA1.getText();
                if (a1.trim().length() == 0) {
                    lblMsg.setText("提示:答案一不能为空!");
                    lblMsg.setForeground(Color.RED);
                    txtA1.requestFocus();
                    return;
                }
                String a2 = txtA2.getText();
                if (a2.trim().length() == 0) {
                    lblMsg.setText("提示:答案二不能为空!");
                    lblMsg.setForeground(Color.RED);
                    txtA2.requestFocus();
                    return;
                }
                String a3 = txtA3.getText();

                IRestPwdDAO restPwdDAO = new RestPwdDAOImpl();
                //实体类中包含正确答案
                RestPwd restPwd = restPwdDAO.findById(txtStuNum.getText());
                //答案正确
                if (a1.equals(restPwd.getAnswer1()) && a2.equals(restPwd.getAnswer2()) &&
                        a3.equals(restPwd.getAnswer3())) {
                    txtPwd1.setEnabled(true);
                    txtPwd2.setEnabled(true);
                    btnSavePwd.setEnabled(true);
                    lblMsg.setText("提示:密码正确,请输入新答案!");
                    restPwd.setRequestCount(3);
                    restPwdDAO.update(restPwd);//重置为3次
                    txtStuNum.setEditable(false);
                } else {
                    lblMsg.setForeground(Color.RED);
                    restPwd.setRequestCount(restPwd.getRequestCount() - 1);
                    restPwdDAO.update(restPwd);
                    lblMsg.setText("提示:找回密码问题错误,您还有" + (restPwd.getRequestCount()) + "次机会!");
                    if (restPwd.getRequestCount() == 0) {
                        btnCheckA.setEnabled(false);
                    }
                }
            }

        });
        btnSavePwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMsg.setText("提示:");
                lblMsg.setForeground(Color.BLACK);
                String pwd1 = txtPwd1.getText();
                String pwd2 = txtPwd2.getText();
                if (pwd1.trim().length() == 0) {
                    lblMsg.setText("提示:新密码不能为空!");
                    lblMsg.setForeground(Color.RED);
                    txtPwd1.requestFocus();
                    return;
                }
                if (pwd2.trim().length() == 0) {
                    lblMsg.setText("提示:确认密码不能为空!");
                    lblMsg.setForeground(Color.RED);
                    txtPwd2.requestFocus();
                    return;
                }
                if (!pwd1.equals(pwd2)) {
                    lblMsg.setText("提示:两次输入密码不一致!");
                    lblMsg.setForeground(Color.RED);
                    return;
                }
                IStudentDAO studentDAO = new StudentDAOImpl();
                Student student = studentDAO.findById(txtStuNum.getText());
                student.setPwd(pwd1);
                if (studentDAO.update(student)) {
                    lblMsg.setText("提示:修改密码成功!");
                    lblMsg.setForeground(Color.BLUE);
                    return;
                }
            }
        });
    }


}
