package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.co.sist.client.controller.ResChkFrmEvt;

@SuppressWarnings("serial")
public class ResChkFrm extends JPanel {

	private JTabbedPane jtRoom;
	private DefaultTableModel dtmRoom;
	private JTable jtRes;
	private JLabel lblRes, lblpng;
	private JButton btnHistory, btnCancel;
	private JPanel jpRes;

	private String id, pass;

	public ResChkFrm(String id, String pass) {
		this.id = id;
		this.pass = pass;
		String[] resStatus = { "예약코드", "이름", "방번호", "인원수", "이용금액", "예약시간", "예약날짜" };
		String[][] resData = { { "", "", "", "", "", "", "" } };
		setLayout(null);
		// defaultTableModel을 생성하고 값 할당
		dtmRoom = new DefaultTableModel(resData, resStatus);
		
		// defaultTableModel을 사용하여 JTable 생성
		jtRes = new JTable(dtmRoom) {

			// 컬럼의 내용편집 막기
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getCloumnClass
		};//

		// 컬럼의 넓이 변경
		jtRes.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtRes.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtRes.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtRes.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtRes.getColumnModel().getColumn(4).setPreferredWidth(150);
		jtRes.getColumnModel().getColumn(5).setPreferredWidth(150);
		jtRes.getColumnModel().getColumn(6).setPreferredWidth(150);
		// 컬럼의 높이 변경
		jtRes.setRowHeight(30);
		// 컬럼의 이동 막기
		jtRes.getTableHeader().setReorderingAllowed(false);
		

		lblRes = new JLabel("예약현황");

		// JTable 컬럼명 보여주기
		JScrollPane jspRes = new JScrollPane(jtRes);
		jspRes.getViewport().setBackground(Color.white);

		String path=System.getProperty("user.dir");
		
		JTableHeader header=jtRes.getTableHeader();
		header.setBackground(new Color(0x9ECC57));
		
		btnHistory = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/history.png"));
		btnCancel = new JButton(new ImageIcon(path+"/src/kr/co/sist/studyroom/img/cancel.png"));
		
		lblpng=new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/studyroom/img/resmike.png"));
		lblpng.setBounds(600, 30, 200, 60);
		
		
		lblRes.setBounds(70, 30, 150, 50);
		jspRes.setBounds(90, 90, 810, 400);
		btnHistory.setBounds(250, 500, 150, 50);
		btnCancel.setBounds(600, 500, 150, 50);
		

		add(lblpng);
		add(lblRes);
		add(jspRes);
		add(btnHistory);
		add(btnCancel);

		// 이벤트 등록
		ResChkFrmEvt rcfe = new ResChkFrmEvt(this);
		jtRes.addMouseListener(rcfe);// jtable이벤트
		btnHistory.addActionListener(rcfe);// button이벤트
		btnCancel.addActionListener(rcfe);// button이벤트
		
		setBackground(Color.WHITE);
		setBounds(100, 100, 1000, 600);
		setVisible(true);

	}// ResChkFrm

	public JTabbedPane getJtRoom() {
		return jtRoom;
	}

	public DefaultTableModel getDtmRoom() {
		return dtmRoom;
	}

	public JTable getJtRes() {
		return jtRes;
	}

	public JPanel getJpRes() {
		return jpRes;
	}

	public JLabel getLblRes() {
		return lblRes;
	}

	public JButton getBtnHistory() {
		return btnHistory;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

}// class
