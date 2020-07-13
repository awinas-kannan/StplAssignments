package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.phonebook.awinas.action.GtnFrameworkEditContactAction;
import com.phonebook.awinas.action.GtnFrameworkUpdateContactAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class EditTabComponentList {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	private String editContactLayoutId="editcontactlayout";
	
	public List<GtnUIFrameworkComponentConfig> getEditContactTabConfigList()
	{
		GtnUIFrameworkTextBoxConfig all = new GtnUIFrameworkTextBoxConfig();

		all.setReadOnly(true);

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


		// editTabList

		List<GtnUIFrameworkComponentConfig> editContactTabConfigList = new ArrayList();

		// editlayout

		GtnUIFrameworkComponentConfig editContactTablayout = configProvider.getVerticalLayoutConfig(editContactLayoutId,
				true, "editcontacttab");
		editContactTablayout.setComponentWidth("100%");
		editContactTabConfigList.add(editContactTablayout);

		// edit dropdown
		GtnUIFrameworkComponentConfig editDropdown = configProvider.getUIFrameworkComponentConfig("editdrop", true,
				editContactLayoutId, GtnUIFrameworkComponentType.COMBOBOX);
		editDropdown.setComponentName("SELECT");
		editDropdown.setGtnComboboxConfig(cb);
		editContactTabConfigList.add(editDropdown);

		// TextFieldS

		GtnUIFrameworkComponentConfig editcontactval = configProvider.getUIFrameworkComponentConfig("editvalue", true,
				editContactLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		editcontactval.setComponentName("VALUE");
		editcontactval.setSpacing(true);
		editContactTabConfigList.add(editcontactval);

		GtnUIFrameworkComponentConfig editcontactid = configProvider.getUIFrameworkComponentConfig("editvaluecid",
				true, editContactLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		editcontactid.setComponentName("CONTACT ID");
		editcontactid.setSpacing(true);
		editcontactid.setVisible(false);
		editcontactid.setGtnTextBoxConfig(all);
		editContactTabConfigList.add(editcontactid);
		
		GtnUIFrameworkComponentConfig editcontactname = configProvider.getUIFrameworkComponentConfig("editvaluename",
				true, editContactLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		editcontactname.setComponentName("NAME");
		editcontactname.setVisible(false);
		editcontactname.setSpacing(true);

		editContactTabConfigList.add(editcontactname);

		GtnUIFrameworkComponentConfig editcontactmail = configProvider.getUIFrameworkComponentConfig("editvaluemail",
				true, editContactLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		editcontactmail.setComponentName("E-MAIL");
		editcontactmail.setVisible(false);
		editcontactmail.setSpacing(true);

		editContactTabConfigList.add(editcontactmail);

		GtnUIFrameworkComponentConfig editcontactphno = configProvider.getUIFrameworkComponentConfig("editvaluephno",
				true, editContactLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		editcontactphno.setComponentName("PHONE NUMBER");
		editcontactphno.setVisible(false);
		editcontactphno.setSpacing(true);

		editContactTabConfigList.add(editcontactphno);

		// editButton
		GtnUIFrameworkComponentConfig editContactButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"editcontactbutton", true, editContactLayoutId, GtnUIFrameworkComponentType.BUTTON);
		editContactButtonConfig.setComponentName("EDIT");

		editContactTabConfigList.add(editContactButtonConfig);


		// editButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkEditContactActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkEditContactActionConfig.addActionParameter(GtnFrameworkEditContactAction.class.getName());
		editContactButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkEditContactActionConfig);

		// updateButton
		GtnUIFrameworkComponentConfig updateContactButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"updatecontactbutton", true, editContactLayoutId, GtnUIFrameworkComponentType.BUTTON);
		updateContactButtonConfig.setComponentName("UPDATE");
		updateContactButtonConfig.setVisible(false);
		editContactTabConfigList.add(updateContactButtonConfig);

		

		// updateButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkUpdateContactActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkUpdateContactActionConfig.addActionParameter(GtnFrameworkUpdateContactAction.class.getName());
		updateContactButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkUpdateContactActionConfig);
		return editContactTabConfigList;
	}	
	
	
}
