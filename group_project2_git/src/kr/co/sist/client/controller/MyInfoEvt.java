package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.MyInfoFrm;
import kr.co.sist.client.vo.ChangePassVO;
import kr.co.sist.client.vo.DeleteVO;
import kr.co.sist.client.vo.InfoChangeVO;

public class MyInfoEvt implements ActionListener {

	private MyInfoFrm mif;
	private ClientDAO c_dao;
	private InfoChangeVO icv;
	private DeleteVO dv;
	private ChangePassVO cpv;

	public MyInfoEvt(MyInfoFrm mif) {
		this.mif = mif;

	}// MyInfoEvt

	public void setMyInfo() throws SQLException {// ó�� �� ������ �ѷ��� �ż���
		c_dao = ClientDAO.getInstance();

		// ���̵�,�̸�,�������,����ȣ,�̸���
		String infoId = c_dao.selectMyInfo(LoginFrmEvt.id).getId();
		String infoName = c_dao.selectMyInfo(LoginFrmEvt.id).getName();
		String infoBirth = c_dao.selectMyInfo(LoginFrmEvt.id).getBirth();
		int infoMileage = c_dao.selectMyInfo(LoginFrmEvt.id).getMileage();
		String fidPhoneF = c_dao.selectMyInfo(LoginFrmEvt.id).getPhone().substring(0, 3);
		String fidPhoneM = c_dao.selectMyInfo(LoginFrmEvt.id).getPhone().substring(4, 8);
		String fidPhoneL = c_dao.selectMyInfo(LoginFrmEvt.id).getPhone().substring(9);
		String infoEmail = c_dao.selectMyInfo(LoginFrmEvt.id).getEmail();
		String infoPassIndex = c_dao.selectMyInfo(LoginFrmEvt.id).getPass_index(); // member ���̺��� pass_hint
		String infoPassAns = c_dao.selectMyInfo(LoginFrmEvt.id).getPass_ans();

		DefaultComboBoxModel<String> dcbm = mif.getDcbPassHint();
		List<String> listQu = c_dao.passHint();// pass_q���̺��� p_question
		for (String qu : listQu) {
			if (!qu.equals("null")) {
				dcbm.addElement(qu);
			}
		} // end for

		mif.getJtfId().setText(infoId);
		mif.getJtfName().setText(infoName);
		mif.getJtfBirth().setText(infoBirth);
		mif.getJtfMileage().setText(String.valueOf(infoMileage));
		mif.getJtfMileage().setEditable(false);

		String[] arr = { "010", "011", "016", "017" };

		// dcbm�� �ִ� �ڵ�����ȣ ����� ȸ���� �ڵ��� ��ȣ�� ������ �����߰�
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(fidPhoneF)) {
				mif.getDcbPhoneF().addElement(fidPhoneF);
			}
		} // end for

		// dcbm�� �ִ� �ڵ�������� ȸ���� ����ȣ�� �ٸ���
		for (int i = 0; i < arr.length; i++) {
			if (!(arr[i].equals(fidPhoneF))) {
				mif.getDcbPhoneF().addElement(arr[i]);

			}

		} // end for

		mif.getJtfPhoneM().setText(fidPhoneM);
		mif.getJtfPhoneL().setText(fidPhoneL);
		mif.getJtfEmail().setText(infoEmail);
		mif.getDcbPassHint().setSelectedItem(listQu.get(Integer.parseInt(infoPassIndex)));
		mif.getJtfAnsPass().setText(infoPassAns);
	}// setMyInfo

	public void modiMyInfo() throws SQLException {// �� ���� ���� �ż���
		c_dao = ClientDAO.getInstance();
		icv = new InfoChangeVO();

		// ��й�ȣ�Է� �Ǵ� ��й�ȣ Ȯ�� â�� ���� �Է����� �� ��й�ȣ �����޼ҵ� ȣ��
		if (!("".equals(new String(mif.getJtfChkPass().getPassword())))
				|| !("".equals(new String(mif.getJtfPass().getPassword())))) {
			changeMypass();
			return;
		}

		int flag = JOptionPane.showConfirmDialog(mif, "�� ������ �����Ͻðڽ��ϱ�?");
		switch (flag) {
		case JOptionPane.OK_OPTION:
			try {
				// �̸�,�������,�̸���,�޴�����ȣ
				String phone = mif.getJcbPhoneF().getSelectedItem() + "-" + mif.getJtfPhoneM().getText() + "-"
						+ mif.getJtfPhoneL().getText();
				String chPassAns = mif.getJtfAnsPass().getText().trim();
				String name = mif.getJtfName().getText().trim();
				String email = mif.getJtfEmail().getText().trim();
				String birth = mif.getJtfBirth().getText().trim();
				String phone_m = mif.getJtfPhoneM().getText();
				String phone_l = mif.getJtfPhoneL().getText();

				if ("".equals(chPassAns)) {
					// ��й�ȣ ��Ʈ�� �Է����� �ʾ�����
					JOptionPane.showMessageDialog(mif, "��й�ȣ ��Ʈ���� �Է����ּ���.");
					mif.getJtfAnsPass().requestFocus();
					return;
				} // end if
					// �̸��� �Է����� �ʾ�����
				if ("".equals(name)) {
					JOptionPane.showMessageDialog(mif, "�̸��� �Է����ּ���.");
					mif.getJtfName().requestFocus();
					return;
				} // end if
				if ("".equals(birth)) {
					// ������� �Է����� �ʾ�����
					JOptionPane.showMessageDialog(mif, "��������� �Է����ּ���.");
					mif.getJtfBirth().requestFocus();
					return;
				} // end if

				for (int i = 0; i < birth.length(); i++) {
					char c1 = birth.charAt(i);
					if ((c1 < 48 || c1 > 57) || (birth.length() != 6)) {// // ������� 6�ڸ� + ���ڷ� �Է����� �ʾ�����
						JOptionPane.showMessageDialog(mif, "�ùٸ��� ���� ������� �Դϴ�.");
						mif.getJtfBirth().requestFocus();
						return;

					} // end if
				} // end for

				// ������ �Է����� �ʾ�����
				if ("".equals(email)) {
					JOptionPane.showMessageDialog(mif, "�̸����� �Է����ּ���.");
					mif.getJtfEmail().requestFocus();
					return;
				} // end if

				// ���� ��ȿ���˻�
				if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(mif, "�ùٸ��� ���� �̸����Դϴ�.");
					mif.getJtfEmail().requestFocus();
					return;
				} // end if

				// ����ȣ�� �Է����� �ʾ�����
				if ("".equals(phone_m) || "".equals(phone_l)) {
					JOptionPane.showMessageDialog(mif, "�ڵ��� ��ȣ�� �Է����ּ���.");
					mif.getJtfEmail().requestFocus();
					return;
				} // end if

				for (int i = 0; i < phone_m.length(); i++) {// ����ȣ ����� ���ڰ� �ƴϰ� 4�ڸ��� �ƴѰ��
					char c1 = phone_m.charAt(i);
					if ((c1 < 48 || c1 > 57) || (phone_m.length() != 4)) {
						JOptionPane.showMessageDialog(mif, "�ڵ��� ��ȣ�� 4�ڸ� ���ڸ� �Է����ּ���");
						mif.getJtfPhoneM().requestFocus();
						return;

					} // end if
				} // end for

				for (int i = 0; i < phone_l.length(); i++) {// ����ȣ �������� ���ڰ� �ƴϰ� 4�ڸ��� �ƴѰ��
					char c2 = phone_l.charAt(i);
					if ((c2 < 48 || c2 > 57) || (phone_l.length() != 4)) {
						JOptionPane.showMessageDialog(mif, "�ڵ��� ��ȣ�� 4�ڸ� ���ڸ� �Է����ּ���");
						mif.getJtfPhoneL().requestFocus();
						return;
					} // end if
				} // end for

				icv.setId(LoginFrmEvt.id);
				icv.setName(name);
				icv.setBirth(birth);
				icv.setPhone(phone);
				icv.setEmail(email);
				icv.setPass_ans(chPassAns);

				// ��й�ȣ ���������� �ε����� �޾ƿ���
				String temp = String.valueOf(mif.getDcbPassHint().getSelectedItem());
				List<String> listQu = c_dao.passHint();
				int tempInt = 0;
				for (int i = 0; i < listQu.size(); i++) {
					if ((listQu.get(i)).equals(temp)) {
						tempInt = i;
						break;
					} //
				} //
				icv.setPass_index(String.valueOf(tempInt));

				if (c_dao.updateMyInfo(icv)) {

					JOptionPane.showMessageDialog(mif, "ȸ�������� �����Ǿ����ϴ�.");
				}
			} catch (SQLException se) {
				if (se.getErrorCode() == 12899) {
					JOptionPane.showMessageDialog(mif, "���ڿ��� �ʹ� Ů�ϴ�.");
				}
				JOptionPane.showMessageDialog(mif, "�ý��ۿ��� �߻� ");
				se.printStackTrace();
			} // end catch
		}// switch

	}// modiMyInfo

	private void changeMypass() throws SQLException {// ��й�ȣ ���� �ż���
		c_dao = ClientDAO.getInstance();
		cpv = new ChangePassVO();

		int flag2 = JOptionPane.showConfirmDialog(mif, "��й�ȣ�� �����Ͻðڽ��ϱ�?");
		switch (flag2) {
		case JOptionPane.OK_OPTION:
			String pw = new String(mif.getJtfPass().getPassword());
			String pwChk = new String(mif.getJtfChkPass().getPassword());
			cpv.setPass(String.valueOf(mif.getJtfPass().getPassword()));
			cpv.setId(LoginFrmEvt.id);

			if (pw.equals("null")) {
				JOptionPane.showMessageDialog(mif, "����� �� ���� ��й�ȣ�Դϴ�.");
				mif.getJtfPass().requestFocus();
				return;
			} // end if
			if (!pw.equals(pwChk)) {
				JOptionPane.showMessageDialog(mif, "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
				mif.getJtfPass().requestFocus();
				return;
			}
			if (pw.length() < 4) {
				JOptionPane.showMessageDialog(mif, "��й�ȣ�� 4�ڸ� �̻��̾�� �մϴ�.");
				mif.getJtfPass().requestFocus();
				return;
			}

			if (c_dao.changePass(cpv)) {
				JOptionPane.showMessageDialog(mif, "��й�ȣ�� �����Ǿ����ϴ�.");
				mif.getJtfPass().setText("");
				mif.getJtfChkPass().setText("");
			} else {
				JOptionPane.showMessageDialog(mif, "�ý��ۿ��� �߻� ");
			}
		}
	}// changeMypass

	private void dropOut() {// ȸ�� Ż�� �ż���

		c_dao = ClientDAO.getInstance();
		dv = new DeleteVO();
		int flag = JOptionPane.showConfirmDialog(mif, "Ż�� �Ͻðڽ��ϱ�?");
		switch (flag) {
		case JOptionPane.OK_OPTION:
			try {

				dv.setId(LoginFrmEvt.id);
				dv.setName("null");
				dv.setBirth("000000");
				dv.setPhone("000-0000-0000");
				dv.setEmail("null");
				dv.setMileage(0);
				dv.setPass("null");
				dv.setPass_ans("null");
				dv.setPass_index("0");

				c_dao.dropMyInfo(dv);
				JOptionPane.showMessageDialog(mif, "Ż�� �Ϸ�Ǿ����ϴ�.");
				mif.getJtfId().setText("");
				mif.getJtfName().setText("");
				mif.getJtfBirth().setText("");
				mif.getJtfMileage().setText(String.valueOf(0));
				mif.getJtfMileage().setEditable(false);
				mif.getDcbPhoneF().setSelectedItem("");
				mif.getJtfPhoneM().setText("");
				mif.getJtfPhoneL().setText("");
				mif.getJtfEmail().setText("");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(mif, "�ý��ۿ��� �߻� ");
				e.printStackTrace();
			} // end catch
		}// switch

	}// dropOut

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mif.getBtnModify()) {
			try {
				modiMyInfo();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end if
		if (ae.getSource() == mif.getBtnDropOut()) {
			dropOut();

		} // end if
	}// actionPerformed

}// class
