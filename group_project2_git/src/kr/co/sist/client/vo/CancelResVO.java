package kr.co.sist.client.vo;

public class CancelResVO {
	private String res_id, id;

	public CancelResVO() {
		super();
	}

	public CancelResVO(String res_id, String id) {
		super();
		this.res_id = res_id;
		this.id = id;
	}

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}//class
