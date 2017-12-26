package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.dao.RoomCDAO;
import kr.co.sist.client.frm.CancelFrm;
import kr.co.sist.client.frm.HistoryFrm;
import kr.co.sist.client.frm.ResChkFrm;
import kr.co.sist.client.vo.ResChkVO;

public class ResChkFrmEvt extends MouseAdapter implements ActionListener {
	private ResChkFrm rcf;
	private String res_id;

	public ResChkFrmEvt(ResChkFrm rcf) {
		this.rcf = rcf;
		// ������ Ŭ������ �̺�Ʈ�� �Ҵ�Ǹ� ����Ȯ�����̺� ������ �߰�
		resChk();
	}// ResChkFrmEvt

	public void resChk() {
		DefaultTableModel tempRes = rcf.getDtmRoom();
		RoomCDAO r_cdao = RoomCDAO.getInstance();

		try {
			// ������ ��ȸ
			List<ResChkVO> listRes = r_cdao.ResChk(rcf.getId());
			tempRes.setRowCount(0);
			Object[] rowData = null;
			// ��ȸ�� ������ �����Ѵٸ�
			ResChkVO rcvo = null;// ?
			for (int i = 0; i < listRes.size(); i++) {
				// �޴��� �޾ƿͼ� �迭�� ����
				rcvo = listRes.get(i);
				rowData = new Object[7];
				rowData[0] = rcvo.getRes_id();// �����ڵ�
				rowData[1] = rcvo.getRes_name();// �̸�
				rowData[2] = rcvo.getRoom_id();// ���ȣ
				rowData[3] = rcvo.getP_cnt();// �ο���
				rowData[4] = rcvo.getPrice();// �̿�ݾ�
				rowData[5] = rcvo.getIn_time() + "~" + rcvo.getOut_time();// ����ð�
				rowData[6] = rcvo.getDate();// ���೯¥

				// �迭�� ������ ���� ���̺� �𵨿� �����Ͽ� ���̺� �ݿ��ǵǷ� ��
				tempRes.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rcf, "������ȸ�� �� �� �����ϴ�.");
			e.printStackTrace();
		}
	}// resChk

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int row = rcf.getJtRes().getSelectedRow();
			res_id = (String) rcf.getJtRes().getValueAt(row, 0);
		}
	}// end if

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == rcf.getBtnHistory()) {
			new HistoryFrm(rcf, this, rcf.getId());
			// ���೻�� ��ȸ ��
		} // end if

		if (ae.getSource() == rcf.getBtnCancel()) {
			try {
				if (rcf.getJtRes().getSelectedRow() != -1) {
					new CancelFrm(rcf, this, rcf.getId(), res_id, rcf.getPass());
				} else {
					JOptionPane.showMessageDialog(rcf, "����� ������ �����ϼ���.");
				} // end else
			} catch (IOException e) {
				System.out.println("���̵� ���� �ʽ��ϴ�.");
				e.printStackTrace();
			}
		} // end if
	}// actionPerformed

}// class
