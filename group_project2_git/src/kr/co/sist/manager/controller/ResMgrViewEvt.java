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

	// 요청사항 확인하는 메소드
	public void setRequest(int Btn_Cnt) {
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		System.out.println(Btn_Cnt + "요청사항버튼!");
		try {
			list = m_dao.selectAll();
			System.out.println(list.size() + " =========list크기");

			// 요청사항이 비어있지 않다면
			if (Btn_Cnt == 0) {
				rmvv = list.get(0);
				TextArea ta = new TextArea(rmvv.getRequest());
				JOptionPane.showMessageDialog(null, ta);
				System.out.println("요청사항 확인  " + Btn_Cnt + "버튼");

			} else if (Btn_Cnt != 0) {
				Btn_Cnt = Btn_Cnt / 5;
				rmvv = list.get(Btn_Cnt);
				TextArea ta = new TextArea(rmvv.getRequest());
				System.out.println(rmvv.getRequest());
				JOptionPane.showMessageDialog(null, ta);
				System.out.println("요청사항 확인  " + Btn_Cnt + "버튼");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// setRequest

	// 입실처리하는 메소드
	public void roomIn(int cnt, int btn_cnt) {
		select_pay sp = new select_pay();
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		ResMgrVO rmvv = null;

		System.out.println("해당 줄" + cnt + " ------------ 버튼index " + btn_cnt);
		// 오늘 날짜 받는 쿼리
		SimpleDateFormat formatter = new SimpleDateFormat("kk", Locale.KOREA);
		Date currentTime = new Date();
		int dTime = Integer.parseInt(formatter.format(currentTime));

		System.out.println(dTime);
		try {

			// cnt를 나눠야 한다.(index를 구하기위해서)
			if (cnt == 0) {
				list = m_dao.selectAll();
				rmvv = list.get(0);
				int Intime = Integer.parseInt(rmvv.getIn_time());
				int Outtime = Integer.parseInt(rmvv.getOut_time());
				if (Intime <= dTime && Outtime > dTime) {
					sp.select_pay(cnt, btn_cnt);

					rmv.getList_btn().get(btn_cnt).setBackground(new Color(255, 128, 0));
				} else {
					JOptionPane.showMessageDialog(rmv, "입실 시간이 되지 않았습니다.");
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
					JOptionPane.showMessageDialog(rmv, "입실 시간이 되지 않았습니다.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// roomIn

	// 퇴실처리하는 메소드
	public void roomOut(int Btn_Cnt) {
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		System.out.println("퇴실 버튼 : " + Btn_Cnt);
		int number = JOptionPane.showConfirmDialog(null, "정말로 퇴실하시겠습니까?");
		System.out.println("joptionpane 예/아니요/취소  " + number);

		try {

			if (number == 0) {
				if (Btn_Cnt == 2) {

					list = m_dao.selectAll();
					rmvv = list.get(0);
					// rmv.setResView(list);

					System.out.println(rmvv.getCheckin() + " ----------------");

					if (rmvv.getCheckin() == null) {
						JOptionPane.showMessageDialog(rmv, "입실여부를 확인해주세요");
					} else if (rmvv.getCheckin() != null) {
						m_dao.out_Rooming(rmvv.getRes_id());

						System.out.println("*****************여기는 퇴실 누를때 마일리지 추가하는곳!");
						double pirce;

						int Intime = Integer.parseInt(rmvv.getIn_time());
						int Outtime = Integer.parseInt(rmvv.getOut_time());
						// 사용 시간
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
						JOptionPane.showMessageDialog(rmv, "입실여부를 확인해주세요");
					} else if (rmvv.getCheckin() != null) {
						m_dao.out_Rooming(rmvv.getRes_id());

						System.out.println("*****************여기는 입실 누를때 마일리지 추가하는곳!");
						double pirce;

						int Intime = Integer.parseInt(rmvv.getIn_time());
						int Outtime = Integer.parseInt(rmvv.getOut_time());
						// 사용 시간
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

	// 예약취소하는 메소드
	public void roomCancle(int Btn_Cnt) {
		System.out.println("예약취소  " + Btn_Cnt + "버튼");
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;

		int number = JOptionPane.showConfirmDialog(rmv, "정말로 예약취소하시겠습니까?");

		System.out.println(number);

		try {
			if (number == 0) {
				// 첫줄 예약취소를 누를경우
				if (Btn_Cnt == 3) {
					list = m_dao.selectAll();
					rmvv = list.get(0);
					rmv.setResView(list);
					m_dao.delete_res(rmvv.getRes_id());

					// 사용되는 마일리지를 다시 충전
					System.out.println("사용한 마일리지 " + rmvv.getUse_mile());
					// DB의 형이 number경우 자바에서 int형으로 가져올 때 null인경우 0으로처리해준다.
					if (rmvv.getUse_mile() != 0) {
						m_dao.Use_Mile_Plus(rmvv.getRes_id(), rmvv.getId());
					}
					setRes();
				} else if (Btn_Cnt != 0) {
					list = m_dao.selectAll();
					Btn_Cnt = Btn_Cnt / 5;
					rmvv = list.get(Btn_Cnt);
					m_dao.delete_res(rmvv.getRes_id());

					// 사용되는 마일리지를 다시 충전
					// DB의 형이 number경우 자바에서 int형으로 가져올 때 null인경우 0으로처리해준다.
					System.out.println("사용한 마일리지 " + rmvv.getUse_mile());
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

	// 시간 추가 메소드
	public void roomRenewal(int cnt) {
		add_timeView atf = new add_timeView(rmv);
		ResMgrView rmv = new ResMgrView();

		System.out.println("시간 추가  " + cnt + "버튼");

		// 시간추가 프레임!
		atf.add_timeForm(cnt);
		try {
			rmv.resetData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// roomRenewal

	// 갱신하는 버튼
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

		// 갱신버튼
		if (e.getSource() == rmv.getBtnrenew()) {
			// rmv.dispose();
			setRes();
		} // end if

		// 비고 버튼
		for (int i = 0; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				setRequest(i);
			} // end if
		} // end for

		// 입실 버튼
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

		// 퇴실 버튼
		for (int i = 2; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				roomOut(i);
			} // end if
		} // end for

		// 예약취소
		for (int i = 3; i < rmv.getList_btn().size(); i = i + 5) {
			if (e.getSource() == rmv.getList_btn().get(i)) {
				roomCancle(i);
			} // end if
		} // end for

		// 추가/변경 버튼
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
