package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import kr.co.sist.manager.view.ChatMgrView;
import kr.co.sist.manager.view.ReqMgrView;

public class ReqMgrViewEvt implements ActionListener {
	private ReqMgrView rmv;
	private ChatMgrView[] cmv;

	public ReqMgrViewEvt(ReqMgrView rmv) {
		this.rmv = rmv;
	}//ReqMgrViewEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton button = (JButton)ae.getSource();
		String temp=button.getName();
		setChat(temp);
	}//actionPerformed
	
	private void setChat(String room_num) {//선택된 방의 채팅으로 넘어가는 메소드
		System.out.println(room_num);
		for(int i=0;i<9;i++) {
			if(cmv[i].getRoom_id()==room_num) {
				cmv[i].setVisible(true);
				cmv[i].getJtfMessage().requestFocus();
			}
		}
	}
}//class

	