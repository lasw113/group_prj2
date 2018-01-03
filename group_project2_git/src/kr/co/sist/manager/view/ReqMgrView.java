package kr.co.sist.manager.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.manager.controller.ServerHelper;

@SuppressWarnings("serial")
public class ReqMgrView extends JPanel implements Runnable,ActionListener{
	private JButton[] btnRoom;
	private ChatMgrView[] cmv;
	private String[] room_num; 
	int[] pNum;
	private Thread tListen;
	private int[] serverport;
	private JLabel lblImg;
	private JLabel[] isInLabel; 
	public static List<ServerHelper> listServer;
	public ImageIcon[] btnImageBefore;
	public ImageIcon[] btnImageAfter;
	public ImageIcon inImg1,inImg2,whiteImg;
	
	public ReqMgrView() {
		
		serverport=new int[9];
		btnImageBefore = new ImageIcon[9];
		btnImageAfter = new ImageIcon[9];
		isInLabel = new JLabel[9];
		inImg1 = new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/HalfMike.png");
		inImg2 = new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/HalfSully.png");
		whiteImg = new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/white_lblIn.png");
		btnRoom=new JButton[9];
		cmv= new ChatMgrView[9];
		listServer = new ArrayList<ServerHelper>();		
		room_num = new String[] {"S_01","S_02","S_03","S_04","M_05","M_06","M_07","L_08","X_09"};
		pNum = new int[] {65000,64900,64800,64700,64600,64500,64400,64300,64200,64100};
		lblImg = new JLabel(new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/mike2.png"));

		for(int i=0 ; i < serverport.length ; i++  ) {
			serverport[i]=pNum[i];
			btnImageBefore[i] = new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/btn_d_"+room_num[i]+".png"); 
			btnImageAfter[i] = new ImageIcon("C:/dev/git/group_prj2/group_project2_git/src/kr/co/sist/studyroom/img/btn_"+room_num[i]+".png");
			btnRoom[i]=new JButton(btnImageBefore[i]);
			isInLabel[i]= new JLabel();

			btnRoom[i].setName(room_num[i]);
		}//end for
		
		setLayout(null);
		 	lblImg.setBounds(755, 250, 240, 278);
		 	add(lblImg);
		 for(int i=0;i<3;i++) {
			 System.out.println(i);
			 btnRoom[i].setBounds(250*i+60,100, 190, 90);
			 btnRoom[i+3].setBounds(250*i+60, 265, 190, 90);
			 btnRoom[i+6].setBounds(250*i+60, 430, 190, 90);
			 
			 isInLabel[i].setBounds(250*i+95, 45, 115, 55);
			 isInLabel[i+3].setBounds(250*i+95, 210, 115, 55);
			 isInLabel[i+6].setBounds(250*i+95, 375, 115, 55);
			 
		 }//end for
		 for(int i=0;i<9;i++) {
			 
			 add(btnRoom[i]);
			 add(isInLabel[i]);
			 
			 btnRoom[i].setToolTipText(room_num[i]+"접속자가 없습니다.");
			 btnRoom[i].addActionListener(this);
			 btnRoom[i].setBorderPainted(false);// 외곽선 제거
			 btnRoom[i].setContentAreaFilled(false); // 내용영역 채우기 x

			 cmv[i] = new ChatMgrView(room_num[i],serverport[i],this);
			 
		 }//end for
		 
		 setBackground(Color.white);
		 isStart();
	}
	
	private void isStart() {
		if(tListen == null) {
			tListen=new Thread(this);
			tListen.start();
			JOptionPane.showMessageDialog(this, "서버가동시작");
		}//end if
	}//isStart
	
	public JButton[] getBtnRoom() {
		return btnRoom;
	}//getBtnRoom
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton button = (JButton)ae.getSource();
		System.out.println(button.getName());
		for(int i=0;i<9;i++) {
			System.out.println(cmv[i].getRoom_id());
			if(cmv[i].getRoom_id().equals(button.getName())) {
				cmv[i].setVisible(true);
				button.setIcon(btnImageBefore[i]);
				isInLabel[i].setIcon(whiteImg);
			}
			System.out.println(listServer);
			System.out.println(listServer.get(i)+"쿠크쿠크");
		}	
	}
	@Override
	public void run() {
		for(int i=0 ; i < serverport.length ; i++) {
			ServerHelper sh=new ServerHelper(this,btnRoom[i] ,serverport[i],isInLabel[i]);
			listServer.add(sh);
			System.out.println(listServer);
			sh.start();
		}
		while(true) {
			for(int i=0 ; i < serverport.length ; i++) {
				if(listServer.get(i).chk !=false) {
					System.out.println(listServer.get(i));
					listServer.remove(i);
					System.out.println(i+"번 째"+"없앴어");
					ServerHelper sh=new ServerHelper(this,btnRoom[i] ,serverport[i],isInLabel[i]);
					listServer.add(sh);
					System.out.println(sh);
					sh.start();
					btnRoom[i].setIcon(btnImageBefore[i]);
					btnRoom[i].setToolTipText(room_num[i]+"접속자가 없습니다.");
					isInLabel[i].setIcon(whiteImg);
					cmv[i].getJtaChat().setText(room_num[i]+" 에서 보내온 메세지입니다. \n");
				}
			}
		}
		
	}//run
	public int[] getServerport() {
		return serverport;
	}//getServerport
	

	public ChatMgrView[] getCmv() {
		return cmv;
	}

	public void setCmv(ChatMgrView[] cmv) {
		this.cmv = cmv;
	}

	public  List<ServerHelper> getListServer() {
		return listServer;
	}

	public void setListServer(List<ServerHelper> listServer) {
		ReqMgrView.listServer = listServer;
	}
}//class
