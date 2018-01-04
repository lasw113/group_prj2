package kr.co.sist.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.manager.dao.ManagerDAO;
import kr.co.sist.manager.view.ResMgrView;
import kr.co.sist.manager.view.add_timeView;
import kr.co.sist.manager.vo.ResMgrVO;

public class add_timeEvt extends WindowAdapter implements ActionListener {

	private add_timeView at;
	ResMgrView rmv;

	public add_timeEvt(add_timeView at, ResMgrView rmv) {
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

		// 확인 버튼을 누를때
		if (e.getSource() == at.getBtn_check()) {
			ResMgrVO rmvv = null;
			ManagerDAO r_dao = ManagerDAO.getInstance();
			List<ResMgrVO> list;

			int index = at.getIndex();
			try {
				list = r_dao.selectAll();
				// System.out.println("------------------" + rmv.getList_tf());
				// 변경 가능한 시간인지 확인하고, 변경 가능한 시간이라면 업데이트를 수행하고,
				// 부모창에 있는 시간을 변경

				int count = (index * 6) + 5;
				list = r_dao.selectAll();
				rmvv = list.get(index);
				String chage_outTime = (Integer.parseInt(list.get(index).getOut_time())
						+ at.getCombo().getSelectedIndex() + 1) + "";
				System.out.println(chage_outTime);
				rmv.getList_tf().get(count).setText(chage_outTime + "시");
				System.out.println(rmvv.getRes_id());
				r_dao.plus_time(at.getCombo().getSelectedIndex() + 1, rmvv.getRes_id());

				at.dispose();

				// rmv.ResMgrView(cnt, list);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			at.dispose();
		}

		// 취소버튼을 누를 때
		if (e.getSource() == at.getBtn_cancle()) {
			at.dispose();
		}
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		at.dispose();
	}// windowClosing
}
