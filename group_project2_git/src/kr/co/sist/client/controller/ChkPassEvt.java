package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.ChkPassFrm;
import kr.co.sist.client.frm.ClientMainFrm;

public class ChkPassEvt implements ActionListener {
	private ChkPassFrm cpf;
	private ClientMainFrm cmf;
	private ClientDAO c_dao;
	private String inputPass = "";

	public ChkPassEvt(ChkPassFrm cpf, ClientMainFrm cmf) {
		this.cpf = cpf;
		this.cmf = cmf;
	}// ChkPassEvt

	private void chkIdPass() throws SQLException {// �� ����Ȯ�� ���� ���� ��й�ȣ�� �´��� Ȯ���ϴ� �޼ҵ�
		inputPass = new String(cpf.getJpwPass().getPassword());

		c_dao = ClientDAO.getInstance();
		try {
			String admin_pass = c_dao.myInfoLogin(LoginFrmEvt.id);
			if (inputPass.equals(admin_pass)) {
				cmf.setAdminLoginStatus(true);

				cpf.dispose();
			} else {
				// �α��� ����
				JOptionPane.showMessageDialog(cpf, "��й�ȣ�� �ٽ� �Է����ּ���");
			}
			cpf.getJpwPass().setText("");
			cpf.getJpwPass().requestFocus();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// chkIdPass

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cpf.getBtnOk() || ae.getSource() == cpf.getJpwPass()) {
			try {
				chkIdPass();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}// actionPerformed

}// class
