package cn.edu.ynu.ordinarydraw.service;

import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.MD5Encoder;

public class loginService {
	private static userDao userDao;
	static {
		userDao = new userDao();
	}

	/**
	 * 验证用户账号
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 */
	public static boolean verifyAccount(String email, String pwd,
			Map<String, Object> session) {
		User user = userDao.getUserByEmail(email);
		if (user!=null&&MD5Encoder.encode(pwd).equals(user.getPwd())) {
			GLobalMethod.setNowUser(user, session);
			session.put("statu",false);
			return true;
		}
		session.put("statu",true);
		return false;
	}

	/**
	 * 根据用户email获取头像
	 * 
	 * @param email
	 * @param json
	 */
	public static void getImgByEmail(String email, Map<String, Object> json) {
		User user = userDao.getUserByEmail(email);
		json.put("img",
				user == null ? CONSTANT.IMG_DEFAULT_FACE : user.getImg());
	}

}
