package com.project.awinas;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class LoginController {

	@RequestMapping("/Login")
	public ModelAndView login(HttpServletRequest req,HttpServletResponse resp) 
	{
		LoginModel lm =new LoginModel();	
		
		lm.setUserid(Integer.parseInt(req.getParameter("userid")));
		lm.setPwd(req.getParameter("pwd"));	
	
		LoginDao dao =new LoginDao();
		
		ModelAndView lmv=new ModelAndView();
		String loginresult;
		try {
			loginresult = dao.getCredentialResult(lm);
			if("SUCCESSFUL".equals(loginresult))
			{
				lmv.setViewName("studentaccess.jsp");
				
			}
			else
			{
				lmv.setViewName("index.jsp");
			}
			
			
		} 
		catch (SQLException | IOException e) {
			lmv.setViewName("index.jsp");
		    Logger.getLogger(LoginController.class.getName()).log(Level.INFO, null, e);
		}
		
		return lmv;	
	}
}
