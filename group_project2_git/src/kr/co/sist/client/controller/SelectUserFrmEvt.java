package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.SelectUserFrm;
import kr.co.sist.client.vo.ModiUserVO;
import kr.co.sist.client.vo.SelectRoomResVO;
import kr.co.sist.client.vo.SelectUserVO;
import kr.co.sist.client.vo.UpdateMileVO;

public class SelectUserFrmEvt implements ActionListener {

	private SelectUserFrm suf;
	private int mille, updateMile, price;
	private boolean flag;

	private SelectUserVO su_vo;

	public SelectUserFrmEvt(SelectUserFrm suf) {
		this.suf = suf;
		setIdInfo(suf.getId(), suf.getRoom_id());
	}

	private void setIdInfo(String id, String room_id) {// 예약자 기본정보 세팅
		ClientDAO c_dao = ClientDAO.getInstance();

		try {
			su_vo = c_dao.selectUserInfo(id, room_id);

			String phoneF = su_vo.getPhone().substring(0, 3);
			String phoneM = su_vo.getPhone().substring(4, 8);
			String phoneL = su_vo.getPhone().substring(9, 13);

			mille = Integer.parseInt(su_vo.getMillege());
			price = (Integer.parseInt(su_vo.getPrice())
					* (Integer.parseInt(suf.getOut_time()) - Integer.parseInt(suf.getIn_time())) * suf.getP_cnt());
			suf.getJtfName().setText(su_vo.getName());
			suf.getJcbPhoneF().setSelectedItem(phoneF);
			suf.getJtfPhoneM().setText(phoneM);
			suf.getJtfPhoneL().setText(phoneL);
			suf.getJtfEmail().setText(su_vo.getEmail());
			suf.getLblCanUse().setText("사용가능마일리지 : " + su_vo.getMillege());
			suf.getJtfMillege().setText("0");
			suf.getJtfPrice().setText(String.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setIdInfo

	private void useMillege() {// 사용 마일리지 적용
		try {
			int useMile = Integer.parseInt(suf.getJtfMillege().getText());
			int use_price = price - useMile;
			int afterMile = Integer.parseInt(su_vo.getMillege()) - useMile;
			System.out.println(Integer.parseInt(su_vo.getPrice()));
			if (useMile <= mille && useMile >= 0 && useMile <= price) {
				suf.getJtfPrice().setText(String.valueOf(use_price));
				suf.getLblCanUse().setText("사용가능마일리지 : " + afterMile);
				updateMile = useMile;
			} else {
				JOptionPane.showMessageDialog(suf, "사용 가능한 마일리지 금액이 아닙니다.");
				suf.getJtfMillege().setText("0");
				suf.getJtfPrice().setText(String.valueOf(price));
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(suf, "숫자만 가능합니다.");
			updateMile = 0;
			suf.getJtfMillege().setText("0");
		} // end catch
	}// useMillege

	private void isNotEmpty() {// 사용자 정보가 적혀있지 않다면 메세지 띄우기
		flag = false;

		if ("".equals(suf.getJtfName().getText())) {
			JOptionPane.showMessageDialog(suf, "예약자명을 반드시 입력해주세요");
			return;
		} // end if

		if ("".equals(suf.getJtfPhoneM().getText()) || "".equals(suf.getJtfPhoneL().getText())) {
			// 폰번호를 입력하지 않았을때
			JOptionPane.showMessageDialog(suf, "핸드폰 번호를 반드시 입력해주세요.");
			return;
		} // end if

		for (int i = 0; i < suf.getJtfPhoneM().getText().length(); i++) {
			char c1 = suf.getJtfPhoneM().getText().charAt(i);
			if ((c1 < 48 || c1 > 57) || (suf.getJtfPhoneM().getText().length() != 4)) {// 폰번호 가운데가 숫자가 아니고 4자리가 아닌경우
				JOptionPane.showMessageDialog(suf, "핸드폰 번호는 4자리 숫자만 입력해주세요");
				suf.getJtfPhoneM().requestFocus();
				return;

			} // end if
		} // end for

		for (int i = 0; i < suf.getJtfPhoneL().getText().length(); i++) {
			char c2 = suf.getJtfPhoneL().getText().charAt(i);
			if ((c2 < 48 || c2 > 57) || (suf.getJtfPhoneL().getText().length() != 4)) {// 폰번호 마지막이 숫자가 아니고 4자리가 아닌경우
				JOptionPane.showMessageDialog(suf, "핸드폰 번호는 4자리 숫자만 입력해주세요");
				suf.getJtfPhoneL().requestFocus();
				return;
			} // end if
		} // end for

		if ("".equals(suf.getJtfEmail().getText())) {
			JOptionPane.showMessageDialog(suf, "이메일을 반드시 입력해주세요.");
			suf.getJtfEmail().requestFocus();
			return;
		} // end if

		// 메일 유효성검사
		if (suf.getJtfEmail().getText().indexOf("@") == -1 || suf.getJtfEmail().getText().indexOf(".") == -1) {
			JOptionPane.showMessageDialog(suf, "올바르지 않은 이메일입니다.");
			suf.getJtfEmail().requestFocus();
			return;
		} // end if

		flag = true;
	}// isNotEmpty

	private boolean reservation() {// 예약 정보를 DB에 insert
		boolean flag = false;
		String phone = suf.getJcbPhoneF().getSelectedItem() + "-" + suf.getJtfPhoneM().getText() + "-"
				+ suf.getJtfPhoneL().getText();
		SelectRoomResVO srr_vo = suf.getSrr_vo();

		ModiUserVO mu_vo = new ModiUserVO(suf.getJtfName().getText(), phone, suf.getJtfEmail().getText(),
				suf.getJtaReq().getText(), suf.getJtfMillege().getText(), suf.getId(), srr_vo);

		UpdateMileVO um_vo = new UpdateMileVO(suf.getId(), updateMile);

		RoomCDAO r_dao = RoomCDAO.getInstance();

		try {
			switch (JOptionPane.showConfirmDialog(suf, "예약하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				r_dao.insertRes(mu_vo);
				r_dao.updateMile(um_vo);
				flag = true;
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
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
			if (!flag) {
				return;
			} // end if
			if (reservation()) {
				JOptionPane.showMessageDialog(suf, "예약 완료되었습니다.");
				suf.dispose();
			} // end if

		} // end if

		if (suf.getBtnClose() == ae.getSource()) {// 닫기 버튼
			suf.dispose();
		} // end if
	}// actionPerformed

}// class
