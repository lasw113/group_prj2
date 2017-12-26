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

	public void setMyInfo() throws SQLException {// 처음 내 정보를 뿌려줄 매서드
		c_dao = ClientDAO.getInstance();

		// 아이디,이름,생년월일,폰번호,이메일
		String infoId = c_dao.selectMyInfo(LoginFrmEvt.id).getId();
		String infoName = c_dao.selectMyInfo(LoginFrmEvt.id).getName();
		String infoBirth = c_dao.selectMyInfo(LoginFrmEvt.id).getBirth();
		int infoMileage = c_dao.selectMyInfo(LoginFrmEvt.id).getMileage();
		String fidPhoneF = c_dao.selectMyInfo(LoginFrmEvt.id).getPhone().substring(0, 3);
		String fidPhoneM = c_dao.selectMyInfo(LoginFrmEvt.id).getPhone().substring(4, 8);
		String fidPhoneL = c_dao.selectMyInfo(LoginFrmEvt.id).getPhone().substring(9);
		String infoEmail = c_dao.selectMyInfo(LoginFrmEvt.id).getEmail();
		String infoPassIndex = c_dao.selectMyInfo(LoginFrmEvt.id).getPass_index(); // member 테이블의 pass_hint
		String infoPassAns = c_dao.selectMyInfo(LoginFrmEvt.id).getPass_ans();

		DefaultComboBoxModel<String> dcbm = mif.getDcbPassHint();
		List<String> listQu = c_dao.passHint();// pass_q테이블의 p_question
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
		mif.getDcbPhoneF().setSelectedItem(fidPhoneF);
		mif.getJtfPhoneM().setText(fidPhoneM);
		mif.getJtfPhoneL().setText(fidPhoneL);
		mif.getJtfEmail().setText(infoEmail);
		mif.getDcbPassHint().setSelectedItem(listQu.get(Integer.parseInt(infoPassIndex)));
		mif.getJtfAnsPass().setText(infoPassAns);
	}// setMyInfo

	public void modiMyInfo() throws SQLException {// 내 정보 수정 매서드
		c_dao = ClientDAO.getInstance();
		icv = new InfoChangeVO();
		if (!(new String(mif.getJtfChkPass().getPassword()).equals(""))
				|| !(new String(mif.getJtfPass().getPassword()).equals(""))) {
			changeMypass();
			return;
		}

		int flag = JOptionPane.showConfirmDialog(mif, "내 정보를 수정하시겠습니까?");
		switch (flag) {
		case JOptionPane.OK_OPTION:
			try {
				// 이름,생년월일,이메일,휴대폰번호
				String phone = mif.getJcbPhoneF().getSelectedItem() + "-" + mif.getJtfPhoneM().getText() + "-"
						+ mif.getJtfPhoneL().getText();
				String chPassAns = mif.getJtfAnsPass().getText().trim();
				String name = mif.getJtfName().getText().trim();
				String email = mif.getJtfEmail().getText().trim();
				String birth = mif.getJtfBirth().getText().trim();
				String phone_m = mif.getJtfPhoneM().getText();
				String phone_l = mif.getJtfPhoneL().getText();

				if (chPassAns.equals("")) {
					// 비밀번호 힌트답 입력하지 않았을때
					JOptionPane.showMessageDialog(mif, "비밀번호 힌트답을 입력해주세요.");
					return;
				} // end if
					// 이름을 입력하지 않았을때
				if (name.equals("")) {
					JOptionPane.showMessageDialog(mif, "이름을 입력해주세요.");
					return;
				} // end if
				if (birth.equals("")) {
					// 생년월일 입력하지 않았을때
					JOptionPane.showMessageDialog(mif, "생년월일을 입력해주세요.");
					return;
				} // end if

				for (int i = 0; i < birth.length(); i++) {
					char c1 = birth.charAt(i);
					if ((c1 < 48 || c1 > 57) || (birth.length() != 6)) {// // 생년월일 6자리 + 숫자로 입력하지 않았을때
						JOptionPane.showMessageDialog(mif, "올바르지 않은 생년월일 입니다.");
						return;

					} // end if
				} // end for

				// 메일을 입력하지 않았을때
				if (email.equals("")) {
					JOptionPane.showMessageDialog(mif, "이메일을 입력해주세요.");
					return;
				} // end if

				// 메일 유효성검사
				if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(mif, "올바르지 않은 이메일입니다.");
					return;
				} // end if

				// 폰번호를 입력하지 않았을때
				if (phone_m.equals("") || phone_l.equals("")) {
					JOptionPane.showMessageDialog(mif, "핸드폰 번호를 입력해주세요.");
					return;
				} // end if

				for (int i = 0; i < phone_m.length(); i++) {// 폰번호 가운데가 숫자가 아니고 4자리가 아닌경우
					char c1 = phone_m.charAt(i);
					if ((c1 < 48 || c1 > 57) || (phone_m.length() != 4)) {
						JOptionPane.showMessageDialog(mif, "핸드폰 번호는 4자리 숫자만 입력해주세요");
						return;

					} // end if
				} // end for

				for (int i = 0; i < phone_l.length(); i++) {// 폰번호 마지막이 숫자가 아니고 4자리가 아닌경우
					char c2 = phone_l.charAt(i);
					if ((c2 < 48 || c2 > 57) || (phone_l.length() != 4)) {
						JOptionPane.showMessageDialog(mif, "핸드폰 번호는 4자리 숫자만 입력해주세요");
						return;
					} // end if
				} // end for

				icv.setId(LoginFrmEvt.id);
				icv.setName(mif.getJtfName().getText());
				icv.setBirth(mif.getJtfBirth().getText());
				icv.setPhone(phone);
				icv.setEmail(mif.getJtfEmail().getText());
				icv.setPass_ans(chPassAns);

				// 비밀번호 질문내용을 인덱스로 받아오기
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

					JOptionPane.showMessageDialog(mif, "회원정보가 수정되었습니다.");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(mif, "시스템오류 발생 ");
				e.printStackTrace();
			} // end catch
		}// switch

	}// modiMyInfo

	private void changeMypass() throws SQLException {// 비밀번호 수정 매서드
		c_dao = ClientDAO.getInstance();
		cpv = new ChangePassVO();

		int flag2 = JOptionPane.showConfirmDialog(mif, "비밀번호를 수정하시겠습니까?");
		switch (flag2) {
		case JOptionPane.OK_OPTION:
			String pw = new String(mif.getJtfPass().getPassword());
			String pwChk = new String(mif.getJtfChkPass().getPassword());
			cpv.setPass(String.valueOf(mif.getJtfPass().getPassword()));
			cpv.setId(LoginFrmEvt.id);

			if (pw.equals("null")) {
				JOptionPane.showMessageDialog(mif, "사용할 수 없는 비밀번호입니다.");
				return;
			} // end if
			if (!pw.equals(pwChk)) {
				JOptionPane.showMessageDialog(mif, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
				return;
			}
			if (pw.length() < 4) {
				JOptionPane.showMessageDialog(mif, "비밀번호는 4자리 이상이어야 합니다.");
				return;
			}

			if (c_dao.changePass(cpv)) {
				JOptionPane.showMessageDialog(mif, "비밀번호가 수정되었습니다.");
			} else {
				JOptionPane.showMessageDialog(mif, "시스템오류 발생 ");
			}
		}
	}// changeMypass

	private void dropOut() {// 회원 탈퇴 매서드

		c_dao = ClientDAO.getInstance();
		dv = new DeleteVO();
		int flag = JOptionPane.showConfirmDialog(mif, "탈퇴 하시겠습니까?");
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
				JOptionPane.showMessageDialog(mif, "탈퇴가 완료되었습니다.");
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
				JOptionPane.showMessageDialog(mif, "시스템오류 발생 ");
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
