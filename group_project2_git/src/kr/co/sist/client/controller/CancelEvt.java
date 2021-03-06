package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.CancelFrm;
import kr.co.sist.client.frm.ResChkFrm;
import kr.co.sist.client.vo.CancelResVO;

public class CancelEvt implements ActionListener {

	private CancelFrm cf;

	private String con_pass;

	public CancelEvt(CancelFrm cf, ResChkFrm rcf) throws IOException {
		this.cf = cf;

	}// CanecelEvt

	//입력한 비밀번호와 회원의 비밀번호 비교
	public boolean chkPass(String pass) {
		boolean flag = false;

		con_pass = new String(cf.getJpwPass().getPassword()).trim();

		if (pass.equals(con_pass)) {
			flag = true;
		} // end if

		return flag;

	}// chkPass

	//예약취소
	public void cacelRes(String id, String res_id) {

		RoomCDAO r_cdao = RoomCDAO.getInstance();
		CancelResVO cr_vo = null;
		try {
			cr_vo = new CancelResVO(res_id, id);
			r_cdao.cancelRes(cr_vo);
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
					cacelRes(cf.getId(), cf.getRes_id());
					cf.getRcfe().resChk();
				case JOptionPane.NO_OPTION:
					cf.dispose();
				case JOptionPane.CANCEL_OPTION:
					cf.dispose();
				}// end switch
			} else {
				if (con_pass.equals("")) {
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
