package com.phonebook.awinas;



import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.phonebook.awinas.controllers.AddContactController;
import com.phonebook.awinas.controllers.DeleteController;
import com.phonebook.awinas.controllers.EditContactController;
import com.phonebook.awinas.controllers.SignUpController;
import com.phonebook.awinas.controllers.UpdateContactController;
import com.phonebook.awinas.controllers.ViewContactController;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookRequest;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.phonebook.UserDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/PhoneBookWs-test.xml" })
public class UserContactDetailsTest {
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int NINE = 9;
	public static final String MAIL = "awinas@gmail.com";
	public static final String NAME = "name";
	public static final String NAME1 = "name1";
	public static final String CNAME = "cname";
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDetails ud;
	

	@Autowired
	private SignUpController suc;

	@Autowired
	private AddContactController acc;

	@Autowired
	private DeleteController dcc;

	@Autowired
	private EditContactController ecc;

	@Autowired
	private UpdateContactController ucc;

	@Autowired
	private ViewContactController vcc;

	
	@Autowired
	private UserContactDetails ucd;
	
	
	public UserContactDetailsTest() {
		// TESTING CONTROLLER
	}
	
	
	@Before
	public void testSignBefore()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		ud.setId(THREE);
		ud.setName("test");
		
		pbr.setUserdetailsrequest(ud);
		request.setPhonebookrequest(pbr);
		suc.adminlogin(request);
		
		//ucd.setCid(1);
		ucd.setCname(NAME);
		ucd.setCphno("9876543210");
		ucd.setUserid(THREE);
		ucd.setMail(MAIL);
		
		pbr.setContactdetailsrequest(ucd);
		request.setPhonebookrequest(pbr);
		acc.addContact(request);
		
	}

	/*@Test
	public void testAddContactPass()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		//ucd.setCid(NINE);
		ucd.setCname(NAME);
		ucd.setCphno("9876543211");
		ucd.setUserid(THREE);
		ucd.setMail(MAIL);
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setContactdetailsrequest(ucd);
		request.setPhonebookrequest(pbr);
		GtnUIFrameworkWebserviceResponse response=acc.addContact(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("CONTACT ADDED SUCCESSFUL",val);
	}*/

	@Test
	public void testAddContactFail()
	{
		

		ucd.setUserid(THREE);
		ucd.setMail(MAIL);
		
		ucd.setCname(NAME);
		ucd.setCphno("9876543210");
		
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setContactdetailsrequest(ucd);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setPhonebookrequest(pbr);
		GtnUIFrameworkWebserviceResponse response=acc.addContact(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("CONTACT ALREADY PRESENT",val);
	}

	@Test
	public void testDeleteContactFail()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		ucd.setUserid(THREE);
		pbr.setContactdetailsrequest(ucd);
		pbr.setStringd(CNAME);
		pbr.setStringv("awinaskannanmr");
		request.setPhonebookrequest(pbr);
		
		GtnUIFrameworkWebserviceResponse response=dcc.delcon(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("INVALID VALUE",val);
	}
	
	@Test
	public void testEditContactPass()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		ucd.setUserid(THREE);
		pbr.setContactdetailsrequest(ucd);
		pbr.setStringd(CNAME);
		pbr.setStringv(NAME);
		request.setPhonebookrequest(pbr);
		
		GtnUIFrameworkWebserviceResponse response=ecc.editCon(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("VALID VALUE",val);
	}
	
	@Test
	public void testEditContactFail()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		ucd.setUserid(THREE);
		pbr.setContactdetailsrequest(ucd);
		pbr.setStringd(CNAME);
		pbr.setStringv("awinaskannanmr");
		request.setPhonebookrequest(pbr);
		
		GtnUIFrameworkWebserviceResponse response=ecc.editCon(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("INVALID VALUE",val);
	}

	
	@Test
	public void testUpdateContactPass()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

	//	ucd.setCid(1);
		ucd.setCname(NAME1);
		ucd.setCphno("9876543210");
		ucd.setUserid(THREE);
		ucd.setMail(MAIL);
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setContactdetailsrequest(ucd);
		request.setPhonebookrequest(pbr);
		GtnUIFrameworkWebserviceResponse response=ucc.updateCon(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("CONTACT UPDATE SUCCESSFUL",val);
	}
	
	@Test
	public void testUpdateContactFail()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

	//	ucd.setCid(1);
		ucd.setCname(NAME);
		ucd.setCphno("9876543210");
		ucd.setUserid(THREE);
		ucd.setMail(MAIL);
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setContactdetailsrequest(ucd);
		request.setPhonebookrequest(pbr);
		GtnUIFrameworkWebserviceResponse response=ucc.updateCon(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("NO UPDATETION MADE",val);
	}
	
	@Test
	public void testViewPass()  {
	
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		ucd.setUserid(THREE);
		pbr.setContactdetailsrequest(ucd);
		request.setPhonebookrequest(pbr);

		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setCount(false);
		request.setGtnWsSearchRequest(gtnWsSearchRequest);
		vcc.viewcon(request);
	}
	
	
	
	@After
	public void testSignAfter()  {
			
			
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			PhoneBookRequest pbr = new PhoneBookRequest();
			ucd.setUserid(THREE);
			pbr.setContactdetailsrequest(ucd);
			pbr.setStringd(CNAME);
			pbr.setStringv(NAME);
			request.setPhonebookrequest(pbr);
			
			dcc.delcon(request);
			pbr.setStringv(NAME1);
			request.setPhonebookrequest(pbr);
			dcc.delcon(request);
			Session session = sessionFactory.openSession();

			
			ud.setId(THREE);
			ud.setName("test");
			session.beginTransaction();
			session.delete(ud);
			session.getTransaction().commit();
		
	}

	
	
}
