package kr.co.sist.client.frm;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import kr.co.sist.client.controller.CancelEvt;
import kr.co.sist.client.controller.ResChkFrmEvt;

public class CancelFrm extends JFrame {

	private ResChkFrm rcf;
	private ResChkFrmEvt rcfe;
	private JLabel lblpass;
	private JPasswordField jpwPass;
	private JButton btnOk;
	private String id, res_id, pass;

	public CancelFrm(ResChkFrm rcf, ResChkFrmEvt rcfe, String id, String res_id, String pass) throws IOException {
		this.rcf = rcf;// ?
		this.id = id;
		this.pass = pass;
		this.res_id = res_id;
		this.rcfe = rcfe;
		lblpass = new JLabel("비밀번호");
		jpwPass = new JPasswordField();
		jpwPass.setEchoChar('*');
		btnOk = new JButton("확인");

		setLayout(null);
		add(lblpass);
		lblpass.setBounds(40, 80, 100, 30);
		add(jpwPass);
		jpwPass.setBounds(180, 80, 155, 30);
		add(btnOk);
		btnOk.setBounds(120, 200, 130, 40);
		setBounds(350, 250, 400, 300);
		setVisible(true);

		CancelEvt ce = new CancelEvt(this, rcf);
		btnOk.addActionListener(ce);
		jpwPass.addActionListener(ce);

	}// CancelFrm

	public ResChkFrmEvt getRcfe() {
		return rcfe;
	}

	public void setRcfe(ResChkFrmEvt rcfe) {
		this.rcfe = rcfe;
	}

	public ResChkFrm getRcf() {
		return rcf;
	}

	public JLabel getLblpass() {
		return lblpass;
	}

	public JPasswordField getJpwPass() {
		return jpwPass;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setJpwPass(JPasswordField jpwPass) {
		this.jpwPass = jpwPass;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public String getId() {
		return id;
	}

	public String getRes_id() {
		return res_id;
	}

	public String getPass() {
		return pass;
	}

}// class
