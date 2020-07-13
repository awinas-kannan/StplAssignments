package com.project.awinas;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class Indexview extends VerticalLayout implements View {
private static final long serialVersionUID = 1L;
	
	public Indexview()
	{
		Label lbl1=new Label("WELCOME TO STUDENT DATABASE" );
		Label lbl2=new Label("LOGIN TO ACCESS THE DATABASE");
		
		Button button = new Button("Login");
	        /**
			 * 
			 */
		button.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("LOGINVIEW");
				
			}
		});
		
	            
	    addComponents(lbl1,lbl2,button);
	    setComponentAlignment(lbl1,Alignment.TOP_CENTER);
	    setComponentAlignment(lbl2,Alignment.TOP_CENTER);
	    setComponentAlignment(button,Alignment.MIDDLE_CENTER);
	}

}
