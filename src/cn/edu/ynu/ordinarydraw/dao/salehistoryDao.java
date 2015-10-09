package cn.edu.ynu.ordinarydraw.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import cn.edu.ynu.ordinarydraw.domain.Goods;
import cn.edu.ynu.ordinarydraw.domain.GoodsImg;
import cn.edu.ynu.ordinarydraw.domain.GoodsTag;
import cn.edu.ynu.ordinarydraw.domain.SaleHistory;
import cn.edu.ynu.ordinarydraw.domain.Tag;
import cn.edu.ynu.ordinarydraw.domain.User;

public class salehistoryDao {

	/**
	 * 根据用户id获取已购列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<SaleHistory> getSaleListByUser(Integer userid) {
		Session session=HibernateSessionFactory.getSession();
		List<SaleHistory> historyList = null;
		try {
			historyList=session.createQuery("from SaleHistory as sh where sh.user.userid=? order by sh.buytime desc")
					.setInteger(0, userid)
					.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return historyList;
	}

	/**
	 * 根据作品id获取销售记录
	 * 
	 * @param goods
	 * @return
	 */
	public List<SaleHistory> getSaleListByGoods(Integer goodsid) {
		Session session=HibernateSessionFactory.getSession();
		List<SaleHistory> historyList = null;
		try {
			historyList=session.createQuery("from SaleHistory as sh where sh.goods.goodsid=?")
					.setInteger(0, goodsid)
					.list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return historyList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
