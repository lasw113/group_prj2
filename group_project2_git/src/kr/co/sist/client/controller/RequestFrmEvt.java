package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.JOptionPane;

import kr.co.sist.client.frm.RequestFrm;

public class RequestFrmEvt implements Runnable, ActionListener {
	private Socket client;
	private DataInputStream readStream;
	private DataOutputStream writeStream;
	private RequestFrm rf;
	private String room_id;
	private int portNum;

	public RequestFrmEvt(RequestFrm rf) {
		this.rf = rf;
		room_id = rf.getRoom_id();

		portNum = pNum(room_id);

		try {
			connectToServer(portNum);
			rf.setFlagMgrIn(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "관리자가 접속중이 아닙니다. 잠시후 다시 시도해주세요");
			rf.setFlagMgrIn(false);
			System.out.println("해당 포트 안열림aaaaa");
			e.printStackTrace();
		}
	}// RequestFrmEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == rf.getBtnSent()||ae.getSource()==rf.getJtfMessage()) {
			try {
				sendMsg();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end if
		
	}// actionPerformed

	private void connectToServer(int portNum) throws UnknownHostException, IOException {
		// 소켓을 생성하여 서버에 연결하는 일
		// 211.63.89.xx :ipV4(A class
		// String ip = JOptionPane.showInputDialog(" 서버 주소 입력 마지막 구간 입력");
		// client = new Socket("211.63.89."+ip,65000);
		Properties prop = new Properties();
		String path = System.getProperty("user.dir");
		prop.load(new FileReader(
				path+"/src/kr/co/sist/client/dao/database.properties"));
		String ip = prop.getProperty("ip");
		client = new Socket(ip, portNum);
		System.out.println(portNum);
		System.out.println(client.getLocalPort());

		// 연결이 되면 메세지를 주고 받기 위해서 스트림 연결
		readStream = new DataInputStream(client.getInputStream());
		writeStream = new DataOutputStream(client.getOutputStream());

		// taTalkDisplay.setText(ip + "번 서버에 접속 하였습니다. \n");
		// 메세지를 읽어들이는 동작
		// readMsg();//동시 수행이 되지 않으므로 삭제
		Thread th = new Thread(this);
		th.start(); // run()호출
	}// connectToServer

	/**
	 * 접속자가 보내오는 메세지를 무한 loop로 읽는 일 동시에 실행되어야 하므로 Thread로 처리
	 */
	@Override
	public void run() {
		while (true) {
		try {
			String msg = "";
			msg = readStream.readUTF();
			// 대화창에 설정
			rf.getJtaChat().append(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
		} // end while
	}// run

	private void sendMsg() throws IOException {
		// 입력한 메세지 받기
		String msg = "[ " + room_id + " ] " + rf.getJtfMessage().getText();

		// 대화창에 입력한 대화를 올려주고
		rf.getJtaChat().append(msg + "\n");
		// 상대방에게 보낸다.
		writeStream.writeUTF(msg);
		writeStream.flush();
		// T.F의 내용을 초기화
		rf.getJtfMessage().setText("");
		rf.getJtfMessage().requestFocus();
	}// sendMsg

	private int pNum(String room_id) {
		int num = 0;
		String[] 	room_num = new String[] {"S_01","S_02","S_03","S_04","M_05","M_06","M_07","L_08","X_09"};
		int[] pNum = new int[] { 65000, 64900, 64800, 64700, 64600, 64500, 64400, 64300, 64200, 64100 };

		for (int i = 0; i < room_num.length; i++) {
			if (room_num[i].equals(room_id)) {
				num = pNum[i];
				break;
			}
		}
		return num;
	}
}// class
