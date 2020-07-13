package com.phonebook.awinas.action;



import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;

import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PBUrlConstants;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookRequest;
import com.stpl.gtn.gtn2o.ws.phonebook.UserDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkUserSignUpAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddContactAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("into sign up action");
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		

		UserDetails alm = new UserDetails();

		try
		{
			
			gtnLogger.info("im inside");
			
			alm.setId(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userid").getIntegerFromField());
			alm.setName(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userpwd").getStringFromField());
			gtnLogger.info(alm.getId() + alm.getName());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

			PhoneBookRequest pbr = new PhoneBookRequest();

			pbr.setUserdetailsrequest(alm);

			request.setPhonebookrequest(pbr);

			gtnLogger.info("length" +alm.getName().length());
			if(alm.getId()!=0 && alm.getName().length()>0)
			{
			GtnUIFrameworkWebserviceResponse response = getResponse(request);

			String result = response.getPhonebookresponse().getResultresponse();
			Notification.show(result);
			}
			else
			{
				
			Notification.show("ENTER ALL EMPTY FIELDS");
			}

		} 
		catch(GtnFrameworkValidationFailedException e)
		{
		gtnLogger.error("exp is " +e);
			Notification.show("ENTER A NUMBERIC VALUE");
		}

		
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		
		return  new GtnUIFrameworkWebServiceClient().callGtnPbWebServiceUrl(
				PBUrlConstants.GTN_WS_PB_SIGNUP_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

	

	
}
