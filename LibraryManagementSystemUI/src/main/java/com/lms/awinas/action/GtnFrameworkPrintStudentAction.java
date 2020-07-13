package com.lms.awinas.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsRequest;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkPrintStudentAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddStudentAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("print student action");
		
		StudentLmsModel slm =new StudentLmsModel();
		
		
		slm.setStuid(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstuid").getIntegerFromField());
		
		StudentLmsRequest StudentLmsRequest =new StudentLmsRequest();
		StudentLmsRequest.setStudentLmsModel(slm);
		
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		request.setStudentLmsRequest(StudentLmsRequest);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_SHOESTUDENTLMS_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());


		if("VALID ID".equals(response.getStudentLmsResponse().getStudentLmsResult()))
		{
			
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstuname").setVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstudept").setVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookreceived").setVisible(true);
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstuname").setPropertyValue(response.getStudentLmsResponse().getStudentLmsModel().getStuname());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstudept").setPropertyValue(response.getStudentLmsResponse().getStudentLmsModel().getStudept());
		if( response.getStudentLmsResponse().getStudentLmsModel().getBookid()!=0)
		{
			
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookid").setVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookname").setVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookid").setPropertyValue(response.getStudentLmsResponse().getStudentLmsModel().getBookid());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookreceived").setPropertyValue(response.getStudentLmsResponse().getStudentLmsModel().getBookreceived());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookname").setPropertyValue(response.getBookLmsResponse().getBookLmsModel().getBookname());
		}
		else
		{
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookid").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookname").setVisible(false);
			
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookreceived").setPropertyValue("No");
		}
		}
		else
		{
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstuname").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("printstudept").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookreceived").setVisible(false);
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookid").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("bookname").setVisible(false);
				Notification.show(response.getStudentLmsResponse().getStudentLmsResult());
		}
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
