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
import com.phonebook.awinas.action.GtnFrameworkUpdateContactAction;
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
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class ,Notification.class})

public class GtnFrameworkUpdateContactActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkUpdateContactAction instance = new GtnFrameworkUpdateContactAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	//@Ignore
	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {

		String componentId = "";
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUpdateContactAction in = new GtnFrameworkUpdateContactAction();
		
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class,Notification.class,Notification.class);

		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		
		doReturn("9898989898").when(object).getStringFromField();
		
		doReturn(0).when(object).getIntegerFromField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		
		
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		
		GtnFrameworkUpdateContactAction ins = Mockito.spy(in);
		
	
		String s="val";
		PhoneBookResponse pbr = new PhoneBookResponse();
		pbr.setResultresponse(s);
		response.setPhonebookresponse(pbr);
		
		doReturn(response).when(ins).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn("").when(object).getStringFromField();
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn("awinas@gmail.com").when(object).getStringFromField();
		in.doAction(componentId, gtnUIFrameWorkActionConfig);

	}



	  @Test
		public void testGetResponse() throws Exception {
		  GtnFrameworkUpdateContactAction fixture = new GtnFrameworkUpdateContactAction();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
			fixture.getResponse(request);
		}
	  
	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnFrameworkUpdateContactAction instance = new GtnFrameworkUpdateContactAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
	}

}
