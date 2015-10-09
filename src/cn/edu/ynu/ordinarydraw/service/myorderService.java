package cn.edu.ynu.ordinarydraw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.orderDao;
import cn.edu.ynu.ordinarydraw.dao.salehistoryDao;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.Order;
import cn.edu.ynu.ordinarydraw.domain.SaleHistory;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.ZIPpack;

public class myorderService {
	private static orderDao orderDao;
	private static salehistoryDao salehistoryDao;
	private static goodsDao goodsDao;
	static {
		orderDao = new orderDao();
		salehistoryDao = new salehistoryDao();
		goodsDao = new goodsDao();
	}

	/**
	 * 获取所有订单
	 * 
	 * @param session
	 */
	public static void getAllOrder(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Order> OrderList = orderDao.getOrderListByUser(user.getUserid());
		session.put("allOrder", OrderList);
	}

	/**
	 * 获取已购作品
	 * 
	 * @param session
	 */
	public static void getAllGoods(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<SaleHistory> saleGoodsList = salehistoryDao.getSaleListByUser(user
				.getUserid());
		session.put("saleGoods", saleGoodsList);
	}

	/**
	 * 获取未付款订单
	 * 
	 * @param session
	 */
	public static void getNopayOrder(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Order> nopayOrderList = orderDao.getOrderListByType(
				user.getUserid(), CONSTANT.TYPE_ORDER.TYPE_ORDER_NOPAY);
		session.put("nopayOrder", nopayOrderList);
	}

	/**
	 * 获取已取消订单
	 * 
	 * @param session
	 */
	public static void getCancelOrder(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Order> cancelOrderList = orderDao.getOrderListByType(
				user.getUserid(), CONSTANT.TYPE_ORDER.TYPE_ORDER_CANCEL);
		session.put("cancelOrder", cancelOrderList);
	}

	/**
	 * 获取已完成订单
	 * 
	 * @param session
	 */
	public static void getDoneOrder(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Order> doneOrderList = orderDao.getOrderListByType(
				user.getUserid(), CONSTANT.TYPE_ORDER.TYPE_ORDER_DONE);
		session.put("doneOrder", doneOrderList);
	}

	/**
	 * 付款
	 * 
	 * @param orderid
	 * @return
	 */
	public static boolean payOrder(int orderid) {
		return orderDao.payOrder(orderid);
	}

	/**
	 * 取消
	 * 
	 * @param orderid
	 * @return
	 */
	public static boolean cancelOrder(int orderid) {
		return orderDao.cancelOrder(orderid);
	}

	/**
	 * 下载文件
	 * 
	 * @param uuid
	 * @param session
	 * @return
	 * @throws IOException
	 */
	public static InputStream download(int goodsid, Map<String, Object> session)
			throws IOException {
		Goods goods = goodsDao.getGoodsById(goodsid);
		String[] srcfiles = new String[goods.getGoodsimgs().size()];
		int i = 0;
		for (GoodsImg goodsImg : goods.getGoodsimgs()) {
			srcfiles[i] = goodsImg.getPath().replace(
					CONSTANT.IMG_LOGICAL_PATH + CONSTANT.IMG_PATH_WORK_SQUARE,
					CONSTANT.IMG_PHYSICAL_PATH
							+ CONSTANT.IMG_PATH_WORK_ORDINARY);
			i++;
		}
		String decfile = CONSTANT.IMG_PHYSICAL_PATH
				+ CONSTANT.IMG_PATH_WORK_RECT + goods.getGname() + ".zip";
		// 打包图片
		ZIPpack.packfiles(srcfiles, decfile);
		File file = new File(decfile);
		try {
			InputStream ips = new FileInputStream(file);
			session.put("downfileName", "test");
			return ips;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
