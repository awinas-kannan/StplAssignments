package com.project.awinas;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateController {

	@RequestMapping("/update")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) throws IOException  
	{
	StudentDao dao =new StudentDao();

	StudentModel asm=new StudentModel();
	asm.setId(Integer.parseInt(request.getParameter("stuid")));
	asm.setName(request.getParameter("stuname"));
	asm.setMark1(Integer.parseInt(request.getParameter("stumark1")));
	asm.setMark2(Integer.parseInt(request.getParameter("stumark2")));
	asm.setMark3(Integer.parseInt(request.getParameter("stumark3")));
	asm.setTotal(asm.getMark1()+asm.getMark2()+asm.getMark3());
	ModelAndView amv=new ModelAndView();
	String result ;
	amv.setViewName("resultdisplay.jsp");
	dao.updateStudentDetail(asm);
 
	result ="STUDENT UPDATE SUCCESSFUL";
	 amv.addObject("result", result);
	
	return amv;
	}

}
