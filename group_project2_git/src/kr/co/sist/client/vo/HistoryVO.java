package kr.co.sist.client.vo;

public class HistoryVO {
	private String res_name, room_id, in_time, out_time, res_date;
	private int p_cnt, price;
	
	public HistoryVO() {
		super();
	}//HistoryVO

	public HistoryVO(String res_name, String room_id, String in_time, String out_time, String res_date, int p_cnt,
			int price) {
		super();
		this.res_name = res_name;
		this.room_id = room_id;
		this.in_time = in_time;
		this.out_time = out_time;
		this.res_date = res_date;
		this.p_cnt = p_cnt;
		this.price = price;
	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
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

	public String getRes_date() {
		return res_date;
	}

	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}

	public int getP_cnt() {
		return p_cnt;
	}

	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "HistoryVO [res_name=" + res_name + ", room_id=" + room_id + ", in_time=" + in_time + ", out_time="
				+ out_time + ", res_date=" + res_date + ", p_cnt=" + p_cnt + ", price=" + price + "]";
	}

	
	
	
}//class
