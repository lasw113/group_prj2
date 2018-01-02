package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.client.controller.MyInfoEvt;

@SuppressWarnings("serial")
public class MyInfoFrm extends JPanel{
	
	private JLabel lblId, lblPass, lblChkPass, lblHintPass, lblHintPassAns, 
	lblName, lblBirth, lblEmail,lblPhone,lblMileage,lblPassGuide,lblChar;
	private JTextField jtfId,jtfName,jtfAnsPass,
	jtfBirth,jtfEmail,jtfPhoneM,jtfPhoneL,jtfMileage;
	private JPasswordField jtfPass,jtfChkPass;
	private JComboBox<String> jcbPhoneF,jdbPassHint;
	private DefaultComboBoxModel<String> dcbPhoneF,dcbPassHint;
	private JButton btnModify, btnDropOut;
	
	public MyInfoFrm() {
		
		jtfId = new JTextField();
		jtfId.setEnabled(false);
		jtfPass = new JPasswordField();
		jtfPass.setEchoChar('*');
		jtfChkPass = new JPasswordField();
		jtfChkPass.setEchoChar('*');
		jtfAnsPass = new JTextField();
		jtfName = new JTextField();
		jtfBirth = new JTextField();
		jtfEmail = new JTextField();
		jtfPhoneM = new JTextField();
		jtfPhoneL = new JTextField();
		jtfMileage = new JTextField();

		jcbPhoneF = new JComboBox<>();
		dcbPhoneF = new DefaultComboBoxModel<>(new String[] {});
		jcbPhoneF.setModel(dcbPhoneF);
		
		jdbPassHint = new JComboBox<>();
		dcbPassHint = new DefaultComboBoxModel<>(new String[] {});
		jdbPassHint.setModel(dcbPassHint);

		String path = System.getProperty("user.dir");
		btnModify = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/수정하기.png"));
		btnDropOut = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/탈퇴하기.png"));
		
		lblId = new JLabel("아이디");
		lblPass = new JLabel("비밀번호");
		lblChkPass = new JLabel("비밀번호 확인");
		lblHintPass = new JLabel("비밀번호 힌트");
		lblHintPassAns = new JLabel("비밀번호 힌트 답");
		lblName = new JLabel("이름");
		lblBirth = new JLabel("생년월일");
		lblEmail = new JLabel("이메일");
		lblPhone = new JLabel("핸드폰");
		lblMileage=new JLabel("마일리지");
		lblPassGuide=new JLabel("*비밀번호 수정시에는 비밀번호만 입력해주세요");
		lblPassGuide.setForeground(Color.RED);
		lblChar=new JLabel(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/MyInfoFrm.png"));
		
		
		JLabel jl1=new JLabel("-");
		JLabel jl2=new JLabel("-");
		

		setLayout(null);
		
		lblId.setBounds(130, 50, 80, 15); 
		jtfId.setBounds(250, 45, 100, 25);

		
		lblName.setBounds(130, 100, 80, 15);
		jtfName.setBounds(250, 95, 80, 25);
		
		lblMileage.setBounds(520, 100, 80, 15);
		jtfMileage.setBounds(640, 95, 80, 25);
		
		lblPass.setBounds(130, 150, 150, 15);
		lblPassGuide.setBounds(130, 178, 280, 15);
		jtfPass.setBounds(250, 145, 145, 25);
		
		lblChkPass.setBounds(520, 150, 90, 15);
		jtfChkPass.setBounds(640, 145, 100, 25);
		
		lblHintPass.setBounds(130, 200, 220, 15);
		jdbPassHint.setBounds(250, 195, 200, 25);
		
		lblHintPassAns.setBounds(520, 200, 110, 15);
		jtfAnsPass.setBounds(640, 195, 160, 25);
		
		lblBirth.setBounds(130, 250, 80, 15);
		jtfBirth.setBounds(250, 245, 100, 25);
		
		lblPhone.setBounds(130, 300, 50, 15);
		jcbPhoneF.setBounds(250, 295, 60, 25);
		jl1.setBounds(320, 300, 20, 10);
		jtfPhoneM.setBounds(335, 295, 60, 25);
		jl2.setBounds(405, 300, 20, 10);
		jtfPhoneL.setBounds(420, 295, 60, 25);
		
		
		lblEmail.setBounds(130,350,80,15);
		jtfEmail.setBounds(250,345, 140, 25);
		
		lblChar.setBounds(650, 250, 250, 300);
		
		btnModify.setBounds(320, 460, 100, 30);
		btnDropOut.setBounds(520, 460, 100, 30);
		MyInfoEvt mife=new MyInfoEvt(this);
		btnModify.addActionListener(mife);
		btnDropOut.addActionListener(mife);
		
		
		add(lblId);
		add(lblPass);
		add(lblChkPass);
		add(lblPassGuide);
		add(lblHintPass);
		add(lblHintPassAns);
		add(lblName);
		add(lblBirth);
		add(lblEmail);
		add(lblPhone);
		add(lblMileage);
		add(lblChar);
		
		
		add(jtfId);
		add(jtfPass);
		add(jtfChkPass);
		add(jtfAnsPass); 
		add(jtfName);
		add(jtfBirth);
		add(jtfEmail);
		add(jtfPhoneM);
		add(jtfPhoneL);
		add(jtfMileage);
		
		add(jcbPhoneF);
		add(jdbPassHint);
		
		
		add(btnModify);
		add(btnDropOut);
		
		add(jl1);
		add(jl2);
		
		setBackground(Color.WHITE);
		
		setBounds(100, 100, 750, 550);
		//setResizable(false);
		setVisible(true);
		
		
	}//MyInfoFrm

	public DefaultComboBoxModel<String> getDcbPassHint() {
		return dcbPassHint;
	}

	public void setDcbPassHint(DefaultComboBoxModel<String> dcbPassHint) {
		this.dcbPassHint = dcbPassHint;
	}

	public JLabel getLblMileage() {
		return lblMileage;
	}

	public void setLblMileage(JLabel lblMileage) {
		this.lblMileage = lblMileage;
	}

	public JTextField getJtfMileage() {
		return jtfMileage;
	}

	public void setJtfMileage(JTextField jtfMileage) {
		this.jtfMileage = jtfMileage;
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

	public JTextField getJtfName() {
		return jtfName;
	}

	public void setJtfName(JTextField jtfName) {
		this.jtfName = jtfName;
	}

	public JTextField getJtfAnsPass() {
		return jtfAnsPass;
	}

	public void setJtfAnsPass(JTextField jtfAnsPass) {
		this.jtfAnsPass = jtfAnsPass;
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

	public JComboBox<String> getJdbPassHint() {
		return jdbPassHint;
	}

	public void setJdbPassHint(JComboBox<String> jdbPassHint) {
		this.jdbPassHint = jdbPassHint;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDropOut() {
		return btnDropOut;
	}

	public void setBtnDropOut(JButton btnDropOut) {
		this.btnDropOut = btnDropOut;
	}

	public DefaultComboBoxModel<String> getDcbPhoneF() {
		return dcbPhoneF;
	}

	public void setDcbPhoneF(DefaultComboBoxModel<String> dcbPhoneF) {
		this.dcbPhoneF = dcbPhoneF;
	}
	
	
	/*public static void main(String[] args) {
		new MyInfoFrm();
	}//main
*/
}//class
