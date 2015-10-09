package cn.edu.ynu.ordinarydraw.dao;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Subscribe;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class subscribeDao {
	/**
	 * 订阅标签
	 * 
	 * @param userid
	 * @param tagid
	 * @return
	 */
	public boolean subscribeTag(Integer userid, Integer tagid) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.clear();
			session.setCacheMode(CacheMode.IGNORE);
			tx = session.beginTransaction();
			Subscribe subscribe = new Subscribe(userid, tagid,
					GLobalMethod.getNowTime());
			session.save(subscribe);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("subscribeDao.subscribeTag()");
			tx.rollback();
			return false;
		} finally {
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	/**
	 * 取消订阅标签
	 * 
	 * @param userid
	 * @param tagid
	 * @return
	 */
	public boolean unsubscribeTag(Integer userid, Integer tagid) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.clear();
			session.setCacheMode(CacheMode.IGNORE);
			tx = session.beginTransaction();
			Subscribe subscribe = new Subscribe(userid, tagid, null);
			subscribe = (Subscribe) session.get(Subscribe.class, subscribe);
			session.delete(subscribe);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("subscribeDao.subscribeTag()");
			tx.rollback();
			return false;
		} finally {
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	/**
	 * 判断用户是否订阅了该标签
	 * 
	 * @param userid
	 * @param tagid
	 * @return
	 */
	public boolean isSubscribe(Integer userid, Integer tagid) {
		Subscribe subscribe = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.clear();
			subscribe = new Subscribe(userid, tagid, null);
			session.setCacheMode(CacheMode.IGNORE);
			subscribe = (Subscribe) session.get(Subscribe.class, subscribe);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("subscribeDao.isSubscribe()");
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		if (subscribe == null) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		subscribeDao dao = new subscribeDao();
		dao.unsubscribeTag(2, 1);
	}

}
