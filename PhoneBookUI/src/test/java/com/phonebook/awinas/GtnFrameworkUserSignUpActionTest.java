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


import com.phonebook.awinas.action.GtnFrameworkUserSignUpAction;
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
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class,
		GtnUIFrameworkActionExecutor.class ,Notification.class})

public class GtnFrameworkUserSignUpActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		System.out.println("configureParams");
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
		GtnFrameworkUserSignUpAction instance = new GtnFrameworkUserSignUpAction();
		instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	
	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {
		String componentId = "";

		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUserSignUpAction in = new GtnFrameworkUserSignUpAction();

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, Notification.class, Notification.class);

		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		doReturn("awinas").when(object).getStringFromField();
		doReturn(1).when(object).getIntegerFromField();
	
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		GtnFrameworkUserSignUpAction ins = Mockito.spy(in);

		String s = "val";
		PhoneBookResponse pbr = new PhoneBookResponse();
		pbr.setResultresponse(s);
		response.setPhonebookresponse(pbr);

		doReturn(response).when(ins).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));


		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn("").when(object).getStringFromField();
		doReturn(0).when(object).getIntegerFromField();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn("").when(object).getStringFromField();
		doReturn(11).when(object).getIntegerFromField();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		doReturn("ak").when(object).getStringFromField();
		doReturn(0).when(object).getIntegerFromField();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}

	@Test
	public void testGetResponse() throws Exception {
	  GtnFrameworkUserSignUpAction fixture = new GtnFrameworkUserSignUpAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getResponse(request);
	}
	
	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnFrameworkUserSignUpAction instance = new GtnFrameworkUserSignUpAction();
		GtnUIFrameWorkAction result = instance.createInstance();
		assertEquals(instance, result);
	}

}
