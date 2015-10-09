package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;
import java.util.Set;

public class Order implements Serializable{
	private Integer orderid;
	private Float price;
	private String gertdate;
	private Short qty;
	private String statu;
	private Integer userid;
	private Set<OrderItem> orderitems;

	public Order() {
		super();
	}

	public Order(Integer orderid, Float price, String gertdate, Short qty,
			String statu, Integer userid, Set<OrderItem> orderitems) {
		super();
		this.orderid = orderid;
		this.price = price;
		this.gertdate = gertdate;
		this.qty = qty;
		this.statu = statu;
		this.userid = userid;
		this.orderitems = orderitems;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getGertdate() {
		return gertdate;
	}

	public void setGertdate(String gertdate) {
		this.gertdate = gertdate;
	}

	public Short getQty() {
		return qty;
	}

	public void setQty(Short qty) {
		this.qty = qty;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Set<OrderItem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}


}
