package com.project.awinas;

import java.io.IOException;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RankController {

	@RequestMapping("/rank")
	public ModelAndView display(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
	
	int rank= Integer.parseInt(request.getParameter("rankvalue"));
	
	StudentDao dao =new StudentDao();
	List<StudentModel> rankresult;
	ModelAndView rmv=new ModelAndView();
	rankresult = dao.getStudentRank(rank);
	if(rankresult.isEmpty())
	{
		String re="INVALID RANK";
		rmv.setViewName("resultdisplay.jsp");
		rmv.addObject("result", re);
	}
	else
	{
		rmv.setViewName("display.jsp");
		
		rmv.addObject("result", rankresult);
		
		
	}
	return rmv;
	}

}
