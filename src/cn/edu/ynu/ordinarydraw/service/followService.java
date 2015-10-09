package cn.edu.ynu.ordinarydraw.service;

import java.util.List;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.followDao;
import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class followService {
	private static userDao userDao;
	private static goodsDao goodsDao;
	private static followDao followDao;
	static {
		userDao = new userDao();
		goodsDao = new goodsDao();
		followDao = new followDao();
	}

	/**
	 * 获取关注推送
	 * 
	 * @param session
	 */
	public static void getFollowPush(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Goods> followPushList = goodsDao.getPushGoodsListByUser(user
				.getUserid());
		session.put("followPush", followPushList);
	}

	/**
	 * 获取关注列表
	 * 
	 * @param session
	 */
	public static void getFollowList(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<User> followUserList = userDao.getFollowUserByUser(user
				.getUserid());
		session.put("followUser", followUserList);
	}

	/**
	 * 取消关注
	 * 
	 * @param userid
	 *            被关注用户
	 * @return true：取消成功 false：取消失败
	 */
	public static boolean cancelFollow(int userid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return followDao.unfollowUser(user.getUserid(), userid);
	}
}
