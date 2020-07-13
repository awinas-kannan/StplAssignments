package com.phonebook.awinas.bi;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public interface Iunique {

	GtnUIFrameworkWebserviceResponse delete(GtnUIFrameworkWebserviceRequest request);
	GtnUIFrameworkWebserviceResponse view(GtnUIFrameworkWebserviceRequest request);
	GtnUIFrameworkWebserviceResponse edit(GtnUIFrameworkWebserviceRequest request);
	GtnUIFrameworkWebserviceResponse update(GtnUIFrameworkWebserviceRequest request);
	
	
}
