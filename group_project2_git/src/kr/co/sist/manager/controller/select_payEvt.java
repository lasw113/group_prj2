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

	// ī��, ���� �����ϴ� method
	public void update_otp(int select) {
		m_dao= ManagerDAO.getInstance();

		int cnt = sp.getNumber();

		String opt = "";

		ResMgrVO rmvv = null;
		List<ResMgrVO> list;
		// System.out.println("�ε��� �׽�Ʈ" + cnt);

		try {
			if (select == 1) {
				select_otp = 1;
				opt = "ī��";
			} else if (select == 2) {
				select_otp = 2;
				opt = "����";
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

	
	//�Խǿ��θ� �����ϴ� method
	public void checkin(int select) {
		m_dao.getInstance();

		int cnt = sp.getNumber();

		String opt = "";


		ResMgrVO rmvv = null;
		List<ResMgrVO> list;
		// System.out.println("�ε��� �׽�Ʈ" + cnt);

		try {
			//System.out.println(opt);
			list = m_dao.selectAll();
			rmvv = list.get(cnt);
			System.out.println(rmvv.getRes_id());
			
			//���� �Խ��� y�� �ٲٴ� ����
			m_dao.in_Rooming(rmvv.getRes_id());
			
			//�Խ� �ð��� ó���ϴ� method
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
			System.out.println("ī�� / " + select_otp);
		}

		if (sp.getMoney().isSelected()) {
			select_otp = 2;
			System.out.println("���� / " + select_otp);
		}

	}// itemStateChanged

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sp.getBtn()) {
			if (select_otp == 0) {
				JOptionPane.showMessageDialog(null, "��������� �������ּ���");
			} else {
				System.out.println( "�޼ҵ带 �θ��� ���� ��"  + select_otp);
				//ī��, 
				update_otp(select_otp);
				checkin(select_otp);
				//���⼭ �Խǽð��� checkin �޼ҵ�ȿ��� ó���Ѵ�.			
				sp.dispose();
			} // end if

		} // end if

	}// actionPerformed

	public void windowClosing(WindowEvent e) {
		sp.dispose();
	}// windowClosing

}
