package kr.co.sist.client.vo;

public class FindIDVO {
	private String name,phone,birth;
	
	public  FindIDVO() {
		
	}//FindIDVO
	
	public FindIDVO( String name,String phone,String birth) {
		super();
		this.name=name;
		this.phone=phone;
		this.birth=birth;
		
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
	
	

}
