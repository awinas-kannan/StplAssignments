package com.lms.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnUIFrameworkLmsMainConfig {

	public GtnUIFrameworkRootConfig getLmsRootConfig() {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();
		
		
		viewList.add(new LoginTabSheetConfig().LoginView());
		
		viewList.add(new StudentTabSheetConfig().StudentTabView());
		
		viewList.add(new LibrarianTabSheetConfig().LibrarianTabView());
		
		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

}
