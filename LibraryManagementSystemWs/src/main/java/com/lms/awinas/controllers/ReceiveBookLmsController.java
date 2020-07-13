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
public class ReceiveBookLmsController {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(IssueBookLmsController.class);
	
	@RequestMapping(value=LmsUrlConstants.GTN_WS_LMS_BOOKRECEIVELMS_SERVICE,method= RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse issuelms(@RequestBody GtnUIFrameworkWebserviceRequest request)
	{
		logger.info("in RECEIVE  BOOK controller lms");

		Lmsdao dao=new Lmsdao();
		
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		
		response = dao.receiveBookDetail(request);

		
		return response;
	
		
		
	}
	
}
