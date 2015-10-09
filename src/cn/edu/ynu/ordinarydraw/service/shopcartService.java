package cn.edu.ynu.ordinarydraw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.ynu.ordinarydraw.dao.cartDao;
import cn.edu.ynu.ordinarydraw.dao.goodsDao;
import cn.edu.ynu.ordinarydraw.dao.orderDao;
import cn.edu.ynu.ordinarydraw.domain.Cart;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.Order;
import cn.edu.ynu.ordinarydraw.domain.OrderItem;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class shopcartService {
	public static cartDao cartDao;
	private static goodsDao goodsDao;
	private static orderDao orderDao;
	static {
		cartDao = new cartDao();
		goodsDao = new goodsDao();
		orderDao=new orderDao();
	}

	/**
	 * 获取商品列表
	 * 
	 * @param session
	 */
	public static void getGoodsList(Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		List<Cart> cartGoodsList = cartDao.getCartListByUser(user.getUserid());
		session.put("cartGoods", cartGoodsList);
	}

	/**
	 * 移除商品
	 * 
	 * @param goodsid
	 *            商品id
	 * @return true：移除成功 false：移除失败
	 */
	public static boolean removeGoods(int goodsid, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		return cartDao.removeCartItem(user.getUserid(), goodsid);
	}

	/**
	 * 生成订单
	 * 
	 * @param data
	 * @return
	 */
	public static boolean balanceCart(String data, Map<String, Object> session) {
		User user = GLobalMethod.getNowUser(session);
		String[] strs = data.split(",");
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		for (String str : strs) {
			OrderItem orderItem = new OrderItem();
			Goods goods = goodsDao.getGoodsById(Integer.valueOf(str));
			orderItem.setGoods(goods);
			orderItem.setPrice(goods.getPrice());
			orderItems.add(orderItem);
		}
		Order order = new Order(1, (float) 0, GLobalMethod.getNowTime(), (short) 0, "0",
				user.getUserid(), orderItems);
		return orderDao.saveOrder(order);
	}
}
