package kr.co.sist.manager.view;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import kr.co.sist.manager.controller.ManagerViewEvt;

@SuppressWarnings("serial")
public class ManagerView extends JFrame {

	private JTabbedPane tpTab;
	private String id;
	
	public ManagerView() {
		super("주) 스터디 룸 관리 - 관리자");
		tpTab= new JTabbedPane();
		add("Center", tpTab);
		
		ManagerViewEvt mve = new ManagerViewEvt(this);
		tpTab.addMouseListener(mve);// JTab 이벤트
		
		setBounds(100, 100, 1000, 650);
		setVisible(true);
		setResizable(false);
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose(); //windowClosed호출
			}//windowClosing
			@Override
			public void windowClosed(WindowEvent we) {
				//mve.close();
			}//windowClosed
		});
		
	}//ClientMainFrm
	
	public JTabbedPane getTpTab() {
		return tpTab;
	}

	public void setTpTab(JTabbedPane tpTab) {
		this.tpTab = tpTab;
	}
}//class
