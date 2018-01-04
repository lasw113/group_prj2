package kr.co.sist.manager.vo;

public class ResMgrVO {
	private String room_id, res_name, in_time, out_time, request, res_id, checkin, id;
	int p_cnt, use_mile, price;

	public ResMgrVO(String room_id, int p_cnt, String res_name, String in_time, String out_time, String request,
			String res_id, String checkin, String id, int use_mile, int price) {
		super();
		this.room_id = room_id;
		this.res_name = res_name;
		this.in_time = in_time;
		this.out_time = out_time;
		this.request = request;
		this.p_cnt = p_cnt;
		this.res_id = res_id;
		this.checkin = checkin;
		this.id = id;
		this.use_mile = use_mile;
		this.price = price;
	}

	public String getRoom_id() {
		return room_id;
	}

	public String getRes_name() {
		return res_name;
	}

	public String getIn_time() {
		return in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public String getRequest() {
		return request;
	}

	public String getRes_id() {
		return res_id;
	}

	public int getP_cnt() {
		return p_cnt;
	}

	public String getCheckin() {
		return checkin;
	}

	public String getId() {
		return id;
	}

	public int getUse_mile() {
		return use_mile;
	}

	public int getPrice() {
		return price;
	}
	
}
