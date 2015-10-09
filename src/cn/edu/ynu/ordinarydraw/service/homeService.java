package cn.edu.ynu.ordinarydraw.service;

import java.util.ArrayList;
import java.util.HashSet;
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

public class homeService {
	private static goodsDao goodsDao;
	private static userDao userDao;
	private static tagDao tagDao;
	static {
		goodsDao = new goodsDao();
		userDao = new userDao();
		tagDao = new tagDao();
	}

	/**
	 * 获取热门作品
	 * 
	 * @param session
	 */
	public static void getHotWork(Map<String, Object> session) {
		List<Goods> hotGoodsList = goodsDao.getHotGoods(CONSTANT.COUNT_HOTWORK);
		for (Goods goods : hotGoodsList) {
			Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
			for (GoodsImg goodsImg : goodsImgs) {
				goodsImg.setPath(goodsImg
						.getPath()
						.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
								CONSTANT.IMG_PATH_WORK_SQUARE)
						.replace("jpeg", "PNG"));
			}
		}
		session.put("hotWork", hotGoodsList);
	}

	/**
	 * 获取订阅作品
	 * 
	 * @param session
	 */
	public static void getSubscribeWork(Map<String, Object> session) {
		int userid = GLobalMethod.getNowUser(session).getUserid();
		List<Tag> subTagList = tagDao.getTagListByUser(userid);
		session.put("subTag", subTagList);
		List<List<Goods>> subWorkList = new ArrayList<List<Goods>>();
		for (int i = 0; i < subTagList.size(); i++) {
			subWorkList.add(goodsDao.getGoodsListByTag(subTagList.get(i)
					.getTagid(), CONSTANT.COUNT_SUBWORK));
		}
		for (List<Goods> list : subWorkList) {
			for (Goods goods : list) {
				Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
				for (GoodsImg goodsImg : goodsImgs) {
					goodsImg.setPath(goodsImg
							.getPath()
							.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
									CONSTANT.IMG_PATH_WORK_SQUARE)
							.replace("jpeg", "PNG"));
				}
			}
		}

		session.put("subWork", subWorkList);
	}

	/**
	 * 获取热门作者
	 * 
	 * @param session
	 */
	public static void getHotAuthor(Map<String, Object> session) {
		List<User> hotUserList = userDao.getHotUser(CONSTANT.COUNT_HOTAUTHOR);
		session.put("hotAuthor", hotUserList);
	}
}
