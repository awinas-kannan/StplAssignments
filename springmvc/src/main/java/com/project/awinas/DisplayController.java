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
public class DisplayController {

	
	@RequestMapping("/display")
	public ModelAndView display(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
	int id= Integer.parseInt(request.getParameter("dispid"));
	
	StudentDao dao =new StudentDao();
	
	ModelAndView dimv=new ModelAndView();

	try {
		
		StudentModel displayresult = dao.getStudentDetail(id);
		
		
		dimv.setViewName("displayid.jsp");
		
		dimv.addObject("result", displayresult);
		
		
		
	} 
	catch (SQLException e) {
		
		dimv.addObject("result", "Invalid id");
	    dimv.setViewName("resultdisplay.jsp");
	    Logger.getLogger(DisplayController.class.getName()).log(Level.INFO, null, e);
	}
	
	return dimv;
	}
}
