package kr.co.sist.manager.controller;

import kr.co.sist.manager.view.ManagerView;
import kr.co.sist.manager.view.ReqMgrView;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.SalesView;
import kr.co.sist.manager.view.UserMgrView;

public class ManagerViewEvt {
	private SalesView sv;
	private ReqMgrView rmv;
	private UserMgrView umv;

	public ManagerViewEvt(ManagerView mv) {

		ResMgrView mgr = new ResMgrView();
		mv.getTpTab().addTab(" 예약관리 ", mgr.Data_Judgment_Frm());
		umv = new UserMgrView();
		mv.getTpTab().addTab(" 회원관리 ", umv);
		sv = new SalesView();
		mv.getTpTab().add(" 매출관리 ", sv);
		rmv = new ReqMgrView();
		mv.getTpTab().addTab(" 건의사항 ", rmv);
	}// ManagerViewEvt
}
