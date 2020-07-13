package com.phonebook.awinas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.awinas.dao.PhoneBookDao;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.phonebook.PBUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;


@RestController
public class EditContactController {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(EditContactController.class);
	@Autowired
	private PhoneBookDao dao;

	public EditContactController() {
		// CONTROLLER CODE
	}
	
	@RequestMapping(value = PBUrlConstants.GTN_WS_PB_EDITCONTACT_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse editCon(@RequestBody GtnUIFrameworkWebserviceRequest request) {

		logger.info("edit contact controller called");

		return dao.edit(request);
	}
}
