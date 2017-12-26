package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.add_timeForm;
import kr.co.sist.manager.vo.ResMgrVO;

public class add_timeEvt extends WindowAdapter implements ActionListener {

	private add_timeForm at;
	ResMgrView rmv;

	public add_timeEvt(add_timeForm at, ResMgrView rmv) {
		this.at = at;
		this.rmv = rmv;

	}// add_timeEvt

	public ResMgrView getRmv() {
		return rmv;
	}

	public void setRmv(ResMgrView rmv) {
		this.rmv = rmv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// combobox�� ���� ��
		int combo_index = 0;

		// Ȯ�� ��ư�� ������
		if (e.getSource() == at.getBtn_check()) {
			ResMgrVO rmvv = null;
			ManagerDAO m_dao = null;
			m_dao = ManagerDAO.getInstance();
			List<ResMgrVO> list;
			
			int index = at.getIndex();
			try {
				list = m_dao.selectAll();
				//System.out.println("------------------" + rmv.getList_tf());
				// ���� ������ �ð����� Ȯ���ϰ�, ���� ������ �ð��̶�� ������Ʈ�� �����ϰ�,
				// �θ�â�� �ִ� �ð��� ����

				//��� �ð��� �ٲ۴�.
				if (at.getCombo().getSelectedIndex() == 0) {
					at.dispose();
				} else {
					int count = (index * 5) + 4;
					list = m_dao.selectAll();
					rmvv = list.get(index);
					String chage_outTime = (Integer.parseInt(list.get(index).getOut_time()) + at.getCombo().getSelectedIndex()) + "";
					System.out.println(chage_outTime);
					rmv.getList_tf().get(count).setText("         " + chage_outTime  + "��");
					System.out.println(rmvv.getRes_id());
					m_dao.plus_time(at.getCombo().getSelectedIndex(), rmvv.getRes_id());
				}//end else
				// int cnt = list.size();
				// ����â�� �ݴ´�.
				at.dispose();

				// rmv.ResMgrView(cnt, list);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			at.dispose();
		}

		// ��ҹ�ư�� ���� ��
		if (e.getSource() == at.getBtn_cancle()) {
			at.dispose();
		}
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		at.dispose();
	}// windowClosing
}