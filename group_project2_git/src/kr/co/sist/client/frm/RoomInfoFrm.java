package kr.co.sist.client.frm;

import java.awt.Color;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import kr.co.sist.client.controller.RoomInfoFrmEvt;

@SuppressWarnings("serial")
public class RoomInfoFrm extends JPanel {
	private JRadioButton rb1, rb2, rb3, rb4, rbRoom3, rbRoom4;
	private ButtonGroup groupType;
	private JLabel lblInfo, lblTime, lblCnt, lblRTime, lblImg, lblRCnt;
	private JLabel[] lblEquipment;
	private List<String> equipment;
	private JTextArea jtaInfo;
	private JButton btnNext;
	private JRadioButton[] rbRoom1, rbRoom2;
	private ButtonGroup groupRoom1,groupRoom2;
	private JButton logo;
	private JPanel roomLogo,roomInfo, room1, room2, room3, room4;

	public RoomInfoFrm() {
		setLayout(null);

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
		lblImg = new JLabel(new ImageIcon("C:/dev/workspace/group_project2/src/studyroom/img/room.png"));
		lblRTime = new JLabel("최소 1시간 부터");
		lblRCnt = new JLabel("DB");

		logo = new JButton("로고이미지");

		jtaInfo = new JTextArea();

		groupType.add(rb1);
		groupType.add(rb2);
		groupType.add(rb3);
		groupType.add(rb4);

		rbRoom1 = new JRadioButton[4];
		for (int i = 0; i < rbRoom1.length; i++) {
			rbRoom1[i] = new JRadioButton((i + 1) + "번방");
			groupRoom1.add(rbRoom1[i]);
		} // end for
		rbRoom1[0].setSelected(true);
		rbRoom2 = new JRadioButton[3];
		for (int i = 0; i < rbRoom2.length; i++) {
			rbRoom2[i] = new JRadioButton((i + 1) + "번방");
			groupRoom2.add(rbRoom2[i]);
		} // end for
		rbRoom2[0].setSelected(true);
		
		rbRoom3 = new JRadioButton("1번방");
		rbRoom4 = new JRadioButton("1번방");


		JPanel type = new JPanel();
		type.setBounds(0, 0, 500, 50);
		type.add(rb1);
		type.add(rb2);
		type.add(rb3);
		type.add(rb4);

		roomLogo = new JPanel();
		roomLogo.setLayout(null);
		roomLogo.setBounds(0, 50, 500, 750);
		roomLogo.add(logo);

		room1 = new JPanel();
		room2 = new JPanel();
		room3 = new JPanel();
		room4 = new JPanel();
		room1.setBounds(0, 50, 500, 50);
		room2.setBounds(0, 50, 500, 50);
		room3.setBounds(0, 50, 500, 50);
		room4.setBounds(0, 50, 500, 50);

		for (int i = 0; i < rbRoom1.length; i++) {
			room1.add(rbRoom1[i]);
		} // end for

		for (int i = 0; i < rbRoom2.length; i++) {
			room2.add(rbRoom2[i]);
		} // end for

		room3.add(rbRoom3);
		room4.add(rbRoom4);

		roomInfo = new JPanel();
		roomInfo.setVisible(false);
		roomInfo.setLayout(null);
		roomInfo.setBounds(0, 100, 500, 400);
		roomInfo.setBackground(Color.LIGHT_GRAY);
		roomInfo.add(lblImg);
		roomInfo.add(lblInfo);
		roomInfo.add(jtaInfo);
		roomInfo.add(lblTime);
		roomInfo.add(lblRTime);
		roomInfo.add(lblCnt);
		roomInfo.add(lblRCnt);


		lblImg.setBounds(10, 10, 200, 380);

		lblInfo.setBounds(230, 10, 60, 30);
		jtaInfo.setBounds(300, 10, 170, 100);

		lblTime.setBounds(230, 120, 60, 30);
		lblRTime.setBounds(300, 120, 100, 30);

		lblCnt.setBounds(230, 160, 60, 30);
		lblRCnt.setBounds(300, 160, 100, 30);

		logo.setBounds(0, 0, 500, 750);

		add(type);
		add(roomLogo);
		add(room1);
		add(room2);
		add(room3);
		add(room4);
		add(roomInfo);		
		
		RoomInfoFrmEvt rife = new RoomInfoFrmEvt(this);
		rb1.addItemListener(rife);
		rb2.addItemListener(rife);
		rb3.addItemListener(rife);
		rb4.addItemListener(rife);
		for(int i =0; i < rbRoom1.length;i++) {
			rbRoom1[i].addItemListener(rife);			
		}//end for
		for(int i =0; i < rbRoom2.length;i++) {
			rbRoom2[i].addItemListener(rife);			
		}//end for
		rbRoom3.addItemListener(rife);			
		rbRoom4.addItemListener(rife);			
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

	public ButtonGroup getGroupType() {
		return groupType;
	}

	public JLabel getLblInfo() {
		return lblInfo;
	}

	public JLabel getLblTime() {
		return lblTime;
	}

	public JLabel getLblCnt() {
		return lblCnt;
	}

	public JLabel getLblRTime() {
		return lblRTime;
	}

	public JLabel getLblRCnt() {
		return lblRCnt;
	}

	public List<String> getEquipment() {
		return equipment;
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

	public static void main(String[] args) {
		new RoomInfoFrm();
	}// main

}// class
