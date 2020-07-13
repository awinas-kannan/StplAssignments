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
public class DeleteController {

	public static final String STR="result";
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
	int id= Integer.parseInt(request.getParameter("deleteid"));
	
	StudentDao dao =new StudentDao();
	ModelAndView dmv=new ModelAndView();
	dmv.setViewName("studentaccess.jsp");	
	String deleteresult;
	String result ;
	dmv.setViewName("resultdisplay.jsp");
	try {
		deleteresult = dao.deleteStudentDetail(id);
		if("REMOVED".equals(deleteresult))
		{
			result ="STUDENT DELETE SUCCESSFUL";
			 dmv.addObject(STR, result);
		}
		else
		{

			result ="INVALID ID";
			 dmv.addObject(STR, result);
		}

	} 
	catch (SQLException | IOException e)
	{
		result ="INVALID ID";
		 dmv.addObject(STR, result);
		Logger.getLogger(DeleteController.class.getName()).log(Level.INFO, null, e);
	}	
	
	return dmv;
	}
}
