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
		mv.getTpTab().addTab(" ������� ", mgr.Data_Judgment_Frm());
		umv = new UserMgrView();
		mv.getTpTab().addTab(" ȸ������ ", umv);
		sv = new SalesView();
		mv.getTpTab().add(" ������� ", sv);
		rmv = new ReqMgrView();
		mv.getTpTab().addTab(" ���ǻ��� ", rmv);
	}// ManagerViewEvt
}
