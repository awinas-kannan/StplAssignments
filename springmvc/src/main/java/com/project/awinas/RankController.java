package com.project.awinas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RankController {


	@RequestMapping("/rank")
	public ModelAndView display(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
	
	int rank= Integer.parseInt(request.getParameter("rankvalue"));
	PrintWriter out = response.getWriter();
	StudentDao dao =new StudentDao();
	List<StudentModel> rankresult;
	ModelAndView rmv=new ModelAndView();
	try {
		rankresult = dao.getStudentRank(rank);
		rmv.setViewName("display.jsp");
		
		rmv.addObject("result", rankresult);
		
		
	}
	catch (SQLException e) {
		out.print("<script type=\"text/javascript\">");
	    out.print("alert(\"INVALID RANK\");");
	    out.print("</script>");
	    rmv.setViewName("studentaccess.jsp");
	    Logger.getLogger(RankController.class.getName()).log(Level.INFO, null, e);

	   
	}
	return rmv;
	}
}
