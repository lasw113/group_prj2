package kr.co.sist.manager.controller;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.add_timeForm;
import kr.co.sist.manager.view.select_pay;
import kr.co.sist.manager.vo.ResMgrVO;

public class ResMgrViewEvt extends WindowAdapter implements ActionListener {

	private ResMgrView rmv;
	private TextArea ta;
	private ManagerDAO m_dao;
	private int count = 0;

	public ResMgrViewEvt(ResMgrView rmv) {
		this.rmv = rmv;
	}// ResInfo

	// ��û���� Ȯ���ϴ� �޼ҵ�
	public void setRequest(int i) {
		ResMgrVO rmvv = null;
		m_dao =ManagerDAO.getInstance();
		int count;
		List<ResMgrVO> list;
		try {
			list = m_dao.selectAll();
			if (i == 0) {
				rmvv = list.get(0);
				ta = new TextArea(rmvv.getRequest());
				JOptionPane.showMessageDialog(null, ta);
				System.out.println("��û���� Ȯ��  " + i + "��ư");

			} else if (i != 0) {
				count = i / 5;
				rmvv = list.get(count);
				// System.out.println(count);
				ta = new TextArea(rmvv.getRequest());
				JOptionPane.showMessageDialog(null, ta);
				System.out.println("��û���� Ȯ��  " + i + "��ư");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// setRequest

	// �Խ�ó���ϴ� �޼ҵ�
	public void roomIn(int cnt) {
		select_pay sp = new select_pay();
		sp.select_pay(cnt);
	}// roomIn

	// ���ó���ϴ� �޼ҵ�
	public void roomOut(int Btn_Cnt) {
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		// System.out.println("�ε��� �׽�Ʈ" + cnt);

		int number = JOptionPane.showConfirmDialog(rmv, "������ ����Ͻðڽ��ϱ�?");
		try {
			if (number == 0) {
				// ù���� �����ϴ°��
				if (Btn_Cnt == 2) {
					list = m_dao.selectAll();
					rmvv = list.get(0);
					rmv.setResView(list);

					// y -> n��
					m_dao.out_Rooming(rmvv.getRes_id());
					// ��ǽð��� ������Ʈ
					m_dao.Update_Outtime(rmvv.getRes_id());

					setRes();
				} else if (Btn_Cnt != 0) {
					list = m_dao.selectAll();
					Btn_Cnt = Btn_Cnt / 5;
					rmvv = list.get(Btn_Cnt);
					// y -> n��
					m_dao.out_Rooming(rmvv.getRes_id());
					// ��� �ð��� �ִ� method
					m_dao.Update_Outtime(rmvv.getRes_id());
					// ���� ���� �߰��ϰ� �Ʒ� reset�޼ҵ� �θ���
					setRes();
				} // end if
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // end catch
	}// roomOut

	// ��������ϴ� �޼ҵ�
	public void roomCancle(int Btn_Cnt) {
		System.out.println("�������  " + Btn_Cnt + "��ư");
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;

		int number = JOptionPane.showConfirmDialog(rmv, "������ ��������Ͻðڽ��ϱ�?");

		System.out.println(number);

		try {
			if (number == 0) {
				// ù�� ������Ҹ� �������
				if (Btn_Cnt == 3) {
					list = m_dao.selectAll();
					rmvv = list.get(0);
					rmv.setResView(list);
					m_dao.delete_res(rmvv.getRes_id());
					setRes();
				} else if (Btn_Cnt != 0) {
					list = m_dao.selectAll();
					Btn_Cnt = Btn_Cnt / 5;
					rmvv = list.get(Btn_Cnt);
					m_dao.delete_res(rmvv.getRes_id());
					setRes();
				} // end else
			} // end if
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// roomCancle

	// �ð� �߰� �޼ҵ�
	public void roomRenewal(int cnt) {
		add_timeForm atf = new add_timeForm(rmv);
		ResMgrView rmv = new ResMgrView();

		System.out.println("�ð� �߰�  " + cnt + "��ư");

		// �ð��߰� ������!
		atf.add_timeForm(cnt);
		try {
			rmv.resetData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// roomRenewal

	// �����ϴ� ��ư
	public void setRes() {

		int list_size = rmv.getLine_panel().size();
		List<JPanel> list1 = rmv.getLine_panel();
		for (int i = 0; i < list_size; i++) {
			list1.get(i).revalidate();
			list1.get(i).repaint();
		}
		// System.out.println("-------" + list_size);

		// System.out.println("tf,btn, line_panel list ��� �� ���� �� ũ��");
		// System.out.println(rmv.getList_tf().size() + "/" + rmv.getList_btn().size() +
		// "/" + rmv.getLine_panel().size());
		rmv.getList_tf().clear();
		rmv.getList_btn().clear();
		rmv.getLine_panel().clear();

		rmv.getTotal_line_panel().removeAll();
		// System.out.println("tf,btn, line_panel list ��� �� ���� �� ũ��");
		// System.out.println(rmv.getList_tf().size() + "/" + rmv.getList_btn().size() +
		// "/" + rmv.getLine_panel().size());

		// ��� �г� ����
		// rmv.getTotalpanel().removeAll();
		// ���ο� â �θ���

		List<ResMgrVO> list;
		try {
			list = rmv.resetData();
			rmv.setResView(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// rmv.invalidate();
		// rmv.validate();
		// rmv.repaint();
		System.out.println("����");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ���Ź�ư
		if (e.getSource() == rmv.getBtnrenew()) {
			// rmv.dispose();
			setRes();
		} // end if

		// ��� ��ư
		for (int i = 0; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				setRequest(i);
			} // end if
		} // end for

		// �Խ� ��ư
		for (int i = 1; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {

				if (i == 1) {
					roomIn(0);
				} else if (i != 1) {
					int count;
					count = i / 5;
					System.out.println(count);
					roomIn(count);
				}

			} // end if
		} // end for

		// ��� ��ư
		for (int i = 2; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				roomOut(i);
			} // end if
		} // end for

		// �������
		for (int i = 3; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				roomCancle(i);
			} // end if
		} // end for

		// �߰�/���� ��ư
		for (int i = 4; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				roomRenewal(i);
			} // end if
		} // end for

	}// end for

	@Override
	public void windowClosing(WindowEvent we) {
		rmv.dispose();
	}// windowClosing

}
