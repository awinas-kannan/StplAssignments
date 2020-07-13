package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.phonebook.awinas.action.GtnFrameworkViewContactAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.GtnUIFrameworkGridComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class ViewTabComponentList {
private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	private String viewContactLayoutId="viewcontactlayout";
	
	
	public List<GtnUIFrameworkComponentConfig> getViewContactTabConfigList(){
	
	// viewTabList

			List<GtnUIFrameworkComponentConfig> viewContactTabConfigList = new ArrayList();

			// viewlayout

			GtnUIFrameworkComponentConfig viewContactTablayout = configProvider.getVerticalLayoutConfig(viewContactLayoutId,
					true, "viewcontacttab");
			viewContactTablayout.setComponentWidth("100%");
			viewContactTabConfigList.add(viewContactTablayout);

		
			// viewButton
			GtnUIFrameworkComponentConfig viewContactButtonConfig = configProvider.getUIFrameworkComponentConfig(
					"viewcontactbutton", true, viewContactLayoutId, GtnUIFrameworkComponentType.BUTTON);
			viewContactButtonConfig.setComponentName("CLICK TO SEE YOUR CONTACTS");

			viewContactTabConfigList.add(viewContactButtonConfig);

			

			// viewButtonAction
			GtnUIFrameWorkActionConfig gtnUIFrameWorkViewContactActionConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			gtnUIFrameWorkViewContactActionConfig.addActionParameter(GtnFrameworkViewContactAction.class.getName());
			viewContactButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkViewContactActionConfig);
			
		
			

			// GRID
			
			GtnUIFrameworkGridComponentConfig ptc = new GtnUIFrameworkGridComponentConfig();
			
		ptc.setColumnHeadersId(new String[] {"cid","userid","cname","cphno","mail"} );
		ptc.setColumnHeadersName(new String[] {"C_ID","USER_ID","C_NAME","C_PHNO","C_MAIL"});
			
			GtnUIFrameworkComponentConfig grid = configProvider.getUIFrameworkComponentConfig("grid", true,
					viewContactLayoutId, GtnUIFrameworkComponentType.GRID);

			grid.setComponentName("TABLE");
			grid.setComponentWidth("100%");
		
			grid.setGtnUIFrameWorkGridConfig(ptc);
			
			viewContactTabConfigList.add(grid);
			return viewContactTabConfigList;
	}
	
}
