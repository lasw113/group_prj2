package kr.co.sist.manager.view;

import java.awt.Color;
import java.awt.TextArea;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import kr.co.sist.manager.controller.add_timeEvt;
import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.vo.ResMgrVO;

@SuppressWarnings({ "serial", "unused" })
public class add_timeView extends JFrame {

	// 확인 취소버튼
	private JButton btn_check, btn_cancle;
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	private int possible_time;
	private int index;
	ResMgrView rmv;

	ManagerDAO m_dao;

	public add_timeView(ResMgrView rmv) {
		this.rmv = rmv;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void frame_disposition(List<ResMgrVO> list, int cnt) {

		m_dao = ManagerDAO.getInstance();
		try {
			ResMgrVO rmvv = null;
			rmvv = list.get(cnt);
			int DB_InTime;

			setSize(300, 380);
			JPanel panel = new JPanel();
			panel.setLayout(null); // 배치관리자를 null로 설정

			JLabel name = new JLabel("예약자명");
			JLabel p_cnt = new JLabel("인원수");
			JLabel room_id = new JLabel("방번호");
			JLabel add_time = new JLabel("추가시간");

			JTextArea jtname = new JTextArea(rmvv.getRes_name());
			JTextArea jtp_cnt = new JTextArea(rmvv.getP_cnt() + "명");
			JTextArea jtroom_id = new JTextArea(rmvv.getRoom_id());

			jtname.setEditable(false);
			jtp_cnt.setEditable(false);
			jtroom_id.setEditable(false);

			int out_time = Integer.parseInt(rmvv.getOut_time());

			// 색바꾸기
			panel.setBackground(new Color(255, 255, 255));

			DB_InTime = m_dao.Get_Intime(rmvv.getRoom_id(), rmvv.getRes_id());


			if (DB_InTime == 0) {
				possible_time = 22 - out_time;
			} else if (DB_InTime != 0) {
				possible_time = DB_InTime - out_time;
			}

			combo = new JComboBox();

			if (possible_time == 0) {
				JOptionPane.showMessageDialog(null, "시간추가를 할 수가 없습니다.");
				return;
			} else {
				for (int i = 1; i <= possible_time; i++) {
					combo.addItem(i);
				}
				combo.setEditable(false);
			}

			btn_check = new JButton("확인");
			btn_cancle = new JButton("취소");

			// 버튼 위치
			btn_check.setBounds(125, 300, 70, 30);
			btn_cancle.setBounds(205, 300, 70, 30);

			// 라벨 위치
			name.setBounds(50, 30, 70, 30);
			p_cnt.setBounds(50, 100, 70, 30);
			room_id.setBounds(50, 170, 70, 30);
			add_time.setBounds(50, 240, 70, 30);

			// teatarea 위치
			jtname.setBounds(150, 35, 70, 30);
			jtp_cnt.setBounds(150, 105, 70, 30);
			jtroom_id.setBounds(150, 175, 70, 30);
			combo.setBounds(150, 240, 75, 30);

			panel.add(btn_check);
			panel.add(btn_cancle);

			panel.add(name);
			panel.add(p_cnt);
			panel.add(room_id);
			panel.add(add_time);

			panel.add(jtname);
			panel.add(jtp_cnt);
			panel.add(jtroom_id);
			panel.add(combo);

			add(panel);
			setLocationRelativeTo(null);
			setVisible(true);

			add_timeEvt ste = new add_timeEvt(this, rmv);
			btn_cancle.addActionListener(ste);
			btn_check.addActionListener(ste);
			combo.addActionListener(ste);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch;

	}// frame_disposition

	public void add_timeForm(int i) {

		// list에 저장된 DB정보 가져오기!
		@SuppressWarnings("unused")
		ResMgrVO rmvv = null;
		m_dao = ManagerDAO.getInstance();

		List<ResMgrVO> list;

		index = i / 5;
		try {
			list = m_dao.selectAll();
			if (index == 0) {
				rmvv = list.get(0);
				frame_disposition(list, 0);
			} else if (index != 0) {
				rmvv = list.get(index);
				frame_disposition(list, index);
			} // end if
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end if

	}// add_time

	public JButton getBtn_check() {
		return btn_check;
	}

	public void setBtn_check(JButton btn_check) {
		this.btn_check = btn_check;
	}

	public JButton getBtn_cancle() {
		return btn_cancle;
	}

	public void setBtn_cancle(JButton btn_cancle) {
		this.btn_cancle = btn_cancle;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCombo() {
		return combo;
	}

	@SuppressWarnings("rawtypes")
	public void setCombo(JComboBox combo) {
		this.combo = combo;
	}

	public int getPossible_time() {
		return possible_time;
	}

	public void setPossible_time(int possible_time) {
		this.possible_time = possible_time;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
