package kr.co.sist.client.frm;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ClientMainFrm extends JFrame {
	private JTabbedPane jtpClient;
	
	public ClientMainFrm() {
		jtpClient = new JTabbedPane();
		jtpClient.add("∑Î¡§∫∏(øπæ‡)", new RoomInfoFrm());
		
		add(jtpClient);
		
		setBounds(100, 100, 500, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//RoomUserFrm
	
	public static void main(String[] args) {
		new ClientMainFrm();
	}//main
	
}//class
