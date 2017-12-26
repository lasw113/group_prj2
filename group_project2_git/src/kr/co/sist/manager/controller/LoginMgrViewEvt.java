package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.LoginMgrView;
import kr.co.sist.manager.vo.LoginVO;

public class LoginMgrViewEvt implements ActionListener {

	private LoginMgrView lmv;
	private LoginVO lv;
	private ManagerDAO m_dao;

	public LoginMgrViewEvt(LoginMgrView lmv) {
		this.lmv = lmv;
	}// LoginMgrViewEvt

	private void managerLogin() {

		String id = lmv.getJtfId().getText().trim();
		String pass = new String(lmv.getJtfPass().getPassword()).trim();

		if (("").equals(id)) { // ID입력란이 비어있는 경우
			JOptionPane.showMessageDialog(lmv, "아이디를 입력하세요");
			lmv.getJtfId().requestFocus();
			return;
		} // end if
			// 비밀번호가 입력되지 않을경우 .
		if (("").equals(pass)) {
			JOptionPane.showMessageDialog(lmv, "비밀번호를 입력하세요");
			lmv.getJtfPass().requestFocus();
			return;
		} // end if

		lv = new LoginVO(id, pass);
		m_dao = ManagerDAO.getInstance();

		try {
			boolean logChk = m_dao.selectLogin(lv);
			if (logChk) {
				// 메인창 띄우고 로그인 창 끄기(관리자)

				lmv.dispose();
			} else {
				// 일치하는 회원정보가 없을때
				JOptionPane.showMessageDialog(lmv, " 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			} // end if
				// 아이디, 비밀번호 초기화
			lmv.getJtfId().setText("");
			lmv.getJtfPass().setText("");
			// 아이디로 커서
			lmv.getJtfId().requestFocus();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// managerLogin

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (lmv.getBtnLogin() == ae.getSource() || lmv.getJtfPass() == ae.getSource()) {// 로그인 버튼, 비밀번호 엔터(로그인)
			managerLogin();
		} // end if

		if (lmv.getJtfId() == ae.getSource()) {// 아이디 엔터(커서)
			lmv.getJtfPass().requestFocus();
		} // end if
	}// actionPerformed

}
