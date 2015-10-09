package cn.edu.ynu.ordinarydraw.domain;

import java.io.Serializable;

public class User implements Serializable{
	private Integer userid;
	private String email;
	private String pwd;
	private String img;
	private String uname;
	private String addr;
	private String udesc;
	private String statu;

	public User() {
	}

	public User(Integer userid, String email, String pwd, String img,
			String uname, String addr, String udesc, String statu) {
		super();
		this.userid = userid;
		this.email = email;
		this.pwd = pwd;
		this.img = img;
		this.uname = uname;
		this.addr = addr;
		this.udesc = udesc;
		this.statu = statu;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getUdesc() {
		return udesc;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

}
