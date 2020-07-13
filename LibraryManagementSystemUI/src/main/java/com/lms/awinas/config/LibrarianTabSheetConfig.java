package com.lms.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.lms.awinas.action.GtnFrameworkAddBookAction;
import com.lms.awinas.action.GtnFrameworkAddStudentAction;
import com.lms.awinas.action.GtnFrameworkDeleteBookAction;
import com.lms.awinas.action.GtnFrameworkDeleteStudentAction;
import com.lms.awinas.action.GtnFrameworkLendBookAction;
import com.lms.awinas.action.GtnFrameworkPrintBookAction;
import com.lms.awinas.action.GtnFrameworkPrintStudentAction;
import com.lms.awinas.action.GtnFrameworkReceiveBookAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class LibrarianTabSheetConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig LibrarianTabView() {

		GtnUIFrameworkTextBoxConfig all=new GtnUIFrameworkTextBoxConfig();
		
		all.setReadOnly(true);
		// Libraryview

		GtnUIFrameworkViewConfig libraryTabSheetView = configProvider.getViewConfig("LIBRARY MANAGEMENT", "library",
				false);

		// ListOfCompoentsInLibraryTabSheetView(view component list)
		List<GtnUIFrameworkComponentConfig> libraryTabSheetcomponentList = new ArrayList<>();

		// LibraryPanel

		GtnUIFrameworkComponentConfig libraryPanel = configProvider.getPanelConfig("librarypanel", false, null);
		libraryPanel.setComponentName("LIBRARY PANEL");
		libraryPanel.setComponentWidth("100%");
		libraryTabSheetcomponentList.add(libraryPanel);

		// LibraryLayout

		GtnUIFrameworkComponentConfig libraryLayoutConfig = configProvider.getHorizontalLayoutConfig("librarylayout",
				true, "librarypanel");
		libraryLayoutConfig.setComponentWidth("100%");
		libraryTabSheetcomponentList.add(libraryLayoutConfig);

		// CreatingTabSheet

		GtnUIFrameworkComponentConfig libraryTabSheetConfig = configProvider.getUIFrameworkComponentConfig(
				"librarytabshet", true, "librarylayout", GtnUIFrameworkComponentType.TABSHEET);
		libraryTabSheetConfig.setComponentName("Library TabSheet");
		libraryTabSheetConfig.setComponentWidth("100%");
		libraryTabSheetcomponentList.add(libraryTabSheetConfig);

		// AddStudentTab

		GtnUIFrameworkTabConfig addStudentTab = configProvider.getTabConfig("addstudenttab", "ADD STUDENT");

		// AddTabkaList

		List<GtnUIFrameworkComponentConfig> addStudentTabConfigList = new ArrayList();

		// AddtabLayout

		GtnUIFrameworkComponentConfig addStudnetTablayout = configProvider
				.getVerticalLayoutConfig("addstudenttablayout", true, "addstundenttab");
		addStudnetTablayout.setComponentWidth("100%");
		addStudentTabConfigList.add(addStudnetTablayout);

		// TextBox1
		GtnUIFrameworkComponentConfig stuid = new GtnUIFrameworkComponentConfig();
		stuid.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		stuid.setComponentId("stuid");
		stuid.setComponentName("STUDENT ID");
		stuid.setSpacing(true);
		stuid.setAddToParent(true);
		stuid.setParentComponentId("addstudenttablayout");
		addStudentTabConfigList.add(stuid);

		// TextBox2
		GtnUIFrameworkComponentConfig stuname = new GtnUIFrameworkComponentConfig();
		stuname.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		stuname.setComponentId("stuname");
		stuname.setComponentName("STUDENT NAME");
		stuname.setSpacing(true);
		stuname.setAddToParent(true);
		stuname.setParentComponentId("addstudenttablayout");
		addStudentTabConfigList.add(stuname);

		// TextBox3
		GtnUIFrameworkComponentConfig studept = new GtnUIFrameworkComponentConfig();
		studept.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		studept.setComponentId("studept");
		studept.setComponentName("STUDENT DEPT");
		studept.setSpacing(true);
		studept.setAddToParent(true);
		studept.setParentComponentId("addstudenttablayout");
		addStudentTabConfigList.add(studept);

		// AddButton
		GtnUIFrameworkComponentConfig addStudentButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"addstudentbutton", true, "addstudenttablayout", GtnUIFrameworkComponentType.BUTTON);
		addStudentButtonConfig.setComponentName("Add student");

		addStudentTabConfigList.add(addStudentButtonConfig);

		addStudentTab.setTabLayoutComponentConfigList(addStudentTabConfigList);

		// AddButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkAddSudentActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkAddSudentActionConfig.addActionParameter(GtnFrameworkAddStudentAction.class.getName());

		addStudentButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkAddSudentActionConfig);

		// DeleteStudentTab

		GtnUIFrameworkTabConfig deleteStudentTab = new GtnUIFrameworkTabConfig();
		deleteStudentTab.setComponentId("deletestudenttab");
		deleteStudentTab.setTabCaption("REMOVE STUDENT");

		// DeleteTabList

		List<GtnUIFrameworkComponentConfig> deleteStudentTabConfigList = new ArrayList();

		// Deletelayout

		GtnUIFrameworkComponentConfig deleteStudentTablayout = configProvider
				.getVerticalLayoutConfig("deletestudentlayout", true, "deletestudenttab");
		deleteStudentTablayout.setComponentWidth("100%");
		deleteStudentTabConfigList.add(deleteStudentTablayout);

		// TextFieldFromAdUsing

		GtnUIFrameworkComponentConfig delstuid = configProvider.getUIFrameworkComponentConfig("delstuid", true,
				"deletestudentlayout", GtnUIFrameworkComponentType.TEXTBOX);
		delstuid.setComponentName("STUDENT ID");
		delstuid.setSpacing(true);
		deleteStudentTabConfigList.add(delstuid);

		// DeleteButton
		GtnUIFrameworkComponentConfig deleteStudentButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"deletestudentbutton", true, "deletestudentlayout", GtnUIFrameworkComponentType.BUTTON);
		deleteStudentButtonConfig.setComponentName("DELETE");

		deleteStudentTabConfigList.add(deleteStudentButtonConfig);

		deleteStudentTab.setTabLayoutComponentConfigList(deleteStudentTabConfigList);

		// DeleteButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkDeleteSudentActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkDeleteSudentActionConfig.addActionParameter(GtnFrameworkDeleteStudentAction.class.getName());
		deleteStudentButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkDeleteSudentActionConfig);

		// PrintStudenttab

		GtnUIFrameworkTabConfig printStudentTab = new GtnUIFrameworkTabConfig();
		printStudentTab.setComponentId("printstudenttab");
		printStudentTab.setTabCaption("SHOW STUDENT");

		// ListOfprintTabComponents
		List<GtnUIFrameworkComponentConfig> printStudentTabConfigList = new ArrayList();

		// PrintTablayout

		GtnUIFrameworkComponentConfig printStudentLayout = configProvider.getVerticalLayoutConfig("printstudentlayout",
				true, "printstudenttab");
		printStudentLayout.setComponentWidth("100%");
		printStudentTabConfigList.add(printStudentLayout);

		// TextBox1
		GtnUIFrameworkComponentConfig printstuid = new GtnUIFrameworkComponentConfig();
		printstuid.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		printstuid.setComponentId("printstuid");
		printstuid.setComponentName("STUDENT ID");
		printstuid.setSpacing(true);
		printstuid.setAddToParent(true);
		printstuid.setParentComponentId("printstudentlayout");
		printStudentTabConfigList.add(printstuid);

		// TextBox2
		GtnUIFrameworkComponentConfig printstuname = new GtnUIFrameworkComponentConfig();
		printstuname.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		printstuname.setComponentId("printstuname");
		printstuname.setComponentName("STUDENT NAME");
		printstuname.setSpacing(true);
		printstuname.setAddToParent(true);
		printstuname.setVisible(false);
		printstuname.setGtnTextBoxConfig(all);
		printstuname.setParentComponentId("printstudentlayout");
		printStudentTabConfigList.add(printstuname);

		// TextBox3
		GtnUIFrameworkComponentConfig printstudept = new GtnUIFrameworkComponentConfig();
		printstudept.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		printstudept.setComponentId("printstudept");
		printstudept.setComponentName("STUDENT DEPT");
		printstudept.setSpacing(true);
		printstudept.setAddToParent(true);
		printstudept.setVisible(false);
		printstudept.setGtnTextBoxConfig(all);
		printstudept.setParentComponentId("printstudentlayout");
		printStudentTabConfigList.add(printstudept);

		// Textbox4
		GtnUIFrameworkComponentConfig printbookreceived = configProvider.getUIFrameworkComponentConfig("bookreceived",
				true, "printstudentlayout", GtnUIFrameworkComponentType.TEXTBOX);
		printbookreceived.setComponentName("BOOK RECEIVED");
		printbookreceived.setVisible(false);
		printbookreceived.setGtnTextBoxConfig(all);
		printStudentTabConfigList.add(printbookreceived);

		// Textbox5
		GtnUIFrameworkComponentConfig printbookid = configProvider.getUIFrameworkComponentConfig("bookid", true,
				"printstudentlayout", GtnUIFrameworkComponentType.TEXTBOX);
		printbookid.setComponentName("BOOK ID");
		printbookid.setGtnTextBoxConfig(all);
		printbookid.setVisible(false);
		printStudentTabConfigList.add(printbookid);

		// Textbox6
		GtnUIFrameworkComponentConfig printbookname = configProvider.getUIFrameworkComponentConfig("bookname", true,
				"printstudentlayout", GtnUIFrameworkComponentType.TEXTBOX);
		printbookname.setComponentName("BOOK NAME");
		printbookname.setGtnTextBoxConfig(all);
		printbookname.setVisible(false);
		printStudentTabConfigList.add(printbookname);

		// PrintButton
		GtnUIFrameworkComponentConfig printStudentButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"printstudentbutton", true, "printstudentlayout", GtnUIFrameworkComponentType.BUTTON);
		printStudentButtonConfig.setComponentName("SHOW");

		printStudentTabConfigList.add(printStudentButtonConfig);

		printStudentTab.setTabLayoutComponentConfigList(printStudentTabConfigList);

		// PrintButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkPrintSudentActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkPrintSudentActionConfig.addActionParameter(GtnFrameworkPrintStudentAction.class.getName());
		printStudentButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkPrintSudentActionConfig);

		// AddBook

		// AddStudentTab

		GtnUIFrameworkTabConfig addBookTab = configProvider.getTabConfig("addbooktab", "ADD BOOK");

		// AddTabkaList

		List<GtnUIFrameworkComponentConfig> addBookTabConfigList = new ArrayList();

		// AddtabLayout

		GtnUIFrameworkComponentConfig addBookTablayout = configProvider.getVerticalLayoutConfig("addbooktablayout",
				true, "addbooktab");
		addBookTablayout.setComponentWidth("100%");
		addBookTabConfigList.add(addBookTablayout);

		// TextBox1
		GtnUIFrameworkComponentConfig bookid = new GtnUIFrameworkComponentConfig();
		bookid.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		bookid.setComponentId("addbookid");
		bookid.setComponentName("BOOK ID");
		bookid.setSpacing(true);
		bookid.setAddToParent(true);
		bookid.setParentComponentId("addbooktablayout");
		addBookTabConfigList.add(bookid);

		// TextBox2
		GtnUIFrameworkComponentConfig bookname = new GtnUIFrameworkComponentConfig();
		bookname.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		bookname.setComponentId("addbookname");
		bookname.setComponentName("BOOK NAME");
		bookname.setSpacing(true);
		bookname.setAddToParent(true);
		bookname.setParentComponentId("addbooktablayout");
		addBookTabConfigList.add(bookname);

		// TextBox3
		GtnUIFrameworkComponentConfig bookdept = new GtnUIFrameworkComponentConfig();
		bookdept.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		bookdept.setComponentId("addbookdept");
		bookdept.setComponentName("BOOK DEPT");
		bookdept.setSpacing(true);
		bookdept.setAddToParent(true);
		bookdept.setParentComponentId("addbooktablayout");
		addBookTabConfigList.add(bookdept);

		// AddBookButton
		GtnUIFrameworkComponentConfig addBookButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"addbookbutton", true, "addbooktablayout", GtnUIFrameworkComponentType.BUTTON);
		addBookButtonConfig.setComponentName("Add Book");

		addBookTabConfigList.add(addBookButtonConfig);

		addBookTab.setTabLayoutComponentConfigList(addBookTabConfigList);

		// AddButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkAddBookActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkAddBookActionConfig.addActionParameter(GtnFrameworkAddBookAction.class.getName());

		addBookButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkAddBookActionConfig);

		// DeleteBook
		GtnUIFrameworkTabConfig deleteBookTab = new GtnUIFrameworkTabConfig();
		deleteBookTab.setComponentId("deletebooktab");
		deleteBookTab.setTabCaption("REMOVE BOOK");

		// DeleteTabList

		List<GtnUIFrameworkComponentConfig> deleteBookTabConfigList = new ArrayList();

		// Deletelayout

		GtnUIFrameworkComponentConfig deleteBookTablayout = configProvider.getVerticalLayoutConfig("deletebooklayout",
				true, "deletebooktab");
		deleteBookTablayout.setComponentWidth("100%");
		deleteBookTabConfigList.add(deleteBookTablayout);

		// TextFieldFromAdUsing

		GtnUIFrameworkComponentConfig delbookid = configProvider.getUIFrameworkComponentConfig("delbookid", true,
				"deletebooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		delbookid.setComponentName("BOOK ID");
		delbookid.setSpacing(true);
		deleteBookTabConfigList.add(delbookid);

		// DeleteButton
		GtnUIFrameworkComponentConfig deleteBookButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"deletebookbutton", true, "deletebooklayout", GtnUIFrameworkComponentType.BUTTON);
		deleteBookButtonConfig.setComponentName("REMOVE");

		deleteBookTabConfigList.add(deleteBookButtonConfig);

		deleteBookTab.setTabLayoutComponentConfigList(deleteBookTabConfigList);

		// DeleteButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkDeleteBookActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkDeleteBookActionConfig.addActionParameter(GtnFrameworkDeleteBookAction.class.getName());
		deleteBookButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkDeleteBookActionConfig);

		//CHECKBOOKTAB

		GtnUIFrameworkTabConfig printBookTab = configProvider.getTabConfig("printbooktab", "CHECK BOOK");

		// ListOfprintTabComponents
		List<GtnUIFrameworkComponentConfig> printBookTabConfigList = new ArrayList();

		// PrintTablayout

		GtnUIFrameworkComponentConfig printBookLayout = configProvider.getVerticalLayoutConfig("printbooklayout", true,
				"printbooktab");
		printBookLayout.setComponentWidth("100%");
		printBookTabConfigList.add(printBookLayout);

		// Textbox1
		GtnUIFrameworkComponentConfig checkbookid = configProvider.getUIFrameworkComponentConfig("checkbookid", true,
				"printbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		checkbookid.setComponentName("BOOK ID");
		printBookTabConfigList.add(checkbookid);

		// Textbox2
		GtnUIFrameworkComponentConfig checkbookname = configProvider.getUIFrameworkComponentConfig("checkbookname",
				true, "printbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		checkbookname.setComponentName("BOOK NAME");
		checkbookname.setVisible(false);
		checkbookname.setGtnTextBoxConfig(all);
		printBookTabConfigList.add(checkbookname);

		// Textbox3
		GtnUIFrameworkComponentConfig checkbookdept = configProvider.getUIFrameworkComponentConfig("checkbookdept",
				true, "printbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		checkbookdept.setComponentName("BOOK DEPT");
		checkbookdept.setGtnTextBoxConfig(all);
		checkbookdept.setVisible(false);
		printBookTabConfigList.add(checkbookdept);

		// Textbox4
		GtnUIFrameworkComponentConfig checkbookavi = configProvider.getUIFrameworkComponentConfig("checkbookavi",
				true, "printbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		checkbookavi.setComponentName("AVAILABLITY");
		checkbookavi.setGtnTextBoxConfig(all);
		checkbookavi.setVisible(false);
		printBookTabConfigList.add(checkbookavi);

		// Textbox5
		GtnUIFrameworkComponentConfig checkbookstuname = configProvider.getUIFrameworkComponentConfig("checkbookstuname",
				true, "printbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		checkbookstuname.setComponentName("STUDENT NAME");
		checkbookstuname.setGtnTextBoxConfig(all);
		checkbookstuname.setVisible(false);
		printBookTabConfigList.add(checkbookstuname);

		// Textbox6
		GtnUIFrameworkComponentConfig checkbookstuid = configProvider.getUIFrameworkComponentConfig("checkbookstuid",
				true, "printbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		checkbookstuid.setComponentName("STUDENT ID");
		checkbookstuid.setGtnTextBoxConfig(all);
		checkbookstuid.setVisible(false);
		printBookTabConfigList.add(checkbookstuid);
		
		
		//CheckButton
		GtnUIFrameworkComponentConfig printBookButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"printbookbutton", true, "printbooklayout", GtnUIFrameworkComponentType.BUTTON);
		printBookButtonConfig.setComponentName("CHECK");

		printBookTabConfigList.add(printBookButtonConfig);

		printBookTab.setTabLayoutComponentConfigList(printBookTabConfigList);

		// ButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkPrintBookActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkPrintBookActionConfig.addActionParameter(GtnFrameworkPrintBookAction.class.getName());
		printBookButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkPrintBookActionConfig);

		//LENDBOOKTAB

		GtnUIFrameworkTabConfig lendBookTab = configProvider.getTabConfig("lendbooktab", "ISSUE BOOK");

		// ListOfprintTabComponents
		List<GtnUIFrameworkComponentConfig> lendBookTabConfigList = new ArrayList();

		//lendTablayout

		GtnUIFrameworkComponentConfig lendBookLayout = configProvider.getVerticalLayoutConfig("lendbooklayout", true,
				"lendbooktab");
		lendBookLayout.setComponentWidth("100%");
		lendBookTabConfigList.add(lendBookLayout);

		// Textbox1
		GtnUIFrameworkComponentConfig lendbookid = configProvider.getUIFrameworkComponentConfig("lendbookid", true,
				"lendbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		lendbookid.setComponentName("BOOK ID");
		lendBookTabConfigList.add(lendbookid);


		// Textbox6
		GtnUIFrameworkComponentConfig lendbookstuid = configProvider.getUIFrameworkComponentConfig("lendbookstuid",
				true, "lendbooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		lendbookstuid.setComponentName("STUDENT ID");
		lendBookTabConfigList.add(lendbookstuid);
		

		//lendButton
		GtnUIFrameworkComponentConfig lendBookButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"lendbookbutton", true, "lendbooklayout", GtnUIFrameworkComponentType.BUTTON);
		lendBookButtonConfig.setComponentName("ISSUE");

		lendBookTabConfigList.add(lendBookButtonConfig);

		lendBookTab.setTabLayoutComponentConfigList(lendBookTabConfigList);

		// ButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkLendBookActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkLendBookActionConfig.addActionParameter(GtnFrameworkLendBookAction.class.getName());
		lendBookButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkLendBookActionConfig);
		
		//LENDBOOKTAB

		GtnUIFrameworkTabConfig receiveBookTab = configProvider.getTabConfig("receivebooktab", "RECEIVE BOOK");

		// ListOfprintTabComponents
		List<GtnUIFrameworkComponentConfig> receiveBookTabConfigList = new ArrayList();

		//lendTablayout

		GtnUIFrameworkComponentConfig receiveBookLayout = configProvider.getVerticalLayoutConfig("receivebooklayout", true,
				"receivebooktab");
		receiveBookLayout.setComponentWidth("100%");
		receiveBookTabConfigList.add(receiveBookLayout);

		// Textbox1
		GtnUIFrameworkComponentConfig receivebookid = configProvider.getUIFrameworkComponentConfig("receivebookid", true,
				"receivebooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		receivebookid.setComponentName("BOOK ID");
		receiveBookTabConfigList.add(receivebookid);


		// Textbox6
		GtnUIFrameworkComponentConfig receivebookstuid = configProvider.getUIFrameworkComponentConfig("receivebookstuid",
				true, "receivebooklayout", GtnUIFrameworkComponentType.TEXTBOX);
		receivebookstuid.setComponentName("STUDENT ID");
		receiveBookTabConfigList.add(receivebookstuid);
		

		//RECEIVEButton
		GtnUIFrameworkComponentConfig receiveBookButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"receivebookbutton", true, "receivebooklayout", GtnUIFrameworkComponentType.BUTTON);
		receiveBookButtonConfig.setComponentName("RECEIVE BOOK");

		receiveBookTabConfigList.add(receiveBookButtonConfig);

		receiveBookTab.setTabLayoutComponentConfigList(receiveBookTabConfigList);

		// ButtonAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkReceiveBookActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkReceiveBookActionConfig.addActionParameter(GtnFrameworkReceiveBookAction.class.getName());
		receiveBookButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkReceiveBookActionConfig);
		
		
		//LIST OF TABS
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(addStudentTab);
		tabConfigList.add(deleteStudentTab);
		tabConfigList.add(printStudentTab);
		tabConfigList.add(addBookTab);
		tabConfigList.add(deleteBookTab);
		tabConfigList.add(printBookTab);
		tabConfigList.add(lendBookTab);
		tabConfigList.add(receiveBookTab);
		

		libraryTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		libraryTabSheetView.setGtnComponentList(libraryTabSheetcomponentList);

		return libraryTabSheetView;
	}

}
