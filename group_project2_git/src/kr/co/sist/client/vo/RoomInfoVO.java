package kr.co.sist.client.vo;

public class RoomInfoVO {

	private String roomInfo, image,p_min,p_max;
	
	public RoomInfoVO() {
	}
	
	public RoomInfoVO(String roomInfo, String image, String p_min, String p_max) {
		this.roomInfo = roomInfo;
		this.image = image;
		this.p_min = p_min;
		this.p_max = p_max;
		
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public String getImage() {
		return image;
	}

	public String getP_min() {
		return p_min;
	}

	public String getP_max() {
		return p_max;
	}
	
}
