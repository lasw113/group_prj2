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
		String[] resStatus = { "�����ڵ�", "�̸�", "���ȣ", "�ο���", "�̿�ݾ�", "����ð�", "���೯¥" };
		String[][] resData = { { "", "", "", "", "", "", "" } };
		setLayout(null);
		// defaultTableModel�� �����ϰ� �� �Ҵ�
		dtmRoom = new DefaultTableModel(resData, resStatus);
		
		// defaultTableModel�� ����Ͽ� JTable ����
		jtRes = new JTable(dtmRoom) {

			// �÷��� �������� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getCloumnClass
		};//

		// �÷��� ���� ����
		jtRes.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtRes.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtRes.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtRes.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtRes.getColumnModel().getColumn(4).setPreferredWidth(150);
		jtRes.getColumnModel().getColumn(5).setPreferredWidth(150);
		jtRes.getColumnModel().getColumn(6).setPreferredWidth(150);
		// �÷��� ���� ����
		jtRes.setRowHeight(30);
		// �÷��� �̵� ����
		jtRes.getTableHeader().setReorderingAllowed(false);
		

		lblRes = new JLabel("������Ȳ");

		// JTable �÷��� �����ֱ�
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

		// �̺�Ʈ ���
		ResChkFrmEvt rcfe = new ResChkFrmEvt(this);
		jtRes.addMouseListener(rcfe);// jtable�̺�Ʈ
		btnHistory.addActionListener(rcfe);// button�̺�Ʈ
		btnCancel.addActionListener(rcfe);// button�̺�Ʈ
		
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
