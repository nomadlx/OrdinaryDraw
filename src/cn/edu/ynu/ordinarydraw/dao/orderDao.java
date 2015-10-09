package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Cart;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.GoodsTag;
import cn.edu.ynu.ordinarydraw.domain.Order;
import cn.edu.ynu.ordinarydraw.domain.OrderItem;
import cn.edu.ynu.ordinarydraw.domain.SaleHistory;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;
import cn.edu.ynu.ordinarydraw.utils.CONSTANT.TYPE_ORDER;

public class orderDao {

	/**
	 * 根据用户id获取订单列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<Order> getOrderListByUser(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		List<Order> orderList = null;
		try {
			orderList = session.createQuery("from Order as o where o.userid=? order by o.gertdate desc")
					.setInteger(0, userid).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return orderList;
	}

	/**
	 * 根据用户id获取特定类型的订单列表
	 * 
	 * @param userid
	 * @param type
	 * @return
	 */
	public List<Order> getOrderListByType(Integer userid,
			CONSTANT.TYPE_ORDER type) {
		String enumType = null;
		if (type == TYPE_ORDER.TYPE_ORDER_NOPAY) {
			enumType = "0";
		} else if (type == TYPE_ORDER.TYPE_ORDER_CANCEL) {
			enumType = "2";
		} else if (type == TYPE_ORDER.TYPE_ORDER_DONE) {
			enumType = "1";
		}
		Session session = HibernateSessionFactory.getSession();
		List<Order> orderList = null;
		try {
			orderList = session
					.createQuery(
							"from Order as o where o.userid=? and o.statu=? order by o.gertdate desc")
					.setInteger(0, userid).setString(1, enumType).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return orderList;

		// return tests(4);
	}

	/**
	 * 付款订单
	 * 
	 * @param orderid
	 * @return
	 */
	public boolean payOrder(Integer orderid) {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Order order = (Order) session.get(Order.class, orderid);
			order.setStatu("1");
			session.saveOrUpdate(order);
			User user = (User) session.get(User.class, order.getUserid());
			for (OrderItem orderItem : order.getOrderitems()) {
				SaleHistory saleHistory = new SaleHistory(user,
						orderItem.getGoods(), GLobalMethod.getNowTime(),
						orderItem.getGoods().getPrice());
				session.saveOrUpdate(saleHistory);
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("orderDao.payOrder()");
			tx.rollback();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return true;

	}

	/**
	 * 取消订单
	 * 
	 * @param orderid
	 * @return
	 */
	public boolean cancelOrder(Integer orderid) {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Order order = (Order) session.get(Order.class, orderid);
			order.setStatu("2");
			session.saveOrUpdate(order);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("orderDao.cancelOrder()");
			tx.rollback();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return true;

	}

	/**
	 * 保存订单
	 * 
	 * @param order
	 * @return
	 */
	public boolean saveOrder(Order order) {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(order);
			for (OrderItem orderItem : order.getOrderitems()) {
				orderItem.setOrderid(order.getOrderid());
				session.save(orderItem);
				Cart cart = new Cart(order.getUserid(), orderItem.getGoods(),
						null, null);
				cart = (Cart) session.get(Cart.class, cart);
				session.delete(cart);
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			tx.rollback();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
