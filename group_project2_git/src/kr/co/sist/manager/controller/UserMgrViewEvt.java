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

	// id로 회원 조회
	public void setUser(String name) {

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

			mgv.getDtmUser().setNumRows(0);// 테이블 초기화
			RoomCDAO rcdao = RoomCDAO.getInstance();////////////////

			try {
				List<HistoryVO> hisList = rcdao.setHistory(history);
				HistoryVO hvo = null;
				Object[] rowData = null;

				for (int i = 0; i < hisList.size(); i++) {
					hvo = hisList.get(i);
					rowData = new Object[6];
					rowData[0] = hvo.getRes_date();// 날짜
					rowData[1] = hvo.getRes_name();// 이름
					rowData[2] = hvo.getRoom_id();// 방번호
					rowData[3] = hvo.getP_cnt();// 인원수
					rowData[4] = hvo.getPrice();// 이용금액
					rowData[5] = hvo.getIn_time() + "~" + hvo.getOut_time();// 예약시간

					// 배열에 설정된 값을 테이블 모델에 설정하여 테이블에 반영
					mgv.getDtmUser().addRow(rowData);
				} // end for

			} catch (SQLException e) {
				e.printStackTrace();
			}
			// setUser("함민이");

		} else if (mgv.getTfUser().getText().equals("")) {
			System.out.println("김홍기");
			JOptionPane.showMessageDialog(mgv, "이름을 입력하세요");
		}

	}// actionPerformed

}// class
