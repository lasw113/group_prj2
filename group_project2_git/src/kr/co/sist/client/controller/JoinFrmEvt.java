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

		// 비밀번호 힌트 받아오기
		c_dao = ClientDAO.getInstance();

		DefaultComboBoxModel<String> dcbm = jf.getDcbPassHint();
		try {
			List<String> listQu = c_dao.passHint();
			for (String qu : listQu) {
				if (qu.equals("null")) {
					dcbm.addElement("----------------선택-------------------");
				} else {
					dcbm.addElement(qu);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// JoinFrmEvt

	private boolean chkDupleId() throws SQLException { // 중복 id가 있는지 확인할 매서드
		c_dao = ClientDAO.getInstance();
		String id = jf.getJtfId().getText().trim();
		// 아이디 중복 체크
		boolean chkId = c_dao.dupleId(id);

		if (chkId) {
			JOptionPane.showMessageDialog(jf, id + "는(은) 이미 사용 중인 아이디 입니다.");

		} else if (!id.equals("")) {
			JOptionPane.showMessageDialog(jf, id + "는(은) 사용 가능한 아이디 입니다.");
			flag = true; // 밑에 입력안했을때의 if문을 타기위해 사용
		} else {// 아이디 입력 하지 않았을때

			JOptionPane.showMessageDialog(jf, "아이디가 입력되지 않았습니다.");

		} // end if

		return chkId;
	}// chkDupleId

	private void join() {// 회원가입 메서드

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

				// 아이디가 영문자,숫자가 아닐때
				for (int i = 0; i < id.length(); i++) {
					char c2 = id.charAt(i);
					if (!((c2 >= 0x61 && c2 <= 0x7A) || (c2 >= 0x41 && c2 <= 0x5A) || (c2 >= 0x30 && c2 <= 0x39))) {
						JOptionPane.showMessageDialog(jf, "아이디는 영문자,숫자로 이루어져야 합니다.");
						return;
					} // end if
				} // end for

				// 비밀번호, 비밀번호 확인 부분이 입력하지 않았을때
				if (pw.equals("null")) {
					JOptionPane.showMessageDialog(jf, "사용할 수 없는 비밀번호입니다.");
					return;
				} // end if
				if (pw.equals("") || pwChk.equals("")) {
					JOptionPane.showMessageDialog(jf, "비밀번호 또는 비밀번호 확인을 반드시 입력해주세요.");
					return;
				} // end if
					// 비밀번호가 일치하지 않을때
				if (!pw.equals(pwChk)) {
					JOptionPane.showMessageDialog(jf, "비밀번호가 일치하지 않습니다.");
					return;
				} // end if
					// 비밀번호가 13자리 초과 4자리 미만일때
				if (pw.length() < 4) {
					JOptionPane.showMessageDialog(jf, "비밀번호는 4자리 이상이여야 합니다.");
					return;
				} // end if
				if (pass_index.equals("----------------선택-------------------")) {// 비밀번호 힌트를 선택으로 했을때
					JOptionPane.showMessageDialog(jf, "비밀번호 힌트를 선택해주세요");
					return;
				}
				if (pass_ans.equals("")) {
					// 비밀번호 힌트답 입력하지 않았을때
					JOptionPane.showMessageDialog(jf, "비밀번호 힌트답을 반드시 입력해주세요.");
					return;
				} // end if
					// 이름을 입력하지 않았을때
				if (name.equals("")) {
					JOptionPane.showMessageDialog(jf, "이름을 반드시 입력해주세요.");
					return;
				} // end if
				if (birth.equals("")) {
					// 생년월일 입력하지 않았을때
					JOptionPane.showMessageDialog(jf, "생년월일을 반드시 입력해주세요.");
					return;
				} // end if

				for (int i = 0; i < birth.length(); i++) {
					char c1 = birth.charAt(i);
					if ((c1 < 48 || c1 > 57) || (birth.length() != 6)) {// // 생년월일 6자리 + 숫자로 입력하지 않았을때
						JOptionPane.showMessageDialog(jf, "올바르지 않은 생년월일 입니다.");
						return;

					} // end if
				} // end for
					// 메일을 입력하지 않았을때
				if (email.equals("")) {
					JOptionPane.showMessageDialog(jf, "이메일을 반드시 입력해주세요.");
					return;
				} // end if

				// 메일 유효성검사
				if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(jf, "올바르지 않은 이메일입니다.");
					return;
				} // end if

				if (phone_m.equals("") || phone_l.equals("")) {
					// 폰번호를 입력하지 않았을때
					JOptionPane.showMessageDialog(jf, "핸드폰 번호를 반드시 입력해주세요.");
					return;
				} // end if

				for (int i = 0; i < phone_m.length(); i++) {
					char c1 = phone_m.charAt(i);
					if ((c1 < 48 || c1 > 57) || (phone_m.length() != 4)) {// 폰번호 가운데가 숫자가 아니고 4자리가 아닌경우
						JOptionPane.showMessageDialog(jf, "핸드폰 번호는 4자리 숫자만 입력해주세요");
						return;

					} // end if
				} // end for

				for (int i = 0; i < phone_l.length(); i++) {
					char c2 = phone_l.charAt(i);
					if ((c2 < 48 || c2 > 57) || (phone_l.length() != 4)) {// 폰번호 마지막이 숫자가 아니고 4자리가 아닌경우
						JOptionPane.showMessageDialog(jf, "핸드폰 번호는 4자리 숫자만 입력해주세요");
						return;
					} // end if
				} // end for

				// 비밀번호 질문내용을 인덱스로 바꾸기
				String temp = String.valueOf(jf.getDcbPassHint().getSelectedItem());
				List<String> listQu = c_dao.passHint();
				int tempInt = 0;
				for (int i = 0; i < listQu.size(); i++) {
					if ((listQu.get(i)).equals(temp)) {
						tempInt = i;
						break;
					} //
				} //

				// JoinVO를 통해 회원정보를 DB에 넣기
				jv.setId(id);
				jv.setPass(pw);
				jv.setName(name);
				jv.setPassIndex(String.valueOf(tempInt));
				jv.setPassAns(pass_ans);
				jv.setBirth(birth);
				jv.setEmail(email);
				jv.setPhone(phone);

				c_dao.joinClient(jv);
				// 가입 성공시 메세지 > 로그인 화면으로 이동
				JOptionPane.showMessageDialog(jf, "가입이 완료되었습니다.");
				jf.dispose();
			} catch (SQLException se) {
				if (se.getErrorCode() == 1) {
					JOptionPane.showMessageDialog(jf, "아이디가 중복되었습니다.");
				}

				if (se.getErrorCode() == 12899) {
					JOptionPane.showMessageDialog(jf, "문자열이 너무 큽니다.");
				}
				JOptionPane.showMessageDialog(jf, "시스템오류 발생 ");
				se.printStackTrace();
			}

		} else {
			// 아이디 중복 체크 버튼을 클릭하지 않았을때
			JOptionPane.showMessageDialog(jf, "아이디 중복 체크를 먼저 해주세요.");
			return;
		} // end if

	}// join

	/**
	 * 가입 취소메소드
	 */
	public void Cancel() {
		int cancelFlag = JOptionPane.showConfirmDialog(jf, "가입을 취소하시겠습니까?");
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
