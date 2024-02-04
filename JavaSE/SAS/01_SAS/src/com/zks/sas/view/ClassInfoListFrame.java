package com.zks.sas.view;

import com.zks.sas.dao.IClassInfoDAO;
import com.zks.sas.dao.impl.ClassInfoDAOImpl;
import com.zks.sas.entity.ClassInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.List;

public class ClassInfoListFrame extends JFrame {
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
    private JScrollPane scrollPane;
    private JTable table;
    public ClassInfoListFrame() {
        setTitle("班级信息列表");
        setResizable(false);
        setSize(770, 516);
        setLocationRelativeTo(null);

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("班级信息列表");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(304, 10, 304, 65);
        getContentPane().add(lblNewLabel);


        scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 73, 684, 244);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        //loadData();

        btnF = new JButton(new ImageIcon("imgs/base/first.png"));
        btnF.setBounds(464, 355, 67, 23);
        getContentPane().add(btnF);
        btnP = new JButton(new ImageIcon("imgs/base/pre.png"));
        btnP.setBounds(531, 355, 67, 23);
        getContentPane().add(btnP);
        btnN = new JButton(new ImageIcon("imgs/base/next.png"));
        btnN.setBounds(598, 355, 67, 23);
        getContentPane().add(btnN);
        btnL = new JButton(new ImageIcon("imgs/base/last.png"));
        btnL.setBounds(665, 355, 67, 23);
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
        btnInsert.setBounds(463, 397, 93, 23);
        getContentPane().add(btnInsert);
        btnInsert.addActionListener(e -> {
            new ClassInfoUpdateFrame().setVisible(true);
            dispose();
        });

        JButton btnUpdate = new JButton("修改", new ImageIcon("imgs/base/edit.png"));
        btnUpdate.setBounds(548, 397, 93, 23);
        getContentPane().add(btnUpdate);
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择要修改的数据行", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int  clsId = Integer.parseInt(table.getValueAt(row, 0).toString());
            ClassInfoUpdateFrame f = new ClassInfoUpdateFrame(clsId);
            f.setVisible(true);
            dispose();
        });

        JButton btnDelete = new JButton("删除", new ImageIcon("imgs/base/delete.png"));
        btnDelete.setBounds(639, 397, 93, 23);
        getContentPane().add(btnDelete);

        lblRowCount = new JLabel("共0条");
        lblRowCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblRowCount.setBounds(178, 355, 67, 23);
        getContentPane().add(lblRowCount);

        JLabel lblAA = new JLabel("每页");
        lblAA.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAA.setBounds(245, 355, 25, 23);
        getContentPane().add(lblAA);

        txtPageSize = new JTextField();
        txtPageSize.setText("3");
        txtPageSize.setColumns(10);
        txtPageSize.setBounds(270, 355, 22, 23);
        getContentPane().add(txtPageSize);
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



        JLabel lblBB = new JLabel("条");
        lblBB.setHorizontalAlignment(SwingConstants.LEFT);
        lblBB.setBounds(292, 355, 20, 23);
        getContentPane().add(lblBB);

        lblPageCount = new JLabel("共0页");
        lblPageCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblPageCount.setBounds(312, 355, 67, 23);
        getContentPane().add(lblPageCount);

        lblNowPage = new JLabel("第1页");
        lblNowPage.setHorizontalAlignment(SwingConstants.CENTER);
        lblNowPage.setBounds(379, 355, 67, 23);
        getContentPane().add(lblNowPage);

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "请选择要删除的数据行", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int clsId = Integer.parseInt(table.getValueAt(row, 0).toString());
            int r = JOptionPane.showConfirmDialog(this, "您要删除编号为[" + clsId + "]的老师吗?", "提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (r == JOptionPane.YES_OPTION) {
                IClassInfoDAO classInfoDAO = new ClassInfoDAOImpl();
                if (classInfoDAO.deleteById(clsId)) {
                    loadData();
                    JOptionPane.showMessageDialog(null, "删除老师信息成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "删除老师信息失败!", "失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loadData();
    }
    private void loadData(){
        //每页显示的条数
        int pageSize = Integer.parseInt(txtPageSize.getText());
        IClassInfoDAO classInfoDAO = new ClassInfoDAOImpl();
        //总行数 数据库
        int rowCount = classInfoDAO.getRowCount();
        //总页数 总行数 与 pageSize计算而来
        pageCount = classInfoDAO.getPageCount(pageSize);
        //当前页具体数据

        List<ClassInfo> clas = classInfoDAO.getNowPageData(pageSize, nowPage);

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
        model.addColumn("班级编号");
        model.addColumn("班级名称");
        model.addColumn("入学年份");
        model.addColumn("老师编号");
        for (ClassInfo cla : clas) {
            int clsId = cla.getClsId();
            String clsName = cla.getClsName();
            Date grade =cla.getGrade();
            String teaNum = cla.getTeaNum();
            Object[] row = {clsId,clsName,grade,teaNum};
            model.addRow(row);
        }
        table.setModel(model);
    }
    public static void main(String[] args) {
        new ClassInfoListFrame().setVisible(true);
    }
}
