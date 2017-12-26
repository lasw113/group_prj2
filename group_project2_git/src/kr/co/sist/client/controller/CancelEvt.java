package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.CancelFrm;
import kr.co.sist.client.frm.ResChkFrm;

public class CancelEvt implements ActionListener {

	private CancelFrm cf;
	private ResChkFrm rcf;


	public CancelEvt(CancelFrm cf, ResChkFrm rcf) throws IOException {
		this.cf = cf;// ?
		this.rcf = rcf;

	}// CanecelEvt

	String admin_pass;

	public boolean chkPass(String pass) {
		boolean flag = false;

		admin_pass = new String(cf.getJpwPass().getPassword()).trim();
		System.out.println(admin_pass);
		System.out.println(pass);

		if (pass.equals(admin_pass)) {
			flag = true;
		} // end if

		return flag;

	}// chkPass

	public void cacelRes(String res_id) {

		RoomCDAO r_cdao = RoomCDAO.getInstance();
		try {
			r_cdao.cancelRes(res_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// cancelRes

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cf.getBtnOk()) {
			boolean flag;
			flag = chkPass(cf.getPass());

			if (flag) {
				switch (JOptionPane.showConfirmDialog(cf, "예약을 취소하시겠습니까?")) {
				case JOptionPane.OK_OPTION:
					cacelRes(cf.getRes_id());
					cf.getRcfe().resChk();
					System.out.println("예약취소");
				case JOptionPane.NO_OPTION:
					cf.dispose();
				case JOptionPane.CANCEL_OPTION:
					cf.dispose();
				}// end switch
			} else {
				if (admin_pass.equals("")) {
					JOptionPane.showMessageDialog(cf, "비밀번호를 입력해주세요");
					return;
				} // end if
				JOptionPane.showMessageDialog(cf, "비밀번호를 틀렸습니다..");
				cf.getJpwPass().setText("");
				cf.getJpwPass().requestFocus();
			} // end else

		} // end if
	}// actionPerformed

}// class
