/**
 * 
 */
package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;

import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

/**
 * @author awinas.kannan
 *
 */
public class PhoneBookTabSheetConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig phoneBookTabView() {

		// phonebookview

		GtnUIFrameworkViewConfig phonebookTabSheetView = configProvider.getViewConfig("PHONE BOOK MANAGEMENT",
				"phonebookview", false);

		// ListOfCompoentsInPBView(view component list)

		List<GtnUIFrameworkComponentConfig> viewcomponentList = new ArrayList<>();

		GtnUIFrameworkComponentConfig phonebookPanel = configProvider.getPanelConfig("phonebookpanel", false, null);
		phonebookPanel.setComponentName("PhoneBook PANEL");
		phonebookPanel.setComponentWidth("100%");
		viewcomponentList.add(phonebookPanel);

		// PbLayout

		GtnUIFrameworkComponentConfig phonebookLayoutConfig = configProvider.getHorizontalLayoutConfig("pblayout", true,
				"phonebookpanel");
		phonebookLayoutConfig.setComponentWidth("100%");
		viewcomponentList.add(phonebookLayoutConfig);

		// CreatingTabSheet

		GtnUIFrameworkComponentConfig phonebookTabSheetConfig = configProvider
				.getUIFrameworkComponentConfig("pbtabsheet", true, "pblayout", GtnUIFrameworkComponentType.TABSHEET);
		phonebookTabSheetConfig.setComponentName("PhoneBook TabSheet");
		phonebookTabSheetConfig.setComponentWidth("100%");
		viewcomponentList.add(phonebookTabSheetConfig);

		// -------------------------------------//

		// AddContactTab

		GtnUIFrameworkTabConfig addContactTab = configProvider.getTabConfig("addcontacttab", "ADD CONTACT");

		addContactTab.setTabLayoutComponentConfigList(new AddTabComponentsList().getAddContactTabConfigList());

		// DeleteContact
		GtnUIFrameworkTabConfig deleteContactTab = new GtnUIFrameworkTabConfig();
		deleteContactTab.setComponentId("deletecontacttab");
		deleteContactTab.setTabCaption("REMOVE CONTACT");

		deleteContactTab.setTabLayoutComponentConfigList(new DeleteTabComponentList().getDeleteContactTabConfigList());

		// VIEWContact

		GtnUIFrameworkTabConfig viewContactTab = new GtnUIFrameworkTabConfig();
		viewContactTab.setComponentId("viewcontacttab");
		viewContactTab.setTabCaption("VIEW CONTACT");

		viewContactTab.setTabLayoutComponentConfigList(new ViewTabComponentList().getViewContactTabConfigList());

		// editContact

		GtnUIFrameworkTabConfig editContactTab = new GtnUIFrameworkTabConfig();
		editContactTab.setComponentId("editcontacttab");
		editContactTab.setTabCaption("EDIT CONTACT");

		editContactTab.setTabLayoutComponentConfigList(new EditTabComponentList().getEditContactTabConfigList());

		// LIST OF TABS
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(addContactTab);
		tabConfigList.add(deleteContactTab);
		tabConfigList.add(viewContactTab);
		tabConfigList.add(editContactTab);

		phonebookTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		phonebookTabSheetView.setGtnComponentList(viewcomponentList);

		return phonebookTabSheetView;

	}

}
