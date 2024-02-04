package com.zks.sas.view;


import com.zks.sas.dao.ICourseArrangementDAO;
import com.zks.sas.dao.impl.CourseArrangementDAOImpl;
import com.zks.sas.entity.CourseArrangement;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseArrangementListFrame extends JFrame {

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

	public CourseArrangementListFrame() {
		
		setTitle("课程信息列表");
		setSize(634, 473);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("课程信息");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(276, 24, 67, 28);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 74, 540, 219);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//设置表格只能选择一行
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		lblNowPage = new JLabel("第10页");
		lblNowPage.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNowPage.setBounds(254, 334, 45, 15);
		getContentPane().add(lblNowPage);

		lblPageCount = new JLabel("共10页");
		lblPageCount.setFont(new Font("宋体", Font.PLAIN, 13));
		lblPageCount.setBounds(199, 334, 45, 15);
		getContentPane().add(lblPageCount);

		JLabel lablBB = new JLabel("条");
		lablBB.setFont(new Font("宋体", Font.PLAIN, 13));
		lablBB.setBounds(180, 334, 18, 15);
		getContentPane().add(lablBB);

		txtPageSize = new JTextField();
		txtPageSize.setText("3");
		txtPageSize.setBounds(155, 331, 24, 21);
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
		JLabel lablAA = new JLabel("每页");
		lablAA.setFont(new Font("宋体", Font.PLAIN, 13));
		lablAA.setBounds(125, 334, 30, 15);
		getContentPane().add(lablAA);

		lblRowCount = new JLabel("共100条");
		lblRowCount.setFont(new Font("宋体", Font.PLAIN, 13));
		lblRowCount.setBounds(63, 334, 45, 15);
		getContentPane().add(lblRowCount);
		
		btnF = new JButton(new ImageIcon("imgs/base/first.png"));
		btnF.setBounds(309, 326, 67, 23);
		getContentPane().add(btnF);
		btnP = new JButton(new ImageIcon("imgs/base/pre.png"));
		btnP.setBounds(376, 326, 67, 23);
		getContentPane().add(btnP);
		btnN = new JButton(new ImageIcon("imgs/base/next.png"));
		btnN.setBounds(443, 326, 67, 23);
		getContentPane().add(btnN);
		btnL = new JButton(new ImageIcon("imgs/base/last.png"));
		btnL.setBounds(510, 326, 67, 23);
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
		btnInsert.setBounds(309, 368, 93, 23);
		getContentPane().add(btnInsert);
		btnInsert.addActionListener(e -> {
			new CourseArrangementAddFrame().setVisible(true);

		});
		
		JButton btnUpdate = new JButton("修改", new ImageIcon("imgs/base/edit.png"));
		btnUpdate.setBounds(399, 368, 93, 23);
		getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(e ->{
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选择要修改的数据行", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int caId = (Integer) table.getValueAt(row, 0);
			new CourseArrangementUpdateFrame(caId).setVisible(true);
		});

		JButton btnDelete = new JButton("删除", new ImageIcon("imgs/base/delete.png"));
		btnDelete.setBounds(484, 368, 93, 23);
		getContentPane().add(btnDelete);
		btnDelete.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选择要删除的数据行", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int caId = (Integer)table.getValueAt(row, 0);
			int clsId = (Integer)table.getValueAt(row, 1);
			int lesId = (Integer)table.getValueAt(row, 2);
			String teaNum = table.getValueAt(row, 3).toString();
			int r = JOptionPane.showConfirmDialog(this, "您要删除课程安排编号为[" + caId + "],班级编号为[" + clsId + "]," +
							"课程编号为[" + lesId + "],老师编号为[" + teaNum + "]的课程吗?", "提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (r == JOptionPane.YES_OPTION) {
				ICourseArrangementDAO courseArrangementDAO = new CourseArrangementDAOImpl();
				if (courseArrangementDAO.deleteById(caId)) {
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
		ICourseArrangementDAO courseArrangementDAO = new CourseArrangementDAOImpl();
		//总行数 数据库
		int rowCount = courseArrangementDAO.getRowCount();
		//总页数 总行数 与 pageSize计算而来
		pageCount = courseArrangementDAO.getPageCount(pageSize);
		//当前页具体数据
		List<CourseArrangement> courseArrangements = courseArrangementDAO.getNowPageData(pageSize, nowPage);

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
		model.addColumn("课程安排编号");
		model.addColumn("班级编号");
		model.addColumn("课程编号");
		model.addColumn("老师编号");
		model.addColumn("新增年份");
		model.addColumn("学期");

		for (CourseArrangement courseArrangement : courseArrangements) {
			String semeter = (courseArrangement.getSemester() == 1 ? "1" : "2");
			Object[] row = {courseArrangement.getCaId(), courseArrangement.getClsId(),
							courseArrangement.getLesId(), courseArrangement.getTeaNum(),
							courseArrangement.getYear(), semeter};
			model.addRow(row);
		}
		table.setModel(model);
	}


	
	public static void main(String[] args) {
		CourseArrangementListFrame c = new CourseArrangementListFrame();
		c.setVisible(true);
	}
}










