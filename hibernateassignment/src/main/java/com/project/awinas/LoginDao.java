package com.project.awinas;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class LoginDao {

	String getCredentialResult(LoginModel lm) {

		String loginresult ;

		LoginModel lmgot;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(LoginModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		lmgot = (LoginModel) session.get(LoginModel.class, lm.getUserid());

		trx.commit();

		if (lmgot.getPwd().equals(lm.getPwd())) {

			loginresult = "SUCCESSFUL";
		} 
		else {
			loginresult = "UNSUCCESSFUL";
		}

		return loginresult;
	}
}
