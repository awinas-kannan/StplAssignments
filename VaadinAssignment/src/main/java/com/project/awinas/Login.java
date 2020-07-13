package com.project.awinas;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class Login extends VerticalLayout implements View {

private static final long serialVersionUID = 1L;
	
	public Login()
	{
		final TextField name = new TextField("USER ID");
        name.setIcon(VaadinIcons.USER);
        name.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        final PasswordField password = new PasswordField("PASSWORD");
        password.setIcon(VaadinIcons.PASSWORD);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        final Button button = new Button("LOGIN");
        button.setIcon(VaadinIcons.USER_CHECK);
        button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
        
        
        button.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				LoginModel lm=new LoginModel();
    			lm.setUserid(Integer.parseInt(name.getValue()));
    			lm.setPwd(password.getValue());
    			LoginDao ldao=new LoginDao();
    			String res=null;
				try {
					res = ldao.getCredentialResult(lm);
					check(res);
				} 
				catch (ClassNotFoundException | IOException | SQLException e1)
				{
					 Notification.show("INCORRCT USER ID OR PASSWORD").setIcon(VaadinIcons.FLASH);
						getUI().getNavigator().navigateTo("LOGINVIEW");
						Logger.getLogger(Login.class.getName()).log(Level.INFO, null, e1);
				}
			
				
			}
		});
        		
        addComponents(name,password,button);
        
        
	}
	void check(String res)
	{
		if("SUCCESSFUL".equals(res))
		{
		 Notification.show("WELCOME - " +"!");
		 getUI().getNavigator().navigateTo("STUDENTVIEW");
		}
		else
		{
			 Notification.show("INCORRCT USER ID OR PASSWORD").setIcon(VaadinIcons.FLASH);
			getUI().getNavigator().navigateTo("LOGINVIEW");
		}
	}
}
