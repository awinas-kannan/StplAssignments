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
public class AddController {

	@RequestMapping("/add")
	public String add(@RequestBody StudentModel asm) throws IOException  
	{
	StudentDao dao =new StudentDao();

	String result ;
	
	Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
	ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
			.buildServiceRegistry();
	SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();

	Object obj =  session.get(StudentModel.class, asm.getId());
	
	trx.commit();
	
	if(obj==null)
	{
	
	dao.addStudentDetail(asm);
	result="STUDENT ADDED SUCCESSFUL";
	}
	else
	{
		result ="ID ALREADY PRESENT";	
	}
	 
	return result;
	}

}
