package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import kr.co.sist.client.controller.SelectRoomFrmEvt;
import kr.co.sist.client.vo.RoomInfoVO;

@SuppressWarnings("serial")
public class SelectRoomFrm extends JPanel {

	private JLabel lblDate, lblCnt, lblMonth, lblDay;
	private JButton btnNext;
	private JTable jtTime;
	private JComboBox<String> jcbMonth, jcbDay, jcbCnt;
	private DefaultComboBoxModel<String> dcmbMonth, dcmbDay, dcmbCnt;
	
	private int p_min, p_max;
	private String room_id;

	public SelectRoomFrm(RoomInfoVO ri_vo, RoomInfoFrm rif, String room_id) {
		setLayout(null);
		setBackground(Color.WHITE);
		
		this.p_min =Integer.parseInt(ri_vo.getP_min()) ;
		this.p_max =Integer.parseInt(ri_vo.getP_max()) ;
		this.room_id = room_id;

		lblDate = new JLabel("날짜 선택");
		lblCnt = new JLabel("인원 선택");
		lblMonth = new JLabel("월");
		lblDay = new JLabel("일");
		
		btnNext = new JButton("다음");
		
		dcmbMonth = new DefaultComboBoxModel<String>();
		for(int i =1; i<13; i++) {
			dcmbMonth.addElement(i+"");			
		}
		jcbMonth = new JComboBox<String>(dcmbMonth);
		
		dcmbDay = new DefaultComboBoxModel<String>();
		jcbDay = new JComboBox<String>(dcmbDay);
		
		dcmbCnt = new DefaultComboBoxModel<String>();
		jcbCnt = new JComboBox<String>(dcmbCnt);
		
//		for(int i = Integer.parseInt(ri_vo.getP_min());i<=Integer.parseInt(ri_vo.getP_max());i++) {
//			dcmbCnt.addElement(i+"");
//		}
		
		JPanel date = new JPanel();		
		date.add(jcbMonth);
		date.add(lblMonth);
		date.add(jcbDay);
		date.add(lblDay);
		date.setBorder(new TitledBorder("날짜 선택"));
		
		JPanel p_cnt = new JPanel();
		p_cnt.add(jcbCnt);
		p_cnt.setBorder(new TitledBorder("인원 선택"));
		
		date.setBounds(0, 0, 970, 100);
		p_cnt.setBounds(0, 100, 970, 100);
				
		add(date);
		add(p_cnt);
		
		
		setBounds(0, 500, 1000, 400);
		
		SelectRoomFrmEvt srfe = new SelectRoomFrmEvt(this);
		
		jcbMonth.addActionListener(srfe);
//		jcbDay.addActionListener(srfe);
//		jcbCnt.addActionListener(srfe);
		
	}// SelectRoomFrm

	public JLabel getLblDate() {
		return lblDate;
	}

	public JLabel getLblCnt() {
		return lblCnt;
	}

	public JLabel getLblMonth() {
		return lblMonth;
	}

	public JLabel getLblDay() {
		return lblDay;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JTable getJtTime() {
		return jtTime;
	}

	public JComboBox<String> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<String> getJcbDay() {
		return jcbDay;
	}

	public JComboBox<String> getJcbCnt() {
		return jcbCnt;
	}

	public DefaultComboBoxModel<String> getDcmbMonth() {
		return dcmbMonth;
	}

	public DefaultComboBoxModel<String> getDcmbDay() {
		return dcmbDay;
	}

	public DefaultComboBoxModel<String> getDcmbCnt() {
		return dcmbCnt;
	}

	public int getP_min() {
		return p_min;
	}

	public int getP_max() {
		return p_max;
	}

	public String getRoom_id() {
		return room_id;
	}
	
	
}// class
