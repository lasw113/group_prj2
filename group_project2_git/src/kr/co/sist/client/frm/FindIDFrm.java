package kr.co.sist.client.frm;

import java.awt.Color;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.client.controller.FindIDFrmEvt;

@SuppressWarnings("serial")
public class FindIDFrm extends JDialog{
	
	private LoginFrm lf;
	private JLabel lblName, lblBirth, lblPhone;
	private JTextField jtfName,jtfBirth,jtfPhoneM,jtfPhoneL;
	private JComboBox<String> jcbPhoneF;
	private JButton btnFindId;
	private DefaultComboBoxModel<String> dcbPhone;

	
	public FindIDFrm(LoginFrm lf) {
		
		super(lf, "아이디 찾기", true); // 부모창이 lf이고 자식창이뜨면 부모창이 안뜨게 modal로 설정
		this.lf = lf;
		
		jtfName = new JTextField();
		jtfBirth = new JTextField();
		jtfPhoneM = new JTextField();
		jtfPhoneL = new JTextField();

		jcbPhoneF = new JComboBox<>();
		dcbPhone = new DefaultComboBoxModel<>(new String[] { "010", "011", "016", "017" });
		jcbPhoneF.setModel(dcbPhone);

//		String path = System.getProperty("user.dir");
//		btnFindId = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/jul/아이디 찾기.png"));
		
		URL url1 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/jul/아이디 찾기.png");		
		btnFindId = new JButton(new ImageIcon(url1));
		
		lblName = new JLabel("이름");
		lblBirth = new JLabel("생년월일");
		lblPhone = new JLabel("핸드폰");
		
		JLabel jl1=new JLabel("-");
		JLabel jl2=new JLabel("-");
		

		setLayout(null);
		
		lblName.setBounds(30, 30, 80, 15);
		jtfName.setBounds(140, 25, 100, 25);

		lblBirth.setBounds(30, 80, 80, 15);
		jtfBirth.setBounds(140, 75, 100, 25);
		
		
		lblPhone.setBounds(30,130,50,15);
		jcbPhoneF.setBounds(90, 125, 60, 25);
		jl1.setBounds(160, 130, 20, 10);
		jtfPhoneM.setBounds(175, 125, 60, 25);
		jl2.setBounds(245, 130, 20, 10);
		jtfPhoneL.setBounds(260, 125, 60, 25);

		btnFindId.setBounds(110, 180, 120, 30);
		
		//이벤트 추가
		FindIDFrmEvt fife=new FindIDFrmEvt(this);
		btnFindId.addActionListener(fife);
		jtfPhoneL.addActionListener(fife);
				
		

		add(jtfName);
		add(jtfBirth);
		add(jtfPhoneM);
		add(jtfPhoneL);
		add(jcbPhoneF);
		add(btnFindId);
		add(lblName);
		add(lblBirth);
		add(lblPhone);
		add(jl1);
		add(jl2);
		
		this.getContentPane().setBackground(Color.WHITE);
		
		setBounds(420, 100, 350, 260);
		//setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}//FindIDFrm
	
	
	public DefaultComboBoxModel<String> getDcbPhone() {
		return dcbPhone;
	}


	public void setDcbPhone(DefaultComboBoxModel<String> dcbPhone) {
		this.dcbPhone = dcbPhone;
	}


	public LoginFrm getLf() {
		return lf;
	}


	public void setLf(LoginFrm lf) {
		this.lf = lf;
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


	public JLabel getLblPhone() {
		return lblPhone;
	}


	public void setLblPhone(JLabel lblPhone) {
		this.lblPhone = lblPhone;
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


	public JComboBox<String> getJcbPhoneF() {
		return jcbPhoneF;
	}


	public void setJcbPhoneF(JComboBox<String> jcbPhoneF) {
		this.jcbPhoneF = jcbPhoneF;
	}


	public JButton getBtnFindId() {
		return btnFindId;
	}


	public void setBtnFindId(JButton btnFindId) {
		this.btnFindId = btnFindId;
	}


	public static void main(String[] args) {
		/*LoginFrm lf = new LoginFrm();
		FindIDFrm fif = new FindIDFrm(lf);*/
	}// main
	
	

}//class
