package cn.edu.ynu.ordinarydraw.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.likeDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class lovesetService {
	private static goodsDao goodsDao;
	private static likeDao likeDao;
	static {
		goodsDao = new goodsDao();
		likeDao = new likeDao();
	}

	/**
	 * 获取喜爱列表
	 * 
	 * @param session
	 */
	public static void getLovesetList(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Goods> loveGoodsList = goodsDao.getLikeGoodsListByUser(user
				.getUserid());
		for (Goods goods : loveGoodsList) {
			Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
			for (GoodsImg goodsImg : goodsImgs) {
				goodsImg.setPath(goodsImg
						.getPath()
						.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
								CONSTANT.IMG_PATH_WORK_SQUARE)
						.replace("jpeg", "PNG"));
			}
		}
		session.put("loveGoods", loveGoodsList);
	}

	/**
	 * 取消喜爱
	 * 
	 * @param goodsid
	 *            被喜爱作品id
	 * @return true：取消成功 false：取消失败
	 */
	public static boolean cancelLike(int goodsid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return likeDao.unlikeGoods(user.getUserid(), goodsid);
	}
}
