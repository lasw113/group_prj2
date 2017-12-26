package kr.co.sist.manager.view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import kr.co.sist.manager.controller.add_timeEvt;
import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.vo.ResMgrVO;

public class add_timeForm extends JFrame {

	// Ȯ�� ��ҹ�ư
	private JButton btn_check, btn_cancle;
	private JComboBox combo;
	private int possible_time;
	private int index;
	ResMgrView rmv;
	public add_timeForm(ResMgrView rmv) {
		this.rmv=rmv;
	}

	public void frame_disposition(List<ResMgrVO> list, int cnt) {
		ResMgrVO rmvv = null;
		rmvv = list.get(cnt);

		setSize(300, 380);
		JPanel panel = new JPanel();
		panel.setLayout(null); // ��ġ�����ڸ� null�� ����

		JLabel name = new JLabel("�����ڸ�");
		JLabel p_cnt = new JLabel("�ο���");
		JLabel room_id = new JLabel("���ȣ");
		JLabel add_time = new JLabel("�߰��ð�");

		int out_time = Integer.parseInt(rmvv.getOut_time());
		possible_time = 22 - out_time;
		// System.out.println(possible_time);

		JTextArea jtname = new JTextArea(rmvv.getRes_name());
		JTextArea jtp_cnt = new JTextArea(rmvv.getP_cnt() + "��");
		JTextArea jtroom_id = new JTextArea(rmvv.getRoom_id());

		jtname.setEditable(false);
		jtp_cnt.setEditable(false);
		jtroom_id.setEditable(false);

		combo = new JComboBox();

		for (int i = 0; i <= possible_time; i++) {
			combo.addItem(i);
		}
		combo.setEditable(false);

		btn_check = new JButton("Ȯ��");
		btn_cancle = new JButton("���");

		// ��ư ��ġ
		btn_check.setBounds(125, 300, 70, 30);
		btn_cancle.setBounds(205, 300, 70, 30);

		// �� ��ġ
		name.setBounds(50, 30, 70, 30);
		p_cnt.setBounds(50, 100, 70, 30);
		room_id.setBounds(50, 170, 70, 30);
		add_time.setBounds(50, 240, 70, 30);

		// teatarea ��ġ
		jtname.setBounds(150, 30, 70, 30);
		jtp_cnt.setBounds(150, 100, 70, 30);
		jtroom_id.setBounds(150, 170, 70, 30);
		combo.setBounds(150, 240, 70, 30);

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

		add_timeEvt ste = new add_timeEvt(this,rmv);
		btn_cancle.addActionListener(ste);
		btn_check.addActionListener(ste);
		combo.addActionListener(ste);

	}// frame_disposition

	public void add_timeForm(int i) {

		// list�� ����� DB���� ��������!
		ResMgrVO rmvv = null;
		ManagerDAO m_dao = null;
		m_dao = ManagerDAO.getInstance();
		List<ResMgrVO> list;
		index = i / 5;
		System.out.println("�ش�Ǵ� index : " + index);
		// System.out.println(index);
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

	public JComboBox getCombo() {
		return combo;
	}

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