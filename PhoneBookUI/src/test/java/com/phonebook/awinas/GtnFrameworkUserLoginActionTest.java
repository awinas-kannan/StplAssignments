package com.phonebook.awinas;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import com.phonebook.awinas.action.GtnFrameworkUserLoginAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookResponse;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class,  GtnUIFrameWorkAction.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class,Notification.class })

public class GtnFrameworkUserLoginActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkUserLoginAction instance = new GtnFrameworkUserLoginAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	
	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {

		String componentId = "";
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUserLoginAction in = new GtnFrameworkUserLoginAction();
		
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class,Notification.class);

		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		
		
		doReturn(9).when(object).getIntegerFromField();
		doReturn("awinas").when(object).getStringFromField();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		
		
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		
		GtnFrameworkUserLoginAction ins = Mockito.spy(in);
		
	
		String s="SUCCESSFUL";
		PhoneBookResponse pbr = new PhoneBookResponse();
		pbr.setResultresponse(s);
		response.setPhonebookresponse(pbr);
		
		doReturn(response).when(ins).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		String s1="SUCCESSFU";
		PhoneBookResponse pbr1 = new PhoneBookResponse();
		pbr.setResultresponse(s1);
		response.setPhonebookresponse(pbr1);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn(9).when(object).getIntegerFromField();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn("").when(object).getStringFromField();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}



	  @Test
		public void testGetResponse() throws Exception {
		  GtnFrameworkUserLoginAction fixture = new GtnFrameworkUserLoginAction();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
			fixture.getResponse(request);
		}
	  
	  
	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnFrameworkUserLoginAction instance = new GtnFrameworkUserLoginAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
	}

}
