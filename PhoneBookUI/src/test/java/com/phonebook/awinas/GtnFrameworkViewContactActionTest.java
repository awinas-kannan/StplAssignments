package com.phonebook.awinas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.phonebook.awinas.action.GtnFrameworkAddContactAction;
import com.phonebook.awinas.action.GtnFrameworkViewContactAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class,
		GtnUIFrameworkActionExecutor.class })

public class GtnFrameworkViewContactActionTest {

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		System.out.println("configureParams");
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
		GtnFrameworkViewContactAction instance = new GtnFrameworkViewContactAction();
		instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {
		String componentId = "";

		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkViewContactAction in = new GtnFrameworkViewContactAction();

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class);

		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		doReturn(0).when(object).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		GtnFrameworkViewContactAction ins = Mockito.spy(in);

		doReturn(response).when(ins).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		List<UserContactDetails> rsm = new ArrayList<>();
		UserContactDetails u =  new UserContactDetails();
		u.setCid(11);
		u.setCname("xx");
		u.setCphno("44");
		u.setMail("mail");
		u.setUserid(22);
		rsm.add(u);

		List<Object[]> lob = new ArrayList<>();
		for (UserContactDetails a : rsm) {
			Object[] array = new Object[] { a.getCid(), a.getUserid(), a.getCname(), a.getCphno(), a.getMail() };
			lob.add(array);
		}
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
		gtnUIFrameworkDataTable.addData(lob);

		gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
		response.setGtnSerachResponse(gtnSerachResponse);
		generalResponse.setSucess(true);
		response.setGtnWsGeneralResponse(generalResponse);

		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGetResponse() throws Exception {
		GtnFrameworkViewContactAction fixture = new GtnFrameworkViewContactAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getResponse(request);
	}

	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnFrameworkViewContactAction instance = new GtnFrameworkViewContactAction();
		GtnFrameworkViewContactAction result = (GtnFrameworkViewContactAction) instance.createInstance();
		assertEquals(instance, result);
	}

}
