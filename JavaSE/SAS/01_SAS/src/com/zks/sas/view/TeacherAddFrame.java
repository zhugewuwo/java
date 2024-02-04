package com.zks.sas.view;

import com.zks.sas.dao.ITeacherDAO;
import com.zks.sas.dao.impl.TeacherDAOImpl;
import com.zks.sas.entity.Teacher;
import com.zks.sas.tool.DateConvert;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Date;

public class TeacherAddFrame extends JFrame {
    private JTextField txtTeaNum;
    private JTextField txtTeaName;
    private JTextField txtTeaBtd;
    private JTextField txtPwd;
    private JTextField txtWorkerAge;
    private JTextField txtTeaEmail;
    private JLabel lblMsg;
    public TeacherAddFrame() {
        setTitle("新增老师信息");
        setSize(650, 430);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);


        JLabel lblNewLabel_1 = new JLabel("新增老师信息");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(283, 10, 286, 32);
        mainPanel.add(lblNewLabel_1);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u57FA\u7840\u6570\u636E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(27, 63, 597, 144);
        mainPanel.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("老师编号:");
        lblNewLabel_2.setBounds(21, 37, 60, 15);
        panel.add(lblNewLabel_2);

        txtTeaNum = new JTextField();
        txtTeaNum.setBounds(78, 34, 194, 21);
        panel.add(txtTeaNum);
        txtTeaNum.setColumns(10);

        JLabel label = new JLabel("老师姓名:");
        label.setBounds(310, 40, 60, 15);
        panel.add(label);

        txtTeaName = new JTextField();
        txtTeaName.setColumns(10);
        txtTeaName.setBounds(367, 37, 194, 21);
        panel.add(txtTeaName);

        JLabel label_1 = new JLabel("老师职称:");
        label_1.setBounds(21, 73, 60, 15);
        panel.add(label_1);

        JComboBox cmbTeaTitle = new JComboBox();
        cmbTeaTitle.setBounds(78, 69, 194, 23);
        panel.add(cmbTeaTitle);
        cmbTeaTitle.addItem("教授");
        cmbTeaTitle.addItem("副教授");
        cmbTeaTitle.addItem("讲师");
        cmbTeaTitle.addItem("助教");
        cmbTeaTitle.setSelectedIndex(2);



        JLabel label_2 = new JLabel("老师类型:");
        label_2.setBounds(310, 73, 60, 15);
        panel.add(label_2);

        JRadioButton rdBTeaType1 = new JRadioButton("授课老师");
        rdBTeaType1.setBounds(389, 69, 85, 23);
        panel.add(rdBTeaType1);
        rdBTeaType1.setSelected(true);

        JRadioButton rdBTeaType2 = new JRadioButton("导员");
        rdBTeaType2.setBounds(481, 69, 79, 23);
        panel.add(rdBTeaType2);
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(rdBTeaType1);
        bg1.add(rdBTeaType2);

        JLabel label_3 = new JLabel("老师生日:");
        label_3.setBounds(21, 105, 60, 15);
        panel.add(label_3);

        txtTeaBtd = new JTextField();
        txtTeaBtd.setColumns(10);
        txtTeaBtd.setBounds(78, 102, 194, 21);
        panel.add(txtTeaBtd);

        JLabel label_5 = new JLabel("老师状态:");
        label_5.setBounds(310, 109, 60, 15);
        panel.add(label_5);

        JRadioButton rdbStates1 = new JRadioButton("正常工作");
        rdbStates1.setSelected(true);
        rdbStates1.setBounds(389, 105, 85, 23);
        panel.add(rdbStates1);

        JRadioButton rdbStates2 = new JRadioButton("暂停工作");
        rdbStates2.setBounds(481, 105, 91, 23);
        panel.add(rdbStates2);
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(rdbStates1);
        bg2.add(rdbStates2);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "\u5176\u4ED6\u6570\u636E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(27, 217, 597, 102);
        mainPanel.add(panel_1);
        panel_1.setLayout(null);

        JLabel label_4 = new JLabel("初始密码:");
        label_4.setBounds(24, 31, 60, 15);
        panel_1.add(label_4);

        txtPwd = new JTextField();
        txtPwd.setText("123456");
        txtPwd.setColumns(10);
        txtPwd.setBounds(81, 28, 194, 21);
        panel_1.add(txtPwd);

        JLabel label_6 = new JLabel("老师工龄:");
        label_6.setBounds(309, 34, 60, 15);
        panel_1.add(label_6);

        txtWorkerAge = new JTextField();
        txtWorkerAge.setColumns(10);
        txtWorkerAge.setBounds(366, 31, 194, 21);
        panel_1.add(txtWorkerAge);

        JLabel label_7 = new JLabel("老师邮箱:");
        label_7.setBounds(24, 59, 60, 15);
        panel_1.add(label_7);

        txtTeaEmail = new JTextField();
        txtTeaEmail.setColumns(10);
        txtTeaEmail.setBounds(81, 56, 194, 21);
        panel_1.add(txtTeaEmail);

        JButton btnCS = new JButton("取消");
        btnCS.setBounds(523, 329, 93, 23);
        mainPanel.add(btnCS);

        JButton btnOK = new JButton("确定");
        btnOK.setBounds(427, 329, 93, 23);
        mainPanel.add(btnOK);



        JPanel msgPanel = new JPanel();
        msgPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(msgPanel, BorderLayout.SOUTH);
        msgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel labelTS = new JLabel("提示:");
        labelTS.setFont(new Font("宋体", Font.PLAIN, 12));
        msgPanel.add(labelTS);

        lblMsg = new JLabel("");
        lblMsg.setFont(new Font("宋体", Font.PLAIN, 12));
        msgPanel.add(lblMsg);

        btnOK.addActionListener(e -> {
            if (!checkData()) return;//验证不通过,返回
            //新增
            String teaNum = txtTeaNum.getText();
            String teaName = txtTeaName.getText();
            String teaTitle = cmbTeaTitle.getSelectedItem().toString();
            int teaType = 1;
            if (rdbStates2.isSelected()) teaType = 2;
            Date teaBtd = DateConvert.convertToDate(txtTeaBtd.getText());
            String pwd = txtPwd.getText();
            int states = 1;
            if (rdbStates2.isSelected()) states = 0;
            int roleId = 0;
            String key1 = txtTeaEmail.getText();
            int key2 = Integer.parseInt(txtWorkerAge.getText());
            Teacher t = new Teacher(teaNum, teaName, teaTitle, teaType, teaBtd, pwd, states, roleId, key1, key2);
            ITeacherDAO teacherDAO = new TeacherDAOImpl();
            if (teacherDAO.save(t)) {
                setOkMsg("新增老师信息成功");
                JOptionPane.showMessageDialog(null, "新增老师信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);

            } else {
                setErrorMsg("新增老师信息失败");
                JOptionPane.showMessageDialog(null, "新增老师信息失败", "失败", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnCS.addActionListener(e -> {
            dispose();
        });
    }
    private void setOkMsg(String msg) {
        lblMsg.setText(msg);
        lblMsg.setForeground(Color.GREEN);
    }
    private void setErrorMsg(String msg) {
        lblMsg.setText(msg);
        lblMsg.setForeground(Color.RED);
    }
    private boolean checkData() {
        lblMsg.setText("");
//		private JTextField txtTeaNum;
        String teaNum = txtTeaNum.getText();
        if (teaNum.trim().length() == 0) {
            txtTeaNum.requestFocus();
            setErrorMsg("老师编号不能为空!");
            return false;
        }
        if (teaNum.trim().length() != 8) {
            txtTeaNum.requestFocus();
            setErrorMsg("老师编号必须是8位!");
            return false;
        }

        String teaName = txtTeaName.getText();
        if (teaName.trim().length() == 0) {
            txtTeaName.requestFocus();
            setErrorMsg("老师姓名不能为空!");
            return false;
        }
        String teaBtd = txtTeaBtd.getText();
        if (teaBtd.trim().length() == 0) {
            setErrorMsg("老师生日不能为空!");
            return false;
        }
        if (!DateConvert.checkDateStr(teaBtd)) {
            setErrorMsg("老师生日格式错误!");
            return false;
        }
        String workerAgeStr = txtWorkerAge.getText();
        if (workerAgeStr.trim().length() == 0) {
            setErrorMsg("老师工龄不能为空!");
            return false;
        }
        int age = 0;
        try {
            age = Integer.parseInt(workerAgeStr);
        }catch (NumberFormatException e) {
            setErrorMsg("工龄必须是数字!");
            return false;
        }
        if (age < 0) {
            setErrorMsg("工龄必须是正数!");
            return false;
        }
        return true;








    }
    public static void main(String[] args) {
        new TeacherAddFrame().setVisible(true);

    }
}
