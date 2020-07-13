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
public class AddController {

	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) throws IOException  
	{
	StudentDao dao =new StudentDao();

	StudentModel asm=new StudentModel();
	asm.setId(Integer.parseInt(request.getParameter("stuid")));
	asm.setName(request.getParameter("stuname"));
	asm.setMark1(Integer.parseInt(request.getParameter("stumark1")));
	asm.setMark2(Integer.parseInt(request.getParameter("stumark2")));
	asm.setMark3(Integer.parseInt(request.getParameter("stumark3")));
	asm.setTotal(asm.getMark1()+asm.getMark2()+asm.getMark3());
	ModelAndView amv=new ModelAndView();
	String result ;
	
	Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentModel.class);
	ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
			.buildServiceRegistry();
	SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();

	Object obj =  session.get(StudentModel.class, asm.getId());
	
	trx.commit();
	amv.setViewName("resultdisplay.jsp");
	if(obj==null)
	{
	
	dao.addStudentDetail(asm);
	result ="STUDENT ADDED SUCCESSFUL";
	}
	else
	{
		result ="ID ALREADY PRESENT";	
	}
	 amv.addObject("result", result);
	return amv;
	}

}
