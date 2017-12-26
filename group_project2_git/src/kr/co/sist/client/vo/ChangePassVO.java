package kr.co.sist.client.vo;

public class ChangePassVO {
	String pass,id;

	public ChangePassVO() {
		super();
	}//ChangePassVO

	public ChangePassVO(String pass, String id) {
		super();
		this.pass=pass;
		this.id = id;
	}//ChangePassVO

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	
	


}
