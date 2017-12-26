package kr.co.sist.client.frm;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.client.controller.HistoryFrmEvt;
import kr.co.sist.client.controller.ResChkFrmEvt;

@SuppressWarnings("serial")
public class HistoryFrm extends JDialog {

	private ResChkFrm rcf;

	private JTable jtHistory;
	private JLabel lblWho;
	private DefaultTableModel dtmHistory;

	private String id;

	public HistoryFrm(ResChkFrm rcf, ResChkFrmEvt rcfe, String id) {
		this.rcf = rcf;// 이거 왜하는거지??
		this.id = id;
		String[] resHis = { "날짜", "이름", "방번호", "인원수", "이용금액", "예약시간" };
		String[][] resData = { { "", "", "", "", "", "" } };
		// defaultTableModel을 생성하고 값 할당
		dtmHistory = new DefaultTableModel(resData, resHis);
		jtHistory = new JTable(dtmHistory) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};

		// 컬럼의 넓이 변경
		jtHistory.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtHistory.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtHistory.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtHistory.getColumnModel().getColumn(3).setPreferredWidth(60);
		jtHistory.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtHistory.getColumnModel().getColumn(5).setPreferredWidth(130);

		// 컬럼의 높이 변경
		jtHistory.setRowHeight(30);
		// 컬럼의 이동 막기
		jtHistory.getTableHeader().setReorderingAllowed(false);

		JScrollPane jspHis = new JScrollPane(jtHistory);

		JPanel jpHis = new JPanel();
		jpHis.add(jspHis);

		lblWho=new JLabel("예약내역");
		
		new HistoryFrmEvt(this, rcfe,id);
		setLayout(null);
		
		lblWho.setBounds(350, 30, 200, 30);
		jspHis.setBounds(50, 100, 700, 500);
		add(lblWho);
		add(jspHis);
		setBounds(100, 100, 800, 650);
		setResizable(false);
		setVisible(true);

	}// HistoryFrm

	
	public ResChkFrm getRcf() {
		return rcf;
	}// ResChkFrm

	public JTable getJtHistory() {
		return jtHistory;
	}// getJtHistory

	public JLabel getLblWho() {
		return lblWho;
	}// getLblWho

	public DefaultTableModel getDtmHistory() {
		return dtmHistory;
	}// getDtmHistory

	public String getId() {
		return id;
	}//getId

}// class
