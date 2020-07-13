package com.project.awinas;

import java.io.IOException;




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
public class DisplayController {

	
	@RequestMapping("/display")
	public StudentModel display(@RequestBody int id) throws IOException 
	{
	
	
	StudentDao dao =new StudentDao();
	StudentModel displayresult =new StudentModel();
	

	Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
	ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
			.buildServiceRegistry();
	SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();

	Object obj =  session.get(StudentModel.class, id);
	
	trx.commit();
	
	
	if(obj!=null)
	{
	 displayresult = dao.getStudentDetail(id);
	}
	
	return displayresult;
	}

}
