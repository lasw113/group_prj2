package kr.co.sist.manager.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.manager.controller.LoginMgrViewEvt;

@SuppressWarnings("serial")
public class LoginMgrView extends JFrame {

	private JTextField jtfId;
	private JPasswordField jtfPass;
	private JButton btnLogin;
	private JLabel lblId, lblPass;

	public LoginMgrView() {
		super("스터디룸 관리자 로그인");

		jtfId = new JTextField();
		jtfPass = new JPasswordField();
		jtfPass.setEchoChar('*');
		String path = System.getProperty("user.dir");
		btnLogin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/로그인 그라데이션.png"));
		lblId = new JLabel("아이디", JLabel.CENTER);
		lblPass = new JLabel("비밀번호", JLabel.CENTER);

		setLayout(null);

		JPanel backImg = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "managerLongin.png").getImage(), 0, 0, null);
			}
		};
		backImg.setLayout(null);
		backImg.setBounds(0, 0, 310, 200);
		
		btnLogin.setBounds(85, 120, 120, 30);
		lblId.setBounds(40, 35, 80, 15);
		jtfId.setBounds(130, 30, 100, 25);
		lblPass.setBounds(35, 75, 80, 15);
		jtfPass.setBounds(130, 70, 100, 25);

		// 이벤트 추가
		LoginMgrViewEvt lmve = new LoginMgrViewEvt(this);

		btnLogin.addActionListener(lmve);
		jtfId.addActionListener(lmve);
		jtfPass.addActionListener(lmve);

		backImg.add(btnLogin);
		backImg.add(lblId);
		backImg.add(lblPass);
		backImg.add(jtfId);
		backImg.add(jtfPass);
		
		
		add(backImg);

		setBounds(100, 100, 310, 200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// LoginMgrView

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJtfPass() {
		return jtfPass;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

}// class
