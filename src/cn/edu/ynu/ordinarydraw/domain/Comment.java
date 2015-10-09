package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Comment implements Serializable{
	private Integer commentid;
	private User user;
	private Integer goodsid;
	private String content;
	private String publishtime;

	public Comment() {
	}

	public Comment(Integer commentid, User user, Integer goodsid,
			String content, String publishtime) {
		this.commentid = commentid;
		this.user = user;
		this.goodsid = goodsid;
		this.content = content;
		this.publishtime = publishtime;
	}

	public Integer getCommentid() {
		return commentid;
	}

	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

}
