package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.myhomeService;

import com.opensymphony.xwork2.ActionSupport;

public class myhomeAction extends ActionSupport implements SessionAware {
	private int goodsid;
	private Map<String, Object> session;
	private Map<String, Object> json = new HashMap<String, Object>();

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String index() {
		if(!commonService.islogined(session)){
			return "login";
		}
		myhomeService.getMyInfo(session);
		myhomeService.getMyWorkList(session);
		return "index";
	}

	/**
	 * 下架作品
	 * 
	 * @return
	 */
	public String offshelve() {
		json.put("statu", myhomeService.offshelveGoods(goodsid));
		return "success";
	}

	/**
	 * 上架作品
	 * 
	 * @return
	 */
	public String shelve() {
		System.out.println("上架" + goodsid);
		json.put("statu", myhomeService.shelveGoods(goodsid));
		return "success";
	}
}
