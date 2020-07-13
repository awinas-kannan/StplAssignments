package com.project.awinas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.project.awinas.StudentDao;
import com.project.awinas.model.StudentModel;

/**
 * Servlet implementation class DisplayController
 */
public class DisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("dispid"));
		PrintWriter out = response.getWriter();
		StudentDao dao =new StudentDao();
		
		
	
		try {
			
			StudentModel displayresult = dao.getStudentDetail(id);
			
			request.setAttribute("result", displayresult);
			RequestDispatcher rd =request.getRequestDispatcher("display.jsp");
			
			rd.forward(request, response);
			
			
			
		} 
		catch (SQLException e) {
			out.print("<script type=\"text/javascript\">");
		    out.print("alert(\"INVALID ID\");");
		    out.print("location='studentaccess.jsp';");
		    out.print("</script>");
		    Logger.getLogger(DisplayController.class.getName()).log(Level.INFO, null, e);
		}
		
				
			}

}
	