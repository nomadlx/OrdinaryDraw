package cn.edu.ynu.ordinarydraw.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.followDao;
import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class authorhomeService {
	private static userDao userDao;
	private static goodsDao goodsDao;
	private static followDao followDao;
	static {
		userDao = new userDao();
		goodsDao = new goodsDao();
		followDao = new followDao();
	}

	/**
	 * 获取创作者信息
	 * 
	 * @param userid
	 * @param session
	 */
	public static void getAuthorInfo(int userid, Map<String, Object> session) {
		User user = userDao.getUserById(userid);
		session.put("userInfo", user);
		session.put("liked", followDao.isfollow(GLobalMethod.getNowUser(session).getUserid(),
				userid));
	}

	/**
	 * 获取作品列表
	 * 
	 * @param userid
	 * @param session
	 */
	public static void getGoodsList(int userid, Map<String, Object> session) {
		List<Goods> userGoodsList = goodsDao.getGoodsListByUser(userid);
		for (Goods goods : userGoodsList) {
			Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
			for (GoodsImg goodsImg : goodsImgs) {
				goodsImg.setPath(goodsImg
						.getPath()
						.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
								CONSTANT.IMG_PATH_WORK_SQUARE)
						.replace("jpeg", "PNG"));
			}
		}
		session.put("userGoods", userGoodsList);
	}

	/**
	 * 关注创作者
	 * 
	 * @param userid
	 * @return
	 */
	public static boolean followAuthor(int userid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return followDao.followUser(user.getUserid(), userid);
	}

	/**
	 * 取消关注创作者
	 * 
	 * @param userid
	 * @return
	 */
	public static boolean unfollowAuthor(int userid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return followDao.unfollowUser(user.getUserid(), userid);

	}
}
