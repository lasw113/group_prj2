package kr.co.sist.manager.view;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.co.sist.manager.controller.MgrHistoryViewEvt;
import kr.co.sist.manager.controller.UserMgrViewEvt;

@SuppressWarnings("serial")
public class MgrHistoryView extends JDialog{

	private UserMgrView umv;
	private UserMgrViewEvt umve;
	
	private JTable jtMgrHis;
	private JLabel lblHis;
	private DefaultTableModel dtmMgrHis;
	
	private String id;

	
	public MgrHistoryView(UserMgrView umv, UserMgrViewEvt umve, String id) {
//		super(umv,"���೻��",true);//���⼭ true�ϴ� ����
		this.umv=umv;
		this.umve=umve;
		this.id=id;
		
		String[] resHis = { "��¥", "�̸�", "���ȣ", "�ο���", "�̿�ݾ�", "����ð�" };
		String[][] resData = { { "", "", "", "", "", "" } };
		// defaultTableModel�� �����ϰ� �� �Ҵ�
		dtmMgrHis = new DefaultTableModel(resData, resHis);
		jtMgrHis = new JTable(dtmMgrHis) {

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
		jtMgrHis.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtMgrHis.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtMgrHis.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtMgrHis.getColumnModel().getColumn(3).setPreferredWidth(60);
		jtMgrHis.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtMgrHis.getColumnModel().getColumn(5).setPreferredWidth(130);

		// �÷��� ���� ����
		jtMgrHis.setRowHeight(30);
		// �÷��� �̵� ����
		jtMgrHis.getTableHeader().setReorderingAllowed(false);

		JScrollPane jspHis = new JScrollPane(jtMgrHis);
		
		jspHis.getViewport().setBackground(Color.white);
		new MgrHistoryViewEvt(this, umve,id);
		
		JTableHeader header=jtMgrHis.getTableHeader();
		header.setBackground(new Color(0x63B7BB));

		add("Center", jspHis);
		this.getContentPane().setBackground(Color.white);
		setBounds(100, 100, 800, 650);
		setResizable(false);
		setVisible(true);
	}//MgrHistoryView

	public UserMgrView getUmv() {
		return umv;
	}

	public UserMgrViewEvt getUmve() {
		return umve;
	}

	public JTable getJtMgrHis() {
		return jtMgrHis;
	}

	public JLabel getLblHis() {
		return lblHis;
	}

	public DefaultTableModel getDtmMgrHis() {
		return dtmMgrHis;
	}
	public String getId() {
		return id;
	}
	
}//class
