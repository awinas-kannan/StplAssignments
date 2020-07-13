package com.project.awinas;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class StudentDao {

	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;

	public static final String STR = "NO DETAILS FOUND";

	public StudentDao() {
		// StudentDao
	}

	void addStudentDetail(StudentModel asm) {

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		
		Transaction trx = session.beginTransaction();
		session.load(StudentModel.class, asm.getId());
		session.save(asm);
		trx.commit();
		calcrank();

	}

	public void calcrank() {

		String ranksortquery = "from StudentModel order by total desc";

		List<StudentModel> getdetails;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		Query qq = session.createQuery(ranksortquery);
		getdetails =  qq.list();

		trx.commit();

		setrank(getdetails);

	}

	public void setrank(List<StudentModel> getdetails) {

		
		String updatestring = "update  StudentModel set RANK= :rk where ID=:rid";
		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		Query qq = session.createQuery(updatestring);

		int i;
		int rank = 1;
		int before = 0;

		for (i = 0; i < getdetails.size(); i++) {
			StudentModel ss;

			ss = getdetails.get(i);

			if (i == 0) {

				qq.setParameter("rk", rank);

				qq.setParameter("rid", ss.getId());
				before = ss.getTotal();
				qq.executeUpdate();
			
			}
			else {
				if (ss.getTotal() != before) {
					qq.setParameter("rk", rank + 1);
					qq.setParameter("rid", ss.getId());
					before = ss.getTotal();
					qq.executeUpdate();
					rank++;
				} 
				else {
					qq.setParameter("rk", rank);
					qq.setParameter("rid", ss.getId());
					before = ss.getTotal();
					qq.executeUpdate();
				}
			}
		}
		trx.commit();

	}

	StudentModel getStudentDetail(int id) {

		StudentModel dsm ;
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		dsm = (StudentModel) session.get(StudentModel.class, id);
		trx.commit();
		return dsm;
	}

	String deleteStudentDetail(int id) {
		int count = 0;
		String deletequery = "delete from StudentModel where ID =:id";
		String displaystring ;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		Query delquery = session.createQuery(deletequery);
		delquery.setInteger("id", id);
		count = delquery.executeUpdate();

		trx.commit();

		if (count != 0) {
			displaystring = "REMOVED";
		} 
		else {
			displaystring = STR;
		}
		calcrank();
		return displaystring;
	}

	List<StudentModel> getStudentRank(int rank) {

		List<StudentModel> rsm;

		String selectstring = " FROM StudentModel where RANK= :rank";

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		Query selectrank = session.createQuery(selectstring);
		selectrank.setInteger("rank", rank);

		rsm = (List<StudentModel>) selectrank.list();

		trx.commit();

		return rsm;

	}

	void updateStudentDetail(StudentModel asm) {

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		session.update(asm);
		trx.commit();
		calcrank();
	}

}
