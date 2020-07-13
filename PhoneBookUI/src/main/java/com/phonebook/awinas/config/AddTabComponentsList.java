package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.phonebook.awinas.action.GtnFrameworkAddContactAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class AddTabComponentsList {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	private String addTabLayoutId="addcontacttablayout";
	
	public List<GtnUIFrameworkComponentConfig> getAddContactTabConfigList() {
		// AddTabList

		List<GtnUIFrameworkComponentConfig> addContactTabConfigList = new ArrayList();

		// AddtabLayout

		GtnUIFrameworkComponentConfig addContactTablayout = configProvider
				.getVerticalLayoutConfig(addTabLayoutId, true, "addcontacttab");
		addContactTablayout.setComponentWidth("100%");
		addContactTabConfigList.add(addContactTablayout);

		// TextBox1
		GtnUIFrameworkComponentConfig cname = configProvider.getUIFrameworkComponentConfig("cname", true,
				addTabLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		cname.setComponentName("CONTACT NAME");
		addContactTabConfigList.add(cname);

		// TextBox2
		GtnUIFrameworkComponentConfig cmail = configProvider.getUIFrameworkComponentConfig("cmail", true,
				addTabLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		cmail.setComponentName("CONTACT MAIL-ID");
		addContactTabConfigList.add(cmail);

		// TextBox3
		GtnUIFrameworkComponentConfig cphno = configProvider.getUIFrameworkComponentConfig("cphno", true,
				addTabLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		cphno.setComponentName("CONTACT NUMBER");
		addContactTabConfigList.add(cphno);

		// AddButton
		GtnUIFrameworkComponentConfig addContactButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"addcontactbutton", true, addTabLayoutId, GtnUIFrameworkComponentType.BUTTON);
		addContactButtonConfig.setComponentName("Add");

		addContactTabConfigList.add(addContactButtonConfig);

		// AddButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkAddContactActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkAddContactActionConfig.addActionParameter(GtnFrameworkAddContactAction.class.getName());

		addContactButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkAddContactActionConfig);
		
		return addContactTabConfigList;
	}

}
