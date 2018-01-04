package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.client.controller.JoinFrmEvt;

@SuppressWarnings("serial")
public class JoinFrm extends JDialog {

	private LoginFrm lf;
	private JLabel lblId, lblPass, lblChkPass, lblHintPass, lblHintPassAns, lblName, lblBirth, lblEmail, lblPhone,
			lblIdGuide,lblBirthGuide;
	private JTextField jtfId, jtfHintAns, jtfName, jtfBirth, jtfEmail, jtfPhoneM, jtfPhoneL;
	private JPasswordField jtfPass, jtfChkPass;
	private JComboBox<String> jcbPhoneF, jcbPassHint;
	private JButton btnDuplicate, btnJoin, btnCancel;
	private DefaultComboBoxModel<String> dcbPhone, dcbPassHint;

	public JoinFrm(LoginFrm lf) {
		super(lf, "회원가입", true);
		this.lf = lf;

		jtfId = new JTextField();
		jtfPass = new JPasswordField();
		jtfPass.setEchoChar('*');
		jtfChkPass = new JPasswordField();
		jtfChkPass.setEchoChar('*');
		jtfHintAns = new JTextField();
		jtfName = new JTextField();
		jtfBirth = new JTextField();
		jtfEmail = new JTextField();
		jtfPhoneM = new JTextField();
		jtfPhoneL = new JTextField();

		jcbPhoneF = new JComboBox<>();
		dcbPhone = new DefaultComboBoxModel<>(new String[] { "010", "011", "016", "017" });
		jcbPhoneF.setModel(dcbPhone);

		jcbPassHint = new JComboBox<>();
		dcbPassHint = new DefaultComboBoxModel<>(new String[] {});
		jcbPassHint.setModel(dcbPassHint);

		String path = System.getProperty("user.dir");
		btnDuplicate = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/중복체크.png"));
		btnJoin = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/JoinFrm-가입.png"));
		btnCancel = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/JoinFrm-취소.png"));

		lblId = new JLabel("아이디");
		lblPass = new JLabel("비밀번호");
		lblChkPass = new JLabel("비밀번호 확인");
		lblHintPass = new JLabel("비밀번호 힌트");
		lblHintPassAns = new JLabel("비밀번호 힌트 답");
		lblName = new JLabel("이름");
		lblBirth = new JLabel("생년월일");
		lblEmail = new JLabel("이메일");
		lblPhone = new JLabel("핸드폰");
		lblIdGuide = new JLabel("*아이디는 4자 이상의 영문자,숫자만 사용 가능합니다.");
		lblIdGuide.setForeground(Color.RED);
		lblBirthGuide = new JLabel("*생년월일은 주민번호 앞자리를 입력해주세요");
		lblBirthGuide.setForeground(Color.RED);
		
		JLabel jl1 = new JLabel("-");
		JLabel jl2 = new JLabel("-");

		setLayout(null);

		lblId.setBounds(30, 30, 80, 15);
		lblIdGuide.setBounds(30, 55, 320, 15);
		jtfId.setBounds(150, 25, 100, 25);

		lblPass.setBounds(30, 80, 80, 15);
		jtfPass.setBounds(150, 75, 100, 25);

		lblChkPass.setBounds(30, 130, 90, 15);
		jtfChkPass.setBounds(150, 125, 100, 25);

		lblHintPass.setBounds(30, 180, 220, 15);
		jcbPassHint.setBounds(150, 175, 200, 25);

		lblHintPassAns.setBounds(30, 230, 110, 15);
		jtfHintAns.setBounds(150, 225, 160, 25);

		lblName.setBounds(30, 280, 80, 15);
		jtfName.setBounds(150, 275, 80, 25);

		lblBirth.setBounds(30, 330, 80, 15);
		jtfBirth.setBounds(150, 325, 100, 25);
		lblBirthGuide.setBounds(30, 355, 280, 15);

		lblEmail.setBounds(30, 380, 80, 15);
		jtfEmail.setBounds(150, 375, 140, 25);

		lblPhone.setBounds(30, 430, 50, 15);
		jcbPhoneF.setBounds(110, 425, 60, 25);
		jl1.setBounds(180, 430, 20, 10);
		jtfPhoneM.setBounds(195, 425, 60, 25);
		jl2.setBounds(265, 430, 20, 10);
		jtfPhoneL.setBounds(280, 425, 60, 25);

		btnDuplicate.setBounds(270, 20, 90, 30);
		btnJoin.setBounds(80, 480, 100, 30);
		btnCancel.setBounds(220, 480, 100, 30);

		 //이벤트 추가
		JoinFrmEvt jfe=new JoinFrmEvt(this);
		btnDuplicate.addActionListener(jfe);
		btnCancel.addActionListener(jfe);
		btnJoin.addActionListener(jfe);
		
		add(lblId);
		add(lblPass);
		add(lblChkPass);
		add(lblHintPass);
		add(lblHintPassAns);
		add(lblName);
		add(lblBirth);
		add(lblEmail);
		add(lblPhone);
		add(lblIdGuide);
		add(lblBirthGuide);

		add(jtfId);
		add(jtfPass);
		add(jtfChkPass);
		add(jtfHintAns);
		add(jtfName);
		add(jtfBirth);
		add(jtfEmail);
		add(jtfPhoneM);
		add(jtfPhoneL);

		add(jcbPhoneF);
		add(jcbPassHint);

		add(btnDuplicate);
		add(btnCancel);
		add(btnJoin);

		add(jl1);
		add(jl2);
		
		this.getContentPane().setBackground(Color.WHITE);

		setBounds(420, 100, 400, 580);
		// setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}// JoinFrm

	public LoginFrm getLf() {
		return lf;
	}

	public void setLf(LoginFrm lf) {
		this.lf = lf;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public JLabel getLblPass() {
		return lblPass;
	}

	public void setLblPass(JLabel lblPass) {
		this.lblPass = lblPass;
	}

	public JLabel getLblChkPass() {
		return lblChkPass;
	}

	public void setLblChkPass(JLabel lblChkPass) {
		this.lblChkPass = lblChkPass;
	}

	public JLabel getLblHintPass() {
		return lblHintPass;
	}

	public void setLblHintPass(JLabel lblHintPass) {
		this.lblHintPass = lblHintPass;
	}

	public JLabel getLblHintPassAns() {
		return lblHintPassAns;
	}

	public void setLblHintPassAns(JLabel lblHintPassAns) {
		this.lblHintPassAns = lblHintPassAns;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JLabel getLblBirth() {
		return lblBirth;
	}

	public void setLblBirth(JLabel lblBirth) {
		this.lblBirth = lblBirth;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JLabel getLblPhone() {
		return lblPhone;
	}

	public void setLblPhone(JLabel lblPhone) {
		this.lblPhone = lblPhone;
	}

	

	public JTextField getJtfId() {
		return jtfId;
	}

	public void setJtfId(JTextField jtfId) {
		this.jtfId = jtfId;
	}

	public JTextField getJtfHintAns() {
		return jtfHintAns;
	}

	public void setJtfHintAns(JTextField jtfHintAns) {
		this.jtfHintAns = jtfHintAns;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public void setJtfName(JTextField jtfName) {
		this.jtfName = jtfName;
	}

	public JTextField getJtfBirth() {
		return jtfBirth;
	}

	public void setJtfBirth(JTextField jtfBirth) {
		this.jtfBirth = jtfBirth;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public void setJtfEmail(JTextField jtfEmail) {
		this.jtfEmail = jtfEmail;
	}

	public JTextField getJtfPhoneM() {
		return jtfPhoneM;
	}

	public void setJtfPhoneM(JTextField jtfPhoneM) {
		this.jtfPhoneM = jtfPhoneM;
	}

	public JTextField getJtfPhoneL() {
		return jtfPhoneL;
	}

	public void setJtfPhoneL(JTextField jtfPhoneL) {
		this.jtfPhoneL = jtfPhoneL;
	}

	public JPasswordField getJtfPass() {
		return jtfPass;
	}

	public void setJtfPass(JPasswordField jtfPass) {
		this.jtfPass = jtfPass;
	}

	public JPasswordField getJtfChkPass() {
		return jtfChkPass;
	}

	public void setJtfChkPass(JPasswordField jtfChkPass) {
		this.jtfChkPass = jtfChkPass;
	}

	public JComboBox<String> getJcbPhoneF() {
		return jcbPhoneF;
	}

	public void setJcbPhoneF(JComboBox<String> jcbPhoneF) {
		this.jcbPhoneF = jcbPhoneF;
	}

	public JComboBox<String> getJcbPassHint() {
		return jcbPassHint;
	}

	public void setJdbPassHint(JComboBox<String> jcbPassHint) {
		this.jcbPassHint = jcbPassHint;
	}

	public JButton getBtnDuplicate() {
		return btnDuplicate;
	}

	public void setBtnDuplicate(JButton btnDuplicate) {
		this.btnDuplicate = btnDuplicate;
	}

	public JButton getBtnJoin() {
		return btnJoin;
	}

	public void setBtnJoin(JButton btnJoin) {
		this.btnJoin = btnJoin;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public DefaultComboBoxModel<String> getDcbPhone() {
		return dcbPhone;
	}

	public void setDcbPhone(DefaultComboBoxModel<String> dcbPhone) {
		this.dcbPhone = dcbPhone;
	}

	public DefaultComboBoxModel<String> getDcbPassHint() {
		return dcbPassHint;
	}

	public void setDcbPassHint(DefaultComboBoxModel<String> dcbPassHint) {
		this.dcbPassHint = dcbPassHint;
	}

	public static void main(String[] args) {
		/*
		 * LoginFrm lf = new LoginFrm(); JoinFrm jf = new JoinFrm(lf);
		 */
	}// main

}// class
