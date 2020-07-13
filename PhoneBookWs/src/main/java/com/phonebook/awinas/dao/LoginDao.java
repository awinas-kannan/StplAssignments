package com.phonebook.awinas.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.awinas.bi.AbstractCommon;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookResponse;
import com.stpl.gtn.gtn2o.ws.phonebook.UserDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Service
public class LoginDao extends AbstractCommon {
	
	@Autowired
	public   SessionFactory sessionFactory;

	private static final GtnWSLogger log = GtnWSLogger.getGTNLogger(LoginDao.class);
	public static final String SUCCESS = "SUCCESSFUL";
	public static final String INVALIDPD = "INVALID PASSWORD";
	public static final String INVALIDID = "INVALID ID";
	public static final String IDPRESENT = "ID ALREADY PRESENT";

	public LoginDao() {
		/// CONTROLLER CODE
	}

	public GtnUIFrameworkWebserviceResponse getAdminCredentialResult(GtnUIFrameworkWebserviceRequest request) {

		log.info("inside login get credit");
		UserDetails alm;
		String loginresult;

		Session session = sessionFactory.openSession();

		alm = session.get(UserDetails.class, request.getPhonebookrequest().getUserdetailsrequest().getId());
		if (alm != null) {
			if (alm.getName().equals(request.getPhonebookrequest().getUserdetailsrequest().getName())) {
				loginresult = SUCCESS;

			}
			else {
				loginresult = INVALIDPD;
			}

		} 
		else

		{
			loginresult = INVALIDID;
		}
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		PhoneBookResponse phonebookresponse = new PhoneBookResponse();
		phonebookresponse.setResultresponse(loginresult);
		response.setPhonebookresponse(phonebookresponse);

		return response;
	}

	@Override
	public GtnUIFrameworkWebserviceResponse add(GtnUIFrameworkWebserviceRequest request) {

		log.info("inside login add");

		String loginresult;

		try  {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(request.getPhonebookrequest().getUserdetailsrequest());
			session.getTransaction().commit();
			loginresult = "USER ADDED";
			session.close();
		} 
		catch (ConstraintViolationException e) {
			log.error("exp is" + e);
			loginresult = IDPRESENT;
		}

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		PhoneBookResponse phonebookresponse = new PhoneBookResponse();
		phonebookresponse.setResultresponse(loginresult);
		response.setPhonebookresponse(phonebookresponse);

		return response;
	}



}
