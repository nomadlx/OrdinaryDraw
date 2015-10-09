package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.GoodsTag;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;
import cn.edu.ynu.ordinarydraw.utils.GLobalMethod;

public class goodsDao {
	public List<Goods> tests(int n) {
		List<Goods> list = new ArrayList<Goods>();
		Set<GoodsImg> goodsimgs = new HashSet<GoodsImg>();
		GoodsImg img = new GoodsImg();
		img.setPath("resources/img/img_default_work.jpg");
		goodsimgs.add(img);
		Set<GoodsTag> goodsTags = new HashSet<GoodsTag>();
		GoodsTag goodstag = new GoodsTag();
		Tag tag = new Tag();
		tag.setTname("标签");
		goodstag.setTag(tag);
		goodsTags.add(goodstag);
		for (int i = 0; i < n; i++) {
			Goods goods = new Goods();
			goods.setGoodsid(1110 + i);
			User user = new User();
			user.setUname("作者" + i);
			user.setImg("resources/img/img_test_face.jpg");
			user.setAddr("中国，昆明");
			user.setUdesc("比癫狂少点理智。");
			goods.setGoodsid(1);
			goods.setGoodsimgs(goodsimgs);
			goods.setGoodstags(goodsTags);
			goods.setGname("test" + i);
			goods.setPrice((float) i);
			goods.setUser(user);
			goods.setGdesc("这是一段作品描述的内容。");
			goods.setUpdatetime("2015-9-22");
			goods.setStatu("" + (i % 3));
			list.add(goods);
		}
		return list;
	}

	/**
	 * 获取前n个热门作品
	 * 
	 * @param n
	 * @return
	 */
	public List<Goods> getHotGoods(int n) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			System.out.println("个数" + goodsList.size());
			List<Object[]> list = session
					.createSQLQuery("call proc_best_hot_goods(?)")
					.setCacheMode(CacheMode.IGNORE).setInteger(0, n).list();
			for (Object[] obj : list) {
				Goods goods = this.getGoodsById(Integer.valueOf(obj[0]
						.toString()));
				goodsList.add(goods);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		HibernateSessionFactory.closeSession();
		System.out.println("个数" + goodsList.size());
		return goodsList;
	}

	/**
	 * 根据标签获取前n个最新作品
	 * 
	 * @param tagid
	 * @param n
	 * @return
	 */
	public List<Goods> getGoodsListByTag(Integer tagid, int n) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		String HQL = "from Goods as g where g.goodsid in(select gs.goodsid from GoodsTag as gs where gs.tag.tagid in"
				+ "(select ggs.tagid from Tag as ggs where ggs.tagid like ?))order by g.updatetime ";
		Query query = session.createQuery(HQL).setCacheMode(CacheMode.IGNORE);
		query.setParameter(0, tagid).setMaxResults(n);
		List<Goods> list = query.list();
		// for (Goods gs : list) {
		// gs.getGoodsimgs()
		// }
		// for(int i=0;i<list.size();i++){
		// GoodsImg is =new GoodsImg();
		//
		// }
		HibernateSessionFactory.closeSession();
		System.out.println("个数" + list.size());
		return list;
	}

	/**
	 * 根据标签获取所有作品
	 * 
	 * @param tagid
	 * @return
	 */
	public List<Goods> getGoodsListByTag(Integer tagid) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		String HQL = "from Goods as g where g.goodsid in(select gs.goodsid from GoodsTag as gs where gs.tag.tagid like ?)";
		Query query = session.createQuery(HQL).setCacheMode(CacheMode.IGNORE);
		query.setParameter(0, tagid);
		List<Goods> list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	/**
	 * 根据用户id获取关注推送
	 * 
	 * @param userid
	 * @return
	 */
	public List<Goods> getPushGoodsListByUser(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		String HQL = "select g from Goods as g ,"
				+ "Follow as f where g.user.userid = f.userid2 and f.userid1 =? order by g.updatetime desc";
		Query query = session.createQuery(HQL).setCacheMode(CacheMode.IGNORE);
		query.setParameter(0, userid);
		List<Goods> list = query.list();
		HibernateSessionFactory.closeSession();
		System.out.println("个数" + list.size());
		return list;
	}

	/**
	 * 根据用户id获取喜爱集
	 * 
	 * @param userid
	 * @return
	 */
	public List<Goods> getLikeGoodsListByUser(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		String HQL = "select g from Goods as g ,Like as l where g.goodsid=l.goodsid and l.userid =? order by l.likedate desc";
		Query query = session.createQuery(HQL).setCacheMode(CacheMode.IGNORE);
		query.setParameter(0, userid);
		List<Goods> list = query.list();
		HibernateSessionFactory.closeSession();
		System.out.println("个数" + list.size());
		return list;
	}

	/**
	 * 根据作者id获取所有作品,非删除作品，包含下架作品
	 * 
	 * @param userid
	 * @return
	 */
	public List<Goods> getAllGoodsListByUser(Integer userid) {

		List<Goods> list = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.clear();
			String HQL = "from Goods as g where g.user.userid=?";
			Query query = session.createQuery(HQL).setCacheMode(
					CacheMode.IGNORE);
			query.setParameter(0, userid);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("goodsDao.getAllGoodsListByUser()");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/**
	 * 根据作者id获取所有作品,不包含下架和已删除作品
	 * 
	 * @param userid
	 * @return
	 */
	public List<Goods> getGoodsListByUser(Integer userid) {
		Session session = HibernateSessionFactory.getSession();
		session.clear();
		String HQL = "from Goods as g where g.user.userid = ? and g.statu='0'";
		Query query = session.createQuery(HQL).setCacheMode(CacheMode.IGNORE);
		query.setParameter(0, userid);
		List<Goods> list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}

	/**
	 * 根据作品id获取作品信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public Goods getGoodsById(Integer goodsid) {
		Goods goods = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			goods = (Goods) session.get(Goods.class, goodsid);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("goodsDao.getGoodsById()");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return goods;
	}

	/**
	 * 下架作品
	 * 
	 * @param goodsid
	 * @return
	 */
	public boolean offshelveGoods(Integer goodsid) {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Goods goods = (Goods) session.get(Goods.class, goodsid);
			goods.setStatu("1");
			session.save(goods);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("goodsDao.offshelveGoods()");
			tx.rollback();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return true;

	}

	/**
	 * 上架作品
	 * 
	 * @param goodsid
	 * @return
	 */
	public boolean shelveGoods(Integer goodsid) {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Goods goods = (Goods) session.get(Goods.class, goodsid);
			goods.setStatu("0");
			session.save(goods);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("goodsDao.offshelveGoods()");
			tx.rollback();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return true;
	}

	/**
	 * 删除作品，软删除
	 * 
	 * @param goodsid
	 * @return
	 */
	public boolean deleteGoods(Integer goodsid) {
		return false;

	}

	/**
	 * 更新作品
	 * 
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods, String tags) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			String hql1 = "delete from GoodsImg where goodsid=?";
			Query hQuery1 = session.createQuery(hql1);
			hQuery1.setInteger(0, goods.getGoodsid());
			hQuery1.executeUpdate();
			String[] tagStrings = tags.trim().split(" ");
			List<String> taglist = new ArrayList<String>();
			for (String s : tagStrings) {
				taglist.add(s);
			}
			for (GoodsTag goodsTag : goods.getGoodstags()) {
				if (taglist.contains(goodsTag.getTag().getTname())) {
					taglist.remove(goodsTag.getTag().getTname());
				} else {
					Tag tag = goodsTag.getTag();
					tag.setGoodscount(tag.getGoodscount() - 1);
					session.saveOrUpdate(tag);
					session.delete(goodsTag);
				}
			}
			for (String tagname : taglist) {
				String hql2 = "from Tag where tname=?";
				Query hQuery2 = session.createQuery(hql2);
				hQuery2.setString(0, tagname);
				List<Tag> tagsList = hQuery2.list();
				Tag tag;
				if (tagsList.isEmpty()) {
					tag = new Tag(null, tagname, GLobalMethod.getNowTime(), 1);
				} else {
					tag = tagsList.get(0);
					tag.setGoodscount(tag.getGoodscount() + 1);
					tag.setUpdatetime(GLobalMethod.getNowTime());
				}
				session.saveOrUpdate(tag);
				GoodsTag goodsTag = new GoodsTag();
				goodsTag.setGoodsid(goods.getGoodsid());
				goodsTag.setSeq((short) goods.getGoodstags().size());
				goodsTag.setTag(tag);
				session.save(goodsTag);
			}
			String hql = "from GoodsTag where goodsid=?";
			Query query = session.createQuery(hql).setInteger(0,
					goods.getGoodsid());
			List<GoodsTag> goodsTags = query.list();
			for (int i = 0; i < goodsTags.size(); i++) {
				String tname = goodsTags.get(i).getTag().getTname();
				int j = 0;
				for (; j < tagStrings.length; j++) {
					if (tagStrings[j].equals(tname)) {
						break;
					}
				}
				goodsTags.get(i).setSeq((short) j);
				session.save(goodsTags.get(i));
			}
			for (GoodsImg goodsimg : goods.getGoodsimgs()) {
				goodsimg.setGoodsid(goods.getGoodsid());
				session.saveOrUpdate(goodsimg);
			}
			Goods goods2 = (Goods) session.get(Goods.class, goods.getGoodsid());
			goods2.setGdesc(goods.getGdesc());
			goods2.setGname(goods.getGname());
			goods2.setImgcount(goods.getImgcount());
			goods2.setPrice(goods.getPrice());
			goods2.setPublishtime(goods.getPublishtime());
			goods2.setStatu(goods.getStatu());
			goods2.setUpdatetime(goods.getUpdatetime());
			session.update(goods2);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("goodsDao.updateGoods()");
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
	 * 发布作品，新增/更新
	 * 
	 * @param goods
	 * @return
	 */
	public boolean saveGoods(Goods goods) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(goods);
			for (GoodsTag goodstag : goods.getGoodstags()) {
				goodstag.setGoodsid(goods.getGoodsid());
				// 修改数量
				Tag tag = goodstag.getTag();
				tag.setGoodscount(tag.getGoodscount() + 1);
				session.saveOrUpdate(tag);
				session.saveOrUpdate(goodstag);
			}
			for (GoodsImg goodsimg : goods.getGoodsimgs()) {
				goodsimg.setGoodsid(goods.getGoodsid());
				session.saveOrUpdate(goodsimg);
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("goodsDao.saveGoods()");
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
		goodsDao dao = new goodsDao();
		dao.shelveGoods(1);
		// System.out.println(goods.getGoodsimgs().get(0).getPath());
	}

}
