package kr.co.sist.manager.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	private int cmvIndex; //몇번째 채팅창을 보여주어야하는지 구분할 인덱스
	public boolean chk,chkClientIn; //chk : 회원이 접속 종료했는지 체크 , chkClientIn : 회원이 접속했는지 체크

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
		}//end catch

		try {
			btnDisp.setToolTipText(btnDisp.getName()+"접속자가 있습니다~~");
			String msg="";
			while(true) {
				//메세지를 읽어들여 대화창에 설정한다. 
				msg =disReadStream.readUTF();
				if(cmv[cmvIndex].isVisible()) {
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
		}catch(java.net.SocketException jnS) {
			chk=true;
			//jnS.printStackTrace();
		}catch(NullPointerException npe) {
			//npe.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		try {
			dosWriteStream.close();
			disReadStream.close();
			client.close();
			ss.close();
			btnDisp.setIcon(rmv.btnImageBefore[cmvIndex]);
			isInLabel.setIcon(rmv.whiteImg);
			btnDisp.setToolTipText("서버닫힘");
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

	/**
	 * port번호를 이용해서 어떤 방의 채팅과 연관된 것인지 파악하는 인덱스를 찾아내는 일
	 * @param port
	 */
	private void whichCMV(int port) {
		for(int i=0;i<cmv.length;i++) {
			if(cmv[i].getP_num()==port) {
				cmvIndex=i;
			}//end if
		}//end for
	}//whichCMV

	/**
	 * 서버를 닫는다.
	 * @throws IOException
	 */
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
