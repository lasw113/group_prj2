package kr.co.sist.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import kr.co.sist.client.frm.RequestFrm;

public class RequestFrmEvt implements Runnable, ActionListener {
	private Socket client;
	private DataInputStream readStream;
	private DataOutputStream writeStream;
	private RequestFrm rf;
	private String id;
	private String room_id;
	private int portNum;

	public RequestFrmEvt(RequestFrm rf) {
		this.rf = rf;
		id = rf.getId();
		room_id = rf.getRoom_id();

		portNum = pNum(room_id);

		try {
			connectToServer(portNum);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("�ش� ��Ʈ �ȿ���aaaaa");
			e.printStackTrace();
		}
	}// RequestFrmEvt

	public void admission(String id) {

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == rf.getBtnSent()) {
			try {
				sendMsg();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end if
	}// actionPerformed

	private void connectToServer(int portNum) throws UnknownHostException, IOException {
		// ������ �����Ͽ� ������ �����ϴ� ��
		// 211.63.89.xx :ipV4(A class
		// String ip = JOptionPane.showInputDialog(" ���� �ּ� �Է� ������ ���� �Է�");
		// client = new Socket("211.63.89."+ip,65000);
		System.out.println("????????");
		client = new Socket("211.63.89.147", portNum);
		System.out.println(portNum);
		System.out.println(client.getLocalPort());

		// ������ �Ǹ� �޼����� �ְ� �ޱ� ���ؼ� ��Ʈ�� ����
		readStream = new DataInputStream(client.getInputStream());
		writeStream = new DataOutputStream(client.getOutputStream());

		// taTalkDisplay.setText(ip + "�� ������ ���� �Ͽ����ϴ�. \n");
		// �޼����� �о���̴� ����
		// readMsg();//���� ������ ���� �����Ƿ� ����
		Thread th = new Thread(this);
		th.start(); // run()ȣ��
	}// connectToServer

	/**
	 * �����ڰ� �������� �޼����� ���� loop�� �д� �� ���ÿ� ����Ǿ�� �ϹǷ� Thread�� ó��
	 */
	@Override
	public void run() {
		try {
			String msg = "";
			while (true) {
				msg = readStream.readUTF();
				// ��ȭâ�� ����
				rf.getJtaChat().append(msg);
			} // end while
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
	}// run

	private void sendMsg() throws IOException {
		// �Է��� �޼��� �ޱ�
		String msg = "[ " + room_id + " ] " + rf.getJtfMessage().getText();

		// ��ȭâ�� �Է��� ��ȭ�� �÷��ְ�
		rf.getJtaChat().append(msg + "\n");
		// ���濡�� ������.
		writeStream.writeUTF(msg);
		writeStream.flush();
		// T.F�� ������ �ʱ�ȭ
		rf.getJtfMessage().setText("");
		rf.getJtfMessage().requestFocus();
	}// sendMsg

	private int pNum(String room_id) {
		int num = 0;
		String[] room_num = new String[] { "S_01", "S_02", "S_03", "S_04", "M_01", "M_02", "M_03", "L_01", "X_01" };
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
