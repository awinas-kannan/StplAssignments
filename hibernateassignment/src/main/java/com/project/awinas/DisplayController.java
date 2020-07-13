package com.project.awinas;

import java.io.IOException;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayController {

	
	@RequestMapping("/display")
	public ModelAndView display(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		
			int id= Integer.parseInt(request.getParameter("dispid"));
			
			StudentDao dao =new StudentDao();
			
			ModelAndView emv=new ModelAndView();


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
			StudentModel displayresult = dao.getStudentDetail(id);
			
			emv.setViewName("displayid.jsp");
			
			emv.addObject("result", displayresult);
			}
			else
			{
				String re="INVALID ID";
				emv.setViewName("resultdisplay.jsp");
				emv.addObject("result", re);
			}
			return emv;
			
}
}