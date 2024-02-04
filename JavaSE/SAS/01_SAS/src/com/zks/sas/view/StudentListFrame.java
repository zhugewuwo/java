package com.zks.sas.view;

import com.zks.sas.dao.IStudentDAO;
import com.zks.sas.dao.impl.StudentDAOImpl;
import com.zks.sas.entity.Student;
import com.zks.sas.tool.DateConvert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class StudentListFrame extends JFrame {
    private JTable table;
    private JTextField txtPageSize;
    private JLabel lblRowCount;
    private JLabel lblPageCount;
    private JLabel lblNowPage;
    private int nowPage = 1;//全局
    private int pageCount = 0;//总页数
    private JButton btnF;
    private JButton btnP;
    private JButton btnN;
    private JButton btnL;
    public StudentListFrame() {
        setTitle("学生信息列表");
        setResizable(false);
        setSize(770, 459);
        setLocationRelativeTo(null);

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("学生信息列表");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(304, 10, 304, 65);
        getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 63, 710, 258);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        //设置表格只能选择一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lblRowCount = new JLabel("共100条");
        lblRowCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblRowCount.setBounds(195, 331, 67, 23);
        getContentPane().add(lblRowCount);

        JLabel lablAA = new JLabel("每页");
        lablAA.setHorizontalAlignment(SwingConstants.RIGHT);
        lablAA.setBounds(262, 331, 25, 23);
        getContentPane().add(lablAA);
        txtPageSize = new JTextField();
        txtPageSize.setText("3");
        txtPageSize.setBounds(287, 331, 22, 23);
        getContentPane().add(txtPageSize);
        txtPageSize.setColumns(10);
        txtPageSize.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String str = txtPageSize.getText();
                int pageSize = 0;
                try {
                    pageSize = Integer.parseInt(str);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "每页显示条数必须是数字!", "错误", JOptionPane.ERROR_MESSAGE);
                    txtPageSize.requestFocus();
                    return;
                }
                if (pageSize < 1) {
                    JOptionPane.showMessageDialog(null, "每页显示条数必须大于0!", "错误", JOptionPane.ERROR_MESSAGE);
                    txtPageSize.requestFocus();
                    return;
                }
                nowPage = 1;
                loadData();
            }
        });
        JLabel lablBB = new JLabel("条");
        lablBB.setHorizontalAlignment(SwingConstants.LEFT);
        lablBB.setBounds(309, 331, 20, 23);
        getContentPane().add(lablBB);


        lblPageCount = new JLabel("共10页");
        lblPageCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblPageCount.setBounds(329, 331, 67, 23);
        getContentPane().add(lblPageCount);

        lblNowPage = new JLabel("第10页");
        lblNowPage.setHorizontalAlignment(SwingConstants.CENTER);
        lblNowPage.setBounds(396, 331, 67, 23);
        getContentPane().add(lblNowPage);

        btnF = new JButton(new ImageIcon("imgs/base/first.png"));
        btnF.setBounds(463, 331, 67, 23);
        getContentPane().add(btnF);
        btnP = new JButton(new ImageIcon("imgs/base/pre.png"));
        btnP.setBounds(530, 331, 67, 23);
        getContentPane().add(btnP);
        btnN = new JButton(new ImageIcon("imgs/base/next.png"));
        btnN.setBounds(597, 331, 67, 23);
        getContentPane().add(btnN);
        btnL = new JButton(new ImageIcon("imgs/base/last.png"));
        btnL.setBounds(664, 331, 67, 23);
        getContentPane().add(btnL);
        btnF.addActionListener(e -> {
            nowPage = 1;
            loadData();
        });
        btnP.addActionListener(e -> {
            nowPage -= 1;
            loadData();
        });
        btnN.addActionListener(e -> {
            nowPage += 1;
            loadData();
        });
        btnL.addActionListener(e -> {
            nowPage = pageCount;
            loadData();
        });

        JButton btnInsert = new JButton("新增", new ImageIcon("imgs/base/add.png"));
        btnInsert.setBounds(463, 364, 93, 23);
        getContentPane().add(btnInsert);

        btnInsert.addActionListener(e -> {
            new StudentAddFrame().setVisible(true);
            dispose();
        });

        JButton btnUpdate = new JButton("修改", new ImageIcon("imgs/base/edit.png"));
        btnUpdate.setBounds(548, 364, 93, 23);
        getContentPane().add(btnUpdate);
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择要修改的数据行", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String stuNum = table.getValueAt(row, 0).toString();
            StudentUpdateFrame frame = new StudentUpdateFrame(stuNum);
            frame.setVisible(true);
            dispose();
        });

        JButton btnDelete = new JButton("删除", new ImageIcon("imgs/base/delete.png"));
        btnDelete.setBounds(639, 364, 93, 23);
        getContentPane().add(btnDelete);




        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择要删除的数据行", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String stuNum = table.getValueAt(row, 0).toString();
            int r = JOptionPane.showConfirmDialog(this, "您要删除编号为[" + stuNum + "]的学生吗?", "提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (r == JOptionPane.YES_OPTION) {
                IStudentDAO studentDAO = new StudentDAOImpl();
                if (studentDAO.deleteById(stuNum)) {
                    loadData();
                    JOptionPane.showMessageDialog(null, "删除老师信息成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "删除老师信息失败!", "失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loadData();
    }
    private void loadData() {
        //每页显示的条数
        int pageSize = Integer.parseInt(txtPageSize.getText());
        IStudentDAO studentDAO = new StudentDAOImpl();
        //总行数 数据库
        int rowCount = studentDAO.getRowCount();
        //总页数 总行数 与 pageSize计算而来
        pageCount = studentDAO.getPageCount(pageSize);
        //当前页具体数据
        List<Student> stus = studentDAO.getNowPageData(pageSize, nowPage);

        lblRowCount.setText("共" + rowCount + "条");
        lblPageCount.setText("共" + pageCount + "页");
        lblNowPage.setText("第" + nowPage + "页");

        btnF.setEnabled(true);
        btnP.setEnabled(true);
        btnN.setEnabled(true);
        btnL.setEnabled(true);
        if (nowPage == 1) {
            btnF.setEnabled(false);
            btnP.setEnabled(false);
        }
        if (nowPage == pageCount) {//最后页
            btnN.setEnabled(false);
            btnL.setEnabled(false);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("学生编号");
        model.addColumn("学生姓名");
        model.addColumn("学生班级");
        model.addColumn("学生生日");

        for (Student stu : stus) {
            String btdStr = DateConvert.convertToString(stu.getStuBtd());
            Object[] row = {stu.getStuNum(), stu.getStuName(), stu.getClsId(),  btdStr};
            model.addRow(row);
        }
        table.setModel(model);
    }

    public static void main(String[] args) {
        new StudentListFrame().setVisible(true);
    }
}
