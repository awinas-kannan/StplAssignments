package com.lms.awinas.dynamicclass;

import com.lms.awinas.action.GtnFrameworkAddBookAction;
import com.lms.awinas.action.GtnFrameworkAddStudentAction;
import com.lms.awinas.action.GtnFrameworkAdminLoginAction;
import com.lms.awinas.action.GtnFrameworkDeleteBookAction;
import com.lms.awinas.action.GtnFrameworkDeleteStudentAction;
import com.lms.awinas.action.GtnFrameworkLendBookAction;
import com.lms.awinas.action.GtnFrameworkPrintBookAction;
import com.lms.awinas.action.GtnFrameworkPrintStudentAction;
import com.lms.awinas.action.GtnFrameworkReceiveBookAction;
import com.lms.awinas.action.GtnFrameworkStudentLoginAction;
import com.lms.awinas.action.GtnFrameworkViewBookAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkLmsDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAdminLoginAction.class.getName(),new GtnFrameworkAdminLoginAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkStudentLoginAction.class.getName(),
				new GtnFrameworkStudentLoginAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddStudentAction.class.getName(),
				new GtnFrameworkAddStudentAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeleteStudentAction.class.getName(),
				new GtnFrameworkDeleteStudentAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPrintStudentAction.class.getName(),
				new GtnFrameworkPrintStudentAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddBookAction.class.getName(),
				new GtnFrameworkAddBookAction());
		
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeleteBookAction.class.getName(),
				new GtnFrameworkDeleteBookAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPrintBookAction.class.getName(),
				new GtnFrameworkPrintBookAction());
		

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkLendBookAction.class.getName(),
				new GtnFrameworkLendBookAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReceiveBookAction.class.getName(),
				new GtnFrameworkReceiveBookAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkViewBookAction.class.getName(),
				new GtnFrameworkViewBookAction());

	}

}
