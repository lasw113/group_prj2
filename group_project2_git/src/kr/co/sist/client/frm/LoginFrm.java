package kr.co.sist.client.frm;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.client.controller.LoginFrmEvt;


@SuppressWarnings("serial")
public class LoginFrm extends JFrame {

	private JTextField jtfId;
	private JPasswordField jtfPass;
	private JButton btnLogin,btnJoin,btnFindId,btnFindPass;
	private JLabel lblId,lblPass,lblLogo;

	public LoginFrm() {
		
		super("���͵�� ����� �α���");

		jtfId = new JTextField();
		jtfPass = new JPasswordField();
		jtfPass.setEchoChar('*');
		//btnLogin = new JButton("�α���");
//		String path = System.getProperty("user.dir");
//		btnLogin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/�α��� �׶��̼�.png"));
//		btnJoin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/ȸ������.png"));
//		btnFindId=new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/���̵� ã��.png"));
//		btnFindPass=new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/��й�ȣ ã��.png"));
		
		URL url1 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/�α��� �׶��̼�.png");
		URL url2 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/ȸ������.png");
		URL url3 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/���̵� ã��.png");
		URL url4 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/��й�ȣ ã��.png");
		URL url5 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/�׸�4.png");
		btnLogin = new JButton(new ImageIcon(url1));
		btnJoin = new JButton(new ImageIcon(url2));
		btnFindId=new JButton(new ImageIcon(url3));
		btnFindPass=new JButton(new ImageIcon(url4));
		
		lblLogo= new JLabel(new ImageIcon(url5));
		lblId = new JLabel("���̵�", JLabel.CENTER);
		lblPass = new JLabel("��й�ȣ", JLabel.CENTER);
		
		//btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setFocusPainted(false);
		
		btnJoin.setContentAreaFilled(false);
		btnJoin.setFocusPainted(false);
		
		setLayout(null);
		
		lblLogo.setBounds(50, 10, 210, 120);
		btnLogin.setBounds(85, 230, 120, 30);
		btnJoin.setBounds(85, 290, 120, 30);
		btnFindId.setBounds(85, 350, 120, 30);
		btnFindPass.setBounds(85, 410, 120, 30);
		lblId.setBounds(40, 145, 80, 15);
		jtfId.setBounds(130, 140, 100, 25);
		lblPass.setBounds(35, 185, 80, 15);
		jtfPass.setBounds(130, 180, 100, 25);
		
		
		//�̺�Ʈ �߰�
		LoginFrmEvt lfe=new LoginFrmEvt(this);
		
		btnLogin.addActionListener(lfe);
		btnJoin.addActionListener(lfe);
		btnFindId.addActionListener(lfe);
		btnFindPass.addActionListener(lfe);
		jtfId.addActionListener(lfe);
		jtfPass.addActionListener(lfe);
		
		add(lblLogo);
		add(btnLogin);
		add(btnJoin);
		add(btnFindId);
		add(btnFindPass);
		add(lblId);
		add(lblPass);
		add(jtfId);
		add(jtfPass);
		
		this.getContentPane().setBackground(Color.WHITE);
		
		setBounds(100, 100, 310, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// LoginFrm

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJtfPass() {
		return jtfPass;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnJoin() {
		return btnJoin;
	}

	public JButton getBtnFindId() {
		return btnFindId;
	}

	public JButton getBtnFindPass() {
		return btnFindPass;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public JLabel getLblPass() {
		return lblPass;
	}
	
	public static void main(String[] args) {
		new LoginFrm();
		
	}// main

}// class
