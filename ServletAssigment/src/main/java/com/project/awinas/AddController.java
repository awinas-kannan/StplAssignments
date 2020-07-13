package com.project.awinas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.project.awinas.StudentDao;
import com.project.awinas.model.StudentModel;

/**
 * Servlet implementation class AddController
 */

public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		StudentDao dao =new StudentDao();
		PrintWriter out = response.getWriter();
		StudentModel asm=new StudentModel();
		asm.setId(Integer.parseInt(request.getParameter("stuid")));
		asm.setName(request.getParameter("stuname"));
		asm.setMark1(Integer.parseInt(request.getParameter("stumark1")));
		asm.setMark2(Integer.parseInt(request.getParameter("stumark2")));
		asm.setMark3(Integer.parseInt(request.getParameter("stumark3")));
		asm.setTotal(asm.getMark1()+asm.getMark2()+asm.getMark3());
		
		
		try {
			dao.addStudentDetail(asm);
			
			out.print("<script type=\"text/javascript\">");
		    out.print("alert(\"STUDENT ADDED SUCCESSFUL\");");
		    out.print("location='studentaccess.jsp';");
		    out.print("</script>");
			

		}
		catch (SQLException e) {
			out.println("<script type=\"text/javascript\">");
		    out.println("alert(\"ID ALREADY PRESENT\");");
		    out.println("location='studentaccess.jsp';");
		    out.println("</script>");
		    Logger.getLogger(AddController.class.getName()).log(Level.INFO, null, e);
		}
			}

}
			