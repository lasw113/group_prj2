package kr.co.sist.client.vo;

public class DeleteVO {
	String id,pass,name,birth,phone,email,pass_ans,pass_index;
	int mileage;
	
	public DeleteVO() {
		
	}//DeleteVO
	public DeleteVO(String id, String pass, String name, String birth, String phone, String email, String pass_ans,
			String pass_index, int mileage) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.pass_ans = pass_ans;
		this.pass_index = pass_index;
		this.mileage = mileage;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass_ans() {
		return pass_ans;
	}
	public void setPass_ans(String pass_ans) {
		this.pass_ans = pass_ans;
	}
	public String getPass_index() {
		return pass_index;
	}
	public void setPass_index(String pass_index) {
		this.pass_index = pass_index;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	

}//class
