package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class GoodsImg  implements Serializable{
	private Integer goodsid;
	private Short seq;
	private String path;

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
