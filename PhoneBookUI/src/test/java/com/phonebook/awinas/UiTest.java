package com.phonebook.awinas;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;



import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.phonebook.awinas.config.GtnUIFrameworkPhoneBookMainConfig;
import com.phonebook.awinas.dynamicclass.GtnUIFrameworkPhoneBookDynamicClassFiller;
import com.phonebook.awinas.init.PhonebookUiInit;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.vaadin.server.VaadinRequest;




@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class UiTest extends PhonebookUiInit {

	@Autowired
	VaadinRequest VaadinRequest ;
	


	@Test
	public void uiTest()
	{
		
		 assertTrue(new GtnUIFrameworkPhoneBookMainConfig().getPhoneBookRootConfig()!=null);

      
	}
	
	@Test
	public void dynamicClassFillerTest()
	{
		 System.out.println("Dynamic class filler");
		 GtnUIFrameworkPhoneBookDynamicClassFiller instance = new GtnUIFrameworkPhoneBookDynamicClassFiller();
		 
		 PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		 instance.addDynamicObject();
		 verifyStatic(times(7));
		
	}
	
	
	//@Ignore
	@Test
	public void initTest () 

	{
		 
	 /*PhonebookUiInit instance1=  new  PhonebookUiInit();
		 UI z = new   PhonebookUiInit();
		java.lang.reflect.Method[] m = z.getClass().getDeclaredMethods();
	System.out.println(m[0]);
	m[0].setAccessible(true);*/
	//m[0].invoke(z, null);
	
	init(VaadinRequest);
	}

	
	

	
		
	
}
