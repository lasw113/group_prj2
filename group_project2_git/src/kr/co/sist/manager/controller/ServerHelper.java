package kr.co.sist.manager.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kr.co.sist.manager.view.ChatMgrView;
import kr.co.sist.manager.view.ReqMgrView;


public class ServerHelper extends Thread{
	private ServerSocket ss;
	private Socket client;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	private JButton btnDisp;
	private JLabel isInLabel;
	private ReqMgrView rmv;
	private ChatMgrView[] cmv;
	private int cmvIndex;
	public boolean chk,chkClientIn;
	
	private int port;
	
	public ServerHelper(ReqMgrView rmv,JButton btnDisp,int port,JLabel isInLabel) {
		this.rmv= rmv;
		this.port=port;
		this.btnDisp=btnDisp;
		this.isInLabel=isInLabel;
		cmv=rmv.getCmv();
		whichCMV(port);
		chk=false;
		chkClientIn=false;
	}//ServerHelper
	
	public void run() {
		try {
			ss=new ServerSocket(port);
			client=ss.accept();
			//대화를 읽고 보낼수 있도록 스트림 연결
			disReadStream = new DataInputStream(client.getInputStream());
			dosWriteStream = new DataOutputStream(client.getOutputStream());
			chkClientIn=true;
		}catch(java.net.BindException be){
			return;
		}catch(java.net.SocketException se) {
			//se.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			btnDisp.setToolTipText(btnDisp.getName()+"접속자가 있습니다~~");
			while(true) {
				//메세지를 읽어들여 대화창에 설정한다. 
				String msg="";
					msg =disReadStream.readUTF();
					if(cmv[cmvIndex].isVisible()==true) {
						btnDisp.setIcon(rmv.btnImageBefore[cmvIndex]);
						isInLabel.setIcon(rmv.whiteImg);
					}else {
					btnDisp.setIcon(rmv.btnImageAfter[cmvIndex]);
						if(cmvIndex<4) {
							isInLabel.setIcon(rmv.inImg1);
						}else {
							isInLabel.setIcon(rmv.inImg2);
						}
					}//end else
					cmv[cmvIndex].getJtaChat().append(msg +"\n");
			}//end while
		}catch(NullPointerException npe) {
			//npe.printStackTrace();
		}catch(EOFException eof) { 
			JOptionPane.showMessageDialog(null, "접속자 접속 종료");
			btnDisp.setIcon(rmv.btnImageBefore[cmvIndex]);
			isInLabel.setIcon(rmv.whiteImg);
		}catch (IOException e) {
			e.printStackTrace();
		}//end catch
		try {
			chk=true;
			btnDisp.setToolTipText("서버닫힘");
			closeServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//run
	
	/**
	 * 접속자에게 발생된 메세지가 있을 때 보내는 일 
	 */
	public void sendMsg(String msg){
		try {
			String serverMsg= msg;
			dosWriteStream.writeUTF(serverMsg);
			dosWriteStream.flush(); //스트림의 메세지를 접속 소켓으로 분출
			chkClientIn=true;
		} catch(NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "해당 방에 접속한 회원이 존재하지 않습니다.");
			chkClientIn=false;
		}catch (IOException e) {
			e.printStackTrace();
		} // 메세지를 스트림 쓰기
	}//sendMsg
	
	private void whichCMV(int port) {
		for(int i=0;i<cmv.length;i++) {
			if(cmv[i].getP_num()==port) {
				cmvIndex=i;
			}
		}
	}
	
	public void closeServer() throws IOException {
		if(client!=null) {
			client.close();	
		}//end if
	}//closeServer

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isChkClientIn() {
		return chkClientIn;
	}

	public void setChkClientIn(boolean chkClientIn) {
		this.chkClientIn = chkClientIn;
	}
	
	

}//class
