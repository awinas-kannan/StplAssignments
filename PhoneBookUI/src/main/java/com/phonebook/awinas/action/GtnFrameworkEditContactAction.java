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

public class GtnFrameworkEditContactAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddContactAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("edit contact action ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		UserContactDetails ucd = new UserContactDetails();
		int userid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userid").getIntegerFromField();
		ucd.setUserid(userid);

		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setStringv(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvalue").getStringFromField());
		pbr.setStringd((String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editdrop").getValueFromComponent());
		
		pbr.setContactdetailsrequest(ucd);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setPhonebookrequest(pbr);
		if (pbr.getStringd() != null) {
			GtnUIFrameworkWebserviceResponse response = getResponse(request);

			if ("VALID VALUE".equals(response.getPhonebookresponse().getResultresponse())) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluecid")
						.setPropertyValue(response.getPhonebookresponse().getContactdetailsresponse().getCid());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluename")
						.setPropertyValue(response.getPhonebookresponse().getContactdetailsresponse().getCname());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluemail")
						.setPropertyValue(response.getPhonebookresponse().getContactdetailsresponse().getMail());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluephno")
						.setPropertyValue(response.getPhonebookresponse().getContactdetailsresponse().getCphno());
				
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluename").setVisible(true);
						
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluemail").setVisible(true);
						
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluephno").setVisible(true);
			
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editcontactbutton").setVisible(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("updatecontactbutton").setVisible(true);
			}
			else {
				Notification.show(response.getPhonebookresponse().getResultresponse());
			}
		}
		else {
			Notification.show("select from drop down");
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		
		return  new GtnUIFrameworkWebServiceClient().callGtnPbWebServiceUrl(
				PBUrlConstants.GTN_WS_PB_EDITCONTACT_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
