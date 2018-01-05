package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.ClientMainFrm;
import kr.co.sist.client.frm.FindIDFrm;
import kr.co.sist.client.frm.FindPassFrm;
import kr.co.sist.client.frm.JoinFrm;
import kr.co.sist.client.frm.LoginFrm;
import kr.co.sist.client.vo.LoginVO;

public class LoginFrmEvt implements ActionListener {
	private LoginFrm lf;
	private LoginVO lv;
	private ClientDAO c_dao;
	public static String id, pass;

	public LoginFrmEvt(LoginFrm lf) {
		this.lf = lf;
	}// LoginFrmEvt

	private void chkLogin() {// 로그인 메서드
		id = lf.getJtfId().getText();
		pass = new String(lf.getJtfPass().getPassword());
		if (pass.equals("null")) {
			JOptionPane.showMessageDialog(lf, "이미 탈퇴된 회원입니다.");
			lf.getJtfId().setText("");
			lf.getJtfPass().setText("");
			return;
		}

		// LogInForm에서 id, pw가 비었을때-누락된 사항
		if ("".equals(id)) { // ID입력란이 비어있는 경우
			JOptionPane.showMessageDialog(lf, "아이디를 입력하세요");
			lf.getJtfId().requestFocus();
			return;
		}
		// 비밀번호가 입력되지 않을경우 .
		if ("".equals(pass)) {
			JOptionPane.showMessageDialog(lf, "비밀번호를 입력하세요");
			lf.getJtfPass().requestFocus();
			return;
		}

		lv = new LoginVO();
		c_dao = ClientDAO.getInstance();

		lv.setId(id);
		lv.setPassword(pass);

		try {
			boolean logChk = c_dao.selectLogin(lv);
			if (logChk) {
				// 메인창 띄우고 로그인 창 끄기
				new ClientMainFrm(id, pass);
				lf.dispose();
			} else {
				// 일치하는 회원정보가 없을때
				JOptionPane.showMessageDialog(lf, " 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			} // end if
			lf.getJtfId().setText("");
			lf.getJtfPass().setText("");
			lf.getJtfId().requestFocus();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// chkLogin

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == lf.getJtfId()) {
			lf.getJtfPass().requestFocus();
		} // end if
		if ((ae.getSource() == lf.getBtnLogin()) || (ae.getSource() == lf.getJtfPass())) {
			chkLogin();
		} // end if

		if (ae.getSource() == lf.getBtnJoin()) {
			new JoinFrm(lf);
		}
		if (ae.getSource() == lf.getBtnFindId()) {
			new FindIDFrm(lf);
		}
		if (ae.getSource() == lf.getBtnFindPass()) {
			new FindPassFrm(lf);
		}

	}// actionPerformed

}// class
