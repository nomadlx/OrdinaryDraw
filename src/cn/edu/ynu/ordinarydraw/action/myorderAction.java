package cn.edu.ynu.ordinarydraw.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.myorderService;

import com.opensymphony.xwork2.ActionSupport;

public class myorderAction extends ActionSupport implements SessionAware {
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

	private int orderid;

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String index() {
		if(!commonService.islogined(session)){
			return "login";
		}
		myorderService.getAllOrder(session);
		myorderService.getAllGoods(session);
		myorderService.getNopayOrder(session);
		myorderService.getCancelOrder(session);
		myorderService.getDoneOrder(session);
		return "index";
	}

	/**
	 * 付款
	 * 
	 * @return
	 */
	public String pay() {
		json.put("statu", myorderService.payOrder(orderid));
		return "success";
	}

	/**
	 * 取消
	 * 
	 * @return
	 */
	public String cancel() {
		json.put("statu", myorderService.cancelOrder(orderid));
		return "success";
	}
	private int goodsid;
	
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public InputStream getDownloadFile() throws IOException {
		return myorderService.download(goodsid,session);
	}

	public String download() {
		return "download";
	}

}