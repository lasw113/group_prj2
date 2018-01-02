package kr.co.sist.client.frm;

import java.awt.Color;

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
	private JLabel lblId,lblPass;

	public LoginFrm() {
		
		super("스터디룸 사용자 로그인");

		jtfId = new JTextField();
		jtfPass = new JPasswordField();
		jtfPass.setEchoChar('*');
		//btnLogin = new JButton("로그인");
		String path = System.getProperty("user.dir");
		btnLogin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/로그인 그라데이션.png"));
		btnJoin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/LoginFrm-회원가입.png"));
		btnFindId=new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/아이디찾기.png"));
		btnFindPass=new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/비밀번호 찾기.png"));
		lblId = new JLabel("아이디", JLabel.CENTER);
		lblPass = new JLabel("비밀번호", JLabel.CENTER);
		
		//btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setFocusPainted(false);
		
		btnJoin.setContentAreaFilled(false);
		btnJoin.setFocusPainted(false);
		
		setLayout(null);
		
		btnLogin.setBounds(85, 120, 120, 30);
		btnJoin.setBounds(85, 180, 120, 30);
		btnFindId.setBounds(85, 240, 120, 30);
		btnFindPass.setBounds(85, 300, 120, 30);
		lblId.setBounds(40, 35, 80, 15);
		jtfId.setBounds(130, 30, 100, 25);
		lblPass.setBounds(35, 75, 80, 15);
		jtfPass.setBounds(130, 70, 100, 25);
		
		//이벤트 추가
		LoginFrmEvt lfe=new LoginFrmEvt(this);
		
		btnLogin.addActionListener(lfe);
		btnJoin.addActionListener(lfe);
		btnFindId.addActionListener(lfe);
		btnFindPass.addActionListener(lfe);
		jtfId.addActionListener(lfe);
		jtfPass.addActionListener(lfe);
		
		add(btnLogin);
		add(btnJoin);
		add(btnFindId);
		add(btnFindPass);
		add(lblId);
		add(lblPass);
		add(jtfId);
		add(jtfPass);
		
		this.getContentPane().setBackground(Color.WHITE);
		
		setBounds(100, 100, 310, 400);
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
