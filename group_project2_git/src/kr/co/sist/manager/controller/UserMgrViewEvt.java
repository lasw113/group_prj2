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

	// 전체 회원 조회
	public void setUser() {

		DefaultTableModel tempHis = mgv.getDtmUser();
		ManagerDAO m_dao = ManagerDAO.getInstance();

		try {
			// 멤버 조회
			List<UserVO> listMem = m_dao.setUser();
			tempHis.setRowCount(0);// ?
			Object[] rowData = null;
			// 히스토리가 존재한다면
			UserVO uvo = null;
			for (int i = 0; i < listMem.size(); i++) {
				uvo = listMem.get(i);
				rowData = new Object[4];
				rowData[0] = uvo.getId();// 아이디
				rowData[1] = uvo.getName();// 이름
				rowData[2] = uvo.getPhone();// 전화
				rowData[3] = uvo.getMileage();// 마일리지

				// 배열에 설정된 값을 테이블 모델에 설정하여 테이블에 반영
				tempHis.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mgv, "회원이 없습니다.");
			e.printStackTrace();
		} // end catch

	}// setUser

	// 회원 id로 회원검색
	public boolean searchUser(String id) {

		boolean flag = false;

		DefaultTableModel tempHis = mgv.getDtmUser();
		ManagerDAO m_dao = ManagerDAO.getInstance();

		try {
			// 멤버 조회
			List<UserVO> listMem = m_dao.searchUser(id);
			tempHis.setRowCount(0);// ?
			Object[] rowData = null;
			// 히스토리가 존재한다면
			UserVO uvo = null;
			for (int i = 0; i < listMem.size(); i++) {
				uvo = listMem.get(i);
				rowData = new Object[4];
				rowData[0] = uvo.getId();// 아이디
				rowData[1] = uvo.getName();// 이름
				rowData[2] = uvo.getPhone();// 전화
				rowData[3] = uvo.getMileage();// 마일리지

				// 배열에 설정된 값을 테이블 모델에 설정하여 테이블에 반영
				tempHis.addRow(rowData);
			} // end for
			if (tempHis.getRowCount() == 0) {
				flag = true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mgv, "회원이 없습니다.");
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
				// mgv.getDtmUser().setNumRows(0);// 테이블 초기화
				if (!searchUser(id)) {

				} else {
					JOptionPane.showMessageDialog(mgv, "존재하는 회원이 없습니다.");
					setUser();
				}

				mgv.getTfUser().requestFocus();
			} else {
				JOptionPane.showMessageDialog(mgv, "이름을 입력하세요");
				setUser();
			} // end else
			mgv.getTfUser().setText("");
		} // end if
	}// actionPerformed

}// class
