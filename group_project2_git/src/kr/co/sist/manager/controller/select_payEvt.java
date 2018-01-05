package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.select_pay;
import kr.co.sist.manager.vo.ResMgrVO;

public class select_payEvt extends WindowAdapter implements ActionListener, ItemListener {

	private select_pay sp;

	@SuppressWarnings("unused")
	private ManagerDAO m_dao;

	private int select_otp = 0;

	@SuppressWarnings("unused")
	private ResMgrView rmv;

	public select_payEvt(select_pay sp) {
		this.sp = sp;
	}

	public select_payEvt(ResMgrView rmv) {
		this.rmv = rmv;
	}

	// 카드, 현금 결제하는 method
	public void update_otp(int select) {
		m_dao = ManagerDAO.getInstance();

		int cnt = sp.getNumber();

		String opt = "";

		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;

		try {
			if (select == 1) {
				select_otp = 1;
				opt = "카드";
			} else if (select == 2) {
				select_otp = 2;
				opt = "현금";
			} // end else
			list = m_dao.selectAll();
			rmvv = list.get(cnt);
			String msg = m_dao.opt_pay(opt, rmvv.getRes_id());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // end catch
	}// end update

	// 입실여부를 결정하는 method
	public void checkin(int select) {
		int cnt = sp.getNumber();

		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;

		try {
			list = m_dao.selectAll();
			rmvv = list.get(cnt);

			// 방이 입실을 y로 바꾸는 쿼리
			m_dao.in_Rooming(rmvv.getRes_id());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // end catch
	}// end checkin

	@Override
	public void itemStateChanged(ItemEvent ie) {

		if (sp.getCard().isSelected()) {
			select_otp = 1;
		}

		if (sp.getMoney().isSelected()) {
			select_otp = 2;
		}

	}// itemStateChanged

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sp.getBtn()) {

			if (select_otp == 0) {
				JOptionPane.showMessageDialog(null, "결제방식을 선택해주세요");
			} else {
				select_pay sp = new select_pay();
				m_dao = ManagerDAO.getInstance();
				List<ResMgrVO> list;
				ResMgrVO rmvv = null;

				try {
					list = m_dao.selectAll();
					rmvv = list.get(sp.getNumber());

					// 카드,
					update_otp(select_otp);
					checkin(select_otp);
					sp.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 여기서 입실시간은 checkin 메소드안에서 처리한다.

			} // end if
			sp.dispose();
		} // end if

	}// actionPerformed

	public void windowClosing(WindowEvent e) {
		sp.dispose();
	}// windowClosing

}
