package cn.edu.ynu.ordinarydraw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.tagDao;
import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class discoveryService {
	private static tagDao tagDao;
	private static userDao userDao;
	private static goodsDao goodsDao;
	static {
		tagDao = new tagDao();
		userDao = new userDao();
		goodsDao = new goodsDao();
	}

	/**
	 * 获取热门标签
	 * 
	 * @param session
	 */
	public static void getHotTag(Map<String, Object> session) {
		List<Tag> hotTagList = tagDao.getHotTag(CONSTANT.COUNT_HOTTAG);
		session.put("hotTag", hotTagList);
		List<Goods> hotTagGoodsList = new ArrayList<Goods>();
		for (int i = 0; i < hotTagList.size(); i++) {
			hotTagGoodsList.add(goodsDao.getGoodsListByTag(
					hotTagList.get(i).getTagid(), 1).get(0));
		}
		for (Goods goods : hotTagGoodsList) {
			Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
			for (GoodsImg goodsImg : goodsImgs) {
				goodsImg.setPath(goodsImg
						.getPath()
						.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
								CONSTANT.IMG_PATH_WORK_SQUARE)
						.replace("jpeg", "PNG"));
			}
		}
		System.out.println(hotTagGoodsList.size());
		session.put("hotTagWork", hotTagGoodsList);
	}

	/**
	 * 获取推荐标签
	 * 
	 * @param session
	 */
	public static void getRecommendTag(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Tag> recommendTagList = tagDao.getRecommendTag(user.getUserid(),
				CONSTANT.COUNT_RECOMMENDTAG);
		session.put("recommendTag", recommendTagList);
		List<User> recommendTagUserList = new ArrayList<User>();
		for (int i = 0; i < recommendTagList.size(); i++) {
			recommendTagUserList.add(userDao.getHotUserByTag(recommendTagList
					.get(i).getTagid()));
		}
		session.put("recommendTagUser", recommendTagUserList);
	}
}
