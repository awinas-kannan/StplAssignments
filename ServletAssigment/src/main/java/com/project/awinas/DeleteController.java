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


/**
 * Servlet implementation class DeleteController
 */
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("deleteid"));
		PrintWriter out = response.getWriter();
		StudentDao dao =new StudentDao();
		
		String deleteresult;
		try {
			deleteresult = dao.deleteStudentDetail(id);
			if("REMOVED".equals(deleteresult))
			{
				out.print("<script type=\"text/javascript\">");
			    out.print("alert('DELETE SUCCESSFUL');");
			    out.print("location='studentaccess.jsp';");
			    out.print("</script>");
			}
			else
			{
				out.print("<script type=\"text/javascript\">");
			    out.print("alert('INVALID ID');");
			    out.print("location='studentaccess.jsp';");
			    out.print("</script>");
			}

		} 
		catch (SQLException e)
		{
			Logger.getLogger(DeleteController.class.getName()).log(Level.INFO, null, e);
		}	
		
				}

}
