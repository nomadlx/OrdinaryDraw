package cn.edu.ynu.ordinarydraw.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.util.finder.Test;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.userDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class myhomeService {

	private static userDao userDao;
	private static goodsDao goodsDao;
	static {
		userDao = new userDao();
		goodsDao = new goodsDao();
	}

	/**
	 * 获取我的信息
	 * 
	 * @param session
	 */
	public static void getMyInfo(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		session.put("myInfo", user);
	}

	/**
	 * 获取我的作品列表
	 * 
	 * @param session
	 */
	public static void getMyWorkList(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Goods> myWorkList = goodsDao.getAllGoodsListByUser(user
				.getUserid());
		for (Goods goods : myWorkList) {
			Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
			for (GoodsImg goodsImg : goodsImgs) {
				goodsImg.setPath(goodsImg
						.getPath()
						.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
								CONSTANT.IMG_PATH_WORK_SQUARE)
						.replace("jpeg", "PNG"));
			}
		}
		session.put("myWork", myWorkList);
	}

	/**
	 * 下架作品
	 * 
	 * @param goodsid
	 *            作品id
	 * @return true：下架成功 false：下架失败
	 */
	public static boolean offshelveGoods(int goodsid) {
		return goodsDao.offshelveGoods(goodsid);
	}

	/**
	 * 上架作品
	 * 
	 * @param goodsid
	 *            作品id
	 * @return true：上架成功 false：上架失败
	 */
	public static boolean shelveGoods(int goodsid) {
		return goodsDao.shelveGoods(goodsid);
	}
}
