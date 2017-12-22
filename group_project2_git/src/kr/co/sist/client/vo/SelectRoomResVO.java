package kr.co.sist.client.vo;

public class SelectRoomResVO {
	private String date, in_time, out_time, room_id;
	private int p_cnt;

	public SelectRoomResVO() {
	}

	public SelectRoomResVO(String date, String in_time, String out_time, String room_id, int p_cnt) {
		this.date = date;
		this.in_time = in_time;
		this.out_time = out_time;
		this.room_id = room_id;
		this.p_cnt = p_cnt;
	}

	public String getDate() {
		return date;
	}

	public String getIn_time() {
		return in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public String getRoom_id() {
		return room_id;
	}

	public int getP_cnt() {
		return p_cnt;
	}

	@Override
	public String toString() {
		return "SelectRoomResVO [date=" + date + ", in_time=" + in_time + ", out_time=" + out_time + ", room_id="
				+ room_id + ", p_cnt=" + p_cnt + "]";
	}

}//class
