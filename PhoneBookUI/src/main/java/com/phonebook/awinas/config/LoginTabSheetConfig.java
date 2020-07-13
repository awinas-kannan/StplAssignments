package com.phonebook.awinas.config;

import java.util.ArrayList;
import java.util.List;

import com.phonebook.awinas.action.GtnFrameworkUserLoginAction;
import com.phonebook.awinas.action.GtnFrameworkUserSignUpAction;
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
	private String userTabLayoutId="usertablayout";
	public GtnUIFrameworkViewConfig loginView() {

		GtnUIFrameworkTextBoxConfig tb = new GtnUIFrameworkTextBoxConfig();

		tb.setPasswordField(true);
		// loginview

		GtnUIFrameworkViewConfig loginTabView = configProvider.getViewConfig("LOGIN VIEW", "login", true);

		// ListOfCompoentsInLoginTab
		List<GtnUIFrameworkComponentConfig> loginTabcomponentList = new ArrayList<>();

		// LoginPanel

		GtnUIFrameworkComponentConfig loginPanel = configProvider.getPanelConfig("loginpanel", false, null);
		loginPanel.setComponentName("LOGIN PANEL");
		loginPanel.setComponentWidth("100%");
		loginTabcomponentList.add(loginPanel);

		// LoginLayout

		GtnUIFrameworkComponentConfig loginLayoutConfig = configProvider.getVerticalLayoutConfig("loginlayout", true,
				"loginpanel");
		loginLayoutConfig.setComponentWidth("100%");
		loginTabcomponentList.add(loginLayoutConfig);

		// CreatingTabSheetForAddingBothStudentLoginAndAdminLogin

		GtnUIFrameworkComponentConfig loginTabSheetConfig = configProvider.getUIFrameworkComponentConfig("logintabshet",
				true, "loginlayout", GtnUIFrameworkComponentType.TABSHEET);
		loginTabSheetConfig.setComponentName("Login TabSheet");
		loginTabSheetConfig.setComponentWidth("100%");
		loginTabcomponentList.add(loginTabSheetConfig);

		// UserLoginTAb

		GtnUIFrameworkTabConfig userTabConfig = configProvider.getTabConfig("usertab", "Login/Register");

		List<GtnUIFrameworkComponentConfig> userTabList = new ArrayList();

		GtnUIFrameworkComponentConfig userTablayout = configProvider.getVerticalLayoutConfig(userTabLayoutId, true,
				"usertab");
		userTablayout.setComponentWidth("100%");
		userTabList.add(userTablayout);

		// TextBox1
		GtnUIFrameworkComponentConfig userid = configProvider.getUIFrameworkComponentConfig("userid", true,
				userTabLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		userid.setComponentName("USER ID");
		userTabList.add(userid);

		// TextBox2
		GtnUIFrameworkComponentConfig userpwd = configProvider.getUIFrameworkComponentConfig("userpwd", true,
				userTabLayoutId, GtnUIFrameworkComponentType.TEXTBOX);
		userpwd.setComponentName("PASSWORD");
		userpwd.setGtnTextBoxConfig(tb);
         
		userTabList.add(userpwd);

		// LoginButton

		GtnUIFrameworkComponentConfig userLoginButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"userloginbutton", true, userTabLayoutId, GtnUIFrameworkComponentType.BUTTON);
		userLoginButtonConfig.setComponentName("LOGIN");

		userTabList.add(userLoginButtonConfig);

		userTabConfig.setTabLayoutComponentConfigList(userTabList);

		// userLoginAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkUserLoginActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkUserLoginActionConfig.addActionParameter(GtnFrameworkUserLoginAction.class.getName());
		userLoginButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkUserLoginActionConfig);

		// signupButton

		GtnUIFrameworkComponentConfig userSignButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"signupbutton", true, userTabLayoutId, GtnUIFrameworkComponentType.BUTTON);
		userSignButtonConfig.setComponentName("SIGN UP");

		userTabList.add(userSignButtonConfig);

		userTabConfig.setTabLayoutComponentConfigList(userTabList);

		// signupAction
		GtnUIFrameWorkActionConfig gtnUIFrameWorkSignUpActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkSignUpActionConfig.addActionParameter(GtnFrameworkUserSignUpAction.class.getName());
		userSignButtonConfig.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkSignUpActionConfig);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();

		tabConfigList.add(userTabConfig);

		loginTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		loginTabView.setGtnComponentList(loginTabcomponentList);

		return loginTabView;
	}
}
