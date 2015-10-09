package cn.edu.ynu.ordinarydraw.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Follow;
import cn.edu.ynu.ordinarydraw.domain.Like;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class likeDao {
	/**
	 * 喜爱作品
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public boolean likeGoods(Integer userid, Integer goodsid) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			System.out.println(userid + "," + goodsid);
			Like like = new Like(userid, goodsid, GLobalMethod.getNowTime());
			session.save(like);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("likeDao.likeGoods()");
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
	 * 取消喜爱作品
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public boolean unlikeGoods(Integer userid, Integer goodsid) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Like like = new Like(userid, goodsid, null);
			like = (Like) session.get(Like.class, like);
			session.delete(like);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("likeDao.unlikeGoods()");
			tx.rollback();
			return false;
		} finally {
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	public boolean islike(Integer userid, Integer goodsid) {
		Session session = null;
		Like like = null;
		try {
			session = HibernateSessionFactory.getSession();
			like = new Like(userid, goodsid, null);
			like = (Like) session.get(Like.class, like);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("likeDao.islike()");
			return false;
		} finally {
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		if (like == null) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		likeDao dao = new likeDao();
		dao.likeGoods(2, 1);
	}

}
