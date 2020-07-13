package com.lms.awinas.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.stpl.gtn.gtn2o.ws.lms.AdminLoginModel;
import com.stpl.gtn.gtn2o.ws.lms.LoginsResponse;
import com.stpl.gtn.gtn2o.ws.lms.StudentLoginModel;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class Loginsdao {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(Loginsdao.class);

	GtnUIFrameworkWebserviceResponse getAdminCredentialResult(GtnUIFrameworkWebserviceRequest request) {
		logger.info("inside getting credentials");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		AdminLoginModel alm = new AdminLoginModel();
		String loginresult;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(AdminLoginModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		alm = (AdminLoginModel) session.get(AdminLoginModel.class,
				request.getAdminLoginRequest().getAdminLoginModel().getAdminid());

		if (alm.getAdminpwd().equals(request.getAdminLoginRequest().getAdminLoginModel().getAdminpwd())) {
			loginresult = "SUCCESSFUL";
			
			
		} else {
			loginresult = "INVALID PASSWORD";
		}
logger.info(loginresult);
		LoginsResponse resp = new LoginsResponse();
		resp.setLoginResult(loginresult);

		response.setLoginsResponse(resp);

		return response;
	}

	GtnUIFrameworkWebserviceResponse getStudentCredentialResult(GtnUIFrameworkWebserviceRequest request) {

		logger.info("inside stu getting credentials");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		StudentLoginModel slm = new StudentLoginModel();
		String loginresult;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLoginModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		slm = (StudentLoginModel) session.get(StudentLoginModel.class,
				request.getStudentLoginRequest().getStudentLoginModel().getStudentid());

		if(slm!=null)
		{
		if (slm.getStudentpwd().equals(request.getStudentLoginRequest().getStudentLoginModel().getStudentpwd())) {
			loginresult = "SUCCESSFUL";
		} else {
			loginresult = "INVALID PASSWORD";
		}
		}
		else
		{
			loginresult = "INVALID ID";
		}

		LoginsResponse resp = new LoginsResponse();
		resp.setLoginResult(loginresult);

		response.setLoginsResponse(resp);

		return response;
	}

}
