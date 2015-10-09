package cn.edu.ynu.ordinarydraw.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Follow;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class followDao {
	/**
	 * 关注用户
	 * 
	 * @param userid1
	 * @param userid2
	 * @return
	 */
	public boolean followUser(Integer userid1, Integer userid2) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Follow follow = new Follow(userid1, userid2, GLobalMethod.getNowTime());
			session.save(follow);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("followDao.followUser()");
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
	 * 取消关注用户
	 * 
	 * @param userid1
	 * @param userid2
	 * @return
	 */
	public boolean unfollowUser(Integer userid1, Integer userid2) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Follow follow = new Follow(userid1, userid2, null);
			follow = (Follow) session.get(Follow.class, follow);
			session.delete(follow);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("followDao.unfollowUser()");
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
	 * 判断用户是否对用户进行了关注
	 * 
	 * @param userid1
	 * @param userid2
	 * @return
	 */
	public boolean isfollow(Integer userid1, Integer userid2) {
		Session session = null;
		Transaction tx=null;
		Follow follow = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			follow = new Follow(userid1, userid2, null);
			follow = (Follow) session.get(Follow.class, follow);
			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("followDao.isfollow()");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		if (follow == null) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		followDao dao = new followDao();
		dao.followUser(2, 3);
	}

}
