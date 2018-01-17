package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.FindIDFrm;
import kr.co.sist.client.vo.FindIDVO;

public class FindIDFrmEvt implements ActionListener {
	private FindIDFrm fidf;
	private ClientDAO c_dao;
	private FindIDVO fiv;
	private boolean flag;
	private String id;

	public FindIDFrmEvt(FindIDFrm fidf) {
		this.fidf = fidf;
	}// FindIDFrmEvt

	private void chkEmpty() {// 누락된 정보가 있는지 확인하는 메서드
		String fidName = fidf.getJtfName().getText().trim();
		String fidBirth = fidf.getJtfBirth().getText().trim();
		String fidPhoneM =  fidf.getJtfPhoneM().getText();
		String fidPhoneL =  fidf.getJtfPhoneL().getText();

		flag = false;

		if ("".equals(fidName)) {
			JOptionPane.showMessageDialog(fidf, "이름을 입력해주세요");
			fidf.getJtfName().requestFocus();
			return;
		}

		if ("".equals(fidBirth)) {
			JOptionPane.showMessageDialog(fidf, "생년월일을 입력해주세요");
			fidf.getJtfBirth().requestFocus();
			return;
		}
		if (fidBirth.equals("000000")) {
			JOptionPane.showMessageDialog(fidf, "이미 탈퇴된 회원입니다.");
			return;
		}

		if ("".equals(fidPhoneM)||"".equals(fidPhoneL)) {
			JOptionPane.showMessageDialog(fidf, "핸드폰번호를 입력해주세요");
			return;
		}
		flag = true;
	}// chkEmpty

	private void findId() throws SQLException { // id를 찾는 메서드
		fiv = new FindIDVO();
		c_dao = ClientDAO.getInstance();

		String fidName = fidf.getJtfName().getText();
		String fidBirth = fidf.getJtfBirth().getText();
		String fidPhone = fidf.getJcbPhoneF().getSelectedItem() + "-" + fidf.getJtfPhoneM().getText() + "-"
				+ fidf.getJtfPhoneL().getText();

		fiv.setName(fidName);
		fiv.setBirth(fidBirth);
		fiv.setPhone(fidPhone);
		chkEmpty();
		if (!flag) {
			return;
		}
		try {
			id = c_dao.findID(fiv);

			if ("".equals(id)) {
				// 일치하는 회원정보가 없을때
				JOptionPane.showMessageDialog(fidf, "일치하는 회원정보가 없습니다.");
				return;
			} else {
				JOptionPane.showMessageDialog(fidf, "회원님의 아이디는 " + id + " 입니다.");
				return;
			} // end if
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// findId

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == fidf.getBtnFindId() || ae.getSource() == fidf.getJtfPhoneL()) {
			try {
				findId();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end if
	}// actionPerformed

}// class
