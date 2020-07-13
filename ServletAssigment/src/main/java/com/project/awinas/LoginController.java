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

import com.project.awinas.LoginDao;
import com.project.awinas.model.LoginModel;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      PrintWriter out = response.getWriter();
		LoginModel lm =new LoginModel();	
		
		lm.setUserid(Integer.parseInt(request.getParameter("userid")));
		lm.setPwd(request.getParameter("pwd"));
		 
		LoginDao dao =new LoginDao();	
		
		String loginresult;
		try {
			loginresult = dao.getCredentialResult(lm);
			if("SUCCESSFUL".equals(loginresult))
			{
				RequestDispatcher rd =request.getRequestDispatcher("studentaccess.jsp");
				
				rd.forward(request, response);
				out.write("Done");
			}
			else
			{
				out.print("<script type=\"text/javascript\">");
			    out.print("alert('LOGIN UNSUCCESSFUL');");
			    out.print("location='index.jsp';");
			    out.print("</script>");
			}
			
			
		} 
		catch (SQLException e) {
			out.print("<script type=\"text/javascript\">");
		    out.print("alert('LOGIN UNSUCCESSFULL');");
		    out.print("location='index.jsp';");
		    out.print("</script>");
		    Logger.getLogger(LoginController.class.getName()).log(Level.INFO, null, e);
		}
		
		
	}
}

