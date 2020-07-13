package com.phonebook.awinas.dynamicclass;



import com.phonebook.awinas.action.GtnFrameworkAddContactAction;
import com.phonebook.awinas.action.GtnFrameworkDeleteContactAction;
import com.phonebook.awinas.action.GtnFrameworkEditContactAction;
import com.phonebook.awinas.action.GtnFrameworkUpdateContactAction;
import com.phonebook.awinas.action.GtnFrameworkUserLoginAction;
import com.phonebook.awinas.action.GtnFrameworkUserSignUpAction;
import com.phonebook.awinas.action.GtnFrameworkViewContactAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkPhoneBookDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUserLoginAction.class.getName(),
				new GtnFrameworkUserLoginAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUserSignUpAction.class.getName(),
				new GtnFrameworkUserSignUpAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeleteContactAction.class.getName(),
				new GtnFrameworkDeleteContactAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddContactAction.class.getName(),
				new GtnFrameworkAddContactAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkViewContactAction.class.getName(),
				new GtnFrameworkViewContactAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkEditContactAction.class.getName(),
				new GtnFrameworkEditContactAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkUpdateContactAction.class.getName(),
				new GtnFrameworkUpdateContactAction());

	}

}
