package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.vo.HistoryVO;
import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.MgrHistoryView;
import kr.co.sist.manager.view.UserMgrView;
import kr.co.sist.manager.vo.UserVO;

public class UserMgrViewEvt extends MouseAdapter implements ActionListener {

	private UserMgrView mgv;

	private String history;//////////////

	public static final int DOUBLE_CLICK = 2;

	public UserMgrViewEvt(UserMgrView mgv, String id) {
		this.mgv = mgv;
		setUser(id);
	}// UserMgrViewEvt

	// id�� ȸ�� ��ȸ
	public void setUser(String name) {

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

	@Override
	public void mouseClicked(MouseEvent me) {
		switch (me.getClickCount()) {
		case DOUBLE_CLICK:
			new MgrHistoryView(null, null, "jul");

		}
	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mgv.getBtnSearch()) {
			history = mgv.getTfUser().getText();////////////////////

			mgv.getDtmUser().setNumRows(0);// ���̺� �ʱ�ȭ
			RoomCDAO rcdao = RoomCDAO.getInstance();////////////////

			try {
				List<HistoryVO> hisList = rcdao.setHistory(history);
				HistoryVO hvo = null;
				Object[] rowData = null;

				for (int i = 0; i < hisList.size(); i++) {
					hvo = hisList.get(i);
					rowData = new Object[6];
					rowData[0] = hvo.getRes_date();// ��¥
					rowData[1] = hvo.getRes_name();// �̸�
					rowData[2] = hvo.getRoom_id();// ���ȣ
					rowData[3] = hvo.getP_cnt();// �ο���
					rowData[4] = hvo.getPrice();// �̿�ݾ�
					rowData[5] = hvo.getIn_time() + "~" + hvo.getOut_time();// ����ð�

					// �迭�� ������ ���� ���̺� �𵨿� �����Ͽ� ���̺� �ݿ�
					mgv.getDtmUser().addRow(rowData);
				} // end for

			} catch (SQLException e) {
				e.printStackTrace();
			}
			// setUser("�Թ���");

		} else if (mgv.getTfUser().getText().equals("")) {
			System.out.println("��ȫ��");
			JOptionPane.showMessageDialog(mgv, "�̸��� �Է��ϼ���");
		}

	}// actionPerformed

}// class
