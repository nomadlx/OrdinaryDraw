package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional.TxType;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Follow;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.MD5Encoder;

public class userDao {

	/**
	 * 根据邮箱获取用户
	 * 
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email) {
		User user = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.clear();
			@SuppressWarnings("unchecked")
			List<User> userList = session
					.createQuery("from User as u where u.email=?")
					.setCacheMode(CacheMode.IGNORE).setString(0, email).list();
			if (!userList.isEmpty()) {
				user = userList.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("userDao.getUserByEmail()");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return user;
	}

	/**
	 * 获取前n个热门创作者
	 * 
	 * @param n
	 * @return
	 */
	public List<User> getHotUser(int n) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		List<User> userList = new ArrayList<User>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> list = session
					.createSQLQuery("call proc_best_hot_user(?)")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, n).list();
			for (Object[] obj : list) {
				User user = this
						.getUserById(Integer.valueOf(obj[0].toString()));
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return userList;
	}

	/**
	 * 根据标签获取热门创作者
	 * 
	 * @param tagid
	 * @return
	 */
	public User getHotUserByTag(Integer tagid) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		List<User> userList = new ArrayList<User>();
		try {
			List<Object[]> list = session
					.createSQLQuery("call proc_hot_tag_user(?)")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, tagid).list();
			System.out.println(list.size() + "dahuai");
			for (Object[] obj : list) {
				User user = this
						.getUserById(Integer.valueOf(obj[0].toString()));
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return userList.get(0);
	}

	/**
	 * 获取用户的关注用户列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<User> getFollowUserByUser(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		List<User> userList = new ArrayList<User>();
		List<Follow> followList = null;
		try {
			session.clear();
			followList = session
					.createQuery(
							"from Follow as f where f.userid1=? order by f.followdate desc")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, userid)
					.list();
			for (int i = 0; i < followList.size(); i++) {
				User user = (User) session.get(User.class, followList.get(i)
						.getUserid2());
				userList.add(user);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return userList;
	}

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserById(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		List<User> userList = null;
		try {
			userList = session.createQuery("from User as u where u.userid=?")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, userid)
					.list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return userList.get(0);
	}

	/**
	 * 根据关键字搜索用户
	 * 
	 * @param key
	 * @return
	 */
	public List<User> getUserListByKey(String key) {
		Session session = null;
		Transaction tx = null;
		List<User> list = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			System.out.println(key);
			String hql = "from User u where u.uname like '%" + key
					+ "%' or u.email like '%" + key + "%' or u.addr like '%" + key
					+ "%' or u.udesc like '%" + key + "%' ";
			list = (List<User>) session.createQuery(hql).list();
			System.out.println(list.size());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("userDao.getUserListByKey()");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateProfile(User user) {
		return false;

	}

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public boolean updatePwd(User user) {
		return false;

	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("userDao.saveUser()");
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
