package kr.co.sist.manager.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.manager.controller.UserMgrViewEvt;

@SuppressWarnings("serial")
public class UserMgrView extends JPanel {

	private DefaultTableModel dtmUser;
	private JButton btnSearch;
	private JTextField tfUser;
	private JLabel lblUser;
	private JTable jtUser;
	private JPanel jpUser;
	private String id;

	public UserMgrView() {

		lblUser = new JLabel("회원검색");
		tfUser = new JTextField();
		btnSearch = new JButton("검색");
		String[] userStatus = { "아이디", "이름", "전화번호", "마일리지" };
		String[][] userData = { { "", "", "", "" } };
		dtmUser = new DefaultTableModel(userData, userStatus);

		jtUser = new JTable(dtmUser) {

			// 컬럼의 내용편집 막기
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable
				// 컬럼명 보여주기

			@Override
			public Class<?> getColumnClass(int column) {
				return super.getColumnClass(column);
			}// getColumnClass
		};

		// 컬럼의 높이 변경
		jtUser.setRowHeight(30);
		// 컬럼의 이동 막기
		jtUser.getTableHeader().setReorderingAllowed(false);

		JScrollPane jspUser = new JScrollPane(jtUser);
		jspUser.setBounds(90, 100, 810, 450);

		// jpUser=new JPanel();
		// jpUser.add(jspUser);

		setLayout(null);

		lblUser.setBounds(680, 30, 110, 30);
		tfUser.setBounds(740, 30, 130, 30);
		btnSearch.setBounds(875, 30, 85, 30);

		add(lblUser);
		add(tfUser);
		add(btnSearch);

		add("Center", jspUser);
		UserMgrViewEvt umve = new UserMgrViewEvt(this, "ham");
		jtUser.addMouseListener(umve);
		btnSearch.addActionListener(umve);

	}// UserMgrView

	public DefaultTableModel getDtmUser() {
		return dtmUser;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public JTextField getTfUser() {
		return tfUser;
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public JTable getJtUser() {
		return jtUser;
	}

	public JPanel getJpUser() {
		return jpUser;
	}

	public String getId() {
		return id;
	}

}// class
