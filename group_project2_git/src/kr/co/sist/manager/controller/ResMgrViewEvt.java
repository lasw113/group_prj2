package kr.co.sist.manager.controller;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.add_timeView;
import kr.co.sist.manager.view.select_pay;
import kr.co.sist.manager.vo.ResMgrVO;

public class ResMgrViewEvt extends WindowAdapter implements ActionListener {

	private ResMgrView rmv;
	ManagerDAO m_dao;

	public ResMgrViewEvt(ResMgrView rmv) {
		this.rmv = rmv;
	}// ResInfo

	// ��û���� Ȯ���ϴ� �޼ҵ�
	public void setRequest(int Btn_Cnt) {
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		System.out.println(Btn_Cnt + "��û���׹�ư!");
		try {
			list = m_dao.selectAll();
			System.out.println(list.size() + " =========listũ��");

			// ��û������ ������� �ʴٸ�
			if (Btn_Cnt == 0) {
				rmvv = list.get(0);
				TextArea ta = new TextArea(rmvv.getRequest());
				JOptionPane.showMessageDialog(null, ta);
				System.out.println("��û���� Ȯ��  " + Btn_Cnt + "��ư");

			} else if (Btn_Cnt != 0) {
				Btn_Cnt = Btn_Cnt / 5;
				rmvv = list.get(Btn_Cnt);
				TextArea ta = new TextArea(rmvv.getRequest());
				System.out.println(rmvv.getRequest());
				JOptionPane.showMessageDialog(null, ta);
				System.out.println("��û���� Ȯ��  " + Btn_Cnt + "��ư");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// setRequest

	// �Խ�ó���ϴ� �޼ҵ�
	public void roomIn(int cnt, int btn_cnt) {
		select_pay sp = new select_pay();
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		ResMgrVO rmvv = null;

		System.out.println("�ش� ��" + cnt + " ------------ ��ưindex " + btn_cnt);
		// ���� ��¥ �޴� ����
		SimpleDateFormat formatter = new SimpleDateFormat("kk", Locale.KOREA);
		Date currentTime = new Date();
		int dTime = Integer.parseInt(formatter.format(currentTime));

		System.out.println(dTime);
		try {

			// cnt�� ������ �Ѵ�.(index�� ���ϱ����ؼ�)
			if (cnt == 0) {
				list = m_dao.selectAll();
				rmvv = list.get(0);
				int Intime = Integer.parseInt(rmvv.getIn_time());
				int Outtime = Integer.parseInt(rmvv.getOut_time());
				if (Intime <= dTime && Outtime > dTime) {
					sp.select_pay(cnt, btn_cnt);

					rmv.getList_btn().get(btn_cnt).setBackground(new Color(255, 128, 0));
				} else {
					JOptionPane.showMessageDialog(rmv, "�Խ� �ð��� ���� �ʾҽ��ϴ�.");
				}
			} else if (cnt != 0) {
				list = m_dao.selectAll();
				rmvv = list.get(cnt);
				int Intime = Integer.parseInt(rmvv.getIn_time());
				int Outtime = Integer.parseInt(rmvv.getOut_time());
				if (Intime <= dTime && Outtime > dTime) {
					sp.select_pay(cnt, btn_cnt);

					rmv.getList_btn().get(btn_cnt).setBackground(new Color(255, 128, 0));
				} else {
					JOptionPane.showMessageDialog(rmv, "�Խ� �ð��� ���� �ʾҽ��ϴ�.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// roomIn

	// ���ó���ϴ� �޼ҵ�
	public void roomOut(int Btn_Cnt) {
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		System.out.println("��� ��ư : " + Btn_Cnt);
		int number = JOptionPane.showConfirmDialog(null, "������ ����Ͻðڽ��ϱ�?");
		System.out.println("joptionpane ��/�ƴϿ�/���  " + number);

		try {

			if (number == 0) {
				if (Btn_Cnt == 2) {

					list = m_dao.selectAll();
					rmvv = list.get(0);
					// rmv.setResView(list);

					System.out.println(rmvv.getCheckin() + " ----------------");

					if (rmvv.getCheckin() == null) {
						JOptionPane.showMessageDialog(rmv, "�Խǿ��θ� Ȯ�����ּ���");
					} else if (rmvv.getCheckin() != null) {
						m_dao.out_Rooming(rmvv.getRes_id());

						System.out.println("*****************����� ��� ������ ���ϸ��� �߰��ϴ°�!");
						double pirce;

						int Intime = Integer.parseInt(rmvv.getIn_time());
						int Outtime = Integer.parseInt(rmvv.getOut_time());
						// ��� �ð�
						int UseTime = Outtime - Intime;

						pirce = (UseTime * rmvv.getP_cnt() * rmvv.getPrice()) * 0.05;

						m_dao.add_mile(pirce, rmvv.getId());

						setRes();
					}
				} else if (Btn_Cnt != 0) {
					list = m_dao.selectAll();
					Btn_Cnt = Btn_Cnt / 5;
					rmvv = list.get(Btn_Cnt);
					System.out.println(rmvv.getCheckin() + " ----------------");
					if (rmvv.getCheckin() == null) {
						JOptionPane.showMessageDialog(rmv, "�Խǿ��θ� Ȯ�����ּ���");
					} else if (rmvv.getCheckin() != null) {
						m_dao.out_Rooming(rmvv.getRes_id());

						System.out.println("*****************����� �Խ� ������ ���ϸ��� �߰��ϴ°�!");
						double pirce;

						int Intime = Integer.parseInt(rmvv.getIn_time());
						int Outtime = Integer.parseInt(rmvv.getOut_time());
						// ��� �ð�
						int UseTime = Outtime - Intime;

						pirce = (UseTime * rmvv.getP_cnt() * rmvv.getPrice()) * 0.05;

						m_dao.add_mile(pirce, rmvv.getId());
						setRes();
					}

				} // end if
			} // end if

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

					// ���Ǵ� ���ϸ����� �ٽ� ����
					System.out.println("����� ���ϸ��� " + rmvv.getUse_mile());
					// DB�� ���� number��� �ڹٿ��� int������ ������ �� null�ΰ�� 0����ó�����ش�.
					if (rmvv.getUse_mile() != 0) {
						m_dao.Use_Mile_Plus(rmvv.getRes_id(), rmvv.getId());
					}
					setRes();
				} else if (Btn_Cnt != 0) {
					list = m_dao.selectAll();
					Btn_Cnt = Btn_Cnt / 5;
					rmvv = list.get(Btn_Cnt);
					m_dao.delete_res(rmvv.getRes_id());

					// ���Ǵ� ���ϸ����� �ٽ� ����
					// DB�� ���� number��� �ڹٿ��� int������ ������ �� null�ΰ�� 0����ó�����ش�.
					System.out.println("����� ���ϸ��� " + rmvv.getUse_mile());
					if (rmvv.getUse_mile() != 0) {
						m_dao.Use_Mile_Plus(rmvv.getRes_id(), rmvv.getId());
					}
					setRes();
				} // end else
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRes();
	}// roomCancle

	// �ð� �߰� �޼ҵ�
	public void roomRenewal(int cnt) {
		add_timeView atf = new add_timeView(rmv);
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

		rmv.getList_btn().clear();
		rmv.getLine_panel().clear();
		rmv.getList_tf().clear();

		rmv.getTotal_line_panel().removeAll();

		try {
			List<ResMgrVO> list = rmv.resetData();
			if (list.size() != 0) {
				list = rmv.resetData();
				rmv.setResView(list);
			}
			if (list.size() == 0) {
				rmv.dispose();
				rmv.setResView(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
					roomIn(0, 1);
				} else if (i != 1) {
					int Btn_Cnt;
					Btn_Cnt = i / 5;
					System.out.println(Btn_Cnt);
					roomIn(Btn_Cnt, i);
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
