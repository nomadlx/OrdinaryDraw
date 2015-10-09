package cn.edu.ynu.ordinarydraw.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.ynu.ordinarydraw.domain.Report;



public class reportDao {
	public boolean saveReport(Report report) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(report);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			System.out.println("reportDao.getReportById()");
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
