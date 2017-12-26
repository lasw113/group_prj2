package kr.co.sist.manager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.manager.controller.ChatMgrViewEvt;

@SuppressWarnings("serial")
public class ChatMgrView extends JDialog {
	private JTextArea jtaChat;
	private JButton btnSent;
	private JTextField jtfMessage;
	private JLabel lblRoom_id;
	private JPanel jpbottom;
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

		jpbottom = new JPanel();
		jtfMessage = new JTextField();
		lblRoom_id = new JLabel("방이름");
		btnSent = new JButton(" 전송 ");
		jtaChat = new JTextArea();
		JScrollPane jspChat = new JScrollPane(jtaChat);

		setLayout(null);

		lblRoom_id.setText(room_id);
		jpbottom.setBackground(Color.white);
		jpbottom.setLayout(null);

		jspChat.setBounds(70, 50, 830, 410);
		jtaChat.setEditable(false);

		lblRoom_id.setBounds(10, 30, 100, 30);
		jtfMessage.setBounds(140, 30, 500, 40);
		btnSent.setBounds(680, 30, 115, 40);
		jpbottom.setBounds(70, 470, 830, 100);

		jpbottom.add(lblRoom_id);
		jpbottom.add(jtfMessage);
		jpbottom.add(btnSent);

		add(jspChat);
		add(jpbottom);

		jtaChat.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSent.setFont(new Font("Dialog", Font.BOLD, 25));
		lblRoom_id.setFont(new Font("Dialog", Font.BOLD, 25));
		jtaChat.setText(room_id + " 에서 보내온 메세지가 없습니다. \n");
		ChatMgrViewEvt cmve = new ChatMgrViewEvt(this, rmv);
		btnSent.addActionListener(cmve);

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

	public JPanel getJpbottom() {
		return jpbottom;
	}

	public void setJpbottom(JPanel jpbottom) {
		this.jpbottom = jpbottom;
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
