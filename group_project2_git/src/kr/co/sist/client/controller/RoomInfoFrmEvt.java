package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.ClientMainFrm;
import kr.co.sist.client.frm.RoomInfoFrm;
import kr.co.sist.client.frm.SelectUserFrm;
import kr.co.sist.client.vo.RoomInfoVO;
import kr.co.sist.client.vo.SelectRoomResVO;
import kr.co.sist.client.vo.SelectTimeChkVO;
import kr.co.sist.client.vo.SelectTimeVO;

public class RoomInfoFrmEvt extends MouseAdapter implements ActionListener, ItemListener {

	public static final int CLICK = 1;

	private RoomInfoFrm rif;
	private String[] room_id = { "S_01", "S_02", "S_03", "S_04", "M_05", "M_06", "M_07", "L_08", "X_09" };
	private RoomInfoVO ri_vo;
	private String room_num, in, out;
	private int year, y_month, day;

	public RoomInfoFrmEvt(RoomInfoFrm rif) {
		this.rif = rif;
		setMonth();
	}// RoomInfoFrmEvt

	public String setRoomInfo(String room_id) {// 방 정보를 보여주는 method
		RoomCDAO r_dao = RoomCDAO.getInstance();
		room_num = room_id;
		try {
			String path = System.getProperty("user.dir");
			ri_vo = r_dao.selectRoomInfo(room_id);
			rif.getLblRCnt().setText(ri_vo.getP_min() + "명~" + ri_vo.getP_max() + "명");
			rif.getJtaInfo().setText(ri_vo.getRoomInfo());
			rif.getLblImg().setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + ri_vo.getImage()));
			List<String> Equip = r_dao.selectEquip(room_id);

			for (int i = 0; i < rif.getLblEquipment().length; i++) {// 비품 초기화
				rif.getLblEquipment()[i].setText("");
				rif.getLblEquipment()[i].setIcon(null);
				rif.getLblEquipment()[i].setToolTipText(null);
			} // end for
			for (int i = 0; i < Equip.size(); i++) {// 비품 채우기
				rif.getLblEquipment()[i].setText(Equip.get(i));
				rif.getLblEquipment()[i].setToolTipText(Equip.get(i));
				if (rif.getLblEquipment()[i].getText().equals("컴퓨터")) {
					rif.getLblEquipment()[i]
							.setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "computer.png"));
				} // end if
				if (rif.getLblEquipment()[i].getText().equals("공유기")) {
					rif.getLblEquipment()[i]
							.setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "wifi.png"));
				} // end if
				if (rif.getLblEquipment()[i].getText().equals("화이트보드")) {
					rif.getLblEquipment()[i]
							.setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "whiteboard.png"));
				} // end if
				if (rif.getLblEquipment()[i].getText().equals("책상")) {
					rif.getLblEquipment()[i]
							.setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "table.png"));
				} // end if
				if (rif.getLblEquipment()[i].getText().equals("빔프로젝터")) {
					rif.getLblEquipment()[i]
							.setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "bim.png"));
				} // end if
				if (rif.getLblEquipment()[i].getText().equals("의자")) {
					rif.getLblEquipment()[i]
							.setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "chair.png"));
				} // end if
			} // end for

			if (rif.getDcmbCnt().getSize() > 0) {
				rif.getDcmbCnt().removeAllElements();
			} // end for

			for (int i = Integer.parseInt(ri_vo.getP_min()); i <= Integer.parseInt(ri_vo.getP_max()); i++) {
				rif.getDcmbCnt().addElement(i + "");
			} // end for

			setResChk(room_id, year);

		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return ri_vo.getP_min();
	}// setRoomInfo

	private void setMonth() {
		Calendar ca = Calendar.getInstance();
		year = ca.get(Calendar.YEAR);
		y_month = ca.get(Calendar.MONTH) + 1;
		day = ca.get(Calendar.DAY_OF_MONTH);
		rif.getDcmbMonth().addElement(String.valueOf(y_month));
		setDay(year, y_month);
		if ((y_month + 1) == 13) {
			y_month = 13;
			rif.getDcmbMonth().addElement("1");
		} else {
			rif.getDcmbMonth().addElement(String.valueOf(y_month + 1));
		}
		rif.getJcbMonth().setSelectedIndex((0));
		rif.getJcbDay().setSelectedIndex(0);
	}// setMonth

	private void setDay(int year, int month) {// 월별 일 설정 메소드
		Calendar ca = Calendar.getInstance();
		int lastDay = 0;
		ca.set(year, month - 1, 1);
		lastDay = ca.getActualMaximum(Calendar.DATE);
		rif.getDcmbDay().removeAllElements();
		if (month == y_month) {
			for (int i = day; i <= lastDay; i++) {
				rif.getDcmbDay().addElement(i + "");
			} // end for
		} else {
			for (int i = 1; i <= lastDay; i++) {
				rif.getDcmbDay().addElement(i + "");
			} // end for
		}
	}// setDay

	private void setResChk(String room_id, int year) {// 예약된 방
		RoomCDAO r_dao = RoomCDAO.getInstance();
		try {
			String month = (String) rif.getDcmbMonth().getSelectedItem();
			String day = (String) rif.getDcmbDay().getSelectedItem();
			if (String.valueOf(rif.getDcmbMonth().getSelectedItem()).length() != 2) {
				month = "0" + rif.getDcmbMonth().getSelectedItem();
			}
			if ((String.valueOf(rif.getDcmbDay().getSelectedItem()).length()) != 2) {
				day = "0" + rif.getDcmbDay().getSelectedItem();
			}

			String date = year + "-" + month + "-" + day;
			SelectTimeChkVO stc_vo = new SelectTimeChkVO(date, room_id);
			List<SelectTimeVO> listTime;
			listTime = r_dao.selectTimeChk(stc_vo);
			for (int i = 0; i < rif.getDtmTime().getColumnCount(); i++) {// 테이블 초기화
				rif.getDtmTime().setValueAt("", 0, i);
			} // end for
			int in_time, out_time;
			for (int i = 0; i < listTime.size(); i++) {// 테이블 예약완료 채우기
				in_time = Integer.parseInt(listTime.get(i).getIn_time()) - 9;
				out_time = Integer.parseInt(listTime.get(i).getOut_time()) - 9;
				for (int j = in_time; j < out_time; j++) {
					rif.getDtmTime().setValueAt("예약완료", 0, j);
				} // end for
			} // end for
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// setResChk

	private void goNext() {// 다음 버튼 눌리면 날짜, 입실, 퇴실, 방번호, 인원 저장하고 사용자 정보창 띄우기
		if (!(in == null) && !(out == null)) {
			for (int j = Integer.parseInt(in); j < Integer.parseInt(out); j++) {
				if (rif.getJtTime().getValueAt(0, j).equals("")) {
					JOptionPane.showMessageDialog(rif, "빈 시간이 있습니다.");
					return;
				} // end if
			} // end for

			for (int i = 0; i < rif.getJtTime().getColumnCount(); i++) {
				if (rif.getDtmTime().getValueAt(0, i).equals("예약")) {
					int p_cnt = Integer.parseInt((String) rif.getJcbCnt().getSelectedItem());
					String month = rif.getJcbMonth().getSelectedItem() + "-" + rif.getJcbDay().getSelectedItem();
					String date = year + "-" + month;
					out = String.valueOf(Integer.parseInt(out) + 9);
					if (in.equals("0")) {
						in += 9;
					} else {
						in = String.valueOf(Integer.parseInt(in) + 9);
					}
					SelectRoomResVO srr_vo = new SelectRoomResVO(date, in, out, room_num, p_cnt);
					ClientMainFrm cmf = null;
					new SelectUserFrm(srr_vo, cmf, rif.getId());

					setResChk(room_num, year);
					in = null;
					out = null;
					return;
				} // end if
			} // end for
		} // end if
		JOptionPane.showMessageDialog(rif, "예약 시간을 정해주세요");
	}// goNext

	private void timeChk() {// 시간 테이블 예약 표시
		int col = rif.getJtTime().getSelectedColumn();
		int row = rif.getJtTime().getSelectedRow();
		String res = rif.getDtmTime().getValueAt(row, col).toString();

		if (rif.getJtTime().getSelectedColumn() == 13) {
			JOptionPane.showMessageDialog(rif, "마감시간입니다.");
			return;
		} // end if
		if (res.equals("예약완료")) { // 예약 완료 클릭하면 메세지
			JOptionPane.showMessageDialog(rif, "예약 완료된 방입니다");
			return;
		} // end if

		if (res.equals("")) {// 예약 체크
			rif.getDtmTime().setValueAt("예약", 0, col);
		} // end if

		if (res.equals("예약")) { // 예약 체크 취소
			rif.getDtmTime().setValueAt("", 0, col);
			in = null;
			out = null;
		} // end if

		for (int i = 0; i < rif.getJtTime().getColumnCount(); i++) {
			if (rif.getJtTime().getValueAt(0, i).equals("예약")) {
				in = String.valueOf(i);
				break;
			} // end if
		} // end for

		for (int i = 0; i < rif.getJtTime().getColumnCount(); i++) {
			if (rif.getJtTime().getValueAt(0, i).equals("예약")) {
				out = String.valueOf(i + 1);
			} // end if
		} // end for
		if (!(in == null) && !(out == null)) {
			for (int i = Integer.parseInt(in); i < Integer.parseInt(out); i++) {
				if (!rif.getJtTime().getValueAt(0, col).equals("")) {
					rif.getJtTime().setValueAt("예약", 0, i);
				} // end if
				for (int j = Integer.parseInt(in); j < Integer.parseInt(out); j++) {
					if (rif.getJtTime().getValueAt(0, j).equals("예약완료")) {
						JOptionPane.showMessageDialog(rif, "예약 완료된 시간이 있습니다.");
						setResChk(room_num, year);
						return;
					} // end if
				} // end for
			} // end for
		} // end if
	}// timeChk

	@Override
	public void mouseClicked(MouseEvent me) {
		switch (me.getClickCount()) {
		case CLICK:
			timeChk();
		} // end switch
	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (rif.getJcbMonth() == ae.getSource()) {

			if (rif.getJcbMonth().getSelectedIndex() != -1) {
				int month = Integer.parseInt((String) rif.getJcbMonth().getSelectedItem());
				Calendar ca = Calendar.getInstance();
				year = ca.get(Calendar.YEAR);
				setDay(year, month);
				if ((y_month) == 13) {
					if (rif.getJcbMonth().getSelectedItem().equals("1")) {
						year += 1;
						setDay(year, 1);
					} // end if

				} // end if
			} // end if
			setResChk(room_num, year);
		} // end if

		if (rif.getJcbDay() == ae.getSource()) {
			setResChk(room_num, year);
		} // end if

		if (rif.getBtnNext() == ae.getSource()) {
			goNext();
		} // end if

	}// actionPerformed

	@Override
	public void itemStateChanged(ItemEvent ie) {
		rif.getSelectRoom().setVisible(true);

		if (rif.getRb1().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(true);
			rif.getRoom2().setVisible(false);
			rif.getRoom3().setVisible(false);
			rif.getRoom4().setVisible(false);

			for (int i = 0; i < rif.getRbRoom1().length; i++) {
				if (rif.getRbRoom1()[i].isSelected()) {
					setRoomInfo(room_id[i]);
				} // end if
			} // end for
		} // end if;

		if (rif.getRb2().isSelected()) {

			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(false);
			rif.getRoom2().setVisible(true);
			rif.getRoom3().setVisible(false);
			rif.getRoom4().setVisible(false);

			for (int i = 0; i < rif.getRbRoom2().length; i++) {
				if (rif.getRbRoom2()[i].isSelected()) {
					setRoomInfo(room_id[(i + 4)]);
				} // end if
			} // end for
		} // end if;

		if (rif.getRb3().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(false);
			rif.getRoom2().setVisible(false);
			rif.getRoom3().setVisible(true);
			rif.getRoom4().setVisible(false);
			rif.getRbRoom3().setSelected(true);

			if (rif.getRbRoom3().isSelected()) {
				setRoomInfo(room_id[7]);
			} // end if
		} // end if;

		if (rif.getRb4().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(false);
			rif.getRoom2().setVisible(false);
			rif.getRoom3().setVisible(false);
			rif.getRoom4().setVisible(true);
			rif.getRbRoom4().setSelected(true);

			if (rif.getRbRoom4().isSelected()) {
				setRoomInfo(room_id[8]);
			} // end if
		} // end if;

		for (int i = 0; i < rif.getRbRoom1().length; i++) {
			if (rif.getRbRoom1()[i].isSelected()) {
				rif.getRoomInfo().setVisible(true);
			} // end if
		} // end for

	}// itemStateChanged

}// class
