package com.phonebook.awinas.bi;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public abstract class AbstractCommon {

	 protected String cAdded ="CONTACT ADDED SUCCESSFUL";
	 protected String cValid ="VALID VALUE";
	 protected String cInvalid="INVALID VALUE";
	 protected String cUpdate="CONTACT UPDATE SUCCESSFUL";
	 protected String cExist="CONTACT ALREADY PRESENT";
	public abstract GtnUIFrameworkWebserviceResponse add(GtnUIFrameworkWebserviceRequest request);
	
}
