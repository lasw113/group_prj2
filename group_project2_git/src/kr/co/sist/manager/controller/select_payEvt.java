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
import kr.co.sist.manager.view.select_pay;
import kr.co.sist.manager.vo.ResMgrVO;

public class select_payEvt extends WindowAdapter implements ActionListener, ItemListener {

	private select_pay sp;

	private ManagerDAO m_dao;

	private int select_otp = 0;

	public select_payEvt(select_pay sp) {
		this.sp = sp;
	}

	// 카드, 현금 결제하는 method
	public void update_otp(int select) {
		m_dao= ManagerDAO.getInstance();

		int cnt = sp.getNumber();

		String opt = "";

		ResMgrVO rmvv = null;
		List<ResMgrVO> list;
		// System.out.println("인덱스 테스트" + cnt);

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
			System.out.println(rmvv.getRes_id());
			String msg = m_dao.opt_pay(opt, rmvv.getRes_id());
			System.out.println(msg);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // end catch
	}// end update

	
	//입실여부를 결정하는 method
	public void checkin(int select) {
		m_dao.getInstance();

		int cnt = sp.getNumber();

		String opt = "";


		ResMgrVO rmvv = null;
		List<ResMgrVO> list;
		// System.out.println("인덱스 테스트" + cnt);

		try {
			//System.out.println(opt);
			list = m_dao.selectAll();
			rmvv = list.get(cnt);
			System.out.println(rmvv.getRes_id());
			
			//방이 입실을 y로 바꾸는 쿼리
			m_dao.in_Rooming(rmvv.getRes_id());
			
			//입실 시간을 처리하는 method
			//db.Update_Outtime(rmvv.getRes_id());
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // end catch
	}// end checkin

	@Override
	public void itemStateChanged(ItemEvent ie) {

		if (sp.getCard().isSelected()) {
			select_otp = 1;
			System.out.println("카드 / " + select_otp);
		}

		if (sp.getMoney().isSelected()) {
			select_otp = 2;
			System.out.println("현금 / " + select_otp);
		}

	}// itemStateChanged

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sp.getBtn()) {
			if (select_otp == 0) {
				JOptionPane.showMessageDialog(null, "결제방식을 선택해주세요");
			} else {
				System.out.println( "메소드를 부를때 들어가는 값"  + select_otp);
				//카드, 
				update_otp(select_otp);
				checkin(select_otp);
				//여기서 입실시간은 checkin 메소드안에서 처리한다.			
				sp.dispose();
			} // end if

		} // end if

	}// actionPerformed

	public void windowClosing(WindowEvent e) {
		sp.dispose();
	}// windowClosing

}
