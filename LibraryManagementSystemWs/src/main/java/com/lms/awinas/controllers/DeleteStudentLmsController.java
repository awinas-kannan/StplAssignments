package com.lms.awinas.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;


@RestController
public class DeleteStudentLmsController {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(DeleteStudentLmsController.class);

	@RequestMapping(value = LmsUrlConstants.GTN_WS_LMS_DELETESTUDENTLMS_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deletestu(@RequestBody GtnUIFrameworkWebserviceRequest request)
	{
		
		logger.info("delete called");
		
		Lmsdao dao=new Lmsdao();
		
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		
		response = dao.deleteStudentDetail(request);

		
		
		return response;
		
	}
	
}
