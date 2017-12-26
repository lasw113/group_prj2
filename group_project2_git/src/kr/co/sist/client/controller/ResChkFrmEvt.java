package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.CancelFrm;
import kr.co.sist.client.frm.HistoryFrm;
import kr.co.sist.client.frm.ResChkFrm;
import kr.co.sist.client.vo.ResChkVO;

public class ResChkFrmEvt extends MouseAdapter implements ActionListener {
	private ResChkFrm rcf;
	private String res_id;

	public ResChkFrmEvt(ResChkFrm rcf) {
		this.rcf = rcf;
		// 디자인 클래스에 이벤트가 할당되면 예약확인테이블에 데이터 추가
		resChk();
	}// ResChkFrmEvt

	public void resChk() {
		DefaultTableModel tempRes = rcf.getDtmRoom();
		RoomCDAO r_cdao = RoomCDAO.getInstance();

		try {
			// 예약을 조회
			List<ResChkVO> listRes = r_cdao.ResChk(rcf.getId());
			tempRes.setRowCount(0);
			Object[] rowData = null;
			// 조회된 예약이 존재한다면
			ResChkVO rcvo = null;// ?
			for (int i = 0; i < listRes.size(); i++) {
				// 메뉴를 받아와서 배열에 설정
				rcvo = listRes.get(i);
				rowData = new Object[7];
				rowData[0] = rcvo.getRes_id();// 예약코드
				rowData[1] = rcvo.getRes_name();// 이름
				rowData[2] = rcvo.getRoom_id();// 방번호
				rowData[3] = rcvo.getP_cnt();// 인원수
				rowData[4] = rcvo.getPrice();// 이용금액
				rowData[5] = rcvo.getIn_time() + "~" + rcvo.getOut_time();// 예약시간
				rowData[6] = rcvo.getDate();// 예약날짜

				// 배열에 설정된 값을 테이블 모델에 설정하여 테이블에 반영되되록 함
				tempRes.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rcf, "예약조회를 할 수 없습니다.");
			e.printStackTrace();
		}
	}// resChk

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int row = rcf.getJtRes().getSelectedRow();
			res_id = (String) rcf.getJtRes().getValueAt(row, 0);
		}
	}// end if

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == rcf.getBtnHistory()) {
			new HistoryFrm(rcf, this, rcf.getId());
			// 예약내역 조회 폼
		} // end if

		if (ae.getSource() == rcf.getBtnCancel()) {
			try {
				if (rcf.getJtRes().getSelectedRow() != -1) {
					new CancelFrm(rcf, this, rcf.getId(), res_id, rcf.getPass());
				} else {
					JOptionPane.showMessageDialog(rcf, "취소할 예약을 선택하세요.");
				} // end else
			} catch (IOException e) {
				System.out.println("아이디가 맞지 않습니다.");
				e.printStackTrace();
			}
		} // end if
	}// actionPerformed

}// class
