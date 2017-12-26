package kr.co.sist.client.vo;

/**
 * 회원가입 VO
 * @author user
 *
 */
public class JoinVO {
	private String id, pass, passAns, name, phone,birth,email;
	private String passIndex;
	
	public JoinVO() {
		
	}//JoinVO
	public JoinVO(String id, String pass, String passAns, String name, String phone, String birth, String email,
			String passIndex) {
		super();
		this.id = id;
		this.pass = pass;
		this.passAns = passAns;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.email = email;
		this.passIndex = passIndex;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPassAns() {
		return passAns;
	}
	public void setPassAns(String passAns) {
		this.passAns = passAns;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassIndex() {
		return passIndex;
	}
	public void setPassIndex(String passIndex) {
		this.passIndex = passIndex;
	}
	
	
	
	
}//class
