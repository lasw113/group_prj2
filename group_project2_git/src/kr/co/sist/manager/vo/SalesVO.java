package kr.co.sist.manager.vo;

public class SalesVO {
	private int cash,card,milleage,t_cnt,t_sales;

	public SalesVO() {
		super();
	}//SalesVO

	public SalesVO(int cash, int card, int milleage, int t_cnt, int t_sales) {
		super();
		this.cash = cash;
		this.card = card;
		this.milleage = milleage;
		this.t_cnt = t_cnt;
		this.t_sales = t_sales;
	}//SalesVO

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public int getMilleage() {
		return milleage;
	}

	public void setMilleage(int milleage) {
		this.milleage = milleage;
	}

	public int getT_cnt() {
		return t_cnt;
	}

	public void setT_cnt(int t_cnt) {
		this.t_cnt = t_cnt;
	}

	public int getT_sales() {
		return t_sales;
	}

	public void setT_sales(int t_sales) {
		this.t_sales = t_sales;
	}
	
}//class
