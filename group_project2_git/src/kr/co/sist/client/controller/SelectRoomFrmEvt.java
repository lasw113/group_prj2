package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import kr.co.sist.client.frm.SelectRoomFrm;

public class SelectRoomFrmEvt extends MouseAdapter implements ActionListener {
	private SelectRoomFrm srf;
	
	public SelectRoomFrmEvt(SelectRoomFrm srf) {
		this.srf = srf;
		Calendar ca = Calendar.getInstance();
		int month = ca.get(Calendar.MONTH) - 1;
		setDay(month);
		srf.getJcbMonth().setSelectedIndex((month + 1));	
		setCnt();
	}// SelectRoomEvt

	private void setDay(int month) {// 월별 일 설정 메소드
		Calendar ca = Calendar.getInstance();
		int lastDay = 0;

		ca.set(ca.get(Calendar.YEAR), month - 1, 1);
		lastDay = ca.getActualMaximum(Calendar.DATE);
		srf.getDcmbDay().removeAllElements();
		for (int i = 1; i <= lastDay; i++) {
			srf.getDcmbDay().addElement(i + "");
		} // end for
	}// setDay
	
	private void setCnt() {
		for(int i =srf.getP_min(); i<=srf.getP_max(); i++) {
			srf.getDcmbCnt().addElement(i+"");			
		}
	}//setCnt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (srf.getJcbMonth().getSelectedIndex() != -1) {
			int month = Integer.parseInt((String) srf.getJcbMonth().getSelectedItem());
			setDay(month);
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {

	}// mouseClicked

}
