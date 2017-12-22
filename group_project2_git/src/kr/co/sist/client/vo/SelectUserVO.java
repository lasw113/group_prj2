package kr.co.sist.client.vo;

public class SelectUserVO {

	private String name, phone, email, millege, price;

	public SelectUserVO() {
	}

	public SelectUserVO(String name, String phone, String email, String millege, String price) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.millege = millege;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getMillege() {
		return millege;
	}

	public String getPrice() {
		return price;
	}

}//class
