package kr.co.sist.client.vo;

public class UpdateMileVO {
	private String id;
	private int useMile;

	public UpdateMileVO() {
	}

	public UpdateMileVO(String id, int useMile) {
		this.id = id;
		this.useMile = useMile;
	}

	public String getId() {
		return id;
	}

	public int getUseMile() {
		return useMile;
	}

}
