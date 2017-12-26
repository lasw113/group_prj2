package kr.co.sist.manager.vo;

public class ResMgrVO {
	private String room_id, res_name, in_time, out_time, request,res_id;
	int p_cnt;

	public ResMgrVO(String room_id, int p_cnt, String res_name, String in_time, String out_time,String request, String res_id) {
		super();
		this.room_id = room_id;
		this.res_name = res_name;
		this.in_time = in_time;
		this.out_time = out_time;
		this.request = request;
		this.p_cnt = p_cnt;
		this.res_id = res_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public int getP_cnt() {
		return p_cnt;
	}

	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}

}
