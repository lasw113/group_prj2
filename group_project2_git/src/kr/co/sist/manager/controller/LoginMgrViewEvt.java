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

		if (("").equals(id)) { // ID�Է¶��� ����ִ� ���
			JOptionPane.showMessageDialog(lmv, "���̵� �Է��ϼ���");
			lmv.getJtfId().requestFocus();
			return;
		} // end if
			// ��й�ȣ�� �Էµ��� ������� .
		if (("").equals(pass)) {
			JOptionPane.showMessageDialog(lmv, "��й�ȣ�� �Է��ϼ���");
			lmv.getJtfPass().requestFocus();
			return;
		} // end if

		lv = new LoginVO(id, pass);
		m_dao = ManagerDAO.getInstance();

		try {
			boolean logChk = m_dao.selectLogin(lv);
			if (logChk) {
				// ����â ���� �α��� â ����(������)

				lmv.dispose();
			} else {
				// ��ġ�ϴ� ȸ�������� ������
				JOptionPane.showMessageDialog(lmv, " ��ϵ��� ���� ���̵��̰ų�, ���̵� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			} // end if
				// ���̵�, ��й�ȣ �ʱ�ȭ
			lmv.getJtfId().setText("");
			lmv.getJtfPass().setText("");
			// ���̵�� Ŀ��
			lmv.getJtfId().requestFocus();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// managerLogin

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (lmv.getBtnLogin() == ae.getSource() || lmv.getJtfPass() == ae.getSource()) {// �α��� ��ư, ��й�ȣ ����(�α���)
			managerLogin();
		} // end if

		if (lmv.getJtfId() == ae.getSource()) {// ���̵� ����(Ŀ��)
			lmv.getJtfPass().requestFocus();
		} // end if
	}// actionPerformed

}
