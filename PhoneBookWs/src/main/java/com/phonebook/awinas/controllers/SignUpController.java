package com.phonebook.awinas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.awinas.dao.LoginDao;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PBUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class SignUpController {

	@Autowired
	private LoginDao dao;

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(SignUpController.class);

	public SignUpController() {
		// CONTROLLER CODE
	}

	@RequestMapping(value = PBUrlConstants.GTN_WS_PB_SIGNUP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse adminlogin(@RequestBody GtnUIFrameworkWebserviceRequest request) {

		logger.info("USER sign up controller called");

		return dao.add(request);
	}

}
