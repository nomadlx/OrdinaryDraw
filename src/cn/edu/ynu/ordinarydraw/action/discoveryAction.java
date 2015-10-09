package cn.edu.ynu.ordinarydraw.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.discoveryService;

import com.opensymphony.xwork2.ActionSupport;

public class discoveryAction extends ActionSupport implements SessionAware {
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
		discoveryService.getHotTag(session);
		discoveryService.getRecommendTag(session);
		return "index";
	}

}
