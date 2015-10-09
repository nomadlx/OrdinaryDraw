package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Cart;
import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.GoodsTag;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class cartDao {
	/**
	 * 获取用户的购物车商品列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<Cart> getCartListByUser(Integer userid) {
		Session session = null;
		Transaction tx = null;
		List<Cart> list=null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.clear();
			session.setCacheMode(CacheMode.IGNORE);
			String HQL = "from Cart as sl where sl.userid =? order by sl.addtime desc";
			Query query = session.createQuery(HQL);
			query.setParameter(0, userid);
			list = query.list();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("cartDao.getCartListByUser()");
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/**
	 * 移除购物车项
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public boolean removeCartItem(Integer userid, Integer goodsid) {
		Session session=null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Goods goods = (Goods) session.get(Goods.class, goodsid);
			Cart cart = new Cart(userid, goods, null, null);
			cart = (Cart) session.get(Cart.class, cart);
			session.delete(cart);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("cartDao.removeCartItem()");
			tx.rollback();
			return false;
		}finally{
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	/**
	 * 添加购物车项
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public boolean addCartItem(Integer userid, Integer goodsid) {
		Session session =null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Goods goods = (Goods) session.get(Goods.class, goodsid);
			Cart cart = new Cart(userid, goods, GLobalMethod.getNowTime(), "0");
			session.save(cart);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("cartDao.addCartItem()");
			tx.rollback();
			return false;
		}finally{
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cartDao dao = new cartDao();
		dao.removeCartItem(2, 1);
	}

}
