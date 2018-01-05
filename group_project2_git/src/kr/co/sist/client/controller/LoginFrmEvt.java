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

	private void chkLogin() {// �α��� �޼���
		id = lf.getJtfId().getText();
		pass = new String(lf.getJtfPass().getPassword());
		if (pass.equals("null")) {
			JOptionPane.showMessageDialog(lf, "�̹� Ż��� ȸ���Դϴ�.");
			lf.getJtfId().setText("");
			lf.getJtfPass().setText("");
			return;
		}

		// LogInForm���� id, pw�� �������-������ ����
		if ("".equals(id)) { // ID�Է¶��� ����ִ� ���
			JOptionPane.showMessageDialog(lf, "���̵� �Է��ϼ���");
			lf.getJtfId().requestFocus();
			return;
		}
		// ��й�ȣ�� �Էµ��� ������� .
		if ("".equals(pass)) {
			JOptionPane.showMessageDialog(lf, "��й�ȣ�� �Է��ϼ���");
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
				// ����â ���� �α��� â ����
				new ClientMainFrm(id, pass);
				lf.dispose();
			} else {
				// ��ġ�ϴ� ȸ�������� ������
				JOptionPane.showMessageDialog(lf, " ��ϵ��� ���� ���̵��̰ų�, ���̵� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
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
