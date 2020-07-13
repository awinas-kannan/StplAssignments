package com.project.awinas;






import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

	@RequestMapping("/Login")
	public String login(@RequestBody LoginModel lm) 
	{
			
		
		LoginDao dao =new LoginDao();
		
		
		String loginresult;
		
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(LoginModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		Object obj =  session.get(LoginModel.class, lm.getUserid());
		
		trx.commit();
		
		if(obj!=null)
		{
		loginresult = dao.getCredentialResult(lm);
		
		}
		else
		{
	  loginresult="UNSUCCESSFUL";
		}
		return loginresult;	
	}
}
