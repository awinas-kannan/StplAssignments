package com.project.awinas;

import org.springframework.web.client.RestTemplate;

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

	private static final String URL_LOGIN="http://localhost:8092/hibernateservice/Login";
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
    			
    			
    			RestTemplate resttemplate = new  RestTemplate();
                String status = resttemplate.postForObject(URL_LOGIN, lm, String.class);
                Notification.show(status);
                check(status);
				
			}
		});
        		
        addComponents(name,password,button);
        
        
	}
	void check(String res)
	{
		if("SUCCESSFUL".equals(res))
		{
		 Notification.show("WELCOME ");
		 getUI().getNavigator().navigateTo("STUDENTVIEW");
		}
		else
		{
			 Notification.show("INCORRCT USER ID OR PASSWORD").setIcon(VaadinIcons.FLASH);
			getUI().getNavigator().navigateTo("LOGINVIEW");
		}
	}
}
