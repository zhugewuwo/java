package com.zks.sas.view;

import com.zks.sas.dao.ILessonDAO;
import com.zks.sas.dao.IScoreDAO;
import com.zks.sas.dao.impl.LessonDAOImpl;
import com.zks.sas.dao.impl.ScoreDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonQueryFrame extends JFrame {
    private String id;
    private JScrollPane scrollPane;
    private JTable table;
    public LessonQueryFrame(String id) {
        this.id = id;
        setTitle("课程查询");
        setResizable(false);
        setSize(771, 464);
        setLocationRelativeTo(null);

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("课程查询");
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
        ILessonDAO lessonDAO = new LessonDAOImpl();
        ResultSet rs = lessonDAO.findByStuId(stuId);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("课程编号");
        model.addColumn("课程名称");
        model.addColumn("课程学分");
        model.addColumn("课程学时");
        try {
            while (rs.next()) {
                int i = 1;
                int lesId = rs.getInt(i++);
                String lesName = rs.getString(i++);
                int score = rs.getInt(i++);
                int hours = rs.getInt(i++);
                Object s[] = {lesId, lesName, score, hours };
                model.addRow(s);
                table.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
