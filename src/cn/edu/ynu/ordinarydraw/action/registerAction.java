package cn.edu.ynu.ordinarydraw.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.registerService;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.MD5Encoder;

import com.opensymphony.xwork2.ActionSupport;

public class registerAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	private String img;
	private String email;
	private String pwd;
	private String uname;
	private int addrindex;
	private String udesc;

	public void setImg(String img) {
		this.img = img;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setAddrindex(int addrindex) {
		this.addrindex = addrindex;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

	private Map<String, Object> json = new HashMap<String, Object>();
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

	public Map<String, Object> getJson() {
		return json;
	}

	public String index() {
		if(!commonService.islogined(session)){
			return "login";
		}
		return "index";
	}

	/**
	 * 获取地址列表
	 * 
	 * @return
	 */
	public String getaddrs() {
		List<String> addrs = registerService.getAddrs();
		json.put("count", addrs.size());
		json.put("addr", addrs);
		return "success";
	}

	/**
	 * 注册用户
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String register() throws Exception {
		json.put("statu", registerService.registerUser(email, pwd, uname,
				addrindex, udesc,img));
		return "success";
	}
}
