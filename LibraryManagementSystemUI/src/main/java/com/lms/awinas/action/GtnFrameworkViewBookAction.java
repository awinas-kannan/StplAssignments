package com.lms.awinas.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.BookLmsRequest;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkViewBookAction implements GtnUIFrameworkDynamicClass,GtnUIFrameWorkAction {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkViewBookAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
 
		Notification.show("INSIDE DO ACTION");
		BookLmsModel blm = new BookLmsModel();
		blm.setBookid(6);
		BookLmsRequest bookLmsRequest = new BookLmsRequest();
		bookLmsRequest.setBookLmsModel(blm);

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setBookLmsRequest(bookLmsRequest);
		
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_VIEWBOOK_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
	
		
		/////////////////////////////////
		List<GtnUIFrameworkDataRow> datarow= response.getGtnSerachResponse().getResultSet().getDataTable();

		List<BookLmsModel> list =response.getBookLmsResponse().getBooklist();
		List<GtnWsRecordBean> lgrb =new ArrayList();
		
		gtnLogger.info("size  " +list.size());
		
		
		for(GtnUIFrameworkDataRow rec : response.getGtnSerachResponse().getResultSet().getDataTable())
		{
			GtnWsRecordBean grb = new GtnWsRecordBean();
			grb.setRecordHeader(Arrays.asList("bookid","bookname","bookdept","avi"));
			grb.setProperties(rec.getColList());
			lgrb.add(grb);
		}
		/*for (BookLmsModel rec: list)
		{
			List<Object> lis=new ArrayList();
			lis.add(rec);
			GtnWsRecordBean grb = new GtnWsRecordBean();
			
			grb.setProperties(lis);
			grb.setRecordHeader(Arrays.asList("bookid","bookname","bookdept","avi"));
		
			lgrb.add(grb);
		}
		*/
		for(BookLmsModel bm :list)
		{
			gtnLogger.info(bm.getBookname());

		}
		
		/*List<Object> rowlist = new ArrayList();
		rowlist.add(list);

		GtnWsRecordBean grb = new GtnWsRecordBean();
		grb.setProperties(rowlist);
		grb.setRecordHeader(Arrays.asList("bookid", "bookname", "bookdept", "avi"));

		lgrb.add(grb);
*/
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("grid").removeAllGridItems();
			
	//		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("grid").setV8GridItems(lgrb);
	
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("grid").addItemsToGrid(lgrb);
			//	GtnUIFrameworkGlobalUI.getVaadinBaseComponent("grid").addItemToDataTable(rowlist);
				
	
   	
   	
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
