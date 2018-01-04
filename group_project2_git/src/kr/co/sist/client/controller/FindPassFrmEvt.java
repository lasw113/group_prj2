package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.FindPassFrm;
import kr.co.sist.client.vo.FindPassVO;

public class FindPassFrmEvt implements ActionListener {
	private FindPassFrm fpf;
	private FindPassVO fpv;
	private ClientDAO c_dao;
	String Pass;
	private boolean flag;

	public FindPassFrmEvt(FindPassFrm fpf) {
		this.fpf = fpf;

		c_dao = ClientDAO.getInstance();

		// ��й�ȣ ��Ʈ �ѷ��ֱ�
		DefaultComboBoxModel<String> dcbm = fpf.getDcbm();
		try {
			List<String> listQu = c_dao.passHint();
			for (String qu : listQu) {
				if (qu.equals("null")) {
					dcbm.addElement("----------------����-------------------");
				} else {
					dcbm.addElement(qu);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// FindPassFrmEvt

	private void chkEmpty() {// �� ���� �ִ��� Ȯ���� �޼���

		String passId = fpf.getJtfId().getText().trim();
		String passBirth = fpf.getJtBirth().getText().trim();
		String passHint = (String) fpf.getJcbPassHint().getSelectedItem();
		String passAns = fpf.getJtfPassAns().getText().trim();

		flag = false;

		if (passId.equals("")) {
			JOptionPane.showMessageDialog(fpf, "���̵� �Է����ּ���");
			fpf.getJtfId().requestFocus();
			return;
		}

		if (passBirth.equals("")) {
			JOptionPane.showMessageDialog(fpf, "��������� �Է����ּ���");
			fpf.getJtBirth().requestFocus();
			return;
		}
		if (passHint.equals("----------------����-------------------")) {// ��й�ȣ ��Ʈ�� �������� ������
			JOptionPane.showMessageDialog(fpf, "��й�ȣ ��Ʈ�� �������ּ���");
			fpf.getJcbPassHint().requestFocus();
			return;
		}

		if (passAns.equals("")) {
			JOptionPane.showMessageDialog(fpf, "��й�ȣ ���� �Է����ּ���");
			fpf.getJtfPassAns().requestFocus();
			return;
		}
		if (passAns.equals("null")) {
			JOptionPane.showMessageDialog(fpf, "�̹� Ż��� ȸ���Դϴ�.");
			return;
		}

		flag = true;
	}// chkEmpty

	private void findPass() throws SQLException {// ��й�ȣ ã�� �޼���

		fpv = new FindPassVO();
		c_dao = ClientDAO.getInstance();

		String passId = fpf.getJtfId().getText();
		String passBirth = fpf.getJtBirth().getText();
		String passHint = (String) fpf.getJcbPassHint().getSelectedItem();
		String passAns = fpf.getJtfPassAns().getText().trim();

		// ��й�ȣ ���������� �ε����� �ٲٱ�
		List<String> listQu = c_dao.passHint();
		int tempInt = 0;
		for (int i = 0; i < listQu.size(); i++) {
			if ((listQu.get(i)).equals(passHint)) {
				tempInt = i;
				break;
			} //
		} //

		fpv.setId(passId);
		fpv.setBrith(passBirth);
		fpv.setPassQ(String.valueOf(tempInt));
		fpv.setPassAns(passAns);

		chkEmpty();
		if (!flag) {
			return;
		}

		try {

			Pass = c_dao.findPass(fpv);

			// ��й�ȣ �ٺ������� �ʰ� 1/2�� *(echo char)����
			int passLen = Pass.length();

			Pass = Pass.substring(0, passLen / 2);
			System.out.println(Pass.length());
			for (int i = Pass.length(); i < passLen; i++) {
				Pass += "*";
			}

			if (Pass.trim().equals("")) { // ��ġ�ϴ� ȸ�������� ������
				JOptionPane.showMessageDialog(fpf, "��ġ�ϴ� ȸ�������� �����ϴ�.");
			} else {
				JOptionPane.showMessageDialog(fpf, "ȸ������ ��й�ȣ�� " + Pass + " �Դϴ�.");
			} // end if
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// findPass

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == fpf.getBtnFindPass() || ae.getSource() == fpf.getJtfPassAns()) {
			try {
				findPass();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end if

	}// actionPerformed

}// class
