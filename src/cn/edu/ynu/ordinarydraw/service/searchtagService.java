package cn.edu.ynu.ordinarydraw.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.subscribeDao;
import cn.edu.ynu.ordinarydraw.dao.tagDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class searchtagService {
	private static goodsDao goodsDao;
	private static tagDao tagDao;
	private static subscribeDao subscribeDao;
	static {
		goodsDao = new goodsDao();
		tagDao = new tagDao();
		subscribeDao = new subscribeDao();
	}

	/**
	 * 根据标签获取作品列表
	 * 
	 * @param tagid
	 * @param session
	 */
	public static void getGoodsList(int tagid, Map<String, Object> session) {
		Tag tag = tagDao.getTagById(tagid);
		session.put("nowTag", tag);
		List<Goods> tagGoodsList = goodsDao.getGoodsListByTag(tagid);
		for (Goods goods : tagGoodsList) {
			Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
			for (GoodsImg goodsImg : goodsImgs) {
				goodsImg.setPath(goodsImg
						.getPath()
						.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
								CONSTANT.IMG_PATH_WORK_SQUARE)
						.replace("jpeg", "PNG"));
			}
		}
		session.put("tagGoods", tagGoodsList);
		session.put("liked", subscribeDao.isSubscribe(
				GLobalMethod.getNowUser(session).getUserid(), tagid));
	}

	/**
	 * 订阅标签
	 * 
	 * @param tagid
	 * @return
	 */
	public static boolean subTag(int tagid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return subscribeDao.subscribeTag(user.getUserid(), tagid);
	}

	/**
	 * 取消订阅标签
	 * 
	 * @param tagid
	 * @return
	 */
	public static boolean unsubTag(int tagid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return subscribeDao.unsubscribeTag(user.getUserid(), tagid);
	}
}
