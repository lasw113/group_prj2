package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import kr.co.sist.client.controller.ClientMainFrmEvt;

@SuppressWarnings("serial")
public class ClientMainFrm extends JFrame {

	private JTabbedPane jtpClient;
	private String id, pass;
	private boolean adminLoginStatus;

	public ClientMainFrm(String id, String pass) {
		super("주) 스터디 룸 관리 - 회원");
		this.id = id;
		this.pass = pass;

		this.getContentPane().setBackground(Color.white);

		jtpClient = new JTabbedPane();
		jtpClient.setBackground(Color.WHITE);

		add("Center", jtpClient);

		ClientMainFrmEvt cmve = new ClientMainFrmEvt(this);
		jtpClient.addMouseListener(cmve); // JTab 이벤트

		setBounds(100, 100, 1000, 650);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// ClientMainFrm

	public JTabbedPane getJtpClient() {
		return jtpClient;
	}

	public void setJtpClient(JTabbedPane jtpClient) {
		this.jtpClient = jtpClient;
	}

	public String getId() {
		return id;
	}

	public boolean isAdminLoginStatus() {
		return adminLoginStatus;
	}

	public void setAdminLoginStatus(boolean adminLoginStatus) {
		this.adminLoginStatus = adminLoginStatus;
	}

	public String getPass() {
		return pass;
	}

}// class
