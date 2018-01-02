package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.client.controller.SelectUserFrmEvt;
import kr.co.sist.client.vo.SelectRoomResVO;

@SuppressWarnings("serial")
public class SelectUserFrm extends JDialog {

	private JLabel lblName, lblPhone, lblEmail, lblRequest, lblMillege, lblCanUse, lblPrice;
	private JButton btnClose, btnRes, btnUseM;
	private JComboBox<String> jcbPhoneF;
	private DefaultComboBoxModel<String> dcbmPhoneF;
	private JTextField jtfName, jtfPhoneM, jtfPhoneL, jtfEmail, jtfMillege, jtfPrice;
	private JTextArea jtaReq;

	private String room_id, in_time, out_time, id;
	private int p_cnt;

	private SelectRoomResVO srr_vo;

	public SelectUserFrm(SelectRoomResVO srr_vo, ClientMainFrm cmf, String id) {
		super(cmf, "사용자 정보", true);

		this.room_id = srr_vo.getRoom_id();
		this.in_time = srr_vo.getIn_time();
		this.out_time = srr_vo.getOut_time();
		this.p_cnt = srr_vo.getP_cnt();
		this.id = id;
		this.srr_vo = srr_vo;
		setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		
		lblName = new JLabel("예약자");
		lblPhone = new JLabel("연락처");
		lblEmail = new JLabel("이메일");
		lblRequest = new JLabel("요청사항");
		lblMillege = new JLabel("마일리지");
		lblCanUse = new JLabel("사용가능마일리지");
		lblPrice = new JLabel("방금액");

		btnClose = new JButton("닫기");
		btnRes = new JButton("예약");
		btnUseM = new JButton("사용");

		dcbmPhoneF = new DefaultComboBoxModel<String>();
		dcbmPhoneF.addElement("010");
		dcbmPhoneF.addElement("011");
		dcbmPhoneF.addElement("016");
		dcbmPhoneF.addElement("017");
		jcbPhoneF = new JComboBox<String>(dcbmPhoneF);

		jtfName = new JTextField();
		jtfPhoneM = new JTextField();
		jtfPhoneL = new JTextField();
		jtfEmail = new JTextField();
		jtfMillege = new JTextField();
		jtfPrice = new JTextField();

		jtaReq = new JTextArea();
		jtaReq.setBorder(new TitledBorder(""));

		jtfPrice.setEditable(false);

		lblName.setBounds(30, 30, 60, 30);
		jtfName.setBounds(120, 30, 200, 30);
		lblPhone.setBounds(30, 80, 60, 30);
		jcbPhoneF.setBounds(120, 80, 60, 30);
		jtfPhoneM.setBounds(190, 80, 60, 30);
		jtfPhoneL.setBounds(260, 80, 60, 30);
		lblEmail.setBounds(30, 130, 60, 30);
		jtfEmail.setBounds(120, 130, 200, 30);
		lblRequest.setBounds(30, 180, 60, 30);
		jtaReq.setBounds(120, 180, 200, 80);
		lblCanUse.setBounds(30, 280, 300, 30);
		lblMillege.setBounds(30, 330, 60, 30);
		jtfMillege.setBounds(120, 330, 100, 30);
		btnUseM.setBounds(230, 330, 70, 30);
		lblPrice.setBounds(30, 380, 60, 30);
		jtfPrice.setBounds(120, 380, 100, 30);
		btnRes.setBounds(190, 430, 70, 30);
		btnClose.setBounds(270, 430, 70, 30);

		add(lblName);
		add(jtfName);
		add(lblPhone);
		add(jcbPhoneF);
		add(jtfPhoneM);
		add(jtfPhoneL);
		add(lblEmail);
		add(jtfEmail);
		add(lblRequest);
		add(jtaReq);
		add(lblCanUse);
		add(lblMillege);
		add(jtfMillege);
		add(btnUseM);
		add(lblPrice);
		add(jtfPrice);
		add(btnRes);
		add(btnClose);

		SelectUserFrmEvt sufe = new SelectUserFrmEvt(this);
		btnUseM.addActionListener(sufe);
		btnRes.addActionListener(sufe);
		btnClose.addActionListener(sufe);

		setBounds(0, 0, 365, 520);
		setVisible(true);
	}// SelectUserFrm

	public JLabel getLblCanUse() {
		return lblCanUse;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public JButton getBtnRes() {
		return btnRes;
	}

	public JButton getBtnUseM() {
		return btnUseM;
	}

	public JComboBox<String> getJcbPhoneF() {
		return jcbPhoneF;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhoneM() {
		return jtfPhoneM;
	}

	public JTextField getJtfPhoneL() {
		return jtfPhoneL;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfMillege() {
		return jtfMillege;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextArea getJtaReq() {
		return jtaReq;
	}

	public String getRoom_id() {
		return room_id;
	}

	public String getIn_time() {
		return in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public int getP_cnt() {
		return p_cnt;
	}

	public SelectRoomResVO getSrr_vo() {
		return srr_vo;
	}

	public String getId() {
		return id;
	}

}
