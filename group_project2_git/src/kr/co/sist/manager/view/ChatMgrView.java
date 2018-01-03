package kr.co.sist.manager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.manager.controller.ChatMgrViewEvt;

@SuppressWarnings("serial")
public class ChatMgrView extends JDialog {
	private JTextArea jtaChat;
	private JButton btnSent;
	private JTextField jtfMessage;
	private JLabel lblRoom_id,lblImage;
	private static ManagerView mv;
	private ReqMgrView rmv;
	private String room_id;
	private int p_num;

	public ChatMgrView(String room_id, int ss, ReqMgrView rmv) {
		super(mv, "건의사항 - 관리자");
		this.room_id = room_id;
		this.rmv = rmv;
		p_num = ss;
		System.out.println(room_id);

		jtfMessage = new JTextField();
		lblRoom_id = new JLabel("방이름");
		
		lblImage = new JLabel(new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/mike1.png"));
		btnSent = new JButton(new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/send.png"));
		jtaChat = new JTextArea();
		JScrollPane jspChat = new JScrollPane(jtaChat);

		setLayout(null);

		btnSent.setBorderPainted(false);// 외곽선 제거
		btnSent.setContentAreaFilled(false); // 내용영역 채우기 x
		lblRoom_id.setText(room_id);

		jspChat.setBounds(250, 65, 650, 450);
		jtaChat.setEditable(false);

		lblImage.setBounds(22, 166, 238, 342);
		lblRoom_id.setBounds(205, 550, 100, 40);
		jtfMessage.setBounds(320, 550, 470, 40);
		btnSent.setBounds(810, 550, 120, 40);

		add(jspChat);
		add(lblImage);
		add(lblRoom_id);
		add(jtfMessage);
		add(btnSent);

		jtfMessage.requestFocus();
		this.getContentPane().setBackground( Color.white );

		jtaChat.setFont(new Font("Dialog", Font.BOLD, 15));
		jtfMessage.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSent.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRoom_id.setFont(new Font("Dialog", Font.BOLD, 20));
		jtaChat.setText(room_id + " 에서 보내온 메세지입니다. \n");
		ChatMgrViewEvt cmve = new ChatMgrViewEvt(this, rmv);
		btnSent.addActionListener(cmve);
		jtfMessage.addActionListener(cmve);
		setBounds(100, 100, 1000, 650);
		
		// setVisible(true);
	}// ChatMgrView

	public JTextArea getJtaChat() {
		return jtaChat;
	}

	public void setJtaChat(JTextArea jtaChat) {
		this.jtaChat = jtaChat;
	}

	public JButton getBtnSent() {
		return btnSent;
	}

	public void setBtnSent(JButton btnSent) {
		this.btnSent = btnSent;
	}

	public JTextField getJtfMessage() {
		return jtfMessage;
	}

	public void setJtfMessage(JTextField jtfMessage) {
		this.jtfMessage = jtfMessage;
	}

	public JLabel getLblRoom_id() {
		return lblRoom_id;
	}

	public void setLblRoom_id(JLabel lblRoom_id) {
		this.lblRoom_id = lblRoom_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
}// class
