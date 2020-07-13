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

import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsResponse;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class AddStudentLmsController {

	
	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(AddStudentLmsController.class);
	@RequestMapping(value=LmsUrlConstants.GTN_WS_LMS_ADDSTUDENTLMS_SERVICE,method= RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse addstulms(@RequestBody GtnUIFrameworkWebserviceRequest request)
	{
		logger.info("in add stu controller lms");
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Lmsdao dao=new Lmsdao();
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		
		logger.info("before object");
		Object obj =  session.get(StudentLmsModel.class, request.getStudentLmsRequest().getStudentLmsModel().getStuid());
		
		logger.info("before trx commit");
		trx.commit();
		

		if(obj == null)
		{
			logger.info("inside if");
		response = dao.addstudentlms(request);
		}
		else
		{
			
			logger.info("inside else");
			StudentLmsResponse slr =new StudentLmsResponse();
			slr.setStudentLmsResult("ID ALREADY PRESENT");
			response.setStudentLmsResponse(slr);
		}
		
		
		return response;
		
	}
	
}
