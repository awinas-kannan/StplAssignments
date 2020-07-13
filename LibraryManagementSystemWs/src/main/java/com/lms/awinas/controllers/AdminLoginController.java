package com.lms.awinas.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.stpl.gtn.gtn2o.ws.lms.AdminLoginModel;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.LoginsResponse;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class AdminLoginController {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(AdminLoginController.class);
	
	
	@RequestMapping(value = LmsUrlConstants.GTN_WS_LMS_ADMINLOGIN_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse adminlogin(@RequestBody GtnUIFrameworkWebserviceRequest request)
	{
		
		logger.info("admin login controller called");
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Loginsdao dao=new Loginsdao();
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(AdminLoginModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		
		logger.info("before object");
		Object obj =  session.get(AdminLoginModel.class, request.getAdminLoginRequest().getAdminLoginModel().getAdminid());
		
		logger.info("before trx commit");
		trx.commit();
		

		if(obj != null)
		{
			logger.info("inside if");
		response = dao.getAdminCredentialResult(request);
		}
		else
		{
			
			logger.info("inside else");
			LoginsResponse resp=new LoginsResponse();
			resp.setLoginResult("INVALID ID");
			
			response.setLoginsResponse(resp);
		}
		
		
		
		return response;
		
	}

}
