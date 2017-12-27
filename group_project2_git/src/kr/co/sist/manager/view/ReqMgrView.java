package kr.co.sist.manager.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
	public static List<ServerHelper> listServer; 
	
	public ReqMgrView() {
		
		serverport=new int[9];
		btnRoom=new JButton[9];
		cmv= new ChatMgrView[9];
		listServer = new ArrayList<ServerHelper>();		
		room_num = new String[] {"S_01","S_02","S_03","S_04","M_05","M_06","M_07","L_08","X_09"};
		pNum = new int[] {65000,64900,64800,64700,64600,64500,64400,64300,64200,64100};

		for(int i=0 ; i < serverport.length ; i++  ) {
			serverport[i]=pNum[i];
			btnRoom[i]=new JButton(room_num[i]);
		}//end for
		
		setLayout(null);
		 
		 for(int i=0;i<3;i++) {
			 System.out.println(i);
			 btnRoom[i].setBounds(300*i+60,40, 250, 155);
			 add(btnRoom[i]);
			 btnRoom[i+3].setBounds(300*i+60, 220, 250, 155);
			 add(btnRoom[i+3]);
			 btnRoom[i+6].setBounds(300*i+60, 400, 250, 155);
			 add(btnRoom[i+6]);
		 }//end for
		 for(int i=0;i<9;i++) {
			 btnRoom[i].setBackground(Color.lightGray);
			 btnRoom[i].addActionListener(this);
			 cmv[i] = new ChatMgrView(room_num[i],serverport[i],this);
		 }//end for
		 setBackground(Color.white);
		 isStart();
	}
	
	private void isStart() {
		if(tListen == null) {
			tListen=new Thread(this);
			tListen.start();
			JOptionPane.showMessageDialog(this, "������������");
		}//end if
	}//isStart
	
	public JButton[] getBtnRoom() {
		return btnRoom;
	}//getBtnRoom

	public int[] getServerport() {
		return serverport;
	}//getServerport
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton button = (JButton)ae.getSource();
		String command = button.getActionCommand();
		command=command.substring(0, 4);
		System.out.println(command);
		for(int i=0;i<9;i++) {
			System.out.println(cmv[i].getRoom_id());
			if(cmv[i].getRoom_id().equals(command)) {
				cmv[i].setVisible(true);
			}
			System.out.println(listServer);
			System.out.println(listServer.get(i)+"��ũ��ũ");
		}	
		button.setBackground(Color.LIGHT_GRAY);
	}
	@Override
	public void run() {
		for(int i=0 ; i < serverport.length ; i++) {
			ServerHelper sh=new ServerHelper(this,btnRoom[i] ,serverport[i]);
			listServer.add(sh);
			System.out.println(listServer);
			sh.start();
		}
		while(true) {
			for(int i=0 ; i < serverport.length ; i++) {
				if(listServer.get(i).chk !=false) {
					System.out.println(listServer.get(i));
					listServer.remove(i);
					System.out.println(i+"�� °"+"���ݾ�");
					ServerHelper sh=new ServerHelper(this,btnRoom[i] ,serverport[i]);
					listServer.add(sh);
					System.out.println(sh);
					sh.start();
					cmv[i].getJtaChat().setText(room_num[i]+" ���� ������ �޼����� �����ϴ�. \n");
				}
			}
		}
		
	}//run

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
