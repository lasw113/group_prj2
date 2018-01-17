package kr.co.sist.manager.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.manager.controller.ServerHelper;

@SuppressWarnings("serial")
public class ReqMgrView extends JPanel implements Runnable, ActionListener {
	private JButton[] btnRoom;
	private ChatMgrView[] cmv;
	private String[] room_num;
	private int[] pNum; //port번호를 담을 변수 
	private Thread tListen;
	private int[] serverport;
	private JLabel lblImg;
	private JLabel[] isInLabel;
	public static List<ServerHelper> listServer; //ServerHelper를 여러개 돌린 정보를 저장할 list
	public ImageIcon[] btnImageBefore;
	public ImageIcon[] btnImageAfter;
	public ImageIcon inImg1, inImg2, whiteImg;

	public ReqMgrView() {
		serverport = new int[9];
		btnImageBefore = new ImageIcon[9];
		btnImageAfter = new ImageIcon[9];
		isInLabel = new JLabel[9];

		URL url1 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/HalfMike.png");
		URL url2 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/halfSully.png");
		URL url3 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/white_lblIn.png");
		URL url4 = getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/mike2.png");

		// String path = System.getProperty("user.dir");

		inImg1 = new ImageIcon(url1);
		inImg2 = new ImageIcon(url2);
		whiteImg = new ImageIcon(url3);
		// inImg1 = new ImageIcon(path+"/src/kr/co/sist/studyroom/img/HalfMike.png");
		// inImg2 = new ImageIcon(path+"/src/kr/co/sist/studyroom/img/HalfSully.png");
		// whiteImg = new
		// ImageIcon(path+"/src/kr/co/sist/studyroom/img/white_lblIn.png");
		btnRoom = new JButton[9];
		cmv = new ChatMgrView[9];
		listServer = new ArrayList<ServerHelper>();
		room_num = new String[] { "S_01", "S_02", "S_03", "S_04", "M_05", "M_06", "M_07", "L_08", "X_09" };
		pNum = new int[] { 65000, 64900, 64800, 64700, 64600, 64500, 64400, 64300, 64200, 64100 };
		lblImg = new JLabel(new ImageIcon(url4));
		// URL
		// url1=getClass().getClassLoader().getResource("kr/co/sist/studyroom/img/HalfMike.png");
		URL[] urlB = new URL[9];
		URL[] urlA = new URL[9];
		String before, after;
		for (int i = 0; i < serverport.length; i++) {
			before = "kr/co/sist/studyroom/img/btn_d_" + room_num[i] + ".png";
			after = "kr/co/sist/studyroom/img/btn_" + room_num[i] + ".png";
			urlB[i] = getClass().getClassLoader().getResource(before);
			urlA[i] = getClass().getClassLoader().getResource(after);
			serverport[i] = pNum[i];
			btnImageBefore[i] = new ImageIcon(urlB[i]);
			btnImageAfter[i] = new ImageIcon(urlA[i]);
			// btnImageBefore[i] = new
			// ImageIcon(path+"/src/kr/co/sist/studyroom/img/btn_d_"+room_num[i]+".png");
			// btnImageAfter[i] = new
			// ImageIcon(path+"/src/kr/co/sist/studyroom/img/btn_"+room_num[i]+".png");
			btnRoom[i] = new JButton(btnImageBefore[i]);
			isInLabel[i] = new JLabel();

			btnRoom[i].setName(room_num[i]);
		} // end for

		setLayout(null);
		lblImg.setBounds(755, 250, 240, 278);
		add(lblImg);
		for (int i = 0; i < 3; i++) {
			btnRoom[i].setBounds(250 * i + 60, 100, 190, 90);
			btnRoom[i + 3].setBounds(250 * i + 60, 265, 190, 90);
			btnRoom[i + 6].setBounds(250 * i + 60, 430, 190, 90);

			isInLabel[i].setBounds(250 * i + 95, 45, 115, 55);
			isInLabel[i + 3].setBounds(250 * i + 95, 210, 115, 55);
			isInLabel[i + 6].setBounds(250 * i + 95, 375, 115, 55);

		} // end for
		for (int i = 0; i < 9; i++) {

			add(btnRoom[i]);
			add(isInLabel[i]);

			btnRoom[i].setToolTipText(room_num[i] + "접속자가 없습니다.");
			btnRoom[i].addActionListener(this);
			btnRoom[i].setBorderPainted(false);// 외곽선 제거
			btnRoom[i].setContentAreaFilled(false); // 내용영역 채우기 x

			cmv[i] = new ChatMgrView(room_num[i], serverport[i], this);

		} // end for

		setBackground(Color.white);
		isStart();
	}//ReqMgrView

	private void isStart() {
		if (tListen == null) {
			tListen = new Thread(this);
			tListen.start();
			JOptionPane.showMessageDialog(this, "서버가동시작");
		} // end if
	}// isStart

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton button = (JButton) ae.getSource();
		for (int i = 0; i < 9; i++) {
			if (cmv[i].getRoom_id().equals(button.getName())) {
				cmv[i].setVisible(true);
				button.setIcon(btnImageBefore[i]);
				isInLabel[i].setIcon(whiteImg);
			}//end if
		}//end for
	}//actionPerformed

	@Override
	public void run() {

		for (int i = 0; i < serverport.length; i++) {
			ServerHelper sh = new ServerHelper(this, btnRoom[i], serverport[i], isInLabel[i]);
			listServer.add(sh);
			sh.start();
		}//end for

		while (true) {
			for (int i = 0; i < listServer.size(); i++) {
				if (listServer.get(i).chk != false) {
					listServer.remove(i);
					ServerHelper sh = new ServerHelper(this, btnRoom[i], serverport[i], isInLabel[i]);
					listServer.add(i, sh);
					sh.start();
					//listServer.get(i).getPort();
					btnRoom[i].setIcon(btnImageBefore[i]);
					btnRoom[i].setToolTipText(room_num[i] + "접속자가 없습니다.");
					isInLabel[i].setIcon(whiteImg);
					cmv[i].getJtaChat().setText(room_num[i] + " 에서 보내온 메세지입니다. \n");
					
				}//end if
			}//end for
		}//end while
	}// run

	public JButton[] getBtnRoom() {
		return btnRoom;
	}// getBtnRoom

	public int[] getServerport() {
		return serverport;
	}// getServerport

	public ChatMgrView[] getCmv() {
		return cmv;
	}

	public void setCmv(ChatMgrView[] cmv) {
		this.cmv = cmv;
	}

	public List<ServerHelper> getListServer() {
		return listServer;
	}

	public void setListServer(List<ServerHelper> listServer) {
		ReqMgrView.listServer = listServer;
	}
}// class
