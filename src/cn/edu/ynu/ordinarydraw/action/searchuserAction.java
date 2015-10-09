package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.searchuserService;

import com.opensymphony.xwork2.ActionSupport;

public class searchuserAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private Map<String, Object> json = new HashMap<String, Object>();

	public Map<String, Object> getJson() {
		return json;
	}

	private String key;

	public void setKey(String key) {
		this.key = key;
	}

	public String index() {
		System.out.println(key);
		searchuserService.searchUser(key, session);
		
		return "index";
	}
}
