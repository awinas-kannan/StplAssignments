package com.project.awinas;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteController {
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response)  
	{
	int id= Integer.parseInt(request.getParameter("deleteid"));
	
	StudentDao dao =new StudentDao();
	ModelAndView dmv=new ModelAndView();
	dmv.setViewName("studentaccess.jsp");	
	String deleteresult;
	String result ;
	dmv.setViewName("resultdisplay.jsp");
	deleteresult = dao.deleteStudentDetail(id);
	if("REMOVED".equals(deleteresult))
	{
		result ="STUDENT DELETE SUCCESSFUL";
		 dmv.addObject("result", result);
	}
	else
	{

		result ="INVALID ID";
		 dmv.addObject("result", result);
	}	
	
	return dmv;
	}

}
