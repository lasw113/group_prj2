package kr.co.sist.client.frm;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import kr.co.sist.client.controller.ChkPassEvt;

public class ChkPassFrm extends JDialog {
	private JLabel lblPass;
	private JPasswordField jpwPass;
	private JButton btnOk;
	private MyInfoFrm mif;
	private ClientMainFrm cmf;

	public ChkPassFrm(ClientMainFrm cmf) {
		super(cmf,"비밀번호 확인",true); //부모창이 cmf이고 자식창이뜨면 부모창이 안뜨게 modal로 설정
		this.cmf=cmf;
		
		lblPass = new JLabel("비밀번호");
		jpwPass = new JPasswordField();
		jpwPass.setEchoChar('*');
		btnOk = new JButton("확인");
		
		setLayout(null);
		
		lblPass.setBounds(30, 30, 80, 15);
		jpwPass.setBounds(130, 25, 100, 25);
		btnOk.setBounds(75, 80, 80, 25);
		
		//이벤트등록
		ChkPassEvt cpe=new ChkPassEvt(this,cmf);
		btnOk.addActionListener(cpe);
		jpwPass.addActionListener(cpe);
		
		add(lblPass);
		add(jpwPass);
		add(btnOk);
		
		setBounds(100, 100, 270, 170);
		//setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	
		
	}//ChkPassFrm
	
	public ChkPassFrm(String id) {
		
	}//ChkPassFrm

	public JLabel getLblPass() {
		return lblPass;
	}

	public void setLblPass(JLabel lblPass) {
		this.lblPass = lblPass;
	}

	public JPasswordField getJpwPass() {
		return jpwPass;
	}

	public void setJpwPass(JPasswordField jpwPass) {
		this.jpwPass = jpwPass;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public MyInfoFrm getMif() {
		return mif;
	}

	public void setMif(MyInfoFrm mif) {
		this.mif = mif;
	}
	
/*	
*/}//class
