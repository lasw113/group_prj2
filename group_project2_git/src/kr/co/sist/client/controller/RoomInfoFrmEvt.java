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
	private String room = "S_01,S_02,S_03,S_04,M_05,M_06,M_07,L_08,X_09";
	private String[] room_id = room.split(",");

	public RoomInfoFrmEvt(RoomInfoFrm rif) {
		this.rif = rif;
		Calendar ca = Calendar.getInstance();
		int month = ca.get(Calendar.MONTH) - 1;
		int day = ca.get(Calendar.DAY_OF_MONTH) - 1;
		setDay(month);
		rif.getJcbMonth().setSelectedIndex((month + 1));
		rif.getJcbDay().setSelectedIndex(day);
	}// RoomInfoFrmEvt

	RoomInfoVO ri_vo = null;
	String room_num = null;

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
				rif.getLblEquipment()[i].setIcon(new ImageIcon(path + "/src/kr/co/sist/studyroom/img/" + "white.png"));
			} // end for
			for (int i = 0; i < Equip.size(); i++) {// 비품 채우기
				rif.getLblEquipment()[i].setText(Equip.get(i));
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

			setResChk(room_id);

		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		return ri_vo.getP_min();
	}// setRoomInfo

	private void setDay(int month) {// 월별 일 설정 메소드
		Calendar ca = Calendar.getInstance();
		int lastDay = 0;

		ca.set(ca.get(Calendar.YEAR), month - 1, 1);
		lastDay = ca.getActualMaximum(Calendar.DATE);
		rif.getDcmbDay().removeAllElements();
		for (int i = 1; i <= lastDay; i++) {
			rif.getDcmbDay().addElement(i + "");
		} // end for
	}// setDay

	private void setResChk(String room_id) {
		RoomCDAO r_dao = RoomCDAO.getInstance();
		try {
			Calendar ca = Calendar.getInstance();
			int year = ca.get(Calendar.YEAR);
			String date = year + "-" + rif.getDcmbMonth().getSelectedItem() + "-" + rif.getDcmbDay().getSelectedItem();
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

	int in = 0, out = 0;

	@Override
	public void mouseClicked(MouseEvent me) {
		switch (me.getClickCount()) {
		case CLICK:
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
				return;
			} // end if

			for (int i = 0; i < rif.getJtTime().getColumnCount(); i++) {
				if (rif.getJtTime().getValueAt(0, i).equals("예약")) {
					in = i;
					break;
				} // end if
			} // end for

			for (int i = 0; i < rif.getJtTime().getColumnCount(); i++) {
				if (rif.getJtTime().getValueAt(0, i).equals("예약")) {
					out = i + 1;
				} // end if
			} // end for

			for (int i = in; i < out; i++) {
				rif.getJtTime().setValueAt("예약", 0, i);
				for (int j = in; j < out; j++) {
					if (rif.getJtTime().getValueAt(0, j).equals("예약완료")) {
						JOptionPane.showMessageDialog(rif, "예약 완료된 시간이 있습니다.");
						setResChk(room_num);
						return;
					} // end if
				} // end for
			} // end for

		} // end switch
	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (rif.getJcbMonth() == ae.getSource()) {
			if (rif.getJcbMonth().getSelectedIndex() != -1) {
				int month = Integer.parseInt((String) rif.getJcbMonth().getSelectedItem());
				setDay(month);
			} // end if
		} // end if

		if (rif.getJcbDay() == ae.getSource()) {
			setResChk(room_num);
		} // end if

		if (rif.getBtnNext() == ae.getSource()) {

			for (int i = 0; i < rif.getJtTime().getColumnCount(); i++) {
				if (rif.getDtmTime().getValueAt(0, i).equals("예약")) {
					Calendar ca = Calendar.getInstance();
					int year = ca.get(Calendar.YEAR);
					int p_cnt = Integer.parseInt((String) rif.getJcbCnt().getSelectedItem());
					String month = rif.getJcbMonth().getSelectedItem() + "-" + rif.getJcbDay().getSelectedItem();
					String date = year + "-" + month;

					SelectRoomResVO srr_vo = new SelectRoomResVO(date, String.valueOf(in + 9), String.valueOf(out + 9),
							room_num, p_cnt);
					ClientMainFrm cmf = null;
					new SelectUserFrm(srr_vo, cmf);

					setResChk(room_num);
					return;
				} // end if
			} // end for

			JOptionPane.showMessageDialog(rif, "예약 시간을 정해주세요");

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
