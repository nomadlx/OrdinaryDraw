package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.profileService;
import cn.edu.ynu.ordinarydraw.service.registerService;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

import com.opensymphony.xwork2.ActionSupport;

public class profileAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private String img;
	private String uname;
	private String email;
	private int addrindex;
	private String udesc;

	public void setImg(String img) {
		this.img = img;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddrindex(int addrindex) {
		this.addrindex = addrindex;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

	public String index() {
		if(!commonService.islogined(session)){
			return "login";
		}
		profileService.getProfile(session);
		return "index";
	}

	private Map<String, Object> json = new HashMap<String, Object>();

	public Map<String, Object> getJson() {
		return json;
	}

	private String oldpwd;
	private String newpwd;

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	/**
	 * 获取地址列表
	 * 
	 * @return
	 */
	public String getaddrs() {
		List<String> addrs = profileService.getAddrs();
		json.put("count", addrs.size());
		json.put("addr", addrs);
		json.put("index", profileService.getAddrIndex(GLobalMethod.getNowUser(
				session).getAddr()));
		return "success";
	}

	/**
	 * 更新个人信息
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String update() throws Exception {
		json.put("statu", profileService.updateProfile(img, uname, email,
				addrindex, udesc, session));
		return "success";
	}

	/**
	 * 更新密码
	 * 
	 * @return
	 */
	public String updatepwd() {
		json.put("statu", profileService.updatePwd(oldpwd, newpwd, session));
		return "success";
	}
}