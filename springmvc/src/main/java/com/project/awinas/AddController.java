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
public class AddController {

	@RequestMapping("/add")
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
	String result =" ";
	amv.setViewName("resultdisplay.jsp");
	try {
		dao.addStudentDetail(asm);
	 
		result ="STUDENT ADDED SUCCESSFUL";
		 amv.addObject("result", result);	

	}
	catch (SQLException | IOException e) {
		
		result ="ID ALREADY PRESENT USE ALTERNATE ID";
		amv.addObject("result", result);
	    Logger.getLogger(AddController.class.getName()).log(Level.INFO, null, e);
	}
	
	return amv;
	}
}
