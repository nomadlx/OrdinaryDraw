package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.loginService;

import com.opensymphony.xwork2.ActionSupport;

public class loginAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private String email;
	private String pwd;
	private Map<String, Object> json = new HashMap<String, Object>();

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	/**
	 * 登录验证
	 * 
	 * @return
	 */
	public String login() {
		if (loginService.verifyAccount(email, pwd, session)) {
			return "home";
		}
		return "login";
	}

	/**
	 * 根据email地址动态获取头像
	 * 
	 * @return
	 */
	public String getimg() {
		loginService.getImgByEmail(email, json);
		return "success";
	}

	/**
	 * 情况session数据
	 * 
	 * @return
	 */
	public String clear() {
		session.put("statu", false);
		return "success";
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 */
	public String logout() {
		session.clear();
		return "login";
	}
}
