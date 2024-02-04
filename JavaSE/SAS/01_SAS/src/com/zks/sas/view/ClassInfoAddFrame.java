package com.zks.sas.view;

import com.zks.sas.dao.IClassInfoDAO;
import com.zks.sas.dao.impl.ClassInfoDAOImpl;
import com.zks.sas.entity.ClassInfo;
import com.zks.sas.tool.DateConvert;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Date;

public class ClassInfoAddFrame extends JFrame {
    private JTextField txtClsId;
    private JTextField txtClsName;
    private JTextField txtTeaNum;
    private JTextField txtKey1;
    private JTextField txtKey2;
    private JTextField txtStuCount;
    private JTextField txtGrade;
    private JLabel labelMsg;
    public ClassInfoAddFrame() {
        setTitle("新增班级信息");
        setSize(721, 472);
        setResizable(false);//大小固定
        setLocationRelativeTo(null);//居中

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JLabel label = new JLabel("新增班级信息");

        label.setFont(new Font("宋体", Font.PLAIN, 18));
        label.setBounds(283, 10, 286, 32);
        mainPanel.add(label);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder(null, "\u57FA\u7840\u6570\u636E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(59, 63, 597, 144);
        mainPanel.add(panel_1);

        JLabel label_1 = new JLabel("班级编号:");
        label_1.setBounds(21, 37, 60, 15);
        panel_1.add(label_1);

        txtClsId = new JTextField();
        txtClsId.setColumns(10);
        txtClsId.setBounds(78, 34, 194, 21);
        panel_1.add(txtClsId);

        JLabel label_2 = new JLabel("班级名称:");
        label_2.setBounds(310, 40, 60, 15);
        panel_1.add(label_2);

        txtClsName = new JTextField();
        txtClsName.setColumns(10);
        txtClsName.setBounds(367, 37, 194, 21);
        panel_1.add(txtClsName);

        JLabel label_3 = new JLabel("入学年份:");
        label_3.setBounds(21, 73, 60, 15);
        panel_1.add(label_3);

        JLabel txtStuCount = new JLabel("班级人数:");
        txtStuCount.setBounds(310, 73, 60, 15);
        panel_1.add(txtStuCount);

        JLabel label_5 = new JLabel("导员编号:");
        label_5.setBounds(21, 105, 60, 15);
        panel_1.add(label_5);

        this.txtStuCount = new JTextField();
        this.txtStuCount.setColumns(10);
        this.txtStuCount.setBounds(367, 70, 194, 21);
        panel_1.add(this.txtStuCount);

        txtGrade = new JTextField();
        txtGrade.setColumns(10);
        txtGrade.setBounds(78, 70, 194, 21);
        panel_1.add(txtGrade);

        txtTeaNum = new JTextField();
        txtTeaNum.setColumns(10);
        txtTeaNum.setBounds(78, 101, 194, 23);
        panel_1.add(txtTeaNum);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(null, "\u5176\u4ED6\u6570\u636E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(59, 217, 597, 80);
        mainPanel.add(panel_2);

        JLabel lblKey = new JLabel("结课日期:");
        lblKey.setBounds(24, 31, 60, 15);
        panel_2.add(lblKey);

        txtKey1 = new JTextField();
        txtKey1.setColumns(10);
        txtKey1.setBounds(81, 28, 194, 21);
        panel_2.add(txtKey1);

        JLabel lblKey_1 = new JLabel("班级备注:");
        lblKey_1.setBounds(309, 34, 60, 15);
        panel_2.add(lblKey_1);

        txtKey2 = new JTextField();
        txtKey2.setColumns(10);
        txtKey2.setBounds(366, 31, 194, 21);
        panel_2.add(txtKey2);

        JButton btnCS = new JButton("取消");
        btnCS.setBounds(560, 340, 93, 23);
        mainPanel.add(btnCS);

        JButton btnOK = new JButton("确定");
        btnOK.setBounds(427, 340, 93, 23);
        mainPanel.add(btnOK);

        JPanel msgPanel = new JPanel();
        getContentPane().add(msgPanel, BorderLayout.SOUTH);
        msgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel lblTS = new JLabel("提示:");
        msgPanel.add(lblTS);

        labelMsg = new JLabel("");
        msgPanel.add(labelMsg);
        btnCS.addActionListener(e -> {
             new ClassInfoListFrame().setVisible(true);
            dispose();
        });

        btnOK.addActionListener(e -> {
            if (!checkData()) return;//验证不通过,返回
            //新增
            int clsId = Integer.parseInt(txtClsId.getText());
            String clsName = txtClsName.getText();
            Date grade = DateConvert.convertToDate(txtGrade.getText());
            int stuCount = Integer.parseInt(txtStuCount.getText());
            String teaNum =txtTeaNum.getText();
            String key1 = "";
            int key2 = 1;
            ClassInfo classInfo = new ClassInfo(clsId, clsName, grade, stuCount, teaNum,  key1, key2);
            IClassInfoDAO classInfoDAO = new ClassInfoDAOImpl();
            if (classInfoDAO.save(classInfo)) {
                setOkMsg("新增班级信息成功");
                JOptionPane.showMessageDialog(null, "新增班级信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);

            } else {
                setErrorMsg("新增班级信息失败");
                JOptionPane.showMessageDialog(null, "新增班级信息失败", "失败", JOptionPane.ERROR_MESSAGE);
            }
        });


    }
    private void setOkMsg(String msg) {
        labelMsg.setText(msg);
        labelMsg.setForeground(Color.GREEN);
    }
    private void setErrorMsg(String msg) {
        labelMsg.setText(msg);
        labelMsg.setForeground(Color.RED);
    }
    private boolean checkData() {
        labelMsg.setText("");
//		private JTextField txtTeaNum;
        String clsId = txtClsId.getText();
        if (clsId.trim().length() == 0) {
            txtClsId.requestFocus();
            setErrorMsg("班级编号不能为空!");
            return false;
        }

        String teaName = txtClsName.getText();
        if (teaName.trim().length() == 0) {
            txtClsName.requestFocus();
            setErrorMsg("班级名称不能为空!");
            return false;
        }
        String grade = txtGrade.getText();
        if (grade.trim().length() == 0) {
            setErrorMsg("入学年份不能为空!");
            return false;
        }
        if (!DateConvert.checkDateStr(grade)) {
            setErrorMsg("入学年份格式错误!");
            return false;
        }
        String teaNum = txtTeaNum.getText();
        if (teaNum.trim().length() == 0) {
            txtTeaNum.requestFocus();
            setErrorMsg("导员编号不能为空!");
            return false;
        }

        if (teaNum.trim().length() != 8) {
            txtTeaNum.requestFocus();
            setErrorMsg("导员编号必须是8位!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new ClassInfoAddFrame().setVisible(true);

    }
}
