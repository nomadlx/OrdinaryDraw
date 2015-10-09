package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Subscribe implements Serializable{
	private Integer userid;
	private Integer tagid;
	private String subdate;

	public Subscribe() {
	}

	public Subscribe(Integer userid, Integer tagid, String subdate) {
		super();
		this.userid = userid;
		this.tagid = tagid;
		this.subdate = subdate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getTagid() {
		return tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	public String getSubdate() {
		return subdate;
	}

	public void setSubdate(String subdate) {
		this.subdate = subdate;
	}

}
