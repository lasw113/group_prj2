package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import kr.co.sist.client.frm.RoomInfoFrm;

public class RoomInfoFrmEvt extends MouseAdapter implements ActionListener, ItemListener {

	private RoomInfoFrm rif;

	public RoomInfoFrmEvt(RoomInfoFrm rif) {
		this.rif = rif;
	}// RoomInfoFrmEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
	}// mouseClicked

	@Override
	public void itemStateChanged(ItemEvent ie) {

		
		if (rif.getRb1().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(true);
			rif.getRoom2().setVisible(false);
			rif.getRoom3().setVisible(false);
			rif.getRoom4().setVisible(false);
			
			
			if(rif.getRbRoom1()[0].isSelected()) {
				rif.getLblRCnt().setText("1~4인실");
			}
		} // end if;

		if (rif.getRb2().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(false);
			rif.getRoom2().setVisible(true);
			rif.getRoom3().setVisible(false);
			rif.getRoom4().setVisible(false);
			
			if(rif.getRbRoom2()[0].isSelected()) {
				rif.getLblRCnt().setText("5~8인실");
			}
		} // end if;

		if (rif.getRb3().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(false);
			rif.getRoom2().setVisible(false);
			rif.getRoom3().setVisible(true);
			rif.getRoom4().setVisible(false);
			rif.getRbRoom3().setSelected(true);
		} // end if;

		if (rif.getRb4().isSelected()) {
			rif.getRoomLogo().setVisible(false);
			rif.getRoom1().setVisible(false);
			rif.getRoom2().setVisible(false);
			rif.getRoom3().setVisible(false);
			rif.getRoom4().setVisible(true);
			rif.getRbRoom4().setSelected(true);
		} // end if;

		for (int i = 0; i < rif.getRbRoom1().length; i++) {
			if (rif.getRbRoom1()[i].isSelected()) {
				rif.getRoomInfo().setVisible(true);
			} // end if
		} // end for

	}// itemStateChanged

}// class
