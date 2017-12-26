package kr.co.sist.client.vo;

public class ResChkVO {
	private String res_id,res_name, room_id, in_time, out_time, date;
	private int p_cnt, price;

	public ResChkVO() {
		//이거 왜하는 건지 모르겟음
		super();
	}//ResChkVO
	public ResChkVO(String res_id,String res_name,String room_id,String in_time,
							String out_time,String date, int p_cnt, int price) {
		super();
		this.res_id=res_id;
		this.res_name=res_name;
		this.room_id=room_id;
		this.in_time=in_time;
		this.out_time=out_time;
		this.date=date;
		this.p_cnt=p_cnt;
		this.price=price;
	}
	public String getRes_name() {
		return res_name;
	}
	public String getRoom_id() {
		return room_id;
	}
	public String getIn_time() {
		return in_time;
	}
	public String getOut_time() {
		return out_time;
	}
	public String getDate() {
		return date;
	}
	public int getP_cnt() {
		return p_cnt;
	}
	public String getRes_id() {
		return res_id;
	}
	public int getPrice() {
		return price;
	}
	
	
}//class
