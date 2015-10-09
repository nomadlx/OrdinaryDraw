package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Like implements Serializable{
	private Integer userid;
	private Integer goodsid;
	private String likedate;

	public Like() {
	}

	public Like(Integer userid, Integer goodsid, String likedate) {
		super();
		this.userid = userid;
		this.goodsid = goodsid;
		this.likedate = likedate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getLikedate() {
		return likedate;
	}

	public void setLikedate(String likedate) {
		this.likedate = likedate;
	}

}
