package cn.edu.ynu.ordinarydraw.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.cartDao;
import cn.edu.ynu.ordinarydraw.dao.commentDao;
import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.likeDao;
import cn.edu.ynu.ordinarydraw.dao.reportDao;
import cn.edu.ynu.ordinarydraw.dao.salehistoryDao;
import cn.edu.ynu.ordinarydraw.domain.Comment;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.Report;
import cn.edu.ynu.ordinarydraw.domain.SaleHistory;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class workshowService {
	private static goodsDao goodsDao;
	private static commentDao commentDao;
	private static salehistoryDao salehistoryDao;
	private static cartDao cartDao;
	private static likeDao likeDao;
	private static reportDao reportDao;
	static {
		goodsDao = new goodsDao();
		commentDao = new commentDao();
		salehistoryDao = new salehistoryDao();
		cartDao = new cartDao();
		likeDao = new likeDao();
		reportDao = new reportDao();
	}

	/**
	 * 获取作品信息
	 * 
	 * @param session
	 */
	public static void getGoodsInfo(int goodsid, Map<String, Object> session) {
		Goods goods = goodsDao.getGoodsById(goodsid);
		Set<GoodsImg> goodsImgs = goods.getGoodsimgs();
		for (GoodsImg goodsImg : goodsImgs) {
			goodsImg.setPath(goodsImg
					.getPath()
					.replace(CONSTANT.IMG_PATH_WORK_ORDINARY,
							CONSTANT.IMG_PATH_WORK_SQUARE)
					.replace("jpeg", "PNG"));
		}
		session.put("liked", likeDao.islike(GLobalMethod.getNowUser(session)
				.getUserid(), goodsid));
		session.put("goodsInfo", goods);
	}

	/**
	 * 获取评论列表
	 * 
	 * @param session
	 */
	public static void getCommentList(int goodsid, Map<String, Object> session) {
		List<Comment> goodsCommentList = commentDao
				.getCommentListByGoods(goodsid);
		session.put("goodsComment", goodsCommentList);
	}

	/**
	 * 获取销售列表
	 * 
	 * @param session
	 */
	public static void getSaleList(int goodsid, Map<String, Object> session) {
		List<SaleHistory> goodsSaleHistorieList = salehistoryDao
				.getSaleListByGoods(goodsid);
		session.put("goodsSaleHistorie", goodsSaleHistorieList);
	}

	/**
	 * 喜爱作品
	 * 
	 * @param goodsid
	 * @return
	 */
	public static boolean likeGoods(int goodsid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return likeDao.likeGoods(user.getUserid(), goodsid);
	}

	/**
	 * 取消喜爱
	 * 
	 * @param goodsid
	 * @return
	 */
	public static boolean unlikeGoods(int goodsid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return likeDao.unlikeGoods(user.getUserid(), goodsid);
	}

	/**
	 * 加入购物车
	 * 
	 * @param goodsid
	 * @return
	 */
	public static boolean addtoCart(int goodsid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return cartDao.addCartItem(user.getUserid(), goodsid);
	}

	/**
	 * 发表评论
	 * 
	 * @param goodsid
	 * @param comment
	 * @return
	 */
	public static boolean publishComment(int goodsid, String content,
			Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		Comment comment = new Comment(null, user, goodsid, content,
				GLobalMethod.getNowTime());
		return commentDao.publishComment(comment);
	}

	/**
	 * 刷新评论
	 * 
	 * @param goodsid
	 * @param json
	 */
	public static void loadCommentList(int goodsid, Map<String, Object> json) {
		List<Comment> goodsCommentList = commentDao
				.getCommentListByGoods(goodsid);
		json.put("count", goodsCommentList.size());
		json.put("goodsComment", goodsCommentList);
	}

	/**
	 * 获取大图
	 * 
	 * @param goodsid
	 * @param seq
	 * @return
	 */
	public static String getImg(int goodsid, int seq) {
		Goods goods = goodsDao.getGoodsById(goodsid);
		String path = "";
		for (GoodsImg goodsImg : goods.getGoodsimgs()) {
			if (goodsImg.getSeq() == seq) {
				path = goodsImg.getPath();
				break;
			}
		}
		return path.replace(CONSTANT.IMG_PATH_WORK_SQUARE,
				CONSTANT.IMG_PATH_WORK_ORDINARY);
	}

	/**
	 * 举报该作品
	 * 
	 * @param goodsid
	 * @return
	 */
	public static boolean reportGoods(int goodsid, String reason,
			Map<String, Object> session) {
		Report report = new Report(null, GLobalMethod.getNowUser(session)
				.getUserid(), goodsid, reason, GLobalMethod.getNowTime(), "0",
				null, null);
		return reportDao.saveReport(report);
	}
}
