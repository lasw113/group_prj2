package kr.co.sist.client.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.controller.RoomInfoFrmEvt;

@SuppressWarnings("serial")
public class RoomInfoFrm extends JPanel {
	private JRadioButton rb1, rb2, rb3, rb4, rbRoom3, rbRoom4;
	private ButtonGroup groupType;
	private JLabel lblInfo, lblTime, lblCnt, lblRTime, lblImg, lblRCnt, lblMonth, lblDay, logo, logo2;
	private JLabel[] lblEquipment;
	private JTextArea jtaInfo;
	private JRadioButton[] rbRoom1, rbRoom2;
	private ButtonGroup groupRoom1, groupRoom2;
	private JButton btnNext;
	private JPanel roomLogo, roomInfo, room1, room2, room3, room4, equip, selectRoom;
	private JTable jtTime;
	private DefaultTableModel dtmTime;
	private JComboBox<String> jcbMonth, jcbDay, jcbCnt;
	private DefaultComboBoxModel<String> dcmbMonth, dcmbDay, dcmbCnt;
	
	private String id;

	public RoomInfoFrm(String id) {
		this.id = id;
		setLayout(null);
		
		lblEquipment = new JLabel[6];
		for (int i = 0; i < lblEquipment.length; i++) {
			lblEquipment[i] = new JLabel();
		}

		rb1 = new JRadioButton("1~4인실");
		rb2 = new JRadioButton("5~9인실");
		rb3 = new JRadioButton("10~15인실");
		rb4 = new JRadioButton("16~20인실");

		groupType = new ButtonGroup();
		groupRoom1 = new ButtonGroup();
		groupRoom2 = new ButtonGroup();

		lblInfo = new JLabel("방 소개");
		lblTime = new JLabel("예약시간");
		lblCnt = new JLabel("예약인원");
		lblImg = new JLabel(new ImageIcon("C:/dev/workspace/group_project2/src/studyroom/img/s_01.png"));
		lblRTime = new JLabel("최소 1시간 부터");
		lblRCnt = new JLabel();
		lblMonth = new JLabel("월");
		lblDay = new JLabel("일");

		String path = System.getProperty("user.dir");
		logo = new JLabel(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "siststudyroom.png"));
		logo2 = new JLabel(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "siststudyroom2.png"));
		btnNext = new JButton("다음");

		jtaInfo = new JTextArea();
		jtaInfo.setEditable(false);
		jtaInfo.setWrapStyleWord(true);
		jtaInfo.setLineWrap(true);

		groupType.add(rb1);
		groupType.add(rb2);
		groupType.add(rb3);
		groupType.add(rb4);

		String[] orderTime = { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22입실불가" };
		String[][] orderEmpty = new String[1][1];

		dtmTime = new DefaultTableModel(orderEmpty, orderTime);
		jtTime = new JTable(dtmTime) {
			// 셀 편집 막기
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable
		};
		jtTime.getTableHeader().setReorderingAllowed(false);
		JScrollPane jspTime = new JScrollPane(jtTime);

		dcmbMonth = new DefaultComboBoxModel<String>();
//		for (int i = 1; i < 13; i++) {
//			dcmbMonth.addElement(i + "");
//		}
		jcbMonth = new JComboBox<String>(dcmbMonth);

		dcmbDay = new DefaultComboBoxModel<String>();
		jcbDay = new JComboBox<String>(dcmbDay);

		dcmbCnt = new DefaultComboBoxModel<String>();
		jcbCnt = new JComboBox<String>(dcmbCnt);

		for (int i = 0; i < orderTime.length; i++) {
			jtTime.getColumnModel().getColumn(i).setPreferredWidth(60);
		}
		jtTime.setRowHeight(50);

		rbRoom1 = new JRadioButton[4];
		for (int i = 0; i < rbRoom1.length; i++) {
			rbRoom1[i] = new JRadioButton((i + 1) + "번방");
			rbRoom1[i].setBackground(Color.white);
			groupRoom1.add(rbRoom1[i]);
		} // end for
		rbRoom1[0].setSelected(true);
		rbRoom2 = new JRadioButton[3];
		for (int i = 0; i < rbRoom2.length; i++) {
			rbRoom2[i] = new JRadioButton((i + 1) + "번방");
			rbRoom2[i].setBackground(Color.white);
			groupRoom2.add(rbRoom2[i]);
		} // end for
		rbRoom2[0].setSelected(true);

		rbRoom3 = new JRadioButton("1번방");
		rbRoom3.setBackground(Color.white);
		rbRoom4 = new JRadioButton("1번방");
		rbRoom4.setBackground(Color.white);

		JPanel type = new JPanel();
		type.setBounds(0, 0, 990, 50);
		type.add(rb1);
		type.add(rb2);
		type.add(rb3);
		type.add(rb4);

		roomLogo = new JPanel();
		roomLogo.setLayout(null);
		roomLogo.setBackground(Color.white);
		roomLogo.setBounds(0, 50, 1000, 1000);
		roomLogo.add(logo);
		roomLogo.add(logo2);

		room1 = new JPanel();
		room2 = new JPanel();
		room3 = new JPanel();
		room4 = new JPanel();
		room1.setVisible(false);
		room2.setVisible(false);
		room3.setVisible(false);
		room4.setVisible(false);
		room1.setBounds(0, 50, 990, 50);
		room2.setBounds(0, 50, 990, 50);
		room3.setBounds(0, 50, 990, 50);
		room4.setBounds(0, 50, 990, 50);

		for (int i = 0; i < rbRoom1.length; i++) {
			room1.add(rbRoom1[i]);
		} // end for

		for (int i = 0; i < rbRoom2.length; i++) {
			room2.add(rbRoom2[i]);
		} // end for

		room3.add(rbRoom3);
		room4.add(rbRoom4);

		equip = new JPanel();
		equip.setLayout(new GridLayout(2, 3));
		equip.setBounds(430, 210, 501, 180);
		for (int i = 0; i < lblEquipment.length; i++) {
			equip.add(lblEquipment[i]);
		} // end for

		roomInfo = new JPanel();
		roomInfo.setBorder(new TitledBorder("방 정보"));
		roomInfo.setLayout(null);
		roomInfo.setBounds(0, 100, 960, 400);
		roomInfo.add(lblImg);
		roomInfo.add(lblInfo);
		roomInfo.add(jtaInfo);
		roomInfo.add(lblTime);
		roomInfo.add(lblRTime);
		roomInfo.add(lblCnt);
		roomInfo.add(lblRCnt);
		roomInfo.add(equip);

		JPanel date = new JPanel();
		date.setBounds(0, 0, 960, 100);
		date.add(jcbMonth);
		date.add(lblMonth);
		date.add(jcbDay);
		date.add(lblDay);
		date.setBorder(new TitledBorder("날짜 선택"));

		JPanel p_cnt = new JPanel();
		p_cnt.setBounds(0, 200, 960, 100);
		p_cnt.add(jcbCnt);
		p_cnt.setBorder(new TitledBorder("인원 선택"));

		JPanel Time = new JPanel();
		Time.setLayout(new BorderLayout());
		Time.setBounds(0, 100, 960, 100);
		Time.add(jspTime);
		Time.setBorder(new TitledBorder("시간 선택"));
		
		JLabel hand = new JLabel(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "hand.png"));

		selectRoom = new JPanel();
		selectRoom.setLayout(null);
		selectRoom.setVisible(false);
		selectRoom.add(date);
		selectRoom.add(p_cnt);
		selectRoom.add(Time);
		selectRoom.add(btnNext);
		selectRoom.add(hand);
		selectRoom.setBounds(0, 500, 990, 400);

		lblImg.setBounds(50, 20, 300, 370);

		lblInfo.setBounds(420, 20, 60, 30);
		jtaInfo.setBounds(490, 20, 350, 100);

		lblTime.setBounds(420, 130, 60, 30);
		lblRTime.setBounds(490, 130, 100, 30);

		lblCnt.setBounds(420, 170, 60, 30);
		lblRCnt.setBounds(490, 170, 100, 30);
		
		logo.setBounds(0, 0, 970, 540);
		logo2.setBounds(0, 540, 970, 320);

		btnNext.setBounds(800, 320, 100, 30);
		hand.setBounds(40, 300, 800, 100);
		hand.setToolTipText("예약해주세요~");

		type.setBackground(Color.white);
		date.setBackground(Color.white);
		p_cnt.setBackground(Color.white);
		Time.setBackground(Color.white);
		roomInfo.setBackground(Color.white);
		selectRoom.setBackground(Color.white);
	
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		rb3.setOpaque(false);
		rb4.setOpaque(false);
		room1.setOpaque(false);
		room2.setOpaque(false);
		room3.setOpaque(false);
		room4.setOpaque(false);

		setPreferredSize(new Dimension(0, 900));

		add(type);
		add(roomLogo);
		add(room1);
		add(room2);
		add(room3);
		add(room4);
		add(roomInfo);
		add(selectRoom);

		RoomInfoFrmEvt rife = new RoomInfoFrmEvt(this);
		rb1.addItemListener(rife);
		rb2.addItemListener(rife);
		rb3.addItemListener(rife);
		rb4.addItemListener(rife);
		for (int i = 0; i < rbRoom1.length; i++) {
			rbRoom1[i].addItemListener(rife);
		} // end for
		for (int i = 0; i < rbRoom2.length; i++) {
			rbRoom2[i].addItemListener(rife);
		} // end for
		rbRoom3.addItemListener(rife);
		rbRoom4.addItemListener(rife);

		jcbMonth.addActionListener(rife);
		jcbDay.addActionListener(rife);
		jtTime.addMouseListener(rife);

		btnNext.addActionListener(rife);
	}// RoomInfoFrm

	public JRadioButton getRb1() {
		return rb1;
	}

	public JRadioButton getRb2() {
		return rb2;
	}

	public JRadioButton getRb3() {
		return rb3;
	}

	public JRadioButton getRb4() {
		return rb4;
	}

	public JLabel getLblRTime() {
		return lblRTime;
	}

	public JLabel getLblRCnt() {
		return lblRCnt;
	}

	public JTextArea getJtaInfo() {
		return jtaInfo;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JLabel getLblImg() {
		return lblImg;
	}

	public JLabel[] getLblEquipment() {
		return lblEquipment;
	}

	public JPanel getRoomLogo() {
		return roomLogo;
	}

	public JPanel getRoom1() {
		return room1;
	}

	public JPanel getRoom2() {
		return room2;
	}

	public JPanel getRoom3() {
		return room3;
	}

	public JPanel getRoom4() {
		return room4;
	}

	public JPanel getRoomInfo() {
		return roomInfo;
	}

	public JRadioButton[] getRbRoom1() {
		return rbRoom1;
	}

	public JRadioButton[] getRbRoom2() {
		return rbRoom2;
	}

	public JRadioButton getRbRoom3() {
		return rbRoom3;
	}

	public JRadioButton getRbRoom4() {
		return rbRoom4;
	}

	public JComboBox<String> getJcbMonth() {
		return jcbMonth;
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

	public JComboBox<String> getJcbDay() {
		return jcbDay;
	}

	public DefaultTableModel getDtmTime() {
		return dtmTime;
	}

	public JTable getJtTime() {
		return jtTime;
	}

	public JComboBox<String> getJcbCnt() {
		return jcbCnt;
	}

	public JPanel getSelectRoom() {
		return selectRoom;
	}

	public String getId() {
		return id;
	}
}// class
