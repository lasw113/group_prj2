package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import kr.co.sist.manager.view.ChatMgrView;
import kr.co.sist.manager.view.ReqMgrView;

public class ChatMgrViewEvt implements ActionListener  {
	private ReqMgrView rmv;
	private ChatMgrView cmv;
	public ChatMgrViewEvt(ChatMgrView cmv,ReqMgrView rmv) {
		this.cmv = cmv;
		this.rmv =rmv;
	}//ChatMgrViewEvt
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==cmv.getJtfMessage()||ae.getSource()==cmv.getBtnSent()) {
			String manager="관리자 : "+cmv.getJtfMessage().getText()+"\n";
			//cmv.getJtaChat().append(manager);
			for(int j=0;j<rmv.listServer.size();j++) {
				System.out.println(rmv.listServer+"에라이");
				if(cmv.getP_num()==rmv.listServer.get(j).getPort()) {
					System.out.println(rmv.listServer.get(j).getPort());
					System.out.println(cmv.getP_num());
					rmv.listServer.get(j).sendMsg(manager);
					if(rmv.listServer.get(j).isChkClientIn()==true) {
					cmv.getJtaChat().append(manager);
					System.out.println(manager);
					}
					cmv.getJtfMessage().setText("");
					cmv.getJtfMessage().requestFocus();
				}
			}

		}//end if
	}//actionPerformed

	public ChatMgrView getCmv() {
		return cmv;
	}
	public void setCmv(ChatMgrView cmv) {
		this.cmv = cmv;
	}
}//class
