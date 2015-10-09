package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.lovesetService;

import com.opensymphony.xwork2.ActionSupport;

public class lovesetAction extends ActionSupport implements SessionAware {
	private int goodsid;// 取消喜爱的作品id
	private Map<String, Object> json = new HashMap<String, Object>();
	private Map<String, Object> session;

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
		lovesetService.getLovesetList(session);
		return "index";
	}

	/**
	 * 取消喜爱
	 * 
	 * @return
	 */
	public String unlike() {
		System.out.println("取消喜爱");
		json.put("statu", lovesetService.cancelLike(goodsid, session));
		return "success";
	}
}
