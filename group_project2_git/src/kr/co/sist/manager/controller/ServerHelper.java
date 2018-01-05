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
			//��ȭ�� �а� ������ �ֵ��� ��Ʈ�� ����
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
			btnDisp.setToolTipText(btnDisp.getName()+"�����ڰ� �ֽ��ϴ�~~");
			while(true) {
				//�޼����� �о�鿩 ��ȭâ�� �����Ѵ�. 
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
			JOptionPane.showMessageDialog(null, "������ ���� ����");
			btnDisp.setIcon(rmv.btnImageBefore[cmvIndex]);
			isInLabel.setIcon(rmv.whiteImg);
		}catch (IOException e) {
			e.printStackTrace();
		}//end catch
		try {
			chk=true;
			btnDisp.setToolTipText("��������");
			closeServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//run
	
	/**
	 * �����ڿ��� �߻��� �޼����� ���� �� ������ �� 
	 */
	public void sendMsg(String msg){
		try {
			String serverMsg= msg;
			dosWriteStream.writeUTF(serverMsg);
			dosWriteStream.flush(); //��Ʈ���� �޼����� ���� �������� ����
			chkClientIn=true;
		} catch(NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "�ش� �濡 ������ ȸ���� �������� �ʽ��ϴ�.");
			chkClientIn=false;
		}catch (IOException e) {
			e.printStackTrace();
		} // �޼����� ��Ʈ�� ����
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
