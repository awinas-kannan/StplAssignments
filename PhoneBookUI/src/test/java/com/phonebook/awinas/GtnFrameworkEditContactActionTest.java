package com.phonebook.awinas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.phonebook.awinas.action.GtnFrameworkAddContactAction;
import com.phonebook.awinas.action.GtnFrameworkEditContactAction;
import com.phonebook.awinas.action.GtnFrameworkUpdateContactAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookResponse;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class,  GtnUIFrameWorkAction.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class ,Notification.class})

public class GtnFrameworkEditContactActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEditContactAction instance = new GtnFrameworkEditContactAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {

		String componentId = "";
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkEditContactAction in = new GtnFrameworkEditContactAction();
		
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class,Notification.class,Notification.class);

		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		
		doReturn("awinas").when(object).getStringFromField();
		doReturn(0).when(object).getIntegerFromField();
		doReturn("name").when(object).getValueFromComponent();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		
		
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		
		GtnFrameworkEditContactAction ins = Mockito.spy(in);
		
	
		String s="VALID VALUE";
		PhoneBookResponse pbr = new PhoneBookResponse();
		pbr.setResultresponse(s);
		
		UserContactDetails ucd = new UserContactDetails();
		ucd.setCid(2);
		ucd.setCname("aa");
		ucd.setCphno("000");
		ucd.setMail("mail");
		ucd.setUserid(22);

		pbr.setContactdetailsresponse(ucd);
		response.setPhonebookresponse(pbr);
		doReturn(response).when(ins).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		
		String s1="VALID VAL";
		PhoneBookResponse pbr1 = new PhoneBookResponse();
		pbr1.setResultresponse(s1);
		response.setPhonebookresponse(pbr1);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn(null).when(object).getValueFromComponent();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}



	  @Test
		public void testGetResponse() throws Exception {
		  GtnFrameworkEditContactAction fixture = new GtnFrameworkEditContactAction();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
			fixture.getResponse(request);
		}

	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnFrameworkEditContactAction instance = new GtnFrameworkEditContactAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
	}

}
