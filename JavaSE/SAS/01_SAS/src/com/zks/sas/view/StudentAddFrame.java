package com.zks.sas.view;

import com.zks.sas.dao.IStudentDAO;
import com.zks.sas.dao.impl.StudentDAOImpl;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.Student;
import com.zks.sas.tool.DateConvert;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentAddFrame extends JFrame {
    private JTextField textFieldstuNum;
    private JTextField textFieldstuName;
    private JTextField textFieldpwd;
    private JTextField textFieldemails;
    private JTextField textFieldBtd;
    private List<Integer> clsList = new ArrayList<>();



    private JLabel lblMsg;
    private String stuNum;
    private JComboBox comboBoxclsId;


    private JRadioButton rdbtnstates1;
    private JRadioButton rdbtnstates2;
    private JRadioButton rdbtnsex1;
    private JRadioButton rdbtnsex2;
    public StudentAddFrame(String stuNum) {
        this();
        this.stuNum = stuNum;
        loadData();
    }

    private void loadData() {
        Student student = DataTool.s;

        textFieldstuNum.setText(stuNum);

        int selectedIndex = 0;
        switch (student.getClsId()) {
            case 39:
                selectedIndex = 1;
                break;
            case 40:
                selectedIndex = 2;
                break;
            case 41:
                selectedIndex = 3;
                break;
        }
        comboBoxclsId.setSelectedIndex(selectedIndex);
        textFieldstuName.setText(student.getStuName());
        textFieldBtd.setText(DateConvert.convertToString(student.getStuBtd()));
        textFieldpwd.setText(student.getPwd());
        rdbtnstates1.setSelected(student.getStates() == 1);
        rdbtnstates2.setSelected(student.getStates() == 0);
        rdbtnsex1.setSelected(student.getSex() == 1);
        rdbtnsex2.setSelected(student.getSex() == 0);
        textFieldemails.setText(student.getKey1());


    }

    public StudentAddFrame() {
        //
        setTitle("新增学生信息");
        setSize(771, 564);
        setResizable(false);
        setLocationRelativeTo(null);
        //
        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
        //
        JLabel lblNewLabel = new JLabel("新增学生信息");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(317, 10, 145, 67);
        mainPanel.add(lblNewLabel);

        JPanel panelbase = new JPanel();
        panelbase.setBorder(new TitledBorder(null, "基础数据", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelbase.setBounds(43, 60, 668, 230);
        mainPanel.add(panelbase);
        panelbase.setLayout(null);

        JLabel lblstuNum = new JLabel("学生编号:");
        lblstuNum.setBounds(37, 52, 93, 28);
        panelbase.add(lblstuNum);

        textFieldstuNum = new JTextField();
        textFieldstuNum.setBounds(100, 53, 181, 27);
        panelbase.add(textFieldstuNum);
        textFieldstuNum.setColumns(10);

        JLabel lblclsId = new JLabel("班级编号:");
        lblclsId.setBounds(366, 52, 93, 28);
        panelbase.add(lblclsId);

        comboBoxclsId = new JComboBox();
        comboBoxclsId.setBounds(438, 53, 181, 27);
        String sql = "select clsId from classInfo";
        DBManager dbManager = new DBManager();
        ResultSet rs = dbManager.query(sql);
        try {
            while(rs.next()) {
                int value = rs.getInt(1);
                comboBoxclsId.addItem(value);
                clsList.add(value);
                panelbase.add(comboBoxclsId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBoxclsId.setSelectedIndex(1);



        JLabel lblstuName = new JLabel("学生姓名:");
        lblstuName.setBounds(37, 103, 100, 28);
        panelbase.add(lblstuName);

        textFieldstuName = new JTextField();
        textFieldstuName.setColumns(10);
        textFieldstuName.setBounds(100, 104, 181, 27);
        panelbase.add(textFieldstuName);

        JLabel lblstuBtd = new JLabel("学生生日:");
        lblstuBtd.setBounds(366, 103, 93, 28);
        panelbase.add(lblstuBtd);

        textFieldBtd = new JTextField();
        textFieldBtd.setColumns(10);
        textFieldBtd.setBounds(439, 104, 181, 28);
        panelbase.add(textFieldBtd);

        JLabel lblstates = new JLabel("学生状态:");
        lblstates.setBounds(37, 160, 93, 28);
        panelbase.add(lblstates);

        rdbtnstates1 = new JRadioButton("在籍");
        rdbtnstates1.setBounds(100, 160, 78, 28);
        panelbase.add(rdbtnstates1);
        rdbtnstates1.setSelected(true);


        rdbtnstates2 = new JRadioButton("不在籍");
        rdbtnstates2.setBounds(227, 160, 117, 28);
        panelbase.add(rdbtnstates2);


        ButtonGroup bgstates = new ButtonGroup();//
        bgstates.add(rdbtnstates1);
        bgstates.add(rdbtnstates2);

        JLabel lblsexes = new JLabel("学生性别:");
        lblsexes.setBounds(366, 160, 93, 28);
        panelbase.add(lblsexes);


        rdbtnsex1 = new JRadioButton("男");
        rdbtnsex1.setSelected(true);
        rdbtnsex1.setBounds(438, 160, 78, 28);
        panelbase.add(rdbtnsex1);

        rdbtnsex2 = new JRadioButton("女");
        rdbtnsex2.setBounds(586, 160, 117, 28);
        panelbase.add(rdbtnsex2);

        ButtonGroup bgsexes = new ButtonGroup();
        bgsexes.add(rdbtnsex1);
        bgsexes.add(rdbtnsex2);



        JPanel paneladd = new JPanel();
        paneladd.setBorder(new TitledBorder(null, "其他信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneladd.setBounds(43, 300, 668, 122);
        mainPanel.add(paneladd);
        paneladd.setLayout(null);

        JLabel lblpwd = new JLabel("学生密码:");
        lblpwd.setBounds(28, 44, 93, 52);
        paneladd.add(lblpwd);

        textFieldpwd = new JTextField();
        textFieldpwd.setColumns(10);
        textFieldpwd.setBounds(100, 57, 184, 27);
        paneladd.add(textFieldpwd);



        JLabel lblemails = new JLabel("学生邮箱:");
        lblemails.setBounds(366, 56, 93, 28);
        paneladd.add(lblemails);

        textFieldemails = new JTextField();
        textFieldemails.setColumns(10);
        textFieldemails.setBounds(439, 57, 184, 27);
        paneladd.add(textFieldemails);

        JButton btnOK = new JButton("确认");
        btnOK.setBounds(448, 446, 104, 29);
        mainPanel.add(btnOK);


        btnOK.addActionListener(e -> {
            if (!checkData()) return;//验证不通过,返回

            String stuNum = textFieldstuNum.getText();
            int clsId = Integer.parseInt(comboBoxclsId.getSelectedItem().toString());
            String stuName = textFieldstuName.getText();
            Date stuBtd = DateConvert.convertToDate(textFieldBtd.getText());
            int states = 1;
            if (rdbtnstates2.isSelected()) states = 0;

            int sex = 1;
            if (rdbtnsex2.isSelected()) sex = 0;
            String pwd = textFieldpwd.getText();

            String key1 = textFieldemails.getText();
            int key2 = 1 ;


            Student s = new Student(stuNum, clsId, stuName, stuBtd, pwd, states, sex, key1, key2);
            IStudentDAO studentDAO = new StudentDAOImpl();
            if (studentDAO.save(s)) {
                setOkMsg("新增学生信息成功");
                DataTool.s = s;
                JOptionPane.showMessageDialog(null, "新增学生信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);

            } else {
                setErrorMsg("新增学生信息失败");
                JOptionPane.showMessageDialog(null, "新增学生信息失败", "失败", JOptionPane.ERROR_MESSAGE);
            }


        });


        JButton buttCan = new JButton("取消");
        buttCan.setBounds(607, 446, 104, 29);
        mainPanel.add(buttCan);
        btnOK.addActionListener(e -> {
            new StudentListFrame().setVisible(true);
            dispose();
        });

        JPanel msgPanel = new JPanel();
        msgPanel.setBorder(new LineBorder(new Color(0, 0, 0)));//边框线条
        getContentPane().add(msgPanel, BorderLayout.SOUTH);
        msgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));


        JLabel labelTS = new JLabel("提示:");
        labelTS.setFont(new Font("宋体", Font.PLAIN, 12));
        msgPanel.add(labelTS);

        lblMsg = new JLabel("");
        lblMsg.setFont(new Font("宋体", Font.PLAIN, 12));
        msgPanel.add(lblMsg);

    }

    private void setOkMsg(String msg) {
        lblMsg.setText(msg);
        lblMsg.setForeground(Color.GREEN);
    }

    private void setErrorMsg(String msg) {
        lblMsg.setText(msg);
        lblMsg.setForeground(Color.RED);
    }



    private boolean checkData(){

        lblMsg.setText("");

        String stuNum = textFieldstuNum.getText();
        if (stuNum.trim().length() == 0) {
            textFieldstuNum.requestFocus();
            setErrorMsg("学生编号不能为空值!");
            return false;
        }

        if (stuNum.trim().length() != 12) {
            textFieldstuNum.requestFocus();
            setErrorMsg("学生编号必须是12位!");
            return false;
        }

        String stuName = textFieldstuName.getText();
        if (stuName.trim().length() == 0) {
            textFieldstuName.requestFocus();
            setErrorMsg("学生姓名不能为空!");
            return false;
        }
        String stuBtd = textFieldBtd.getText();
        if (stuBtd.trim().length() == 0) {
            setErrorMsg("学生生日不能为空!");
            return false;
        }
        if (!DateConvert.checkDateStr(stuBtd)) {
            setErrorMsg("学生生日格式错误!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new StudentAddFrame().setVisible(true);
    }
}
