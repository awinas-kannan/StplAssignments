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

import com.stpl.gtn.gtn2o.ws.lms.BookLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsResponse;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsResponse;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class AddBookLmsController {

	
	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(AddBookLmsController.class);
	
	@RequestMapping(value=LmsUrlConstants.GTN_WS_LMS_ADDBOOKLMS_SERVICE,method= RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse addbooklms(@RequestBody GtnUIFrameworkWebserviceRequest request)
	{
logger.info("in add book controller lms");
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Lmsdao dao=new Lmsdao();
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		
		logger.info("before object");
		Object obj =  session.get(BookLmsModel.class, request.getBookLmsRequest().getBookLmsModel().getBookid());
		
		logger.info("before trx commit");
		trx.commit();
		

		if(obj == null)
		{
			logger.info("inside if");
		response = dao.addbooklms(request);
		}
		else
		{
			
			logger.info("inside else");
			BookLmsResponse blr =new BookLmsResponse();
			blr.setBookResult("ID ALREADY PRESENT");
			response.setBookLmsResponse(blr);
		}
		
		
		return response;
		
	}
	
}
