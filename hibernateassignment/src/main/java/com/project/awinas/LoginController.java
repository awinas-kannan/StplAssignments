package com.project.awinas;




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
public class LoginController {

	@RequestMapping("/Login")
	public ModelAndView login(HttpServletRequest req,HttpServletResponse resp) 
	{
			
		LoginModel lm =new LoginModel();
		
		lm.setUserid(Integer.parseInt(req.getParameter("userid")));
		lm.setPwd(req.getParameter("pwd"));	
	
		LoginDao dao =new LoginDao();
		ModelAndView lmv=new ModelAndView();
		
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
		if("SUCCESSFUL".equals(loginresult))
		{
		lmv.setViewName("studentaccess.jsp");
		}
		else
		{
		lmv.setViewName("index.jsp");
		}
		}
		else
		{
			
			lmv.setViewName("index.jsp");
		}
		return lmv;	
	}
}
