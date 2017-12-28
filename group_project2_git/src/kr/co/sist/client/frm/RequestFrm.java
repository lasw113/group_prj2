package kr.co.sist.client.frm;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.client.controller.ClientMainFrmEvt;
import kr.co.sist.client.controller.RequestFrmEvt;

@SuppressWarnings("serial")
public class RequestFrm extends JPanel {
	private JPanel jpbottom;
	private JTextArea jtaChat;
	private JButton btnSent;
	private JTextField jtfMessage;
	private String id, room_id;
	private ClientMainFrmEvt cmve;
	private JLabel lblRoom_id;
	private boolean flagMgrIn;

	public RequestFrm(ClientMainFrmEvt cmve) {
		this.cmve = cmve;
		id = cmve.getId();
		room_id = cmve.getRoom_id();

		jpbottom = new JPanel();
		jtfMessage = new JTextField();
		btnSent = new JButton(" Àü¼Û ");
		jtaChat = new JTextArea();
		JScrollPane jspChat = new JScrollPane(jtaChat);
		lblRoom_id = new JLabel(room_id);

		setLayout(null);

		jpbottom.setBackground(Color.white);
		jpbottom.setLayout(null);

		jspChat.setBounds(70, 50, 830, 410);

		lblRoom_id.setBounds(10, 30, 100, 30);
		jtfMessage.setBounds(70, 30, 600, 40);
		btnSent.setBounds(680, 30, 115, 40);
		jpbottom.setBounds(70, 470, 830, 100);

		jpbottom.add(lblRoom_id);
		jpbottom.add(jtfMessage);
		jpbottom.add(btnSent);
		
		add(jspChat);
		add(jpbottom);

		setBackground(Color.white);
		lblRoom_id.setFont(new Font("Dialog", Font.BOLD, 15));
		jtaChat.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSent.setFont(new Font("Dialog", Font.BOLD, 20));

		RequestFrmEvt rfe = new RequestFrmEvt(this);
		btnSent.addActionListener(rfe);
	}// RequestFrm

	public JPanel getJpbottom() {
		return jpbottom;
	}

	public void setJpbottom(JPanel jpbottom) {
		this.jpbottom = jpbottom;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public ClientMainFrmEvt getCmve() {
		return cmve;
	}

	public void setCmve(ClientMainFrmEvt cmve) {
		this.cmve = cmve;
	}

	public JLabel getLblRoom_id() {
		return lblRoom_id;
	}

	public void setLblRoom_id(JLabel lblRoom_id) {
		this.lblRoom_id = lblRoom_id;
	}

	public boolean isFlagMgrIn() {
		return flagMgrIn;
	}

	public void setFlagMgrIn(boolean flagMgrIn) {
		this.flagMgrIn = flagMgrIn;
	}
}// class
