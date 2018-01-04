package kr.co.sist.client.frm;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.co.sist.client.controller.HistoryFrmEvt;
import kr.co.sist.client.controller.ResChkFrmEvt;

@SuppressWarnings("serial")
public class HistoryFrm extends JDialog {

	private ResChkFrm rcf;

	private JTable jtHistory;
	private JLabel lblWho, lblpng;
	private DefaultTableModel dtmHistory;

	private String id;

	public HistoryFrm(ResChkFrm rcf, ResChkFrmEvt rcfe, String id) {
		this.rcf = rcf;// �̰� ���ϴ°���??
		this.id = id;
		String[] resHis = { "��¥", "�̸�", "���ȣ", "�ο���", "�̿�ݾ�", "����ð�" };
		String[][] resData = { { "", "", "", "", "", "" } };
		// defaultTableModel�� �����ϰ� �� �Ҵ�
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

		// �÷��� ���� ����
		jtHistory.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtHistory.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtHistory.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtHistory.getColumnModel().getColumn(3).setPreferredWidth(60);
		jtHistory.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtHistory.getColumnModel().getColumn(5).setPreferredWidth(130);
		

		// �÷��� ���� ����
		jtHistory.setRowHeight(30);
		// �÷��� �̵� ����
		jtHistory.getTableHeader().setReorderingAllowed(false);

		JScrollPane jspHis = new JScrollPane(jtHistory);
		jspHis.getViewport().setBackground(Color.white);
		lblWho=new JLabel("���೻��");
		
//		lblpng=new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/studyroom/img/historysully.jpg"));
		URL url1 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/historysully.png");
		lblpng=new JLabel(new ImageIcon(url1));
		
		new HistoryFrmEvt(this, rcfe,id);
		setLayout(null);
		
		JTableHeader header=jtHistory.getTableHeader();
		header.setBackground(new Color(0x63B7BB));
		
		lblpng.setBounds(500, 40, 200, 60);
		lblWho.setBounds(350, 30, 200, 30);
		jspHis.setBounds(50, 100, 700, 500);
		add(lblpng);
		add(lblWho);
		add(jspHis);
		setBounds(100, 100, 800, 650);
		this.getContentPane().setBackground(Color.white);
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
