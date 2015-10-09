package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class GoodsTag implements Serializable{
	private Integer goodsid;
	private Tag tag;
	private Short seq;

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

}
