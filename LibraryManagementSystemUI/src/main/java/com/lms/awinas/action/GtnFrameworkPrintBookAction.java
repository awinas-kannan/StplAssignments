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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkPrintBookAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkPrintBookAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("print book action");

		BookLmsModel blm = new BookLmsModel();

		blm.setBookid(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookid").getIntegerFromField());
		BookLmsRequest bookLmsRequest = new BookLmsRequest();
		bookLmsRequest.setBookLmsModel(blm);

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setBookLmsRequest(bookLmsRequest);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_SHOWBOOKLMS_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if ("VALID ID".equals(response.getBookLmsResponse().getBookResult())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookname").setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookdept").setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookavi").setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuid").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuname").setVisible(false);
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookname")
					.setPropertyValue(response.getBookLmsResponse().getBookLmsModel().getBookname());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookdept")
					.setPropertyValue(response.getBookLmsResponse().getBookLmsModel().getBookdept());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookavi")
					.setPropertyValue(response.getBookLmsResponse().getBookLmsModel().getBookavailability());

			if ("no".equals(response.getBookLmsResponse().getBookLmsModel().getBookavailability())) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuid").setVisible(true);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuname").setVisible(true);
				
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuid")
						.setPropertyValue(response.getStudentLmsResponse().getStudentLmsModel().getStuid());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuname")
						.setPropertyValue(response.getStudentLmsResponse().getStudentLmsModel().getStuname());
			}

		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookname").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookdept").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookavi").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuid").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("checkbookstuname").setVisible(false);
			Notification.show(response.getBookLmsResponse().getBookResult());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
