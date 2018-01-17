package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.MgrHistoryView;
import kr.co.sist.manager.view.UserMgrView;
import kr.co.sist.manager.vo.UserVO;

public class UserMgrViewEvt extends MouseAdapter implements ActionListener {

	private UserMgrView mgv;

	private String id;

	public static final int DOUBLE_CLICK = 2;

	public UserMgrViewEvt(UserMgrView mgv, String id) {
		this.mgv = mgv;
		this.id = id;
		setUser();
	}// UserMgrViewEvt

	// ��ü ȸ�� ��ȸ
	public void setUser() {

		DefaultTableModel tempHis = mgv.getDtmUser();
		ManagerDAO m_dao = ManagerDAO.getInstance();

		try {
			// ��� ��ȸ
			List<UserVO> listMem = m_dao.setUser();
			tempHis.setRowCount(0);// ?
			Object[] rowData = null;
			// �����丮�� �����Ѵٸ�
			UserVO uvo = null;
			for (int i = 0; i < listMem.size(); i++) {
				uvo = listMem.get(i);
				rowData = new Object[4];
				rowData[0] = uvo.getId();// ���̵�
				rowData[1] = uvo.getName();// �̸�
				rowData[2] = uvo.getPhone();// ��ȭ
				rowData[3] = uvo.getMileage();// ���ϸ���

				// �迭�� ������ ���� ���̺� �𵨿� �����Ͽ� ���̺� �ݿ�
				tempHis.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mgv, "ȸ���� �����ϴ�.");
			e.printStackTrace();
		} // end catch

	}// setUser

	// ȸ�� id�� ȸ���˻�
	public boolean searchUser(String id) {

		boolean flag = false;

		DefaultTableModel tempHis = mgv.getDtmUser();
		ManagerDAO m_dao = ManagerDAO.getInstance();

		try {
			// ��� ��ȸ
			List<UserVO> listMem = m_dao.searchUser(id);
			tempHis.setRowCount(0);// ?
			Object[] rowData = null;
			// �����丮�� �����Ѵٸ�
			UserVO uvo = null;
			for (int i = 0; i < listMem.size(); i++) {
				uvo = listMem.get(i);
				rowData = new Object[4];
				rowData[0] = uvo.getId();// ���̵�
				rowData[1] = uvo.getName();// �̸�
				rowData[2] = uvo.getPhone();// ��ȭ
				rowData[3] = uvo.getMileage();// ���ϸ���

				// �迭�� ������ ���� ���̺� �𵨿� �����Ͽ� ���̺� �ݿ�
				tempHis.addRow(rowData);
			} // end for
			if (tempHis.getRowCount() == 0) {
				flag = true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mgv, "ȸ���� �����ϴ�.");
			e.printStackTrace();
		} // end catch
		return flag;
	}// searchUser

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount()==2) {
		//case DOUBLE_CLICK:
			int row=mgv.getJtUser().getSelectedRow();
			String id=(String)mgv.getJtUser().getValueAt(row, 0);
			new MgrHistoryView(mgv, null, id);

		}
	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mgv.getTfUser() || ae.getSource() == mgv.getBtnSearch()) {
			id = mgv.getTfUser().getText();////////////////////
			if (!"".equals(id)) {
				// mgv.getDtmUser().setNumRows(0);// ���̺� �ʱ�ȭ
				if (!searchUser(id)) {

				} else {
					JOptionPane.showMessageDialog(mgv, "�����ϴ� ȸ���� �����ϴ�.");
					setUser();
				}

				mgv.getTfUser().requestFocus();
			} else {
				JOptionPane.showMessageDialog(mgv, "�̸��� �Է��ϼ���");
				setUser();
			} // end else
			mgv.getTfUser().setText("");
		} // end if
	}// actionPerformed

}// class
