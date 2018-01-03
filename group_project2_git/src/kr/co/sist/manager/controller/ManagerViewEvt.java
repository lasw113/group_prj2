package kr.co.sist.manager.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTabbedPane;

import kr.co.sist.manager.view.ManagerView;
import kr.co.sist.manager.view.ReqMgrView;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.SalesView;
import kr.co.sist.manager.view.UserMgrView;

public class ManagerViewEvt extends MouseAdapter {
	private ManagerView mv;
	private SalesView sv;
	private ReqMgrView rmv;
	private UserMgrView umv;

	public static final int RES_MANAGE_TAB = 0;
	public static final int MANAGE_MEMBER_TAB = 1;
	public static final int MANAGE_SALES_TAB = 2;
	public static final int MANAGE_REQUEST_TAB = 3;

	public ManagerViewEvt(ManagerView mv) {
		this.mv = mv;

		ResMgrView mgr = new ResMgrView();
		mv.getTpTab().addTab(" 예약관리 ", mgr.Data_Judgment_Frm());
		umv = new UserMgrView();
		mv.getTpTab().addTab(" 회원관리 ", umv);
		sv = new SalesView();
		mv.getTpTab().add(" 매출관리 ", sv);
		rmv = new ReqMgrView();
		mv.getTpTab().addTab(" 건의사항 ", rmv);
	}// ManagerViewEvt
	
	@SuppressWarnings("static-access")
	public void close() {
		try {
			for(int i=0;i<rmv.listServer.size();i++) {
				rmv.getListServer().get(i).closeServer();
				System.out.println("서버껐다.");
			}//end for
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}//close
	@Override
	public void mouseClicked(MouseEvent me) {
		// 탭선택 판별
		JTabbedPane tempTab = mv.getTpTab();
		switch (tempTab.getSelectedIndex()) {
		case RES_MANAGE_TAB:
			System.out.println(RES_MANAGE_TAB);
			break;
		case MANAGE_MEMBER_TAB:
			System.out.println(MANAGE_MEMBER_TAB);
			break;
		case MANAGE_SALES_TAB:
			System.out.println(MANAGE_SALES_TAB);
			break;
		case MANAGE_REQUEST_TAB:
			System.out.println(MANAGE_REQUEST_TAB);
			break;
		}
	}// mouseClicked
}
