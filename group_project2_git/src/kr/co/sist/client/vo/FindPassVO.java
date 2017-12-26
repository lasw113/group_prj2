package kr.co.sist.client.vo;

public class FindPassVO {
	String id,passAns,passQ,brith;
	
	public FindPassVO() {
		
	}//FindPassVO

	public FindPassVO(String id, String passAns, String passQ, String brith) {
		super();
		this.id = id;
		this.passAns = passAns;
		this.passQ = passQ;
		this.brith = brith;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassAns() {
		return passAns;
	}

	public void setPassAns(String passAns) {
		this.passAns = passAns;
	}

	public String getPassQ() {
		return passQ;
	}

	public void setPassQ(String passQ) {
		this.passQ = passQ;
	}

	public String getBrith() {
		return brith;
	}

	public void setBrith(String brith) {
		this.brith = brith;
	}
	

}
