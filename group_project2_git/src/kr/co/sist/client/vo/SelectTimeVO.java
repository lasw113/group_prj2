package kr.co.sist.client.vo;

public class SelectTimeVO {// ½Ã°£ »Ì´Â VO
	private String in_time, out_time;

	public SelectTimeVO() {

	}

	public SelectTimeVO(String in_time, String out_time) {
		this.in_time = in_time;
		this.out_time = out_time;
	}

	public String getIn_time() {
		return in_time;
	}

	public String getOut_time() {
		return out_time;
	}

}
