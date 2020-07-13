package com.phonebook.awinas.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.awinas.bi.AbstractCommon;
import com.phonebook.awinas.bi.Iunique;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookResponse;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

 

@Service
public class PhoneBookDao extends AbstractCommon implements Iunique {

	@Autowired
	private SessionFactory sessionFactory;
	private String userId="userid";
	
	private static final GtnWSLogger log = GtnWSLogger.getGTNLogger(LoginDao.class);

	public PhoneBookDao() {
		// CONTROLLER CODE
	}
	
	
	
	
	public GtnUIFrameworkWebserviceResponse add(GtnUIFrameworkWebserviceRequest request) {

		log.info("inside pb add");

		String result;

		
			
			Session session = sessionFactory.openSession();
			
			
			List<UserContactDetails> ucd = checkDuplication(request, session);
			
			if(ucd.isEmpty())
			{
				session.beginTransaction();
				session.save(request.getPhonebookrequest().getContactdetailsrequest());
				session.getTransaction().commit();
				
				result = cAdded;
			}
			else
			{
				result = cExist;
			}
		
			session.close();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		PhoneBookResponse phonebookresponse = new PhoneBookResponse();
		phonebookresponse.setResultresponse(result);
		response.setPhonebookresponse(phonebookresponse);

		return response;
	}




	public List<UserContactDetails> checkDuplication(GtnUIFrameworkWebserviceRequest request, Session session) {
		Criterion cuid = Restrictions.eq(userId, request.getPhonebookrequest().getContactdetailsrequest().getUserid());
		Criterion cname = Restrictions.eq("cname", request.getPhonebookrequest().getContactdetailsrequest().getCname());
		Criterion cphno= Restrictions.eq("cphno", request.getPhonebookrequest().getContactdetailsrequest().getCphno());
		Conjunction land = Restrictions.and(cuid,cname,cphno);
		return session.createCriteria(UserContactDetails.class).add(land).list();
		
	}

	
	@Override
	public GtnUIFrameworkWebserviceResponse delete(GtnUIFrameworkWebserviceRequest request) {
		String result;

		log.info("inside pb del");
		int uid = request.getPhonebookrequest().getContactdetailsrequest().getUserid();
		String drop = request.getPhonebookrequest().getStringd();
		String val = request.getPhonebookrequest().getStringv();
		log.info(val);
		log.info(drop);

	
		Session session = sessionFactory.openSession();

		session.beginTransaction();


			Criterion cuid = Restrictions.eq(userId, uid);
			Criterion cdrop= Restrictions.eq(drop, val);
			LogicalExpression land = Restrictions.and(cuid,cdrop);
			
		List<UserContactDetails> ucd = session.createCriteria(UserContactDetails.class).add(land).list();
		
		
		if(ucd.isEmpty())
		{
			result = cInvalid;
		
		}
		else
		{
			int  count =0;
			for(UserContactDetails lucd:ucd)
			{
			session.delete(lucd);
			count++;
			}
			session.getTransaction().commit();

			
			result = count +  " contact deleted";
			
		}
		session.close();
		
		

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		PhoneBookResponse pbr = new PhoneBookResponse();
		pbr.setResultresponse(result);
		response.setPhonebookresponse(pbr);
		return response;
	}



	@Override
	public GtnUIFrameworkWebserviceResponse view(GtnUIFrameworkWebserviceRequest request) {

		String selectstring = "FROM UserContactDetails where user_id=:uid";
		List<UserContactDetails> rsm;

		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query selecttable = session.createQuery(selectstring);
		selecttable.setInteger("uid", request.getPhonebookrequest().getContactdetailsrequest().getUserid());

		
		rsm = selecttable.list();

		session.close();


		List<Object[]> lob = new ArrayList<>();
		for (UserContactDetails a : rsm) {
			Object[] array = new Object[] { a.getCid(), a.getUserid(), a.getCname(), a.getCphno(), a.getMail() };
			lob.add(array);
		}
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData(lob);
		
		gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
		response.setGtnSerachResponse(gtnSerachResponse);

		return response;

		
	}

	@Override
	public GtnUIFrameworkWebserviceResponse edit(GtnUIFrameworkWebserviceRequest request) {

		log.info("inside pb edit");
		List<UserContactDetails> list;
		
		



		int uid = request.getPhonebookrequest().getContactdetailsrequest().getUserid();
		String drop = request.getPhonebookrequest().getStringd();
		String val = request.getPhonebookrequest().getStringv();
		log.info(val);
		log.info(drop);

	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria cr = session.createCriteria(UserContactDetails.class);
		Criterion cuid = Restrictions.eq(userId, uid);
		Criterion cdrop= Restrictions.eq(drop, val);
		LogicalExpression land = Restrictions.and(cuid,cdrop);
		
		cr.add(land);
		list = cr.list();
	
		session.getTransaction().commit();
		session.close();
		log.info("size is " + list.size());

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		PhoneBookResponse pbr = new PhoneBookResponse();
		try {
			UserContactDetails ucd = new UserContactDetails();
			ucd.setCname(list.get(0).getCname());
			ucd.setCphno(list.get(0).getCphno());
			ucd.setMail(list.get(0).getMail());
			ucd.setCid(list.get(0).getCid());
			pbr.setContactdetailsresponse(ucd);
			pbr.setResultresponse(cValid);
		}
		catch (IndexOutOfBoundsException e) {
			log.error("the exp is " + e);
			pbr.setResultresponse(cInvalid);
		}
		response.setPhonebookresponse(pbr);
		return response;

	}

	@Override
	public GtnUIFrameworkWebserviceResponse update(GtnUIFrameworkWebserviceRequest request) {

		String result;
		Session session = sessionFactory.openSession();
		
		List<UserContactDetails> ucd = checkDuplication(request, session);
		
		if(ucd.isEmpty())
		{
		session.beginTransaction();
		session.update(request.getPhonebookrequest().getContactdetailsrequest());
		session.getTransaction().commit();
		session.close();
		result = cUpdate;
		}
	
		else
		{
			result = "NO UPDATETION MADE";
		}
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		PhoneBookResponse pbr = new PhoneBookResponse();
		pbr.setResultresponse(result);

		response.setPhonebookresponse(pbr);
		return response;
	}

}
