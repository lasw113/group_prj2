package kr.co.sist.client.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.HistoryFrm;
import kr.co.sist.client.vo.HistoryVO;

public class HistoryFrmEvt {
	private HistoryFrm hv;

	public HistoryFrmEvt(HistoryFrm hv, ResChkFrmEvt rcfe, String id) {
		this.hv = hv;

		// HistoryFrm�� ������ �߰�
		setHistory();
		search(id);// ���߿� �ٲ�� ham
	}// HistoryFrmEvt

	public void search(String id) {
		RoomCDAO rcdao = RoomCDAO.getInstance();
		try {
			hv.getLblWho().setText(rcdao.searchName(id) + "�� ���೻��");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setHistory() {
		DefaultTableModel tempHis = hv.getDtmHistory();
		RoomCDAO r_cdao = RoomCDAO.getInstance();

		try {
			// �����丮 ��ȸ
			List<HistoryVO> listHis = r_cdao.setHistory(hv.getId());
			tempHis.setRowCount(0);// ?
			Object[] rowData = null;
			// �����丮�� �����Ѵٸ�
			HistoryVO hvo = null;
			for (int i = 0; i < listHis.size(); i++) {
				hvo = listHis.get(i);
				rowData = new Object[6];
				rowData[0] = hvo.getRes_date();// ��¥
				rowData[1] = hvo.getRes_name();// �̸�
				rowData[2] = hvo.getRoom_id();// ���ȣ
				rowData[3] = hvo.getP_cnt();// �ο���
				rowData[4] = hvo.getPrice();// �̿�ݾ�
				rowData[5] = hvo.getIn_time() + "~" + hvo.getOut_time();// ����ð�

				// �迭�� ������ ���� ���̺� �𵨿� �����Ͽ� ���̺� �ݿ�
				tempHis.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(hv, "�����丮�� �����ϴ�.");
			e.printStackTrace();
		} // end catch
	}// setHistory

}// class
