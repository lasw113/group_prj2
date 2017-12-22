package kr.co.sist.client.vo;

public class SelectTimeChkVO {//시간 정보 알아오기 위한 정보
	private String date, room_id;

	public SelectTimeChkVO() {
	}

	public SelectTimeChkVO(String date, String room_id) {
		this.date = date;
		this.room_id = room_id;
	}

	public String getDate() {
		return date;
	}

	public String getRoom_id() {
		return room_id;
	}
	
}//class
