package com.phonebook.awinas.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PBUrlConstants;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookRequest;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkDeleteContactAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddContactAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//DELETE ACTION

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PhoneBookRequest pbr = new PhoneBookRequest();
		UserContactDetails ucd = new UserContactDetails();
		
		int userid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userid").getIntegerFromField();
		ucd.setUserid(userid);
		
		
		pbr.setStringv(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("deletevalue").getStringFromField());
		pbr.setStringd((String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("deldrop").getValueFromComponent());
		gtnLogger.info(pbr.getStringd() + pbr.getStringv());
		pbr.setContactdetailsrequest(ucd);
		
		request.setPhonebookrequest(pbr);
		if(pbr.getStringd()!=null)
		{
		GtnUIFrameworkWebserviceResponse response = getReponse(request);

		

		Notification.show(response.getPhonebookresponse().getResultresponse());
		}
		else
		{
			Notification.show("select from drop down");
		}

	}

	public GtnUIFrameworkWebserviceResponse getReponse(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnPbWebServiceUrl(
				PBUrlConstants.GTN_WS_PB_DELETECONTACT_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
