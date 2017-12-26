package kr.co.sist.manager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.co.sist.manager.controller.ResMgrViewEvt;
import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.vo.ResMgrVO;

public class ResMgrView extends JFrame {

	private JButton btnrenew; // 갱신 버튼
	private ArrayList<JButton> list_btn; // btn 리스트
	private ArrayList<JLabel> list_Jbl;
	private ArrayList<JPanel> line_panel;

	// tf갯수 파악
	private int tf_count = 0;
	// btn갯수파악
	private int btn_count = 0;

	private static int cnt;

	private JPanel total_line_panel;
	private JPanel totalpanel;
	private List<ResMgrVO> list_count;

	public void admin_line_name(int i, JPanel line_panel, List<ResMgrVO> list) {
		JLabel jbres_num, jbRoom, jbCnt, jbName, jbIn, jbOut, jbnote;// 룸, 인원수, 입실/퇴실 시간, 비고 보여주는 textFiled
		JLabel gap1, gap2, gap3, gap4, gap5;
		JPanel[] wrap = new JPanel[12];

		for (int cnt = 0; cnt < 12; cnt++) {
			wrap[cnt] = new JPanel();
			wrap[cnt].setPreferredSize(new Dimension(50, 60));
		} // end for

		jbres_num = new JLabel("예약번호");
		jbRoom = new JLabel("방번호");
		jbCnt = new JLabel("인원수");
		jbName = new JLabel("예약자명");
		jbIn = new JLabel("입실");
		jbOut = new JLabel("퇴실");
		jbnote = new JLabel("비고");

		jbres_num.setHorizontalAlignment(jbres_num.CENTER);
		jbRoom.setHorizontalAlignment(jbRoom.CENTER);
		jbCnt.setHorizontalAlignment(jbCnt.CENTER);
		jbName.setHorizontalAlignment(jbName.CENTER);
		jbIn.setHorizontalAlignment(jbIn.CENTER);
		jbOut.setHorizontalAlignment(jbOut.CENTER);
		jbnote.setHorizontalAlignment(jbnote.CENTER);

		gap1 = new JLabel("");
		gap2 = new JLabel("");
		gap3 = new JLabel("");
		gap4 = new JLabel("");
		gap5 = new JLabel("");

		wrap[0].add(jbres_num);
		wrap[1].add(jbRoom);
		wrap[2].add(jbCnt);
		wrap[3].add(jbName);
		wrap[4].add(jbIn);
		wrap[5].add(jbOut);
		wrap[6].add(jbnote);
		wrap[7].add(gap1);
		wrap[8].add(gap2);
		wrap[9].add(gap3);
		wrap[10].add(gap4);
		wrap[11].add(gap5);

		for (int cnt = 0; cnt < wrap.length; cnt++) {
			line_panel.add(wrap[cnt]);
		} // end for

	}// admin_line_name

	// TextFiedl를 list에 넣기위한 method
	public void admin_line_tf(int i, JPanel line_panel, List<ResMgrVO> list) {

		ResMgrVO rmvv;

		rmvv = list.get(i);

		// TextField 추가
		list_Jbl.add(new JLabel(rmvv.getRes_id()));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getRoom_id()));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(String.valueOf(rmvv.getP_cnt()) + "명"));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getRes_name()));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getIn_time() + "시"));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

		list_Jbl.add(new JLabel(rmvv.getOut_time() + "시"));
		list_Jbl.get(tf_count).setHorizontalAlignment(list_Jbl.get(tf_count).CENTER);
		line_panel.add(list_Jbl.get(tf_count));
		tf_count++;

	}// admin_line_tf

	// Jbutton을 list에 넣기위한 method
	public void admin_line_btn(int i, JPanel line_panel, List<ResMgrVO> list) {

		// 버튼 추가 0 5 10 15

		ResMgrVO rmvv;
		rmvv = list.get(i);

		if (rmvv.getRequest() == null) {
			JButton btn = new JButton("없음");
			btn.setFont(new Font("고딕", Font.BOLD, 10));
			list_btn.add(btn);
			line_panel.add(list_btn.get(btn_count));
			btn_count++;
		}else {
			JButton btn = new JButton("있음");
			btn.setFont(new Font("고딕", Font.BOLD, 10));
			list_btn.add(btn);
			line_panel.add(list_btn.get(btn_count));
			btn_count++;
		}

		JLabel gap1 = new JLabel("");
		line_panel.add(gap1);

		JButton btton = new JButton("입실");
		btton.setFont(new Font("고딕", Font.BOLD, 10));
		list_btn.add(btton);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

		JButton btton2 = new JButton("퇴실");
		btton2.setFont(new Font("고딕", Font.BOLD, 10));
		list_btn.add(btton2);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

		JButton btton3 = new JButton("예약취소");
		btton3.setFont(new Font("고딕", Font.BOLD, 10));
		list_btn.add(btton3);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

		JButton btton4 = new JButton("변경");
		btton4.setFont(new Font("고딕", Font.BOLD, 10));
		list_btn.add(btton4);
		line_panel.add(list_btn.get(btn_count));
		btn_count++;

		// System.out.println("btn = " + btn_count);
	}// admin_line_btn

	public JPanel ResMgrView(int count, List<ResMgrVO> list) {

		// 전체 패널
		totalpanel = new JPanel();

		// 갱신버튼, 날짜버튼이든 패널을 넣는 panel
		JPanel panel = new JPanel();

		// 날짜와 갱신버튼 넣을 패널
		JPanel pan_date = new JPanel();
		JPanel pan_btn = new JPanel();

		// 한줄을 담는 패널
		total_line_panel = new JPanel();

		// 버튼과 textfield를 배열에 저장하기위해서 크기 지정
		list_btn = new ArrayList<>();
		list_Jbl = new ArrayList<>();
		line_panel = new ArrayList<>();

		// 오늘 날짜 받는 쿼리
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		// 날짜와 갱신하기 버튼 클릭!
		JLabel now_date = new JLabel("  " + dTime + "  ");
		btnrenew = new JButton("갱신");

		// date를 테두리치는 코드
		now_date.setBorder(new TitledBorder(new LineBorder(Color.black)));
		panel.setLayout(new BorderLayout());

		// 각각의 패널에 크기지정하고, gridlayout으로 잡기
		for (int i = 0; i < count; i++) {
			line_panel.add(new JPanel());
			line_panel.get(i).setBorder(new TitledBorder(new LineBorder(Color.black)));
			line_panel.get(i).setPreferredSize(new Dimension(950, 50));
			line_panel.get(i).setLayout(new GridLayout(2, 12));
		} // end for

		// 크기지정
		panel.setPreferredSize(new Dimension(950, 50));
		total_line_panel.setPreferredSize(new Dimension(950, 56 * list.size()));
		//totalpanel.setPreferredSize(new Dimension(1000, 600));

		// panel을 totalpanel에 넣기
		for (int i = 0; i < count; i++) {
			total_line_panel.add(line_panel.get(i));
		} // end for

		// 라벨, 버튼, 텍스트에어리어 생성하고 넣기!
		for (int i = 0; i < count; i++) {
			admin_line_name(i, line_panel.get(i), list);
			admin_line_tf(i, line_panel.get(i), list);
			admin_line_btn(i, line_panel.get(i), list);
		} // end for

		// 날짜와 갱신버튼 추가
		pan_date.add(now_date);
		pan_btn.add(btnrenew);

		// panel에 위치잡고 추가
		panel.add("Center", pan_date);
		panel.add("East", pan_btn);

		// add하는 부분
		totalpanel.add(panel);

		// 스크롤 넣기 위한 것!
		JScrollPane scrollPane = new JScrollPane(total_line_panel);

		scrollPane.setPreferredSize(new Dimension(980, 480));

		totalpanel.add(scrollPane);
		add(totalpanel);

		// 이벤트 - 비고, 입실, 퇴실, 예약취소,추가/변경
		ResMgrViewEvt rnf = new ResMgrViewEvt(this);
		for (int i = 0; i < btn_count; i++) {
			list_btn.get(i).addActionListener(rnf);
		} // end for

		btnrenew.addActionListener(rnf);

		// 윈도우 크기설정
		setSize(1000, 600);
		// 프레임 위치선정
		setLocationRelativeTo(null);

		// 가시화
		setResizable(false);
		// setVisible(true);

		// setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});// windowClosing

		return totalpanel;
	}// ResMgrView

	public JPanel Data_Judgment_Frm() {
		ResMgrView rmv = new ResMgrView();

		List<ResMgrVO> list;
		try {
			list = rmv.resetData();

			// 데이터가 하나도 없으면
			if (list.size() == 0) {
				// 전체 패널
				totalpanel = new JPanel();

				// 갱신버튼, 날짜버튼이든 패널을 넣는 panel
				JPanel panel = new JPanel();

				// 날짜와 갱신버튼 넣을 패널
				JPanel pan_date = new JPanel();
				JPanel pan_btn = new JPanel();

				// 한줄을 담는 패널
				total_line_panel = new JPanel();

				// 버튼과 textfield를 배열에 저장하기위해서 크기 지정
				list_btn = new ArrayList<>();
				list_Jbl = new ArrayList<>();
				line_panel = new ArrayList<>();

				// 오늘 날짜 받는 쿼리
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
				Date currentTime = new Date();
				String dTime = formatter.format(currentTime);

				// 날짜와 갱신하기 버튼 클릭!
				JLabel now_date = new JLabel("  " + dTime + "  ");
				btnrenew = new JButton("갱신");

				// date를 테두리치는 코드
				now_date.setBorder(new TitledBorder(new LineBorder(Color.black)));
				panel.setLayout(new BorderLayout());

				// 각각의 패널에 크기지정하고, gridlayout으로 잡기

				// 크기지정
				panel.setPreferredSize(new Dimension(950, 50));
				total_line_panel.setPreferredSize(new Dimension(950, 56 * list.size()));
				//totalpanel.setPreferredSize(new Dimension(1000, 600));

				// 날짜와 갱신버튼 추가
				pan_date.add(now_date);
				pan_btn.add(btnrenew);

				// panel에 위치잡고 추가
				panel.add("Center", pan_date);
				panel.add("East", pan_btn);

				// add하는 부분
				totalpanel.add(panel);

				JPanel view = new JPanel();

				JLabel jlbl = new JLabel("데이터가 없다");

				view.add(jlbl);

				// 스크롤 넣기 위한 것!
				JScrollPane scrollPane = new JScrollPane(total_line_panel);

				scrollPane.setPreferredSize(new Dimension(980, 480));

				total_line_panel.add(view);
				totalpanel.add(scrollPane);
				add(totalpanel);

				// 이벤트 - 비고, 입실, 퇴실, 예약취소,추가/변경
				ResMgrViewEvt rnf = new ResMgrViewEvt(this);

				btnrenew.addActionListener(rnf);

				// 윈도우 크기설정
				setSize(1000, 600);
				// 프레임 위치선정
				setLocationRelativeTo(null);
				setResizable(false);

				// 가시화
				// setVisible(true);

				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						dispose();
					}
				});// windowClosing
			} else {// 아니면 아래코드 실행

				cnt = list.size();
				System.out.println("데이터 행의 수" + cnt);

				// rmv.ResMgrView(cnt, list);
				return rmv.ResMgrView(cnt, list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalpanel;
	}

	// db에서 데이터 받아오는 method
	public List<ResMgrVO> resetData() throws SQLException {
		ResMgrView rmv = new ResMgrView();
		ManagerDAO m_dao = ManagerDAO.getInstance() ;
		List<ResMgrVO> list;

		list = m_dao.selectAll();
		// 갱신하기전 데이터 갯수를 파악하기!
		return list;
	}

	// 갱신 버튼을 누를 때 새로운 프레임 생성!
	public void setResView(List<ResMgrVO> list) {
		ResMgrView rmv = new ResMgrView();
		tf_count = 0;
		btn_count = 0;
		int size = list.size();

		if (size == 0) {
			rmv.Data_Judgment_Frm();
		} else {
			try {
				list = rmv.resetData();
				int count = list.size();
				list_btn = new ArrayList<>();
				list_Jbl = new ArrayList<>();
				System.out.println("setresview 부르기");
				System.out.println(list_Jbl.size());

				// 윈도우 크기설정
				setSize(999, 600);
				setSize(1000, 600);
				setLocationRelativeTo(null);

				// // 각각의 패널에 크기지정하고, gridlayout으로 잡기
				for (int i = 0; i < count; i++) {
					line_panel.add(new JPanel());
					line_panel.get(i).setBorder(new TitledBorder(new LineBorder(Color.black)));
					line_panel.get(i).setPreferredSize(new Dimension(950, 50));
					line_panel.get(i).setLayout(new GridLayout(2, 12));
				} // end for

				// panel을 totalpanel에 넣기
				for (int i = 0; i < count; i++) {
					total_line_panel.add(line_panel.get(i));
				} // end for

				// line_panel에 버튼, 라벨, 텍스트에어리어 넣기!
				for (int i = 0; i < count; i++) {
					admin_line_name(i, line_panel.get(i), list);
					admin_line_tf(i, line_panel.get(i), list);
					admin_line_btn(i, line_panel.get(i), list);
				} // end for

				// 이벤트 - 비고, 입실, 퇴실, 예약취소,추가/변경
				ResMgrViewEvt rnf = new ResMgrViewEvt(this);
				for (int i = 0; i < btn_count; i++) {
					list_btn.get(i).addActionListener(rnf);
				} // end for

				btnrenew.addActionListener(rnf);
				setLocationRelativeTo(null);
				setResizable(false);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end catch
		}

	}

	public JButton getBtnrenew() {
		return btnrenew;
	}

	public void setBtnrenew(JButton btnrenew) {
		this.btnrenew = btnrenew;
	}

	public List<JButton> getList_btn() {
		return list_btn;
	}

	public void setList_btn(ArrayList<JButton> list_btn) {
		this.list_btn = list_btn;
	}

	public List<JLabel> getList_tf() {
		return list_Jbl;
	}

	public void setList_tf(ArrayList<JLabel> list_tf) {
		this.list_Jbl = list_tf;
	}

	public List<JPanel> getLine_panel() {
		return line_panel;
	}

	public void setLine_panel(ArrayList<JPanel> line_panel) {
		this.line_panel = line_panel;
	}

	public int getTf_count() {
		return tf_count;
	}

	public void setTf_count(int tf_count) {
		this.tf_count = tf_count;
	}

	public int getBtn_count() {
		return btn_count;
	}

	public void setBtn_count(int btn_count) {
		this.btn_count = btn_count;
	}

	public static int getCnt() {
		return cnt;
	}

	public static void setCnt(int cnt) {
		ResMgrView.cnt = cnt;
	}

	public JPanel getTotal_line_panel() {
		return total_line_panel;
	}

	public void setTotal_line_panel(JPanel total_line_panel) {
		this.total_line_panel = total_line_panel;
	}

	public JPanel getTotalpanel() {
		return totalpanel;
	}

	public void setTotalpanel(JPanel totalpanel) {
		this.totalpanel = totalpanel;
	}

}
