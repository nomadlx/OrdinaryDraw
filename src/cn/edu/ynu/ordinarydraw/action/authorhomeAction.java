package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.authorhomeService;
import cn.edu.ynu.ordinarydraw.service.commonService;

import com.opensymphony.xwork2.ActionSupport;

public class authorhomeAction extends ActionSupport implements SessionAware {
	private int userid;
	private Map<String, Object> session;
	private Map<String, Object> json = new HashMap<String, Object>();

	public void setUserid(int userid) {
		this.userid = userid;
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
		authorhomeService.getAuthorInfo(userid, session);
		authorhomeService.getGoodsList(userid, session);
		return "index";
	}

	/**
	 * 关注用户
	 * 
	 * @return
	 */
	public String follow() {
		json.put("statu", authorhomeService.followAuthor(userid, session));
		return "success";
	}

	/**
	 * 取消关注
	 * 
	 * @return
	 */
	public String unfollow() {
		json.put("statu", authorhomeService.unfollowAuthor(userid, session));
		return "success";
	}

}
