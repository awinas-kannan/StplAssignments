package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.phonebook.awinas.action.GtnFrameworkDeleteContactAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class DeleteTabComponentList {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	private String deleteContactLayoutId ="deletecontactlayout";
	
	
	public List<GtnUIFrameworkComponentConfig> getDeleteContactTabConfigList()
	{
		GtnUIFrameworkComboBoxConfig cb = new GtnUIFrameworkComboBoxConfig();
		List<String> itemValues = new ArrayList<>();
		itemValues.add("cname");
		itemValues.add("mail");
		itemValues.add("cphno");

		cb.setItemValues(itemValues);

		List<String> itemCaptionValues = new ArrayList<>();
		itemCaptionValues.add("NAME");
		itemCaptionValues.add("EMAIL ID");
		itemCaptionValues.add("PHONE NUMBER");
		cb.setItemCaptionValues(itemCaptionValues);

		// DeleteTabList

		List<GtnUIFrameworkComponentConfig> deleteContactTabConfigList = new ArrayList();

		// Deletelayout

		GtnUIFrameworkComponentConfig deleteContactTablayout = configProvider
				.getVerticalLayoutConfig(deleteContactLayoutId, true, "deletecontacttab");
		deleteContactTablayout.setComponentWidth("100%");
		deleteContactTabConfigList.add(deleteContactTablayout);

		// delete dropdown
		GtnUIFrameworkComponentConfig deleteDropdown = configProvider.getUIFrameworkComponentConfig("deldrop", true,
				deleteContactLayoutId, GtnUIFrameworkComponentType.COMBOBOX);
		deleteDropdown.setComponentName("SELECT");
		deleteDropdown.setGtnComboboxConfig(cb);
		deleteContactTabConfigList.add(deleteDropdown);

		// TextFieldFromAdUsing

		GtnUIFrameworkComponentConfig delcontactno = configProvider.getUIFrameworkComponentConfig("deletevalue", true,
				deleteContactLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		delcontactno.setComponentName("VALUE");
		delcontactno.setSpacing(true);
		deleteContactTabConfigList.add(delcontactno);

		// DeleteButton
		GtnUIFrameworkComponentConfig deleteContactButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"deletecontactbutton", true, deleteContactLayoutId, GtnUIFrameworkComponentType.BUTTON);
		deleteContactButtonConfig.setComponentName("REMOVE");

		deleteContactTabConfigList.add(deleteContactButtonConfig);

		

		// DeleteButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkDeleteContactActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkDeleteContactActionConfig.addActionParameter(GtnFrameworkDeleteContactAction.class.getName());
		deleteContactButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkDeleteContactActionConfig);
		
		return deleteContactTabConfigList;
	}
}
