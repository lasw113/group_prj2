package kr.co.sist.client.frm;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
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
	private JTextArea jtaChat;
	private JButton btnSent;
	private JTextField jtfMessage;
	private String id, room_id;
	private ClientMainFrmEvt cmve;
	private JLabel lblRoom_id;
	private JLabel lblImage;
	private boolean flagMgrIn;

	public RequestFrm(ClientMainFrmEvt cmve) {
		this.cmve = cmve;
		id = cmve.getId();
		room_id = cmve.getRoom_id();
		jtfMessage = new JTextField();
		String path = System.getProperty("user.dir");	
		btnSent = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/send.png"));
		jtaChat = new JTextArea("<<관리자에게 전달할 말을 적어주세요>>\n");
		JScrollPane jspChat = new JScrollPane(jtaChat);
		lblRoom_id = new JLabel(room_id);
		lblImage= new JLabel(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/both1.png"));

		setLayout(null);
		
		btnSent.setBorderPainted(false);// 외곽선 제거
		btnSent.setContentAreaFilled(false); // 내용영역 채우기 x
		jtaChat.setEditable(false);

		jspChat.setBounds(240, 50, 690, 400);

		lblImage.setBounds(0, 130, 250, 330);
		lblRoom_id.setBounds(200, 500, 100, 40);
		jtfMessage.setBounds(320, 500, 470, 40);
		btnSent.setBounds(820, 500, 120, 40);

		add(lblRoom_id);
		add(lblImage);
		add(jtfMessage);
		add(btnSent);
		add(jspChat);

		setBackground(Color.white);
		lblRoom_id.setFont(new Font("Dialog", Font.BOLD, 20));
		jtaChat.setFont(new Font("Dialog", Font.BOLD, 15));
		jtfMessage.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSent.setFont(new Font("Dialog", Font.BOLD, 20));

		RequestFrmEvt rfe = new RequestFrmEvt(this);
		btnSent.addActionListener(rfe);
		jtfMessage.addActionListener(rfe);
	}// RequestFrm

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
