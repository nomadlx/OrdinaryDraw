package cn.edu.ynu.ordinarydraw.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.ynu.ordinarydraw.service.commonService;

public class commonAction extends ActionSupport implements SessionAware {
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

	public String showimg() throws Exception {
		json.put("path",
				commonService.showImg(upImg, upImgFileName, upImgContentType));
		return "success";
	}

	private String tname;

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String gettagid() {
		int tagid = commonService.getTagid(tname);
		if (tagid == 0) {
			json.put("statu", false);
		} else {
			json.put("statu", true);
			json.put("tagid", tagid);
		}
		return "success";
	}
}
