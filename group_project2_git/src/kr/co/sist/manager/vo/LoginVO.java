package kr.co.sist.manager.vo;

public class LoginVO {

	private String id, pass;

	public LoginVO() {
	}

	public LoginVO(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

}
