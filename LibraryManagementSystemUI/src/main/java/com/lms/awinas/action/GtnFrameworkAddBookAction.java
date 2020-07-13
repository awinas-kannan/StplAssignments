package com.lms.awinas.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsRequest;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsRequest;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkAddBookAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddStudentAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("add book action");
		
		BookLmsModel blm =new BookLmsModel();
		
		
		blm.setBookid(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addbookid").getIntegerFromField());
		blm.setBookname(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addbookname").getStringFromField());
		blm.setBookdept(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addbookdept").getStringFromField());
		blm.setBookavailability("yes");
		
		BookLmsRequest bookLmsRequest =new BookLmsRequest();
		bookLmsRequest.setBookLmsModel(blm);
		
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		request.setBookLmsRequest(bookLmsRequest);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_ADDBOOKLMS_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());


		String result = response.getBookLmsResponse().getBookResult();
		
			Notification.show(result);
	
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		
		return this;
	}

}
