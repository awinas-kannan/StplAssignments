package com.lms.awinas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsResponse;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsResponse;
import com.stpl.gtn.gtn2o.ws.lms.StudentLoginModel;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

public class Lmsdao {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(Lmsdao.class);

	GtnUIFrameworkWebserviceResponse addstudentlms(GtnUIFrameworkWebserviceRequest request) {

		StudentLmsModel slm = new StudentLmsModel();

		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		session.save(request.getStudentLmsRequest().getStudentLmsModel());
		trx.commit();
		StudentLmsResponse resp = new StudentLmsResponse();
		resp.setStudentLmsResult("STUDENT ADDED TO LIBRARY DATABASE");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		response.setStudentLmsResponse(resp);

		// AddStudentLoginIdandpwd

		StudentLoginModel login = new StudentLoginModel();
		login.setStudentid(request.getStudentLmsRequest().getStudentLmsModel().getStuid());
		login.setStudentpwd(request.getStudentLmsRequest().getStudentLmsModel().getStuname());
		Configuration configuration1 = new Configuration().configure().addAnnotatedClass(StudentLoginModel.class);
		ServiceRegistry reg1 = new ServiceRegistryBuilder().applySettings(configuration1.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory1 = configuration1.buildSessionFactory(reg1);
		Session session1 = sessionFactory1.openSession();
		Transaction trx1 = session1.beginTransaction();

		session1.save(login);
		trx1.commit();

		return response;
	}

	GtnUIFrameworkWebserviceResponse addbooklms(GtnUIFrameworkWebserviceRequest request) {

		BookLmsModel blm = new BookLmsModel();

		Configuration configuration = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		session.save(request.getBookLmsRequest().getBookLmsModel());
		trx.commit();

		logger.info("before object");

		BookLmsResponse resp = new BookLmsResponse();
		resp.setBookResult("BOOK ADDED TO LIBRARY DB");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		response.setBookLmsResponse(resp);
		
		
		
		
		return response;
	}

	GtnUIFrameworkWebserviceResponse deleteStudentDetail(GtnUIFrameworkWebserviceRequest request) {

		int id = request.getStudentLmsRequest().getStudentLmsModel().getStuid();
StudentLmsModel slm =new StudentLmsModel();
		int count = 0;
		String deletequery = "delete from StudentLmsModel where STUDENT_ID =:id";
		String deletequery1 = "delete from StudentLoginModel where STUDENT_ID =:id";
		String displaystring;
		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		
		slm = (StudentLmsModel)session.get(StudentLmsModel.class, id);
		if(slm !=null)
		{
		if(slm.getBookid()==0)
		{
		Query delquery = session.createQuery(deletequery);
		delquery.setInteger("id", id);
		delquery.executeUpdate();

		trx.commit();

		
			displaystring = "STUDENT REMOVED";
			Configuration configuration1 = new Configuration().configure().addAnnotatedClass(StudentLoginModel.class);
			ServiceRegistry reg1 = new ServiceRegistryBuilder().applySettings(configuration1.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactory1 = configuration1.buildSessionFactory(reg1);
			Session session1 = sessionFactory1.openSession();
			Transaction trx1 = session1.beginTransaction();
			Query delquery1 = session1.createQuery(deletequery1);
			delquery1.setInteger("id", id);
			delquery1.executeUpdate();
			trx1.commit();

		}
		else
		{
			displaystring = "STUDENT OWNS A BOOK YOU CANT DELETE";
		}
		}
		else
		{
			displaystring = "ID NOT PRESENT";
		}
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		StudentLmsResponse Studentresponse = new StudentLmsResponse();

		Studentresponse.setStudentLmsResult(displaystring);
		response.setStudentLmsResponse(Studentresponse);

		
		//delete student id from db

		
		
			
		
		return response;

	}

	GtnUIFrameworkWebserviceResponse deleteBookDetail(GtnUIFrameworkWebserviceRequest request) {

		int id = request.getBookLmsRequest().getBookLmsModel().getBookid();
BookLmsModel blm =new BookLmsModel();
		logger.info("inside delete book details method");

		
		String deletequery = "delete from BookLmsModel where BOOK_ID =:id";
		String displaystring;
		Configuration configuration = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		blm =(BookLmsModel)session.get(BookLmsModel.class, id);
		if(blm!=null)
		{
		if("yes".equals(blm.getBookavailability()))
		{
		Query delquery = session.createQuery(deletequery);
		delquery.setInteger("id", id);
		delquery.executeUpdate();

		trx.commit();
			displaystring = "BOOK REMOVED";
		}
		else
		{
			displaystring = "CANT DELETE BECAUSE BOOK UN-AVAILABLE IN DATABASE";
		}
		}
		else
		{
			displaystring = "ID NOT PRESENT";
		}
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		BookLmsResponse Bookresponse = new BookLmsResponse();

		Bookresponse.setBookResult(displaystring);
		response.setBookLmsResponse(Bookresponse);

		return response;

	}

	GtnUIFrameworkWebserviceResponse printBookDetail(GtnUIFrameworkWebserviceRequest request) {

		int id = request.getBookLmsRequest().getBookLmsModel().getBookid();
		List<StudentLmsModel> list;
		BookLmsModel blm;
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		Configuration configurationn = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
		ServiceRegistry regi = new ServiceRegistryBuilder().applySettings(configurationn.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactoryy = configurationn.buildSessionFactory(regi);
		Session sessionn = sessionFactoryy.openSession();
		Transaction trxx = sessionn.beginTransaction();
		blm = (BookLmsModel) sessionn.get(BookLmsModel.class, id);
		trxx.commit();

		BookLmsResponse blr = new BookLmsResponse();
		blr.setBookLmsModel(blm);
		response.setBookLmsResponse(blr);

		if ("no".equals(blm.getBookavailability())) {
			// select select stu for book id
			String selectstuforbook = " FROM StudentLmsModel where BOOK_ID= :id";
			Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
			Session session = sessionFactory.openSession();
			Transaction trx = session.beginTransaction();

			Query bid = session.createQuery(selectstuforbook);
			bid.setInteger("id", blm.getBookid());

			list = bid.list();

			trx.commit();
			StudentLmsModel slm = new StudentLmsModel();
			slm.setStuid(list.get(0).getStuid());
			slm.setStuname(list.get(0).getStuname());

			StudentLmsResponse Studentresponse = new StudentLmsResponse();

			Studentresponse.setStudentLmsModel(slm);

			response.setStudentLmsResponse(Studentresponse);

		}

		blr.setBookResult("VALID ID");

		return response;
	}

	public GtnUIFrameworkWebserviceResponse printStudentDetail(GtnUIFrameworkWebserviceRequest request) {

		int id = request.getStudentLmsRequest().getStudentLmsModel().getStuid();
		StudentLmsModel dsm;
		BookLmsModel blm;

		// fetching student for the id
		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();

		dsm = (StudentLmsModel) session.get(StudentLmsModel.class, id);
		trx.commit();

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		StudentLmsResponse Studentresponse = new StudentLmsResponse();

		Studentresponse.setStudentLmsModel(dsm);
		Studentresponse.setStudentLmsResult("VALID ID");

		response.setStudentLmsResponse(Studentresponse);
		BookLmsResponse blr = new BookLmsResponse();

		if (dsm.getBookid() != 0) {
			// Fetching book for student
			Configuration configurationn = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
			ServiceRegistry regi = new ServiceRegistryBuilder().applySettings(configurationn.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactoryy = configurationn.buildSessionFactory(regi);
			Session sessionn = sessionFactoryy.openSession();
			Transaction trxx = sessionn.beginTransaction();
			blm = (BookLmsModel) sessionn.get(BookLmsModel.class, dsm.getBookid());
			trxx.commit();
			blr.setBookLmsModel(blm);
			response.setBookLmsResponse(blr);

		}

		return response;
	}

	public GtnUIFrameworkWebserviceResponse issueBookDetail(GtnUIFrameworkWebserviceRequest request) {
		BookLmsResponse blr = new BookLmsResponse();

		int bid = request.getStudentLmsRequest().getStudentLmsModel().getBookid();
		int sid = request.getStudentLmsRequest().getStudentLmsModel().getStuid();

		StudentLmsModel slm;
		BookLmsModel blm;

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		Configuration configuration1 = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
		ServiceRegistry reg1 = new ServiceRegistryBuilder().applySettings(configuration1.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory1 = configuration1.buildSessionFactory(reg1);
		Session session1 = sessionFactory1.openSession();

		blm = (BookLmsModel) session1.get(BookLmsModel.class, bid);

		if (blm != null) {

			Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
			Session session = sessionFactory.openSession();

			slm = (StudentLmsModel) session.get(StudentLmsModel.class, sid);

			if (slm != null) {
				logger.info(blm.getBookavailability());

				if ("yes".equals(blm.getBookavailability())) {

					if ("no".equals(slm.getBookreceived())) {
						blm.setBookavailability("no");
						logger.info("befor trxx");

						Transaction trx1 = session1.beginTransaction();
						session1.update(blm);
						trx1.commit();

						slm.setBookid(bid);
						slm.setBookreceived("yes");
						logger.info("befor trx");
						Transaction trx = session.beginTransaction();
						session.update(slm);
						trx.commit();
						blr.setBookResult("BOOK ISSUED TO  ::" + slm.getStuname());
					} else {
						blr.setBookResult("STUDENT ALREADY HAS BOOK");
					}
				} else {
					blr.setBookResult("NO STOCK");
				}

			} else {
				blr.setBookResult("INVALID STUDENT ID");
			}

		} else {
			blr.setBookResult("INVALID BOOK ID");
		}

		response.setBookLmsResponse(blr);

		return response;
	}

	GtnUIFrameworkWebserviceResponse receiveBookDetail(GtnUIFrameworkWebserviceRequest request) {
		BookLmsResponse blr = new BookLmsResponse();

		int bid = request.getStudentLmsRequest().getStudentLmsModel().getBookid();
		int sid = request.getStudentLmsRequest().getStudentLmsModel().getStuid();
		StudentLmsModel slm;
		BookLmsModel blm;
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Configuration configuration = new Configuration().configure().addAnnotatedClass(StudentLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();

		slm = (StudentLmsModel) session.get(StudentLmsModel.class, sid);

		
		
		if (slm != null) {

			Configuration configurationn = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
			ServiceRegistry regi = new ServiceRegistryBuilder().applySettings(configurationn.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactoryy = configurationn.buildSessionFactory(regi);
			Session sessionn = sessionFactoryy.openSession();

			blm = (BookLmsModel) sessionn.get(BookLmsModel.class, slm.getBookid());
	
			if (blm != null) {
				if ("no".equals(blm.getBookavailability())) {
					blm.setBookavailability("yes");

					Transaction trxx = sessionn.beginTransaction();
					sessionn.update(blm);
					trxx.commit();

					slm.setBookid(0);
					slm.setBookreceived("no");
					Transaction trx = session.beginTransaction();
					session.update(slm);
					trx.commit();

					blr.setBookResult("BOOK RECEIVED FROM  :" + slm.getStuname());

				} else {
					blr.setBookResult("BOOK ALREADY AVAILABLE");
				}

			} else {
				blr.setBookResult("INVALID BOOK ID");
			}

		} else {
			blr.setBookResult("INVALID STUDENT ID");
		}

		response.setBookLmsResponse(blr);

		return response;
	}

	GtnUIFrameworkWebserviceResponse viewallbooks() {

		List<BookLmsModel> rsm;

		String selectstring = "FROM BookLmsModel";

		Configuration configuration = new Configuration().configure().addAnnotatedClass(BookLmsModel.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(reg);
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		Query selecttable = session.createQuery(selectstring);

		rsm = (List<BookLmsModel>) selecttable.list();

		trx.commit();

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		BookLmsResponse blr = new BookLmsResponse();
		blr.setBooklist(rsm);

		response.setBookLmsResponse(blr);
		
		
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
	/*	GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria ss = session.createCriteria(StBean.class);
		
		
		
		List<StBean> list= ss.list();
		List<GtnStBean> list1=new ArrayList<>();
		StBean sb=new StBean();
		sb.setList(list);
		session.close();
		logger.info("before for"+list.get(0).getId());
		
		if (request.getGtnWsSearchRequest().isCount()) {
			GtnSerachResponse gtnSerachResponse=new GtnSerachResponse();
			gtnSerachResponse.setCount(list.size());
			response.setGtnSerachResponse(gtnSerachResponse);
			generalResponse.setSucess(true);
			response.setGtnWsGeneralResponse(generalResponse);
			return response;
		}*/
		
		/*for(StBean a: list)
		{			
			GtnStBean gtn = new GtnStBean();
			gtn.setId(a.getId());
			gtn.setName(a.getName());
			gtn.setMark(a.getMark());		
			list1.add(gtn);
		}*/
		
		List<Object[]> lob=new ArrayList<>();
		for(BookLmsModel a: rsm){		
                        Object[] array=new Object[]{a.getBookid(),a.getBookname(),a.getBookdept(),a.getBookavailability()};
                        lob.add(array);
		}
		GtnSerachResponse gtnSerachResponse=new GtnSerachResponse();
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData(lob);
		gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
		response.setGtnSerachResponse(gtnSerachResponse);
		generalResponse.setSucess(true);
		response.setGtnWsGeneralResponse(generalResponse);
		
		return response;

	}

}
