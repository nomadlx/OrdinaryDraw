package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Cart implements Serializable{
	private Integer userid;
	private Goods goods;
	private String addtime;
	private String statu;

	public Cart() {
	}

	public Cart(Integer userid, Goods goods, String addtime, String statu) {
		super();
		this.userid = userid;
		this.goods = goods;
		this.addtime = addtime;
		this.statu = statu;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

}
