package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnUIFrameworkPhoneBookMainConfig {

	public GtnUIFrameworkRootConfig getPhoneBookRootConfig() {
		
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		
		
		viewList.add(new LoginTabSheetConfig().loginView());
		
	
		
		viewList.add(new PhoneBookTabSheetConfig().phoneBookTabView());
		
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
