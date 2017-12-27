package kr.co.sist.manager.controller;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.co.sist.manager.view.ChatMgrView;
import kr.co.sist.manager.view.ReqMgrView;


public class ServerHelper extends Thread{
	private ServerSocket ss;
	private Socket client;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	private JButton btnDisp;
	private ReqMgrView rmv;
	private ChatMgrView[] cmv;
	private int cmvIndex;
	public boolean chk;
	
	private int port;
	
	public ServerHelper(ReqMgrView rmv,JButton btnDisp,int port) {
		this.rmv= rmv;
		this.port=port;
		this.btnDisp=btnDisp;
		cmv=rmv.getCmv();
		whichCMV(port);
		chk=false;
	}//ServerHelper
	
	public void run() {
		try {
			ss=new ServerSocket(port);
			String originMsg= btnDisp.getText();
			btnDisp.setText(originMsg);
			btnDisp.setText(originMsg+"���� ����");
			btnDisp.setToolTipText(originMsg+"���� ����");
			client=ss.accept();
			//��ȭ�� �а� ������ �ֵ��� ��Ʈ�� ����
			disReadStream = new DataInputStream(client.getInputStream());
			dosWriteStream = new DataOutputStream(client.getOutputStream());
			//btnDisp.setBackground(Color.ORANGE);
			//JOptionPane.showInputDialog(client.getInetAddress()	+"�� �濡�� ��û��������");
		}catch(java.net.BindException be){
			return;
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while(true) {
				//�޼����� �о�鿩 ��ȭâ�� �����Ѵ�. 
				String msg="";
					msg =disReadStream.readUTF();
					btnDisp.setBackground(Color.ORANGE);
					cmv[cmvIndex].getJtaChat().append(msg +"\n");
				
			}//end while
		}catch(EOFException eof) { 
			JOptionPane.showMessageDialog(null, "������ ���� ����");
			btnDisp.setBackground(Color.LIGHT_GRAY);
		}catch (IOException e) {
			//e.printStackTrace();
		}//end catch
		try {
			chk=true;
			btnDisp.setToolTipText("��������");
			dosWriteStream.close();
			disReadStream.close();
			client.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//run
	
	/**
	 * �����ڿ��� �߻��� �޼����� ���� �� ������ �� 
	 * @throws IOException 
	 */
	public void sendMsg(String msg) throws IOException {
		String serverMsg= msg;
		System.out.println(serverMsg);
		System.out.println(dosWriteStream);
		dosWriteStream.writeUTF(serverMsg); // �޼����� ��Ʈ�� ����
		dosWriteStream.flush(); //��Ʈ���� �޼����� ���� �������� ����
	}//sendMsg
	
	private void whichCMV(int port) {
		for(int i=0;i<cmv.length;i++) {
			if(cmv[i].getP_num()==port) {
				cmvIndex=i;
			}
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}//class
