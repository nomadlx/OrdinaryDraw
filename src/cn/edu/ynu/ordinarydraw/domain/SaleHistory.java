package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class SaleHistory implements Serializable{
	private User user;
	private Goods goods;
	private String buytime;
	private Float price;

	public SaleHistory() {
	}

	public SaleHistory(User user, Goods goods, String buytime, Float price) {
		this.user = user;
		this.goods = goods;
		this.buytime = buytime;
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
