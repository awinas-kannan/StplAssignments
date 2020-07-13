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

import com.phonebook.awinas.controllers.DeleteController;
import com.phonebook.awinas.controllers.LoginController;
import com.phonebook.awinas.controllers.SignUpController;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookRequest;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.phonebook.UserDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/PhoneBookWs-test.xml" })
public class UserDetailsTest {

	public static final int ONE = 1;
	public static final int TWO = 2;
	
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final String MAIL = "awinas@gmail.com";
	public static final String NAME = "name";
	public static final String CNAME = "cname";

	@Autowired
	private SignUpController suc;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LoginController lc;
	
	@Autowired
	private DeleteController dcc;
	
	@Autowired
	private UserDetails ud;
	
	@Autowired
	private UserContactDetails ucd;

	public UserDetailsTest() {
	// TESTING CONTROLER
}
	
	
	
	@Before
	public void testSignBefore()
	{
		

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		ucd.setUserid(THREE);
		pbr.setContactdetailsrequest(ucd);
		pbr.setStringd(CNAME);
		pbr.setStringv(NAME);
		request.setPhonebookrequest(pbr);
		
		dcc.delcon(request);
		
		
		ud.setId(ONE);
		ud.setName("test");
		
		pbr.setUserdetailsrequest(ud);
		request.setPhonebookrequest(pbr);

		suc.adminlogin(request);
		
		
	}


	@Test
	public void testSignServletPass() 
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		ud.setId(TWO);
		ud.setName("test");
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setUserdetailsrequest(ud);
		request.setPhonebookrequest(pbr);

		GtnUIFrameworkWebserviceResponse response = suc.adminlogin(request);
		String val = response.getPhonebookresponse().getResultresponse();
		assertEquals("USER ADDED",val);
		
	}
	@Test
	public void testSignServletFail() {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		ud.setId(ONE);
		ud.setName("test");
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setUserdetailsrequest(ud);
		request.setPhonebookrequest(pbr);

		GtnUIFrameworkWebserviceResponse response = suc.adminlogin(request);
		String val = response.getPhonebookresponse().getResultresponse();
		assertEquals("ID ALREADY PRESENT",val);
		

	}

	@Test
	public void loginpass() {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		ud.setId(ONE);
		ud.setName("test");
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setUserdetailsrequest(ud);
		request.setPhonebookrequest(pbr);

		GtnUIFrameworkWebserviceResponse response = lc.adminlogin(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("SUCCESSFUL",val);
	}

	@Test
	public void loginFailPwd()  {

		

		PhoneBookRequest pbr = new PhoneBookRequest();
		ud.setId(ONE);
		ud.setName("tet");
		
		pbr.setUserdetailsrequest(ud);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setPhonebookrequest(pbr);

		GtnUIFrameworkWebserviceResponse response = lc.adminlogin(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("INVALID PASSWORD",val);
	}

	@Test
	public void loginFailId()  {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		ud.setId(FOUR);
		ud.setName("tet");
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setUserdetailsrequest(ud);
		request.setPhonebookrequest(pbr);

		GtnUIFrameworkWebserviceResponse response = lc.adminlogin(request);
		String val = response.getPhonebookresponse().getResultresponse();
		
		assertEquals("INVALID ID",val);
	}

	@After
	public void testSignAfter()  {

		Session session = sessionFactory.openSession();
		
		ud.setId(ONE);
		ud.setName("test");
		session.beginTransaction();
		session.delete(ud);
		session.getTransaction().commit();
		
		ud.setId(TWO);
		ud.setName("test");
		session.beginTransaction();
		session.delete(ud);
		session.getTransaction().commit();
		
		session.close();
			
	}
}
