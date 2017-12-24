package kr.co.sist.client.frm;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ClientMainFrm extends JFrame {
	private JTabbedPane jtpClient;
	
	public ClientMainFrm(String id) {
		jtpClient = new JTabbedPane();
		
		JPanel roomInfo = new RoomInfoFrm(id);
		roomInfo.setBackground(Color.white);
		
		JScrollPane jspRoomInfo = new JScrollPane(roomInfo);
		
		jtpClient.add("룸정보(예약)", jspRoomInfo);		
		jtpClient.addTab("예약확인", new JPanel());
		jtpClient.addTab("건의 사항", new JPanel());
		jtpClient.addTab("개인정보 수정", new JPanel());

		add(jtpClient);
		
		setBounds(100, 100, 1000, 600);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//RoomUserFrm
	
	public static void main(String[] args) {
		new ClientMainFrm("ham");
	}//main
	
}//class
