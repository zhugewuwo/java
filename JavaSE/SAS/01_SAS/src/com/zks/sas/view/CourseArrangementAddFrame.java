package com.zks.sas.view;


import com.zks.sas.dao.ICourseArrangementDAO;
import com.zks.sas.dao.impl.CourseArrangementDAOImpl;
import com.zks.sas.db.DBManager;
import com.zks.sas.entity.CourseArrangement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class CourseArrangementAddFrame extends JFrame{

	private JTextField txtCourseYear;
	public JPanel mainPanel;
	private JComboBox cmbClsId;
	private JComboBox cmbLesId;
	private JComboBox cmbTeaNum;
	private JLabel lblMsg;

	public CourseArrangementAddFrame() {

		setTitle("课程安排信息");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(342, 404);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel msgPanel = new JPanel();
		msgPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(msgPanel, BorderLayout.SOUTH);
		msgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JLabel label_3 = new JLabel("新增课程安排信息");
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		label_3.setBounds(117, 21, 128, 37);
		mainPanel.add(label_3);

		JLabel lblNewLabel_2 = new JLabel("班级编号:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(66, 68, 72, 19);
		mainPanel.add(lblNewLabel_2);

		cmbClsId = new JComboBox();
		cmbClsId.setBounds(134, 66, 128, 23);
		cmbClsId();

		JLabel label = new JLabel("课程编号:");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(66, 115, 72, 19);
		mainPanel.add(label);

		cmbLesId = new JComboBox();
		cmbLesId.setBounds(134, 113, 128, 23);
		cmbLesId();

		JLabel label_1 = new JLabel("老师编号:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(66, 162, 72, 19);
		mainPanel.add(label_1);

		cmbTeaNum = new JComboBox();
		cmbTeaNum.setBounds(134, 160, 128, 23);
		cmbTeaNum();

		JLabel lblNewLabel_3 = new JLabel("新增年份:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(66, 207, 72, 20);
		mainPanel.add(lblNewLabel_3);

		txtCourseYear = new JTextField();
		txtCourseYear.setBounds(134, 207, 128, 21);
		mainPanel.add(txtCourseYear);
		txtCourseYear.setColumns(10);

		JLabel label_2 = new JLabel("  学期:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(78, 254, 60, 19);
		mainPanel.add(label_2);

		JRadioButton rdBCourseSem1 = new JRadioButton("1");
		rdBCourseSem1.setFont(new Font("宋体", Font.PLAIN, 14));
		rdBCourseSem1.setBounds(144, 252, 46, 23);
		mainPanel.add(rdBCourseSem1);

		JRadioButton rdBCourseSem2 = new JRadioButton("2");
		rdBCourseSem2.setFont(new Font("宋体", Font.PLAIN, 14));
		rdBCourseSem2.setBounds(229, 252, 46, 23);
		mainPanel.add(rdBCourseSem2);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdBCourseSem1);
		bg.add(rdBCourseSem2);

		JButton btnOK = new JButton("确定");
		btnOK.setFont(new Font("宋体", Font.PLAIN, 15));
		btnOK.setBounds(163, 295, 72, 32);
		mainPanel.add(btnOK);
		btnOK.addActionListener(e ->{
			if (!checkData()) return;
			//新增
			int clsId = (Integer) cmbClsId.getSelectedItem();
			int lesId = Integer.parseInt( (String) cmbLesId.getSelectedItem() );
			String teaNum = cmbTeaNum.getSelectedItem().toString();
			int year = Integer.parseInt(txtCourseYear.getText());
			int semester = 1;
			if (rdBCourseSem2.isSelected()) semester = 2;

			CourseArrangement courseArrangement = new CourseArrangement(clsId, lesId, teaNum, year, semester);
			ICourseArrangementDAO courseArrangementDAO = new CourseArrangementDAOImpl();
			if (courseArrangementDAO.save(courseArrangement)) {
				setOkMsg("新增课程安排信息成功");
				JOptionPane.showMessageDialog(null, "新增课程安排信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);

			} else {
				setErrorMsg("新增课程安排信息失败");
				JOptionPane.showMessageDialog(null, "新增课程安排信息失败", "失败", JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton btnCS = new JButton("取消");
		btnCS.setFont(new Font("宋体", Font.PLAIN, 15));
		btnCS.setBounds(235, 295, 72, 32);
		mainPanel.add(btnCS);
		btnCS.addActionListener(e ->{
			dispose();
		});

		JLabel lblNewLabel = new JLabel("提示:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		msgPanel.add(lblNewLabel);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 13));
		msgPanel.add(lblMsg);
	}

	//班级编号下拉列表框
	private void cmbClsId() {
		String sql = "select distinct clsId from coursearrangement";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql);
		try {
			while(rs.next()) {
				int value = rs.getInt(1);
				cmbClsId.addItem(value);
				mainPanel.add(cmbClsId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//课程编号
	private void cmbTeaNum() {
		String sql = "select distinct teaNum from teacher";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql);
		try {
			while(rs.next()) {
				int value = rs.getInt(1);
				cmbTeaNum.addItem(value);
				mainPanel.add(cmbTeaNum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//老师编号下拉列表框
	private void cmbLesId() {
		String sql = "select distinct lesId from lesson";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql);
		try {
			while(rs.next()) {
				String value = rs.getString(1);
				cmbLesId.addItem(value);
				mainPanel.add(cmbLesId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		String yearStr = txtCourseYear.getText();
		if (yearStr.trim().length() == 0) {
			txtCourseYear.requestFocus();
			setErrorMsg("新增年份不能为空！");
			return false;
		}
		return true;
	}



	public static void main(String[] args) {
		CourseArrangementAddFrame courseArrangement = new CourseArrangementAddFrame();
		courseArrangement.setVisible(true);
	}

}
