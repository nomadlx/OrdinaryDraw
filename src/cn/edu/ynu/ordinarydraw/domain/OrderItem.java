package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class OrderItem implements Serializable{
	private Integer orderid;
	private Goods goods;
	private Float price;

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
