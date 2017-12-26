package kr.co.sist.manager.view;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import kr.co.sist.manager.controller.ManagerViewEvt;

@SuppressWarnings("serial")
public class ManagerView extends JFrame {

	private JTabbedPane tpTab;
	private String id;

	//private Server ser;
	
	public ManagerView() {
		super("주) 스터디 룸 관리 - 관리자");
		tpTab= new JTabbedPane();
		add("Center", tpTab);
		
		ManagerViewEvt mve = new ManagerViewEvt(this);
		tpTab.addMouseListener(mve);// JTab 이벤트
		
		setBounds(100, 100, 1000, 650);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//ClientMainFrm
	
	public JTabbedPane getTpTab() {
		return tpTab;
	}

	public void setTpTab(JTabbedPane tpTab) {
		this.tpTab = tpTab;
	}
}//class
