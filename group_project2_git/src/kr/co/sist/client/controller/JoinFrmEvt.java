package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.JoinFrm;
import kr.co.sist.client.vo.JoinVO;

public class JoinFrmEvt implements ActionListener {
	private JoinFrm jf;
	private ClientDAO c_dao;
	private JoinVO jv;
	private boolean flag;

	public JoinFrmEvt(JoinFrm jf) {
		this.jf = jf;

		// ��й�ȣ ��Ʈ �޾ƿ���
		c_dao = ClientDAO.getInstance();

		DefaultComboBoxModel<String> dcbm = jf.getDcbPassHint();
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
	}// JoinFrmEvt

	private boolean chkDupleId() throws SQLException { // �ߺ� id�� �ִ��� Ȯ���� �ż���
		c_dao = ClientDAO.getInstance();
		String id = jf.getJtfId().getText().trim();
		// ���̵� �ߺ� üũ
		boolean chkId = c_dao.dupleId(id);

		if (chkId) {
			JOptionPane.showMessageDialog(jf, id + "��(��) �̹� ��� ���� ���̵� �Դϴ�.");

		} else if (!id.equals("")) {
			JOptionPane.showMessageDialog(jf, id + "��(��) ��� ������ ���̵� �Դϴ�.");
			flag = true; // �ؿ� �Է¾��������� if���� Ÿ������ ���
		} else {// ���̵� �Է� ���� �ʾ�����

			JOptionPane.showMessageDialog(jf, "���̵� �Էµ��� �ʾҽ��ϴ�.");

		} // end if

		return chkId;
	}// chkDupleId

	private void join() {// ȸ������ �޼���

		c_dao = ClientDAO.getInstance();
		jv = new JoinVO();

		String id = jf.getJtfId().getText().trim();
		String pw = new String(jf.getJtfPass().getPassword());
		String pwChk = new String(jf.getJtfChkPass().getPassword());
		String name = jf.getJtfName().getText().trim();
		String email = jf.getJtfEmail().getText().trim();
		String birth = jf.getJtfBirth().getText().trim();
		String phone = jf.getJcbPhoneF().getSelectedItem() + "-" + jf.getJtfPhoneM().getText() + "-"
				+ jf.getJtfPhoneL().getText();
		String phone_m = jf.getJtfPhoneM().getText();
		String phone_l = jf.getJtfPhoneL().getText();
		String pass_ans = jf.getJtfHintAns().getText().trim();
		String pass_index = (String) jf.getJcbPassHint().getSelectedItem();

		if (flag) {
			try {

				// ���̵� ������,���ڰ� �ƴҶ�
				for (int i = 0; i < id.length(); i++) {
					char c2 = id.charAt(i);
					if (!((c2 >= 0x61 && c2 <= 0x7A) || (c2 >= 0x41 && c2 <= 0x5A) || (c2 >= 0x30 && c2 <= 0x39))) {
						JOptionPane.showMessageDialog(jf, "���̵�� ������,���ڷ� �̷������ �մϴ�.");
						return;
					} // end if
				} // end for

				// ��й�ȣ, ��й�ȣ Ȯ�� �κ��� �Է����� �ʾ�����
				if (pw.equals("null")) {
					JOptionPane.showMessageDialog(jf, "����� �� ���� ��й�ȣ�Դϴ�.");
					return;
				} // end if
				if (pw.equals("") || pwChk.equals("")) {
					JOptionPane.showMessageDialog(jf, "��й�ȣ �Ǵ� ��й�ȣ Ȯ���� �ݵ�� �Է����ּ���.");
					return;
				} // end if
					// ��й�ȣ�� ��ġ���� ������
				if (!pw.equals(pwChk)) {
					JOptionPane.showMessageDialog(jf, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					return;
				} // end if
					// ��й�ȣ�� 13�ڸ� �ʰ� 4�ڸ� �̸��϶�
				if (pw.length() < 4) {
					JOptionPane.showMessageDialog(jf, "��й�ȣ�� 4�ڸ� �̻��̿��� �մϴ�.");
					return;
				} // end if
				if (pass_index.equals("----------------����-------------------")) {// ��й�ȣ ��Ʈ�� �������� ������
					JOptionPane.showMessageDialog(jf, "��й�ȣ ��Ʈ�� �������ּ���");
					return;
				}
				if (pass_ans.equals("")) {
					// ��й�ȣ ��Ʈ�� �Է����� �ʾ�����
					JOptionPane.showMessageDialog(jf, "��й�ȣ ��Ʈ���� �ݵ�� �Է����ּ���.");
					return;
				} // end if
					// �̸��� �Է����� �ʾ�����
				if (name.equals("")) {
					JOptionPane.showMessageDialog(jf, "�̸��� �ݵ�� �Է����ּ���.");
					return;
				} // end if
				if (birth.equals("")) {
					// ������� �Է����� �ʾ�����
					JOptionPane.showMessageDialog(jf, "��������� �ݵ�� �Է����ּ���.");
					return;
				} // end if

				for (int i = 0; i < birth.length(); i++) {
					char c1 = birth.charAt(i);
					if ((c1 < 48 || c1 > 57) || (birth.length() != 6)) {// // ������� 6�ڸ� + ���ڷ� �Է����� �ʾ�����
						JOptionPane.showMessageDialog(jf, "�ùٸ��� ���� ������� �Դϴ�.");
						return;

					} // end if
				} // end for
					// ������ �Է����� �ʾ�����
				if (email.equals("")) {
					JOptionPane.showMessageDialog(jf, "�̸����� �ݵ�� �Է����ּ���.");
					return;
				} // end if

				// ���� ��ȿ���˻�
				if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(jf, "�ùٸ��� ���� �̸����Դϴ�.");
					return;
				} // end if

				if (phone_m.equals("") || phone_l.equals("")) {
					// ����ȣ�� �Է����� �ʾ�����
					JOptionPane.showMessageDialog(jf, "�ڵ��� ��ȣ�� �ݵ�� �Է����ּ���.");
					return;
				} // end if

				for (int i = 0; i < phone_m.length(); i++) {
					char c1 = phone_m.charAt(i);
					if ((c1 < 48 || c1 > 57) || (phone_m.length() != 4)) {// ����ȣ ����� ���ڰ� �ƴϰ� 4�ڸ��� �ƴѰ��
						JOptionPane.showMessageDialog(jf, "�ڵ��� ��ȣ�� 4�ڸ� ���ڸ� �Է����ּ���");
						return;

					} // end if
				} // end for

				for (int i = 0; i < phone_l.length(); i++) {
					char c2 = phone_l.charAt(i);
					if ((c2 < 48 || c2 > 57) || (phone_l.length() != 4)) {// ����ȣ �������� ���ڰ� �ƴϰ� 4�ڸ��� �ƴѰ��
						JOptionPane.showMessageDialog(jf, "�ڵ��� ��ȣ�� 4�ڸ� ���ڸ� �Է����ּ���");
						return;
					} // end if
				} // end for

				// ��й�ȣ ���������� �ε����� �ٲٱ�
				String temp = String.valueOf(jf.getDcbPassHint().getSelectedItem());
				List<String> listQu = c_dao.passHint();
				int tempInt = 0;
				for (int i = 0; i < listQu.size(); i++) {
					if ((listQu.get(i)).equals(temp)) {
						tempInt = i;
						break;
					} //
				} //

				// JoinVO�� ���� ȸ�������� DB�� �ֱ�
				jv.setId(id);
				jv.setPass(pw);
				jv.setName(name);
				jv.setPassIndex(String.valueOf(tempInt));
				jv.setPassAns(pass_ans);
				jv.setBirth(birth);
				jv.setEmail(email);
				jv.setPhone(phone);

				c_dao.joinClient(jv);
				// ���� ������ �޼��� > �α��� ȭ������ �̵�
				JOptionPane.showMessageDialog(jf, "������ �Ϸ�Ǿ����ϴ�.");
				jf.dispose();
			} catch (SQLException se) {
				if (se.getErrorCode() == 1) {
					JOptionPane.showMessageDialog(jf, "���̵� �ߺ��Ǿ����ϴ�.");
				}

				if (se.getErrorCode() == 12899) {
					JOptionPane.showMessageDialog(jf, "���ڿ��� �ʹ� Ů�ϴ�.");
				}
				JOptionPane.showMessageDialog(jf, "�ý��ۿ��� �߻� ");
				se.printStackTrace();
			}

		} else {
			// ���̵� �ߺ� üũ ��ư�� Ŭ������ �ʾ�����
			JOptionPane.showMessageDialog(jf, "���̵� �ߺ� üũ�� ���� ���ּ���.");
			return;
		} // end if

	}// join

	/**
	 * ���� ��Ҹ޼ҵ�
	 */
	public void Cancel() {
		int cancelFlag = JOptionPane.showConfirmDialog(jf, "������ ����Ͻðڽ��ϱ�?");
		switch (cancelFlag) {
		case JOptionPane.OK_OPTION:
			jf.dispose();
		}// end switch
	}// checkCancel

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jf.getBtnJoin()) {
			join();

		} // end if

		if (ae.getSource() == jf.getBtnCancel()) {
			Cancel();
		} // end if

		if (ae.getSource() == jf.getBtnDuplicate()) {
			try {
				chkDupleId();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end if

	}// actionPerformed
}// class
