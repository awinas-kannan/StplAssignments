package com.project.awinas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class LoginControllerTest {

	@Test
	public void testLoginServletPass() throws Exception {
     HttpServletRequest request = mock(HttpServletRequest.class);       
     HttpServletResponse response = mock(HttpServletResponse.class);    

     when(request.getParameter("userid")).thenReturn("1");
     when(request.getParameter("pwd")).thenReturn("awi");
     HttpSession session = mock(HttpSession.class);
     RequestDispatcher rd = mock(RequestDispatcher.class);     

     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     when(response.getWriter()).thenReturn(writer);
     when(request.getSession()).thenReturn(session);
     
     when(request.getRequestDispatcher("studentaccess.jsp")).thenReturn(rd);
     new LoginController().doPost(request, response);

     writer.flush();
     String result = stringWriter.getBuffer().toString();
     
     assertEquals("Done", result);

	}

	@Test
	public void testLoginServletFail() throws Exception {
     HttpServletRequest request = mock(HttpServletRequest.class);       
     HttpServletResponse response = mock(HttpServletResponse.class);    

     when(request.getParameter("userid")).thenReturn("111");
     when(request.getParameter("pwd")).thenReturn("awi");
     
     

     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     when(response.getWriter()).thenReturn(writer);

     new LoginController().doPost(request, response);

     writer.flush();
     assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\'LOGIN UNSUCCESSFULL\');location='index.jsp';</script>");
	}
	
	@Test
	public void testLoginServletPasswordFail() throws Exception {
     HttpServletRequest request = mock(HttpServletRequest.class);       
     HttpServletResponse response = mock(HttpServletResponse.class);    

     when(request.getParameter("userid")).thenReturn("1");
     when(request.getParameter("pwd")).thenReturn("awinas");
     
     

     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     when(response.getWriter()).thenReturn(writer);

     new LoginController().doPost(request, response);

     writer.flush();
     assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\'LOGIN UNSUCCESSFUL\');location='index.jsp';</script>");
	}


}
