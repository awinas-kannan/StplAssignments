package com.project.awinas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddControllerTest {

	 @Before
	    public void DeleteBrforeAddTest() throws SQLException
	    {
	    	try (Connection connect = DriverManager.getConnection("jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_TRAINEE", "Awinas","^D$b2K5!3"); Statement selectst = connect.createStatement()) {

	            String deletequery = "delete from student";
	            selectst.executeUpdate(deletequery);
	           
	        }
	    }
	 @After
	    public void DeleteAfterAddTest() throws SQLException
	    {
	    	try (Connection connect = DriverManager.getConnection("jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_TRAINEE", "Awinas","^D$b2K5!3"); Statement selectst = connect.createStatement()) {

	            String deletequery = "delete from student";
	            selectst.executeUpdate(deletequery);
	           
	        }
	    }
	
	
	@Test
	public void testAddServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("stuid")).thenReturn("1");
        when(request.getParameter("stuname")).thenReturn("awinas");
        when(request.getParameter("stumark1")).thenReturn("100");
        when(request.getParameter("stumark2")).thenReturn("100");
        when(request.getParameter("stumark3")).thenReturn("100");
        

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new AddController().doPost(request, response);

        writer.flush();
        assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\"STUDENT ADDED SUCCESSFUL\");location='studentaccess.jsp';</script>");
	}
	/*@Test
	public void testAddServletFail() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("stuid")).thenReturn("1");
        when(request.getParameter("stuname")).thenReturn("awinas");
        when(request.getParameter("stumark1")).thenReturn("100");
        when(request.getParameter("stumark2")).thenReturn("100");
        when(request.getParameter("stumark3")).thenReturn("100");
        

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new AddController().doPost(request, response);

        writer.flush();
        assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\"ID ALREADY PRESENT\");location='studentaccess.jsp';</script>");
	}
*/
}
