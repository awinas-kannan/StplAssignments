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
public class StudentLoginControllerLms {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(StudentLoginControllerLms.class);

	@RequestMapping(value = LmsUrlConstants.GTN_WS_LMS_STUDENTLOGIN_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse adminlogin(@RequestBody GtnUIFrameworkWebserviceRequest request) {

		logger.info("stu login controller called");

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		Loginsdao dao = new Loginsdao();

		
		response = dao.getStudentCredentialResult(request);

		return response;

	}
}
