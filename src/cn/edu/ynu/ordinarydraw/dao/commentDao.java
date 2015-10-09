package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Cart;
import cn.edu.ynu.ordinarydraw.domain.Comment;
import cn.edu.ynu.ordinarydraw.domain.User;

public class commentDao {

	/**
	 * 根据作品获取评论列表
	 * 
	 * @param goodsid
	 * @return
	 */
	public List<Comment> getCommentListByGoods(Integer goodsid) {
		Session session = null;
		Transaction tx = null;
		List<Comment> list=null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.clear();
			session.setCacheMode(CacheMode.IGNORE);
			String HQL = "from Comment as c where c.goodsid =? order by c.publishtime desc";
			Query query = session.createQuery(HQL);
			query.setParameter(0, goodsid);
			tx.commit();
			list = query.list();
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
	 * 发表评论
	 * 
	 * @param comment
	 * @return
	 */
	public boolean publishComment(Comment comment) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(comment);;
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("commentDao.publishComment()");
			tx.rollback();
			return false;
		} finally {
			session.flush();
			session.clear();
			HibernateSessionFactory.closeSession();
		}
		return true;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		commentDao dao = new commentDao();
		User user = new userDao().getUserByEmail("nomadlx@live.cn");
		Comment comment = new Comment(null, user, 18, "neir", "2015-9-6");
		dao.publishComment(comment);
	}

}
