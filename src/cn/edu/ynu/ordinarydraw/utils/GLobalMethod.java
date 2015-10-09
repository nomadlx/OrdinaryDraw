package cn.edu.ynu.ordinarydraw.utils;

import java.util.Calendar;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.domain.User;

public class GLobalMethod {
	/**
	 * 添加当前用户
	 * 
	 * @param user
	 * @param session
	 */
	public static void setNowUser(User user, Map<String, Object> session) {
		session.put("nowUser", user);
	}

	/**
	 * 获取当前用户
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	public static User getNowUser(Map<String, Object> session) {
		return (User) session.get("nowUser");
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return year + "-" + (month+1 )+ "-" + date + " " + hour + ":" + minute
				+ ":" + second;
	}
}
