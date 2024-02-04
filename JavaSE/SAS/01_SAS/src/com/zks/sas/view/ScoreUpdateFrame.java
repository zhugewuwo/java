package com.zks.sas.view;

import com.zks.sas.dao.IScoreDAO;
import com.zks.sas.dao.impl.ScoreDAOImpl;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.Score;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreUpdateFrame extends JFrame {

    private JTextField txtScore;
    private JLabel lblMsg;
    private IScoreDAO scoreDAO ;
    private JComboBox cmbLesId;
    private int scoreId;
    private JTextField txtScoreId;
    private JComboBox cmbStuNum;
    private List<String > stuNums = new ArrayList<String>();
    private List<Integer> lesIds = new ArrayList<Integer>();
    public ScoreUpdateFrame(int scoreId ){
        this();
        this.scoreId = scoreId;
        loadData();
    }

    private void loadData() {
        IScoreDAO scoreDAO = new ScoreDAOImpl();
        Score score = scoreDAO.findById(scoreId);
        txtScoreId.setText(scoreId + "");
        txtScore.setText(score.getScore() + "");
        int i = 0;
        for (; i < lesIds.size(); i++) {
            if (score.getLesId() == lesIds.get(i))break;
        }
        cmbLesId.setSelectedIndex(i);
        i = 0;
        for (; i < lesIds.size(); i++) {
            if (score.getStuNum() == stuNums.get(i))break;
        }
        cmbStuNum.setSelectedIndex(i);


    }
    public ScoreUpdateFrame(){

        setTitle("修改成绩信息");
        setSize(396, 375);
        setResizable(false);//大小固定
        setLocationRelativeTo(null);//居中

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("修改成绩信息");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(139, 10, 186, 32);
        mainPanel.add(lblNewLabel_1);

        JLabel label_4 = new JLabel("学生编号:");
        label_4.setBounds(34, 123, 60, 15);
        mainPanel.add(label_4);

        JLabel label_5 = new JLabel("课程编号:");
        label_5.setBounds(34, 166, 60, 15);
        mainPanel.add(label_5);

        JLabel label_6 = new JLabel("考试成绩:");
        label_6.setBounds(34, 207, 60, 15);
        mainPanel.add(label_6);

        txtScore = new JTextField();
        txtScore.setColumns(10);
        txtScore.setBounds(91, 204, 246, 18);
        mainPanel.add(txtScore);

        JButton btnOK = new JButton("确定");
        btnOK.setBounds(123, 262, 93, 23);
        mainPanel.add(btnOK);

        JButton btnCS = new JButton("取消");
        btnCS.setBounds(244, 262, 93, 23);
        mainPanel.add(btnCS);



        cmbLesId = new JComboBox();
        cmbLesId.setBounds(91, 162, 246, 19);
        mainPanel.add(cmbLesId);
        cmbLesId();

        JLabel label = new JLabel("成绩编号:");
        label.setBounds(34, 77, 60, 15);
        mainPanel.add(label);

        txtScoreId = new JTextField();
        txtScoreId.setEditable(false);
        txtScoreId.setColumns(10);
        txtScoreId.setBounds(91, 74, 246, 18);
        mainPanel.add(txtScoreId);

        cmbStuNum = new JComboBox();
        cmbStuNum.setBounds(91, 120, 246, 18);
        mainPanel.add(cmbStuNum);
        cmbStuNum();


        JPanel msgPanel = new JPanel();
        getContentPane().add(msgPanel, BorderLayout.SOUTH);
        msgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel labelTS = new JLabel("标签:");
        msgPanel.add(labelTS);
        lblMsg = new JLabel("");
        msgPanel.add(lblMsg);

        btnOK.addActionListener(e ->{
            if (!checkData()) return;//验证不通过,返回
            //新增
            int scoreId = Integer.parseInt(txtScoreId.getText());
            int lesId = Integer.parseInt(cmbLesId.getSelectedItem().toString());
            String stuNum = cmbStuNum.getSelectedItem().toString();
            int score = Integer.parseInt(txtScore.getText());
            Score s = new Score(scoreId,lesId,stuNum,score);
            scoreDAO = new ScoreDAOImpl();
            if (scoreDAO.update(s)) {
                setOkMsg("修改成绩信息成功");
                JOptionPane.showMessageDialog(null, "修改成绩信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);

            } else {
                setErrorMsg("修改成绩信息失败");
                JOptionPane.showMessageDialog(null, "修改成绩信息失败", "失败", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCS.addActionListener(e -> {
            new ScoreListFrame().setVisible(true);
            dispose();
        });

    }


    public void cmbStuNum() {
        String sql = "select stuNum from student";
        DBManager dbManager = new DBManager();
        ResultSet rs =  dbManager.query(sql);
        try {
            while(rs.next()){
                String stuNum = rs.getString(1);
                cmbStuNum.addItem(stuNum);
                stuNums.add(stuNum);
            }
        } catch (SQLException e) {

        }

    }

    public void cmbLesId() {
        String sql = "select lesId from lesson";
        DBManager dbManager = new DBManager();
        ResultSet rs =  dbManager.query(sql);
        try {
            while(rs.next()){
                int lesId = rs.getInt("lesId");
                cmbLesId.addItem(lesId);
                lesIds.add(lesId);
            }
        } catch (SQLException e) {

        }

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
        String scoreStr = txtScore.getText();
        if (scoreStr.trim().length() == 0) {
            setErrorMsg("成绩不能为空!");
            return false;
        }
        int score  = 0;
        try {
            score = Integer.parseInt(scoreStr);
        }catch (NumberFormatException e) {
            setErrorMsg("成绩必须是数字!");
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        new ScoreUpdateFrame().setVisible(true);
    }
}
