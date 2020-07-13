package com.lms.awinas.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.StudentLoginModel;
import com.stpl.gtn.gtn2o.ws.lms.StudentLoginRequest;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkStudentLoginAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAdminLoginAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		StudentLoginModel slm=new StudentLoginModel();
		slm.setStudentid(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("stuid").getIntegerFromField());
		slm.setStudentpwd(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("stupwd").getStringFromField());
		
		StudentLoginRequest slr = new StudentLoginRequest();
		slr.setStudentLoginModel(slm);
		
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		request.setStudentLoginRequest(slr);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_STUDENTLOGIN_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());


		String result = response.getLoginsResponse().getLoginResult();
		
		if("SUCCESSFUL".equals(result))
		{
			Notification.show(result);
		GtnUIFrameWorkActionConfig NavigateToLibrarianTabSheet = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		NavigateToLibrarianTabSheet.addActionParameter("stuview");
		
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, NavigateToLibrarianTabSheet);
		}
		else
		{
			Notification.show(result);
		}
		
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
