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

		// 비밀번호 힌트 뿌려주기
		DefaultComboBoxModel<String> dcbm = fpf.getDcbm();
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
	}// FindPassFrmEvt

	private void chkEmpty() {// 빈 곳이 있는지 확인할 메서드

		String passId = fpf.getJtfId().getText().trim();
		String passBirth = fpf.getJtBirth().getText().trim();
		String passHint = (String) fpf.getJcbPassHint().getSelectedItem();
		String passAns = fpf.getJtfPassAns().getText().trim();

		flag = false;

		if (passId.equals("")) {
			JOptionPane.showMessageDialog(fpf, "아이디를 입력해주세요");
			fpf.getJtfId().requestFocus();
			return;
		}

		if (passBirth.equals("")) {
			JOptionPane.showMessageDialog(fpf, "생년월일을 입력해주세요");
			fpf.getJtBirth().requestFocus();
			return;
		}
		if (passHint.equals("----------------선택-------------------")) {// 비밀번호 힌트를 선택으로 했을때
			JOptionPane.showMessageDialog(fpf, "비밀번호 힌트를 선택해주세요");
			fpf.getJcbPassHint().requestFocus();
			return;
		}

		if (passAns.equals("")) {
			JOptionPane.showMessageDialog(fpf, "비밀번호 답을 입력해주세요");
			fpf.getJtfPassAns().requestFocus();
			return;
		}
		if (passAns.equals("null")) {
			JOptionPane.showMessageDialog(fpf, "이미 탈퇴된 회원입니다.");
			return;
		}

		flag = true;
	}// chkEmpty

	private void findPass() throws SQLException {// 비밀번호 찾기 메서드

		fpv = new FindPassVO();
		c_dao = ClientDAO.getInstance();

		String passId = fpf.getJtfId().getText();
		String passBirth = fpf.getJtBirth().getText();
		String passHint = (String) fpf.getJcbPassHint().getSelectedItem();
		String passAns = fpf.getJtfPassAns().getText().trim();

		// 비밀번호 질문내용을 인덱스로 바꾸기
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

			// 비밀번호 다보여주지 않고 1/2만 *(echo char)생성
			int passLen = Pass.length();

			Pass = Pass.substring(0, passLen / 2);
			System.out.println(Pass.length());
			for (int i = Pass.length(); i < passLen; i++) {
				Pass += "*";
			}

			if (Pass.trim().equals("")) { // 일치하는 회원정보가 없을때
				JOptionPane.showMessageDialog(fpf, "일치하는 회원정보가 없습니다.");
			} else {
				JOptionPane.showMessageDialog(fpf, "회원님의 비밀번호는 " + Pass + " 입니다.");
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
