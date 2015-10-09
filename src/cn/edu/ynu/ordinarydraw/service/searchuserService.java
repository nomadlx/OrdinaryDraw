package cn.edu.ynu.ordinarydraw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.followDao;
import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class searchuserService {
	private static userDao userDao;
	private static followDao followDao;
	static {
		userDao = new userDao();
		followDao = new followDao();
	}

	public static void setUserDao(userDao userDao) {
		searchuserService.userDao = userDao;
	}

	/**
	 * 搜索用户
	 * 
	 * @param key
	 * @param session
	 */
	public static void searchUser(String key, Map<String, Object> session) {
		System.out.println(key);
		User user = GLobalMethod.getNowUser(session);
		List<User> userlist = userDao.getUserListByKey(key);
		session.put("searchUsers", userlist);
		List<Boolean> islikedlist = new ArrayList<Boolean>();
		for (int i = 0; i < userlist.size(); i++) {
			islikedlist.add(followDao.isfollow(user.getUserid(), userlist
					.get(i).getUserid()));
		}
		session.put("isLiked", islikedlist);

	}
}
