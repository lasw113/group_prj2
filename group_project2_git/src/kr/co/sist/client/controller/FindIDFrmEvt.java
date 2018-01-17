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

	private void chkEmpty() {// ������ ������ �ִ��� Ȯ���ϴ� �޼���
		String fidName = fidf.getJtfName().getText().trim();
		String fidBirth = fidf.getJtfBirth().getText().trim();
		String fidPhoneM =  fidf.getJtfPhoneM().getText();
		String fidPhoneL =  fidf.getJtfPhoneL().getText();

		flag = false;

		if ("".equals(fidName)) {
			JOptionPane.showMessageDialog(fidf, "�̸��� �Է����ּ���");
			fidf.getJtfName().requestFocus();
			return;
		}

		if ("".equals(fidBirth)) {
			JOptionPane.showMessageDialog(fidf, "��������� �Է����ּ���");
			fidf.getJtfBirth().requestFocus();
			return;
		}
		if (fidBirth.equals("000000")) {
			JOptionPane.showMessageDialog(fidf, "�̹� Ż��� ȸ���Դϴ�.");
			return;
		}

		if ("".equals(fidPhoneM)||"".equals(fidPhoneL)) {
			JOptionPane.showMessageDialog(fidf, "�ڵ�����ȣ�� �Է����ּ���");
			return;
		}
		flag = true;
	}// chkEmpty

	private void findId() throws SQLException { // id�� ã�� �޼���
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
				// ��ġ�ϴ� ȸ�������� ������
				JOptionPane.showMessageDialog(fidf, "��ġ�ϴ� ȸ�������� �����ϴ�.");
				return;
			} else {
				JOptionPane.showMessageDialog(fidf, "ȸ������ ���̵�� " + id + " �Դϴ�.");
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
