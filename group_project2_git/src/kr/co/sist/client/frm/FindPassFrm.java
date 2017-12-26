package kr.co.sist.client.frm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.client.controller.FindPassFrmEvt;

public class FindPassFrm extends JDialog {

	private LoginFrm lf;
	private JTextField jtfId, jtBirth, jtfPassHint, jtfPassAns;
	private JComboBox<String> jcbPassHint;
	private DefaultComboBoxModel<String> dcbm;
	private JButton btnFindPass;
	private JLabel lblId, lblBirth, lblPassHint, lblPassAns;

	public FindPassFrm(LoginFrm lf) { // 매개변수에 LoginFrm lf
		super(lf, "비밀번호 찾기", true); // 부모창이 lf이고 자식창이뜨면 부모창이 안뜨게 modal로 설정
		this.lf = lf;

		jtfId = new JTextField();
		jtBirth = new JTextField();
		jtfPassHint = new JTextField();
		jtfPassAns = new JTextField();

		dcbm = new DefaultComboBoxModel<>(new String[] {});
		jcbPassHint = new JComboBox<>(dcbm);
		jcbPassHint.setModel(dcbm);

		btnFindPass = new JButton("비밀번호 찾기");
		lblId = new JLabel("아이디");
		lblBirth = new JLabel("생년월일");
		lblPassHint = new JLabel("비밀번호 힌트");
		lblPassAns = new JLabel("비밀번호 힌트 답");

		setLayout(null);

		lblId.setBounds(30, 30, 80, 15);
		jtfId.setBounds(180, 25, 100, 25);

		lblBirth.setBounds(30, 80, 80, 15);
		jtBirth.setBounds(180, 75, 100, 25);

		lblPassHint.setBounds(30, 130, 100, 15);
		jcbPassHint.setBounds(180, 125, 200, 25);

		lblPassAns.setBounds(30, 180, 100, 25);
		jtfPassAns.setBounds(180, 180, 140, 25);

		btnFindPass.setBounds(130, 230, 130, 30);

		// 이벤트 추가
		FindPassFrmEvt fpfe = new FindPassFrmEvt(this);
		btnFindPass.addActionListener(fpfe);
		jtfPassAns.addActionListener(fpfe);

		add(jtfId);
		add(jtBirth);
		add(jtfPassHint);
		add(jtfPassAns);
		add(btnFindPass);
		add(jcbPassHint);
		add(lblId);
		add(lblBirth);
		add(lblPassHint);
		add(lblPassAns);

		setBounds(420, 100, 420, 320);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}// FindPassFrm
	

	public LoginFrm getLf() {
		return lf;
	}


	public void setLf(LoginFrm lf) {
		this.lf = lf;
	}


	public JTextField getJtfId() {
		return jtfId;
	}


	public void setJtfId(JTextField jtfId) {
		this.jtfId = jtfId;
	}


	public JTextField getJtBirth() {
		return jtBirth;
	}


	public void setJtBirth(JTextField jtBirth) {
		this.jtBirth = jtBirth;
	}


	public JTextField getJtfPassHint() {
		return jtfPassHint;
	}


	public void setJtfPassHint(JTextField jtfPassHint) {
		this.jtfPassHint = jtfPassHint;
	}


	public JTextField getJtfPassAns() {
		return jtfPassAns;
	}


	public void setJtfPassAns(JTextField jtfPassAns) {
		this.jtfPassAns = jtfPassAns;
	}


	public JComboBox<String> getJcbPassHint() {
		return jcbPassHint;
	}


	public void setJcbPassHint(JComboBox<String> jcbPassHint) {
		this.jcbPassHint = jcbPassHint;
	}


	public DefaultComboBoxModel<String> getDcbm() {
		return dcbm;
	}


	public void setDcbm(DefaultComboBoxModel<String> dcbm) {
		this.dcbm = dcbm;
	}


	public JButton getBtnFindPass() {
		return btnFindPass;
	}


	public void setBtnFindPass(JButton btnFindPass) {
		this.btnFindPass = btnFindPass;
	}


	public JLabel getLblId() {
		return lblId;
	}


	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}


	public JLabel getLblBirth() {
		return lblBirth;
	}


	public void setLblBirth(JLabel lblBirth) {
		this.lblBirth = lblBirth;
	}


	public JLabel getLblPassHint() {
		return lblPassHint;
	}


	public void setLblPassHint(JLabel lblPassHint) {
		this.lblPassHint = lblPassHint;
	}


	public JLabel getLblPassAns() {
		return lblPassAns;
	}


	public void setLblPassAns(JLabel lblPassAns) {
		this.lblPassAns = lblPassAns;
	}


	public static void main(String[] args) {
		/*
		 * LoginFrm lf = new LoginFrm(); FindPassFrm lef = new FindPassFrm(lf);
		 */
	}// main
}// class
