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
import kr.co.sist.client.vo.UpdateMileVO;

public class SelectUserFrmEvt implements ActionListener {

	private SelectUserFrm suf;
	private int mille, updateMile;

	private SelectUserVO su_vo;

	public SelectUserFrmEvt(SelectUserFrm suf) {
		this.suf = suf;
		setIdInfo(suf.getId(), suf.getRoom_id());
	}

	private void setIdInfo(String id, String room_id) {// ������ �⺻���� ����
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
			suf.getLblCanUse().setText("��밡�ɸ��ϸ��� : " + su_vo.getMillege());
			suf.getJtfMillege().setText("0");
			suf.getJtfPrice().setText(String.valueOf(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setIdInfo

	private void useMillege() {// ��� ���ϸ��� ����
		try {
			int useMile = Integer.parseInt(suf.getJtfMillege().getText());
			int price = (Integer.parseInt(su_vo.getPrice())
					* (Integer.parseInt(suf.getOut_time()) - Integer.parseInt(suf.getIn_time())) * suf.getP_cnt())
					- useMile;
			int afterMile = Integer.parseInt(su_vo.getMillege()) - useMile;
			if (useMile <= mille && useMile >= 0) {
				suf.getJtfPrice().setText(String.valueOf(price));
				suf.getLblCanUse().setText("��밡�ɸ��ϸ��� : " + afterMile);
				updateMile = useMile;
			} else {
				JOptionPane.showMessageDialog(suf, "��� ������ ���ϸ��� �ݾ��� �ƴմϴ�.");
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(suf, "���ڸ� �����մϴ�.");
			updateMile =0;
			suf.getJtfMillege().setText("0");
		} // end catch
	}// useMillege

	private void isNotEmpty() {// ����� ������ �������� �ʴٸ� �޼��� ����
		if (suf.getJtfEmail().getText().equals("") || suf.getJtfName().getText().equals("")
				|| suf.getJtfPhoneL().getText().equals("") || suf.getJtfPhoneM().getText().equals("")) {
			JOptionPane.showMessageDialog(suf, "��� ������ �Է����ּ���");
			return;
		} // end if
	}// isNotEmpty

	private boolean reservation() {// ���� ������ DB�� insert
		boolean flag = false;
		String phone = suf.getJcbPhoneF().getSelectedItem() + "-" + suf.getJtfPhoneM().getText() + "-"
				+ suf.getJtfPhoneL().getText();
		SelectRoomResVO srr_vo = suf.getSrr_vo();

		ModiUserVO mu_vo = new ModiUserVO(suf.getJtfName().getText(), phone, suf.getJtfEmail().getText(),
				suf.getJtaReq().getText(), suf.getJtfMillege().getText(), suf.getId(), srr_vo);

		UpdateMileVO um_vo = new UpdateMileVO(suf.getId(), updateMile);

		RoomCDAO r_dao = RoomCDAO.getInstance();

		try {
			switch (JOptionPane.showConfirmDialog(suf, "�����Ͻðڽ��ϱ�?")) {
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
		if (suf.getBtnUseM() == ae.getSource()) {// ���ϸ��� ��� ��ư
			if (("").equals(suf.getJtfMillege().getText())) {
				JOptionPane.showMessageDialog(suf, "����� ���ϸ����� �Է����ּ���");
				return;
			} // end if;
			useMillege();
		} // end if

		if (suf.getBtnRes() == ae.getSource()) {// ���� �ϱ� ��ư
			isNotEmpty();
			if (reservation()) {
				JOptionPane.showMessageDialog(suf, "���� �Ϸ�Ǿ����ϴ�.");
				suf.dispose();
			} // end if

		} // end if

		if (suf.getBtnClose() == ae.getSource()) {// �ݱ� ��ư
			suf.dispose();
		} // end if
	}// actionPerformed

}// class
