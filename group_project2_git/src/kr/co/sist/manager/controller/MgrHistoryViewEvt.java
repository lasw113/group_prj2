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
		//MgrHistoryView�� �̺�Ʈ �߰�
		searchHis();
		
	}//MgrHistoryViewEvt
	
	public void searchHis() {
		DefaultTableModel tempHis=mhv.getDtmMgrHis();
		ManagerDAO m_dao=ManagerDAO.getInstance();
		
		try {
			List<HistoryVO> MgrHis=m_dao.searchHis(mhv.getId());
			//��� mhv���� ���̵� �����������
			tempHis.setRowCount(0);
			Object[] rowData=null;
			//ȸ���� �����丮�� �����Ѵٸ�
			HistoryVO hvo=null;
			for(int i=0; i < MgrHis.size(); i++) {
				hvo=MgrHis.get(i);
				rowData=new Object[6];
				rowData[0]=hvo.getRes_date();//��¥
				rowData[1]=hvo.getRes_name();//�̸�
				rowData[2]=hvo.getRoom_id();//���ȣ
				rowData[3]=hvo.getP_cnt();//�ο���
				rowData[4]=hvo.getPrice();//�̿�ݾ�
				rowData[5]=hvo.getIn_time()+"~"+hvo.getOut_time();//����ð�
			
				tempHis.addRow(rowData);
		}//end for
	}catch(SQLException e) {
		JOptionPane.showMessageDialog(mhv, "ȸ���� ���೻���� �����ϴ�.");
		e.printStackTrace();
	}//end catch
	}//searchHistory
}
