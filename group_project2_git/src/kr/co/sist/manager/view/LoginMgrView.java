package kr.co.sist.manager.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.manager.controller.LoginMgrViewEvt;

@SuppressWarnings("serial")
public class LoginMgrView extends JFrame {	
	
	private JTextField jtfId;
	private JPasswordField jtfPass;
	private JButton btnLogin;
	private JLabel lblId,lblPass;
	
	public LoginMgrView() {
		super("스터디룸 관리자 로그인");
		
		jtfId = new JTextField();
		jtfPass = new JPasswordField();
		jtfPass.setEchoChar('*');
		btnLogin = new JButton("로그인");
		lblId = new JLabel("아이디", JLabel.CENTER);
		lblPass = new JLabel("비밀번호", JLabel.CENTER);
	
		setLayout(null);
		
		btnLogin.setBounds(85, 120, 120, 30);
		lblId.setBounds(40, 35, 80, 15);
		jtfId.setBounds(130, 30, 100, 25);
		lblPass.setBounds(35, 75, 80, 15);
		jtfPass.setBounds(130, 70, 100, 25);
		
		//이벤트 추가
		LoginMgrViewEvt lmve=new LoginMgrViewEvt(this);
		
		btnLogin.addActionListener(lmve);
		jtfId.addActionListener(lmve);
		jtfPass.addActionListener(lmve);
		
		add(btnLogin);
		add(lblId);
		add(lblPass);
		add(jtfId);
		add(jtfPass);

		
		setBounds(100, 100, 310, 200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//LoginMgrView

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJtfPass() {
		return jtfPass;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

}//class
