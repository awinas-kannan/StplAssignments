package com.lms.awinas.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lms.awinas.action.GtnFrameworkAddStudentAction;
import com.lms.awinas.action.GtnFrameworkViewBookAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.GtnUIFrameworkGridComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.GtnUIFrameworkGridComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class StudentTabSheetConfig {

	
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig StudentTabView() {

		// STUview

		GtnUIFrameworkViewConfig studentTabSheetView = configProvider.getViewConfig("STUDENT MANAGEMENT", "stuview",
				false);

		// ListOfCompoentsInLibraryTabSheet
		List<GtnUIFrameworkComponentConfig> studentTabSheetcomponentList = new ArrayList<>();

		// STUPanel

		GtnUIFrameworkComponentConfig stuPanel = configProvider.getPanelConfig("stupanel", false, null);
		stuPanel.setComponentName("STUDENT PANEL");
		stuPanel.setComponentWidth("100%");
		studentTabSheetcomponentList.add(stuPanel);

		// LibraryLayout

		GtnUIFrameworkComponentConfig studentLayoutConfig = configProvider.getVerticalLayoutConfig("stulayout", true,
				"stupanel");
		studentLayoutConfig.setComponentWidth("100%");
		studentTabSheetcomponentList.add(studentLayoutConfig);

		// CreatingTabSheet

		GtnUIFrameworkComponentConfig studnetTabSheetConfig = configProvider.getUIFrameworkComponentConfig("stutabshet",
				true, "stulayout", GtnUIFrameworkComponentType.TABSHEET);
		studnetTabSheetConfig.setComponentName("STUDENT TabSheet");
		studnetTabSheetConfig.setComponentWidth("100%");
		studentTabSheetcomponentList.add(studnetTabSheetConfig);

		// VIEW TAB

		GtnUIFrameworkTabConfig viewBookTab = configProvider.getTabConfig("viewbooktab", "VIEW BOOK");

		// VIEWTabList

		List<GtnUIFrameworkComponentConfig> viewBookTabConfigList = new ArrayList();

		// VIEWtabLayout

		GtnUIFrameworkComponentConfig viewBookTablayout = configProvider.getVerticalLayoutConfig("viewbooktablayout",
				true, "viewbooktab");
		viewBookTablayout.setComponentWidth("100%");
		
		viewBookTabConfigList.add(viewBookTablayout);

		// VIEWButton
		GtnUIFrameworkComponentConfig viewBookButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"viewbookbutton", true, "viewbooktablayout", GtnUIFrameworkComponentType.BUTTON);
		viewBookButtonConfig.setComponentName("VIEW BOOK");

		viewBookTabConfigList.add(viewBookButtonConfig);

		// GRID
		GtnUIFrameworkPagedTableConfig ptc=new GtnUIFrameworkPagedTableConfig();
		ptc.setSelectable(false);
		ptc.setTableColumnDataType(new Class[] {Integer.class,String.class,String.class,String.class});
		ptc.setTableVisibleHeader(new String[] {"bookid","bookname","bookdept","bookavi"} );
		ptc.setTableColumnMappingId(new Object[] {"BOOK_ID","BOOK_NAME","BOOK_DEPT","BOOK_AVAILABILITY"});
	
		//
		/*GtnUIFrameworkGridComponentConfig gcc = new GtnUIFrameworkGridComponentConfig();
		gcc.setColumnHeadersName(new String[] {"bookid","bookname","bookdept","bookavi"});
		gcc.setColumnHeadersId(new String[] {"BOOK_ID","BOOK_NAME","BOOK_DEPT","BOOK_AVAILABILITY"});
		*/
		
		GtnUIFrameworkComponentConfig grid = configProvider.getUIFrameworkComponentConfig("grid", true,
				"viewbooktablayout", GtnUIFrameworkComponentType.PAGEDTABLE);



		grid.setComponentName("TABLE");
		grid.setComponentWidth("100%");
	
		grid.setGtnPagedTableConfig(ptc);
		//grid.setGtnUIFrameWorkGridConfig(gcc);
		viewBookTabConfigList.add(grid);

		
		
		viewBookTab.setTabLayoutComponentConfigList(viewBookTabConfigList);

		// VIEWButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkViewBookActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkViewBookActionConfig.addActionParameter(GtnFrameworkViewBookAction.class.getName());

		viewBookButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkViewBookActionConfig);

		// LIST OF TABS
		
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(viewBookTab);

		studnetTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		studentTabSheetView.setGtnComponentList(studentTabSheetcomponentList);

		return studentTabSheetView;
	}

}
