package kr.co.sist.manager.vo;

public class UserVO {
	private String id, name, phone;
	private int mileage;
	
	public UserVO() {
		super();
	}//UserVO

	public UserVO(String id, String name, String phone, int mileage) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.mileage = mileage;
	}//UserVO

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public int getMileage() {
		return mileage;
	}
	
}//class
