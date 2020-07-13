package com.phonebook.awinas.action;

import java.util.regex.Pattern;

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
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkAddContactAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddContactAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("add contact action ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			PhoneBookRequest pbr = new PhoneBookRequest();
			String regexPhno = "^[0-9]{10}$";
			String regexEmail = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
			
			String name = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cname").getStringFromField();
			gtnLogger.info("name =" + name);
			int userid
			= GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userid").getIntegerFromField();
			
			String phno = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cphno").getStringFromField();
			String mail = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cmail").getStringFromField();
			
			UserContactDetails ucd = new UserContactDetails();
			ucd.setMail(mail);
			ucd.setCname(name);
			ucd.setCphno(phno);
			ucd.setUserid(userid);
			
			pbr.setContactdetailsrequest(ucd);

			
			request.setPhonebookrequest(pbr);
			
			if (ucd.getCname().length() > 0 && ucd.getCphno().length() > 0
					&& ucd.getMail().length() > 0) {
				Pattern pattern1 = Pattern.compile(regexPhno);
				Pattern pattern2 = Pattern.compile(regexEmail);

				if (pattern1.matcher(ucd.getCphno()).matches()&&pattern2.matcher(ucd.getMail()).matches()) {
					

						GtnUIFrameworkWebserviceResponse response = getResponse(request);

						Notification.show(response.getPhonebookresponse().getResultresponse());

				}

				else {
					Notification.show("ENTER VALID PHONE NUMBER OR MAIL ID");
				}
			} 
			
			else {
				Notification.show("ENTER ALL  EMPTY FILEDS");
			}

		} 
		catch (GtnFrameworkValidationFailedException e) {

			gtnLogger.error("exp is " + e);
			Notification.show("ENTER NUMERIC VALUE FOR C ID");
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		 
		return new GtnUIFrameworkWebServiceClient()
				.callGtnPbWebServiceUrl(PBUrlConstants.GTN_WS_PB_ADDCONTACT_SERVICE, request,
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
