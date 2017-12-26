package kr.co.sist.manager.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.vo.HistoryVO;
import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.MgrHistoryView;

public class MgrHistoryViewEvt {
	private MgrHistoryView mhv;
	private UserMgrViewEvt umve;
	private String id;
	
	public MgrHistoryViewEvt(MgrHistoryView mhv, UserMgrViewEvt umve, String id) {
		this.mhv=mhv;
		this.umve=umve;
		this.id=id;
		//MgrHistoryView에 이벤트 추가
		searchHis();
		
	}//MgrHistoryViewEvt
	
	public void searchHis() {
		DefaultTableModel tempHis=mhv.getDtmMgrHis();
		ManagerDAO m_dao=ManagerDAO.getInstance();
		
		try {
			List<HistoryVO> MgrHis=m_dao.searchHis(mhv.getId());
			//어떻게 mhv에서 아이디를 가지고오는지
			tempHis.setRowCount(0);
			Object[] rowData=null;
			//회원의 히스토리가 존재한다면
			HistoryVO hvo=null;
			for(int i=0; i < MgrHis.size(); i++) {
				hvo=MgrHis.get(i);
				rowData=new Object[6];
				rowData[0]=hvo.getRes_date();//날짜
				rowData[1]=hvo.getRes_name();//이름
				rowData[2]=hvo.getRoom_id();//방번호
				rowData[3]=hvo.getP_cnt();//인원수
				rowData[4]=hvo.getPrice();//이용금액
				rowData[5]=hvo.getIn_time()+"~"+hvo.getOut_time();//예약시간
			
				tempHis.addRow(rowData);
		}//end for
	}catch(SQLException e) {
		JOptionPane.showMessageDialog(mhv, "회원의 예약내역이 없습니다.");
		e.printStackTrace();
	}//end catch
	}//searchHistory
}
