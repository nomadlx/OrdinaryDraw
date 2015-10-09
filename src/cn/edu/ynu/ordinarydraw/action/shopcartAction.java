package cn.edu.ynu.ordinarydraw.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.shopcartService;

import com.opensymphony.xwork2.ActionSupport;

public class shopcartAction extends ActionSupport implements SessionAware {
	private int goodsid;
	private String data;
	private Map<String, Object> session;
	private Map<String, Object> json = new HashMap<String, Object>();

	public void setData(String data) {
		this.data = data;
	}

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
		shopcartService.getGoodsList(session);
		return "index";
	}

	/**
	 * 把商品从购物车移除
	 * 
	 * @return
	 */
	public String remove() {
		json.put("statu", shopcartService.removeGoods(goodsid, session));
		return "success";
	}

	/**
	 * 购物车结算
	 * 
	 * @return
	 */
	public String balance() {
		json.put("statu", shopcartService.balanceCart(data, session));
		return "success";
	}
}