package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Goods implements Serializable {
	private Integer goodsid;
	private String gname;
	private String gdesc;
	private Float price;
	private String publishtime;
	private String updatetime;
	private Short imgcount;
	private String statu;
	private User user;
	private Set<GoodsImg> goodsimgs;
	private Set<GoodsTag> goodstags;

	public Goods() {
	}

	public Goods(Integer goodsid, String gname, String gdesc, Float price,
			String publishtime, String updatetime, Short imgcount,
			String statu, User user, Set<GoodsImg> goodsimgs,
			Set<GoodsTag> goodstags) {
		super();
		this.goodsid = goodsid;
		this.gname = gname;
		this.gdesc = gdesc;
		this.price = price;
		this.publishtime = publishtime;
		this.updatetime = updatetime;
		this.imgcount = imgcount;
		this.statu = statu;
		this.user = user;
		this.goodsimgs = goodsimgs;
		this.goodstags = goodstags;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGdesc() {
		return gdesc;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Short getImgcount() {
		return imgcount;
	}

	public void setImgcount(Short imgcount) {
		this.imgcount = imgcount;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<GoodsImg> getGoodsimgs() {
		return goodsimgs;
	}

	public void setGoodsimgs(Set<GoodsImg> goodsimgs) {
		this.goodsimgs = goodsimgs;
	}

	public Set<GoodsTag> getGoodstags() {
		return goodstags;
	}

	public void setGoodstags(Set<GoodsTag> goodstags) {
		this.goodstags = goodstags;
	}

}
