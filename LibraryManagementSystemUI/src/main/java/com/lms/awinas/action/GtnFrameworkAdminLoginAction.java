package com.lms.awinas.action;


import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.lms.AdminLoginModel;
import com.stpl.gtn.gtn2o.ws.lms.AdminLoginRequest;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkAdminLoginAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAdminLoginAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		 gtnLogger.debug("Entering inside AdminLoginAction");
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		
		String regex = "\\d*";
		Pattern pattern = Pattern.compile(regex);
		
		AdminLoginModel alm=new AdminLoginModel();
		String userid =GtnUIFrameworkGlobalUI.getVaadinBaseComponent("uid").getStringFromField();
		
		if(pattern.matcher(userid).matches())
		{
		
		alm.setAdminid(Integer.parseInt(userid));
		alm.setAdminpwd(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("pwd").getStringFromField());
		
		AdminLoginRequest alr= new AdminLoginRequest();
		alr.setAdminLoginModel(alm);
		
		
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		request.setAdminLoginRequest(alr);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_ADMINLOGIN_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());


		String result = response.getLoginsResponse().getLoginResult();
		
		if("SUCCESSFUL".equals(result))
		{
		
		GtnUIFrameWorkActionConfig NavigateToLibrarianTabSheet = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		NavigateToLibrarianTabSheet.addActionParameter("library");
		
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, NavigateToLibrarianTabSheet);
		}
		else
		{
			Notification.show(result);
		}
		}
		else
		{
			Notification.show("ENTER A NUMBERIC VALUE");
		}
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		
		return this;
	}

}
