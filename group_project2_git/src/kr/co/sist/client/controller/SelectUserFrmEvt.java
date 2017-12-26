package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.SelectUserFrm;
import kr.co.sist.client.vo.ModiUserVO;
import kr.co.sist.client.vo.SelectRoomResVO;
import kr.co.sist.client.vo.SelectUserVO;

public class SelectUserFrmEvt implements ActionListener {

	private SelectUserFrm suf;
	private int mille;

	public SelectUserFrmEvt(SelectUserFrm suf) {
		this.suf = suf;
		setIdInfo(suf.getId(), suf.getRoom_id());
	}

	SelectUserVO su_vo = null;

	private void setIdInfo(String id, String room_id) {// 예약자 기본정보 세팅
		RoomCDAO r_dao = RoomCDAO.getInstance();

		try {
			su_vo = r_dao.selectUserInfo(id, room_id);

			String phoneF = su_vo.getPhone().substring(0, 3);
			String phoneM = su_vo.getPhone().substring(4, 8);
			String phoneL = su_vo.getPhone().substring(9, 13);

			mille = Integer.parseInt(su_vo.getMillege());
			int price = (Integer.parseInt(su_vo.getPrice())
					* (Integer.parseInt(suf.getOut_time()) - Integer.parseInt(suf.getIn_time())) * suf.getP_cnt());
			suf.getJtfName().setText(su_vo.getName());
			suf.getJcbPhoneF().setSelectedItem(phoneF);
			suf.getJtfPhoneM().setText(phoneM);
			suf.getJtfPhoneL().setText(phoneL);
			suf.getJtfEmail().setText(su_vo.getEmail());
			suf.getLblCanUse().setText("사용가능마일리지 : " + su_vo.getMillege());
			suf.getJtfPrice().setText(String.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setIdInfo

	private void useMillege() {// 사용 마일리지 적용
		int useMile = Integer.parseInt(suf.getJtfMillege().getText());
		int price = (Integer.parseInt(su_vo.getPrice())
				* (Integer.parseInt(suf.getOut_time()) - Integer.parseInt(suf.getIn_time())) * suf.getP_cnt())
				- useMile;
		if (useMile <= mille && useMile > 0) {
			suf.getJtfPrice().setText(String.valueOf(price));
		} else {
			JOptionPane.showMessageDialog(suf, "사용 가능한 마일리지 금액이 아닙니다.");
		}
	}// useMillege

	private void isNotEmpty() {// 사용자 정보가 적혀있지 않다면 메세지 띄우기
		if (suf.getJtfEmail().getText().equals("") || suf.getJtfName().getText().equals("")
				|| suf.getJtfPhoneL().getText().equals("") || suf.getJtfPhoneM().getText().equals("")) {
			JOptionPane.showMessageDialog(suf, "모든 정보를 입력해주세요");
			return;
		} // end if
	}// isNotEmpty

	private void reservation() {// 예약 정보를 DB에 insert
		String phone = suf.getJcbPhoneF().getSelectedItem() + "-" + suf.getJtfPhoneM().getText() + "-"
				+ suf.getJtfPhoneL().getText();
		ModiUserVO mu_vo = new ModiUserVO(suf.getJtfName().getText(), phone, suf.getJtfEmail().getText(),
				suf.getJtaReq().getText(), suf.getJtfMillege().getText());

		SelectRoomResVO srr_vo = suf.getSrr_vo();

		RoomCDAO r_dao = RoomCDAO.getInstance();

		try {
			r_dao.insertRes(mu_vo, srr_vo, suf.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// reservation

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (suf.getBtnUseM() == ae.getSource()) {// 마일리지 사용 버튼
			if (("").equals(suf.getJtfMillege().getText())) {
				JOptionPane.showMessageDialog(suf, "사용할 마일리지를 입력해주세요");
				return;
			} // end if;
			useMillege();
		} // end if

		if (suf.getBtnRes() == ae.getSource()) {// 예약 하기 버튼
			isNotEmpty();
			reservation();
			JOptionPane.showMessageDialog(suf, "예약 완료되었습니다.");

			suf.dispose();
		} // end if

		if (suf.getBtnClose() == ae.getSource()) {// 닫기 버튼
			suf.dispose();
		} // end if
	}// actionPerformed

}// class
