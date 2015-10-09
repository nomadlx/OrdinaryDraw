package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.searchtagService;

import com.opensymphony.xwork2.ActionSupport;

public class searchtagAction extends ActionSupport implements SessionAware {
	private Map<String, Object> json = new HashMap<String, Object>();

	public Map<String, Object> getJson() {
		return json;
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private int tagid;

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public String index() {
		if(!commonService.islogined(session)){
			return "login";
		}
		searchtagService.getGoodsList(tagid, session);
		return "index";
	}

	/**
	 * 订阅标签
	 * 
	 * @return
	 */
	public String subscribe() {
		json.put("statu", searchtagService.subTag(tagid, session));
		return "success";
	}

	/**
	 * 取消订阅标签
	 * 
	 * @return
	 */
	public String unsubscribe() {
		json.put("statu", searchtagService.unsubTag(tagid, session));
		return "success";
	}

}
