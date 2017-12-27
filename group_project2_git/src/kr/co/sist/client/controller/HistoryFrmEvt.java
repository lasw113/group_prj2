package kr.co.sist.client.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.HistoryFrm;
import kr.co.sist.client.vo.HistoryVO;

public class HistoryFrmEvt {
	private HistoryFrm hv;

	public HistoryFrmEvt(HistoryFrm hv, ResChkFrmEvt rcfe, String id) {
		this.hv = hv;

		// HistoryFrm에 데이터 추가
		setHistory();
		search(id);// 나중에 바꿔야 ham
	}// HistoryFrmEvt

	public void search(String id) {
		RoomCDAO rcdao = RoomCDAO.getInstance();
		try {
			hv.getLblWho().setText(rcdao.searchName(id) + "님 예약내역");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setHistory() {
		DefaultTableModel tempHis = hv.getDtmHistory();
		RoomCDAO r_cdao = RoomCDAO.getInstance();

		try {
			// 히스토리 조회
			List<HistoryVO> listHis = r_cdao.setHistory(hv.getId());
			tempHis.setRowCount(0);// ?
			Object[] rowData = null;
			// 히스토리가 존재한다면
			HistoryVO hvo = null;
			for (int i = 0; i < listHis.size(); i++) {
				hvo = listHis.get(i);
				rowData = new Object[6];
				rowData[0] = hvo.getRes_date();// 날짜
				rowData[1] = hvo.getRes_name();// 이름
				rowData[2] = hvo.getRoom_id();// 방번호
				rowData[3] = hvo.getP_cnt();// 인원수
				rowData[4] = hvo.getPrice();// 이용금액
				rowData[5] = hvo.getIn_time() + "~" + hvo.getOut_time();// 예약시간

				// 배열에 설정된 값을 테이블 모델에 설정하여 테이블에 반영
				tempHis.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(hv, "히스토리가 없습니다.");
			e.printStackTrace();
		} // end catch
	}// setHistory

}// class
