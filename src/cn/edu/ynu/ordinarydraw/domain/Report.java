package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class Report implements Serializable{
	private Integer reportid;
	private Integer userid;
	private Integer goodsid;
	private String reason;
	private String reportdate;
	private String statu;
	private String username;
	private String operatedate;

	public Report() {
	}

	public Report(Integer reportid, Integer userid, Integer goodsid,
			String reason, String reportdate, String statu, String username,
			String operatedate) {
		this.reportid = reportid;
		this.userid = userid;
		this.goodsid = goodsid;
		this.reason = reason;
		this.reportdate = reportdate;
		this.statu = statu;
		this.username = username;
		this.operatedate = operatedate;
	}

	public Integer getReportid() {
		return reportid;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperatedate() {
		return operatedate;
	}

	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}

}
