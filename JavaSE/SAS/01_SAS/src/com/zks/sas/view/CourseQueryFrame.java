package com.zks.sas.view;


import com.zks.sas.dao.IScoreDAO;

import com.zks.sas.dao.impl.ScoreDAOImpl;
import com.zks.sas.entity.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;;

public class CourseQueryFrame extends JFrame {
    private String id  ;
    private JScrollPane scrollPane;
    private JTable table;
    public CourseQueryFrame(String id) {
        this.id = id;
        setTitle("成绩查询");
        setResizable(false);
        setSize(771, 464);
        setLocationRelativeTo(null);

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("成绩查询");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(357, 10, 304, 65);
        getContentPane().add(lblNewLabel);


        scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 73, 684, 244);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        loadData(id);

    }
    private void loadData(String stuId){
        IScoreDAO scoreDAO = new ScoreDAOImpl();
        ResultSet rs = scoreDAO.findByStuId(stuId);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("成绩编号");
        model.addColumn("课程名称");
        model.addColumn("考试成绩");
        try {
            while (rs.next()) {
                int i = 1;
                int scoreId = rs.getInt(i++);
                String lesName = rs.getString(i++);
                int score = rs.getInt(i++);
                Object s[] = {scoreId, lesName, score };
                model.addRow(s);
                table.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
