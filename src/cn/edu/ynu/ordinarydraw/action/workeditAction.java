package cn.edu.ynu.ordinarydraw.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.registerService;
import cn.edu.ynu.ordinarydraw.service.workeditService;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;

import com.opensymphony.xwork2.ActionSupport;

public class workeditAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	// 前端注入上传的文件
	private File upImg;
	private String upImgFileName;
	private String upImgContentType;

	public File getUpImg() {
		return upImg;
	}

	public void setUpImg(File upImg) {
		this.upImg = upImg;
	}

	public String getUpImgFileName() {
		return upImgFileName;
	}

	public void setUpImgFileName(String upImgFileName) {
		this.upImgFileName = upImgFileName;
	}

	public String getUpImgContentType() {
		return upImgContentType;
	}

	public void setUpImgContentType(String upImgContentType) {
		this.upImgContentType = upImgContentType;
	}

	public String index() throws Exception {
		if(!commonService.islogined(session)){
			return "login";
		}
		// 清空已经上传的图片
		session.remove("goodsInfo");
		System.out.println(goodsid);
		if (goodsid != 0) {
			workeditService.getGoodsInfo(goodsid, session);
			session.put("title", "编辑作品");
		} else {
			session.put("title", "发布新作品");
		}
		return "index";
	}

	private Map<String, Object> json = new HashMap<String, Object>();

	public Map<String, Object> getJson() {
		return json;
	}

	private int index;

	public void setIndex(int index) {
		this.index = index;
	}

	private int goodsid;
	private String gname;
	private float price;
	private String tags;
	private String gdesc;
	private String imgs;
	private boolean isnew;

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}

	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}

	/**
	 * 发表作品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String publish() throws Exception {
		System.out.println("标签"+tags);
		if (isnew) {
			json.put("statu", workeditService.publishGoods(goodsid, gname,
					imgs, price, tags, gdesc, session));
		} else {
			json.put("statu", workeditService.updateGoods(goodsid, gname, imgs,
					price, tags, gdesc, isnew, session));
		}

		return "success";
	}
}
