package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Tag implements Serializable{
	private Integer tagid;
	private String tname;
	private String updatetime;
	private Integer goodscount;
	
	public Tag() {
	}
	public Tag(Integer tagid, String tname, String updatetime,
			Integer goodscount) {
		super();
		this.tagid = tagid;
		this.tname = tname;
		this.updatetime = updatetime;
		this.goodscount = goodscount;
	}
	public Integer getTagid() {
		return tagid;
	}
	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getGoodscount() {
		return goodscount;
	}
	public void setGoodscount(Integer goodscount) {
		this.goodscount = goodscount;
	}
	
}
