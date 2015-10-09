package cn.edu.ynu.ordinarydraw.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.service.commonService;
import cn.edu.ynu.ordinarydraw.service.workshowService;

import com.opensymphony.xwork2.ActionSupport;

public class workshowAction extends ActionSupport implements SessionAware {
	private int goodsid;
	private String content;
	private Map<String, Object> session;
	private Map<String, Object> json = new HashMap<String, Object>();;

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String index() {
		if (!commonService.islogined(session)) {
			return "login";
		}
		workshowService.getGoodsInfo(goodsid, session);
		workshowService.getCommentList(goodsid, session);
		workshowService.getSaleList(goodsid, session);
		return "index";
	}

	/**
	 * 添加到购物车
	 * 
	 * @return
	 */
	public String addtocart() {
		json.put("statu", workshowService.addtoCart(goodsid, session));
		return "success";
	}

	/**
	 * 喜爱该作品
	 * 
	 * @return
	 */
	public String like() {
		json.put("statu", workshowService.likeGoods(goodsid, session));
		return "success";
	}

	/**
	 * 取消喜爱该作品
	 * 
	 * @return
	 */
	public String unlike() {
		json.put("statu", workshowService.unlikeGoods(goodsid, session));
		return "success";
	}

	/**
	 * 发表评论
	 * 
	 * @return
	 */
	public String comment() {
		json.put("statu",
				workshowService.publishComment(goodsid, content, session));
		return "success";
	}

	/**
	 * 刷新评论
	 * 
	 * @return
	 */
	public String loadcomment() {
		workshowService.loadCommentList(goodsid, json);
		return "success";
	}

	private int seq;

	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * 显示大图
	 * 
	 * @return
	 */
	public String getimg() {
		json.put("path", workshowService.getImg(goodsid, seq));
		return "success";
	}

	private String reason;

	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 举报该作品
	 * 
	 * @return
	 */
	public String report() {
		json.put("statu", workshowService.reportGoods(goodsid, reason, session));
		return "success";
	}
}
