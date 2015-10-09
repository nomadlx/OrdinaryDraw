package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.followService;

import com.opensymphony.xwork2.ActionSupport;

public class followAction extends ActionSupport implements SessionAware {
	private int userid;// 取消关注用户id
	private Map<String, Object> json = new HashMap<String, Object>();

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String index() {
		if(!commonService.islogined(session)){
			return "login";
		}
		followService.getFollowPush(session);
		followService.getFollowList(session);
		return "index";
	}

	/**
	 * 取消关注
	 * 
	 * @return
	 */
	public String unfollow() {
		json.put("statu", followService.cancelFollow(userid, session));
		return "success";
	}

}
