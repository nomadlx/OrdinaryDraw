package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Follow implements Serializable{
	private Integer userid1;
	private Integer userid2;
	private String followdate;

	public Follow() {
	}

	public Follow(Integer userid1, Integer userid2, String followdate) {
		super();
		this.userid1 = userid1;
		this.userid2 = userid2;
		this.followdate = followdate;
	}

	public Integer getUserid1() {
		return userid1;
	}

	public void setUserid1(Integer userid1) {
		this.userid1 = userid1;
	}

	public Integer getUserid2() {
		return userid2;
	}

	public void setUserid2(Integer userid2) {
		this.userid2 = userid2;
	}

	public String getFollowdate() {
		return followdate;
	}

	public void setFollowdate(String followdate) {
		this.followdate = followdate;
	}

}
