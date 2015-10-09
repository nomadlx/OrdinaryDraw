package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.Subscribe;
import cn.edu.ynu.ordinarydraw.domain.Tag;

public class tagDao {
	
	/**
	 * 获取用户订阅的标签
	 * 
	 * @param userid
	 * @return
	 */
	public List<Tag> getTagListByUser(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		List<Tag> tagList = new ArrayList<Tag>();
		List<Subscribe> subList = null;
		try {
			subList = session
					.createQuery("from Subscribe as sub where sub.userid=?")
					.setInteger(0, userid).list();
			for (int i = 0; i < subList.size(); i++) {
				Tag tag = this.getTagById(subList.get(i).getTagid());
				tagList.add(tag);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return tagList;
	}

	/**
	 * 获取前n个热门标签
	 * 
	 * @param n
	 * @return
	 */
	public List<Tag> getHotTag(int n) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		List<Tag> tagList = new ArrayList<Tag>();
		try {
			List<Object[]> list = session
					.createSQLQuery("call proc_best_hot_tag(?)")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, n).list();
			for (Object[] obj : list) {
				Tag tag = this.getTagById(Integer.valueOf(obj[0].toString()));
				tagList.add(tag);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return tagList;
	}

	/**
	 * 获取推荐给用户userid的n个标签
	 * 
	 * @param userid
	 * @param n
	 * @return
	 */
	public List<Tag> getRecommendTag(Integer userid, int n) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		List<Tag> tagList = new ArrayList<Tag>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> list = session
					.createSQLQuery("call proc_recommend_tag(?,?)")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, userid)
					.setInteger(1, n).list();
			for (Object[] obj : list) {
				Tag tag = this.getTagById(Integer.valueOf(obj[0].toString()));
				tagList.add(tag);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return tagList;
	}

	/**
	 * 根据id获取标签
	 * 
	 * @param tagid
	 * @return
	 */
	public Tag getTagById(Integer tagid) {
		Tag tag = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tag = (Tag) session.get(Tag.class, tagid);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("tagDao.getTagById()");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return tag;
	}

	/**
	 * 根据标签名字获取标签
	 * 
	 * @param tname
	 * @return
	 */
	public Tag getTagByName(String tname) {
		Tag tag = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.clear();
			session.setCacheMode(CacheMode.IGNORE);
			String HQL = "from Tag as t where t.tname=?";
			Query query = session.createQuery(HQL);
			query.setString(0, tname);
			if (!query.list().isEmpty()) {
				tag = (Tag) query.list().get(0);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("tagDao.getTagByName()");
		} finally {
			
			HibernateSessionFactory.closeSession();
		}
		return tag;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
