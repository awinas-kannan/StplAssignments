package com.phonebook.awinas.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PBUrlConstants;
import com.stpl.gtn.gtn2o.ws.phonebook.PhoneBookRequest;
import com.stpl.gtn.gtn2o.ws.phonebook.UserContactDetails;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkViewContactAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkViewContactAction.class);

	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("into view action");
	}

	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		gtnLogger.info("comp id=" + componentId);
		UserContactDetails ucd = new UserContactDetails();
	
		int userid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userid").getIntegerFromField();
		ucd.setUserid(userid);

		PhoneBookRequest pbr = new PhoneBookRequest();

		pbr.setContactdetailsrequest(ucd);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();


		
		
		request.setPhonebookrequest(pbr);

		GtnUIFrameworkWebserviceResponse response = getResponse(request);

		List<GtnWsRecordBean> gtnWsRecordBeanList = new ArrayList<>();
		for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
			GtnWsRecordBean dto = new GtnWsRecordBean();

			dto.setRecordHeader(Arrays.asList("cid", "userid", "cname", "cphno", "mail"));
			gtnLogger.info("" + record.getColList());
			dto.setProperties(record.getColList());
			
			gtnWsRecordBeanList.add(dto);

		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("grid").setGridItems(gtnWsRecordBeanList);
		
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		 
		return new GtnUIFrameworkWebServiceClient().callGtnPbWebServiceUrl(
				PBUrlConstants.GTN_WS_PB_VIEWCONTACT_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
