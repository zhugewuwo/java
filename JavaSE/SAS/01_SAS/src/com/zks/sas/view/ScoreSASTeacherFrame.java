package com.zks.sas.view;

import com.zks.sas.dao.SASDAO;
import com.zks.sas.entity.SASTeacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScoreSASTeacherFrame extends JFrame {
    private JTable table;
    public ScoreSASTeacherFrame() {
        getContentPane().setLayout(null);
        setTitle("成绩分析列表");
        setResizable(false);
        setSize(740, 470);
        setLocationRelativeTo(null);

        JComboBox cmbYear = new JComboBox();
        cmbYear.setBounds(52, 74, 101, 23);
        getContentPane().add(cmbYear);
        cmbYear.addItem("2019");
        cmbYear.addItem("2020");
        cmbYear.addItem("2021");
        cmbYear.addItem("2022");

        JLabel label = new JLabel("学年:");
        label.setBounds(10, 78, 60, 15);
        getContentPane().add(label);

        JLabel label_1 = new JLabel("学期:");
        label_1.setBounds(177, 78, 60, 15);
        getContentPane().add(label_1);

        JComboBox cmbS = new JComboBox();
        cmbS.setBounds(222, 74, 101, 23);
        getContentPane().add(cmbS);
        cmbS.addItem("1");
        cmbS.addItem("2");

        JComboBox cmbLesName = new JComboBox();
        cmbLesName.setBounds(462, 74, 138, 23);
        getContentPane().add(cmbLesName);

        JLabel label_3 = new JLabel("学生成绩列表");
        label_3.setFont(new Font("宋体", Font.PLAIN, 18));
        label_3.setBounds(313, 10, 304, 65);
        getContentPane().add(label_3);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 133, 710, 241);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        //设置表格只能选择一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnKC = new JButton("查看课程");
        btnKC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmbLesName.removeAllItems();
                cmbLesName.addItem("Java");
                cmbLesName.addItem("python");
                cmbLesName.addItem("C");

                cmbLesName.addItem("数据结构");
            }
        });
        btnKC.setBounds(348, 74, 93, 23);
        getContentPane().add(btnKC);

        JButton btnSAS = new JButton("成绩分析");
        btnSAS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lesName = cmbLesName.getSelectedItem().toString();
                System.out.println(lesName);
                loadData(lesName);
            }
        });
        btnSAS.setBounds(627, 74, 93, 23);
        getContentPane().add(btnSAS);
    }
    private void loadData(String lesName) {
        DefaultTableModel model = new DefaultTableModel();
        //1 标题
        model.addColumn("老师编号");
        model.addColumn("老师名称");
        model.addColumn("考试人数");
        model.addColumn("平均成绩");

        //2 具体行数据
        SASDAO sasDAO = new SASDAO();
        List<SASTeacher> sasClassInfos = sasDAO.sasTeacherByLesName(lesName);
        for (SASTeacher tea : sasClassInfos) {
            Object[] row = {tea.getTeaNum(), tea.getTeaName(), tea.getStuCount(), tea.getScoreAvg()};
            model.addRow(row);
        }
        //3 让模型中的数据在表格中显示
        table.setModel(model);
    }

    public static void main(String[] args) {
        new ScoreSASTeacherFrame().setVisible(true);
    }
}
