package kr.co.sist.client.controller;

import java.awt.Color;
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

public class ClientMainFrmEvt extends MouseAdapter{
	public static final int RESERVATION_TAB = 0;
	public static final int CHECK_TAB = 1;
	public static final int REQUEST_TAB = 2;
	public static final int INFO_TAB = 3;
	public static final int LOGOUT_TAB = 4;
	private RoomInfoFrm rif;
	private ClientMainFrm cmf;
	private ResChkFrm rcf;
	private RequestFrm rf;
	private String id, pass;
	private String room_id;
	private boolean isIn; //입실여부 판별 결과 변수
	private ClientDAO c_dao;

	public ClientMainFrmEvt(ClientMainFrm cmf) {
		this.cmf = cmf;
		id = cmf.getId();
		pass = cmf.getPass();

		rif = new RoomInfoFrm(id);
		rif.setBackground(Color.white);

		JScrollPane jspRoomInfo = new JScrollPane(rif);
		cmf.getJtpClient().addTab("  룸 정보 (예약)  ", jspRoomInfo);

		rcf = new ResChkFrm(id, pass);
		cmf.getJtpClient().addTab("  예 약 확 인  ", rcf);

		cmf.getJtpClient().addTab("  건 의 사 항  ", new JLabel("입실한 사용자 대상으로 사용하는 탭입니다."));
		cmf.getJtpClient().addTab("  내 정보 확인  ", new JLabel());

		cmf.getJtpClient().addTab("  로그아웃  ", new JLabel());

	}// ClientMainViewEvt

	@Override
	public void mouseClicked(MouseEvent me) {
		// 탭선택 판별
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
			// 건의사항 탭이 클릭되면 입실여부를 판별하여 채팅을 사용할 수 있게 한다.
			if (isIn && rf.isFlagMgrIn() ) { //입실여부 판별 수행했으면 다시 입실여부 판별 하지 않도록 한다.
				break;
			}//end if
			if (isRight()) { //입실여부 판별 수행
				JOptionPane.showMessageDialog(cmf, "환영합니다.잠시만 기다려주세요");
				rf= new RequestFrm(this);
				if (rf.isFlagMgrIn()) {
					tempTab.remove(2);// 탭을 삭제 후
					tempTab.insertTab("  건 의 사 항  ", null, rf, null, 2);// 디자인을 가진 컴포넌트 배치
					// 탭이 메뉴로 넘어가는 걸 막는다.
					tempTab.setSelectedIndex(2);
				}//end if
				break;
			}//end if
			JOptionPane.showMessageDialog(cmf, "사용중이 아닙니다. 사용불가합니다.");
			break;

		case INFO_TAB:
			if (cmf.isAdminLoginStatus() == false) { // 내 정보 확인 처음 들어올때만 비밀번호 치도록
				new ChkPassFrm(cmf);
			} // end if
			if (cmf.isAdminLoginStatus() == true) {
				MyInfoFrm mif = new MyInfoFrm();
//				tempTab.remove(3);// 탭을 삭제 후
//				tempTab.add("  내 정보 확인  ", mif);// 디자인을 가진 컴포넌트 배치
				tempTab.setComponentAt(3, mif);
				// 탭이 메뉴로 넘어가는 걸 막는다.
//				tempTab.setSelectedIndex(3);
				MyInfoEvt miv = new MyInfoEvt(mif);
				try {
					miv.setMyInfo();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			} // end if
			break;
		case LOGOUT_TAB:
			switch (JOptionPane.showConfirmDialog(cmf, "로그아웃하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				new LoginFrm();
				cmf.dispose();
				break;
			}// end if
			break;

		}
	}

	/**
	 * 이용중인 방이름을 입력받아 입실 여부 판별하는 일
	 * @return true 입실 false 입실x
	 */
	private boolean isRight() {
		String room = JOptionPane.showInputDialog(cmf, "이용 중인 방이름을 입력해주세요.");
		int cnt = 0;
		c_dao = ClientDAO.getInstance();

		try {
			room_id = room;
			cnt = c_dao.rightUser(id, room); 
			//id와 방이름을 매개변수로 받아서 맞는 사용자인지 판별
			if (cnt == 1) { 
				isIn = true; //입실한 회원 
			} else {
				isIn = false; //입실하지 않은 회원
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
}
