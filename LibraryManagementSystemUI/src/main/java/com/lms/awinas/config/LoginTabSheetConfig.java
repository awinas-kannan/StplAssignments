package com.lms.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.lms.awinas.action.GtnFrameworkAdminLoginAction;
import com.lms.awinas.action.GtnFrameworkStudentLoginAction;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class LoginTabSheetConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	
	public GtnUIFrameworkViewConfig LoginView() {

		//loginview
		
		GtnUIFrameworkViewConfig LoginTabView = configProvider.getViewConfig("LOGIN VIEW", "login", true);
		
		//ListOfCompoentsInLoginTab
		List<GtnUIFrameworkComponentConfig> LoginTabcomponentList = new ArrayList<>();
		
		//LoginPanel
		
		GtnUIFrameworkComponentConfig LoginPanel = configProvider.getPanelConfig("loginpanel", false, null);
		LoginPanel.setComponentName("LOGIN PANEL");
		LoginPanel.setComponentWidth("100%");
		LoginTabcomponentList.add(LoginPanel);
		
		
		//LoginLayout
		
		GtnUIFrameworkComponentConfig LoginLayoutConfig = configProvider.getVerticalLayoutConfig("loginlayout", true, "loginpanel");
		LoginLayoutConfig.setComponentWidth("100%");
		LoginTabcomponentList.add(LoginLayoutConfig);
		
		//CreatingTabSheetForAddingBothStudentLoginAndAdminLogin
		
		GtnUIFrameworkComponentConfig LoginTabSheetConfig =configProvider.getUIFrameworkComponentConfig("logintabshet", true, "loginlayout", GtnUIFrameworkComponentType.TABSHEET);
		LoginTabSheetConfig.setComponentName("Login TabSheet");
		LoginTabSheetConfig.setComponentWidth("100%");
		LoginTabcomponentList.add(LoginTabSheetConfig);
		
		//Adminlogintab
		GtnUIFrameworkTabConfig AdminTabConfig =configProvider.getTabConfig("admintab", "Admin Login");
		
		//AddtabList
		
		List<GtnUIFrameworkComponentConfig> adminTabList=new ArrayList();
		
		//AdminTabComponents
		//AdminTabLayout
		
		GtnUIFrameworkComponentConfig AdminTablayout = configProvider.getVerticalLayoutConfig("admintablayout", true,
				"admintab");
		AdminTablayout.setComponentWidth("100%");
		adminTabList.add(AdminTablayout);

		//TextBox1
		GtnUIFrameworkComponentConfig uid = new GtnUIFrameworkComponentConfig();
		uid.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		uid.setComponentId("uid");
		uid.setComponentName("USER ID");
		uid.setSpacing(true);
		uid.setAddToParent(true);
		
		uid.setParentComponentId("admintablayout");

		adminTabList.add(uid);
		
		//TextBox2		
		GtnUIFrameworkComponentConfig pwd = new GtnUIFrameworkComponentConfig();
		pwd.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		pwd.setComponentId("pwd");
		pwd.setComponentName("PASSWORD");
		pwd.setSpacing(true);
		pwd.setAddToParent(true);
		pwd.setParentComponentId("admintablayout");
		
		GtnUIFrameworkTextBoxConfig tb=new GtnUIFrameworkTextBoxConfig();

		tb.setPasswordField(true);
		pwd.setGtnTextBoxConfig(tb);
		
		adminTabList.add(pwd);
		
		
		//AdminButton
		

		GtnUIFrameworkComponentConfig adminLoginButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"adminloginbutton", true, "admintablayout",
				GtnUIFrameworkComponentType.BUTTON);
		adminLoginButtonConfig.setComponentName("LOGIN");
	
		adminTabList.add(adminLoginButtonConfig);

		AdminTabConfig.setTabLayoutComponentConfigList(adminTabList);
		//AdminLoginAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkAdminLoginActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkAdminLoginActionConfig.addActionParameter(GtnFrameworkAdminLoginAction.class.getName());
		adminLoginButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkAdminLoginActionConfig);	
		
		
		//StudentLoginTAb

		//Adminlogintab
		GtnUIFrameworkTabConfig studentTabConfig =configProvider.getTabConfig("stundenttab", "Student Login");
		
		//AddtabList
		
		List<GtnUIFrameworkComponentConfig> studentTabList=new ArrayList();
		
		//AdminTabComponents
		//AdminTabLayout
		
		GtnUIFrameworkComponentConfig studentTablayout = configProvider.getVerticalLayoutConfig("studenttablayout", true,
				"studenttab");
		studentTablayout.setComponentWidth("100%");
		studentTabList.add(studentTablayout);

		//TextBox1
		GtnUIFrameworkComponentConfig stuid = configProvider.getUIFrameworkComponentConfig("stuid", true, "studenttablayout", GtnUIFrameworkComponentType.TEXTBOX);
		stuid.setComponentName("USER ID");
		studentTabList.add(stuid);
		
		//TextBox2		
		GtnUIFrameworkComponentConfig stupwd = new GtnUIFrameworkComponentConfig();
		stupwd.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		stupwd.setComponentId("stupwd");
		stupwd.setComponentName("PASSWORD");
		stupwd.setSpacing(true);
		stupwd.setAddToParent(true);
		stupwd.setParentComponentId("studenttablayout");
		

		stupwd.setGtnTextBoxConfig(tb);
		
		studentTabList.add(stupwd);
		
		
		//StudentLoginButton
		

		GtnUIFrameworkComponentConfig studentLoginButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"studentloginbutton", true, "studenttablayout",
				GtnUIFrameworkComponentType.BUTTON);
		studentLoginButtonConfig.setComponentName("LOGIN");
	
		studentTabList.add(studentLoginButtonConfig);

		studentTabConfig.setTabLayoutComponentConfigList(studentTabList);
		
		//StudentLoginAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkStudentLoginActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkStudentLoginActionConfig.addActionParameter(GtnFrameworkStudentLoginAction.class.getName());
		studentLoginButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkStudentLoginActionConfig);	
		
		
		 List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		 tabConfigList.add(AdminTabConfig);
	     tabConfigList.add(studentTabConfig);
	     
	     LoginTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);
	     
	     LoginTabView.setGtnComponentList(LoginTabcomponentList);
		 
		 
		return LoginTabView;
	}

}
