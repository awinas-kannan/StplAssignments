package com.phonebook.awinas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.phonebook.awinas.action.GtnFrameworkDeleteContactAction;
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
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class,
		GtnUIFrameworkBaseComponent.class,Notification.class })


public class GtnFrameworkDeleteContactActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkDeleteContactAction instance = new GtnFrameworkDeleteContactAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);	}


	//	@Ignore
		@Test
		public void testDoAction() throws GtnFrameworkGeneralException {

			String componentId = "";
			
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
			GtnFrameworkDeleteContactAction in = new GtnFrameworkDeleteContactAction();
			
			
			PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,
					 Notification.class);

			GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

			
			doReturn("string").when(object).getStringFromField();
			doReturn(0).when(object).getIntegerFromField();
			
			doReturn("9898989898").when(object).getValueFromComponent();
			when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

			
			
			
			GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
			
			
			
			GtnFrameworkDeleteContactAction ins = Mockito.spy(in);
			
			doReturn(response).when(ins).getReponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
			
			String s="notification";
			PhoneBookResponse pbr = new PhoneBookResponse();
			pbr.setResultresponse(s);
			response.setPhonebookresponse(pbr);
			
			ins.doAction(componentId, gtnUIFrameWorkActionConfig);
			

			doReturn(null).when(object).getValueFromComponent();
			ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		}



		 
		  


	 @Test
		public void testGetResponse() throws Exception {
		  GtnFrameworkDeleteContactAction fixture = new GtnFrameworkDeleteContactAction();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
			fixture.getReponse(request);
		}
	
	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnFrameworkDeleteContactAction instance = new GtnFrameworkDeleteContactAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
	}

}
