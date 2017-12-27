package kr.co.sist.client.vo;

public class ModiUserVO {
	private String res_name, res_phone, res_email, request, use_mile, id;
	private SelectRoomResVO srr_vo;

	public ModiUserVO() {
	}

	public ModiUserVO(String res_name, String res_phone, String res_email, String request, String use_mile, String id,
			SelectRoomResVO srr_vo) {
		super();
		this.res_name = res_name;
		this.res_phone = res_phone;
		this.res_email = res_email;
		this.request = request;
		this.use_mile = use_mile;
		this.id = id;
		this.srr_vo = srr_vo;
	}

	public String getRes_name() {
		return res_name;
	}

	public String getRes_phone() {
		return res_phone;
	}

	public String getRes_email() {
		return res_email;
	}

	public String getRequest() {
		return request;
	}

	public String getUse_mile() {
		return use_mile;
	}

	public String getId() {
		return id;
	}

	public SelectRoomResVO getSrr_vo() {
		return srr_vo;
	}

}// class
