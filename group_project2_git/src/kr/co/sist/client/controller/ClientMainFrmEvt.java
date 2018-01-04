package kr.co.sist.client.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import kr.co.sist.client.dao.ClientDAO;
import kr.co.sist.client.frm.ChkPassFrm;
import kr.co.sist.client.frm.ClientMainFrm;
import kr.co.sist.client.frm.LoginFrm;
import kr.co.sist.client.frm.MyInfoFrm;
import kr.co.sist.client.frm.RequestFrm;
import kr.co.sist.client.frm.ResChkFrm;
import kr.co.sist.client.frm.RoomInfoFrm;

public class ClientMainFrmEvt extends MouseAdapter implements ActionListener {
	public static final int RESERVATION_TAB = 0;
	public static final int CHECK_TAB = 1;
	public static final int REQUEST_TAB = 2;
	public static final int INFO_TAB = 3;
	public static final int LOGOUT_TAB = 4;
	private RoomInfoFrm rif;
	private ClientMainFrm cmf;
	private ResChkFrm rcf;
	private String id, pass;
	private String room_id;
	private boolean isIn = false;
	private ClientDAO c_dao;

	public ClientMainFrmEvt(ClientMainFrm cmf) {
		this.cmf = cmf;
		id = cmf.getId();
		pass = cmf.getPass();

		rif = new RoomInfoFrm(id);
		rif.setBackground(Color.white);

		JScrollPane jspRoomInfo = new JScrollPane(rif);
		cmf.getJtpClient().addTab("  �� ���� (����)  ", jspRoomInfo);

		rcf = new ResChkFrm(id, pass);
		cmf.getJtpClient().addTab("  �� �� Ȯ ��  ", rcf);

		cmf.getJtpClient().addTab("  �� �� �� ��  ", new JLabel("�Խ��� ����� ������� ����ϴ� ���Դϴ�."));
		cmf.getJtpClient().addTab("  �� ���� Ȯ��  ", new JLabel());

		cmf.getJtpClient().addTab("  �α׾ƿ�  ", new JLabel());

	}// ClientMainViewEvt

	@Override
	public void mouseClicked(MouseEvent me) {
		// �Ǽ��� �Ǻ�
		JTabbedPane tempTab = cmf.getJtpClient();
		switch (tempTab.getSelectedIndex()) {
		case RESERVATION_TAB:
			new RoomInfoFrmEvt(rif);
			break;

		case CHECK_TAB:
			ResChkFrmEvt rcfe = new ResChkFrmEvt(rcf);
			rcfe.resChk();
			break;

		case REQUEST_TAB:
			// �ֹ����� Ŭ���Ǹ� �α��� ���θ� �Ǻ��Ͽ�
			if (isIn) {
				break;
			}
			if (isRight()) {
				JOptionPane.showMessageDialog(cmf, "ȯ���մϴ�.��ø� ��ٷ��ּ���");
				RequestFrm rf = new RequestFrm(this);
				if (rf.isFlagMgrIn()) {
					tempTab.remove(2);// ���� ���� ��
					tempTab.insertTab("  �� �� �� ��  ", null, rf, null, 2);// �������� ���� ������Ʈ ��ġ
					// ���� �޴��� �Ѿ�� �� ���´�.
					tempTab.setSelectedIndex(2);
				}
				break;
			}
			JOptionPane.showMessageDialog(cmf, "������� �ƴմϴ�. ���Ұ��մϴ�.");
			break;

		case INFO_TAB:
			if (cmf.isAdminLoginStatus() == false) { // �� ���� Ȯ�� ó�� ���ö��� ��й�ȣ ġ����
				new ChkPassFrm(cmf);
			} // end if
			if (cmf.isAdminLoginStatus() == true) {
				MyInfoFrm mif = new MyInfoFrm();
				tempTab.remove(3);// ���� ���� ��
				tempTab.add("  �� ���� Ȯ��  ", mif);// �������� ���� ������Ʈ ��ġ
				// ���� �޴��� �Ѿ�� �� ���´�.
				tempTab.setSelectedIndex(3);
				MyInfoEvt miv = new MyInfoEvt(mif);
				try {
					miv.setMyInfo();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			} // end if
		case LOGOUT_TAB:
			switch (JOptionPane.showConfirmDialog(cmf, "�α׾ƿ��Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				new LoginFrm();
				cmf.dispose();
				break;
			}// end if
			break;

		}
	}

	private boolean isRight() {
		String room = JOptionPane.showInputDialog(cmf, "�̿� ���� ���̸��� �Է����ּ���.");
		int cnt = 0;
		c_dao = ClientDAO.getInstance();

		try {
			System.out.println(id);
			System.out.println(room);
			room_id = room;
			cnt = c_dao.rightUser(id, room);
			if (cnt == 1) {
				isIn = true;
			} else {
				isIn = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isIn;

	}// isRight

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
