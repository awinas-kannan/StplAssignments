package com.lms.awinas.init;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.lms.awinas.config.GtnUIFrameworkLmsMainConfig;
import com.lms.awinas.dynamicclass.GtnUIFrameworkLmsDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;


@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=GTN-BUILDINGBLOCKS",
        "javax.portlet.name=GTN LMS",
        "javax.portlet.display-name=GTN LMS",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class lmsUiInit extends UI{

	@Override
	protected void init(VaadinRequest request) {
		addStyleName("bootstrap");
		addStyleName("bootstrap-bb");
		Navigator navigator = new Navigator(this, this);
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkLmsMainConfig().getLmsRootConfig();
		GtnUIFrameworkEngine frameworkEngine = new GtnUIFrameworkEngine();
		frameworkEngine.buildVaadinScreen(rootConfig, navigator, request, this, "GTN LMS",
				new GtnUIFrameworkLmsDynamicClassFiller());
		
	}

}
