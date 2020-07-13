package com.phonebook.awinas.action;

import java.util.regex.Pattern;

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

public class GtnFrameworkUpdateContactAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddContactAction.class);

	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("into update contact action");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String regexPhno = "^[0-9]{10}$";
		String regexEmail = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
		String name = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluename").getStringFromField();
		String phno = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluephno").getStringFromField();
		String mail = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluemail").getStringFromField();
		int userid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userid").getIntegerFromField();
		int cid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluecid").getIntegerFromField();
		UserContactDetails ucd = new UserContactDetails();
		ucd.setMail(mail);
		ucd.setCname(name);
		ucd.setCphno(phno);
		ucd.setUserid(userid);
		ucd.setCid(cid);
		PhoneBookRequest pbr = new PhoneBookRequest();
		pbr.setContactdetailsrequest(ucd);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setPhonebookrequest(pbr);

		if (name.length() > 0 && phno.length() > 0 && mail.length() > 0) {
			Pattern pattern1 = Pattern.compile(regexPhno);
			Pattern pattern2 = Pattern.compile(regexEmail);

			if (pattern1.matcher(ucd.getCphno()).matches()) {
				if (pattern2.matcher(ucd.getMail()).matches()) {

					GtnUIFrameworkWebserviceResponse response = getResponse(request);
					
					Notification.show(response.getPhonebookresponse().getResultresponse());
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluename").setVisible(false);

					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluemail").setVisible(false);

					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editvaluephno").setVisible(false);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editcontactbutton").setVisible(true);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("updatecontactbutton").setVisible(false);

				}
				else {
					Notification.show("ENTER VALID  MAIL ID");
				}
			}

			else {
				Notification.show("ENTER VALID PHONE NUMBER");
			}
		} 
		else {
			Notification.show("ENTER ALL  EMPTY FILEDS");
		}

	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		
		return  new GtnUIFrameworkWebServiceClient()
				.callGtnPbWebServiceUrl(PBUrlConstants.GTN_WS_PB_UPDATECONTACT_SERVICE, request,
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
