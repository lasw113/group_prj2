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
			btnDisp.setText(originMsg+"서버 열림");
			btnDisp.setToolTipText(originMsg+"서버 열림");
			client=ss.accept();
			//대화를 읽고 보낼수 있도록 스트림 연결
			disReadStream = new DataInputStream(client.getInputStream());
			dosWriteStream = new DataOutputStream(client.getOutputStream());
			//btnDisp.setBackground(Color.ORANGE);
			//JOptionPane.showInputDialog(client.getInetAddress()	+"번 방에서 요청사항있음");
		}catch(java.net.BindException be){
			return;
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while(true) {
				//메세지를 읽어들여 대화창에 설정한다. 
				String msg="";
					msg =disReadStream.readUTF();
					btnDisp.setBackground(Color.ORANGE);
					cmv[cmvIndex].getJtaChat().append(msg +"\n");
				
			}//end while
		}catch(EOFException eof) { 
			JOptionPane.showMessageDialog(null, "접속자 접속 종료");
			btnDisp.setBackground(Color.LIGHT_GRAY);
		}catch (IOException e) {
			//e.printStackTrace();
		}//end catch
		try {
			chk=true;
			btnDisp.setToolTipText("서버닫힘");
			dosWriteStream.close();
			disReadStream.close();
			client.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//run
	
	/**
	 * 접속자에게 발생된 메세지가 있을 때 보내는 일 
	 * @throws IOException 
	 */
	public void sendMsg(String msg) throws IOException {
		String serverMsg= msg;
		System.out.println(serverMsg);
		System.out.println(dosWriteStream);
		dosWriteStream.writeUTF(serverMsg); // 메세지를 스트림 쓰기
		dosWriteStream.flush(); //스트림의 메세지를 접속 소켓으로 분출
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
