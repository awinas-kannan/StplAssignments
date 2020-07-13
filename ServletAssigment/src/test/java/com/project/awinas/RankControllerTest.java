package com.project.awinas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.project.awinas.StudentDao;
import com.project.awinas.model.StudentModel;



public class RankControllerTest {

	 @Before
	    public void AddBeforeDeleteTest() throws SQLException, ServletException, IOException
	    {
		 try (Connection connect = DriverManager.getConnection("jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_TRAINEE", "Awinas","^D$b2K5!3"); Statement selectst = connect.createStatement()) {

	            String deletequery = "delete from student";
	            selectst.executeUpdate(deletequery);
	           
	        }

		 StudentDao daoA =new StudentDao();
		 StudentModel smA=new StudentModel();
		 smA.setId(1);
		 smA.setName("Aravind");
		 smA.setMark1(99);
		 smA.setMark2(99);
		 smA.setMark3(99);
		 smA.setTotal(297);
		 daoA.addStudentDetail(smA);
		 
		 
		 StudentModel smB=new StudentModel();
		 smB.setId(4);
		 smB.setName("Akash");
		 smB.setMark1(99);
		 smB.setMark2(99);
		 smB.setMark3(99);
		 smB.setTotal(297);
		 daoA.addStudentDetail(smB);
		 
		 
		 StudentModel smC=new StudentModel();
		 smC.setId(3);
		 smC.setName("Akash");
		 smC.setMark1(99);
		 smC.setMark2(99);
		 smC.setMark3(98);
		 smC.setTotal(296);
		 daoA.addStudentDetail(smC);
		 
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
	public void testRankServletPass() throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class); 
       // HttpSession session = mock(HttpSession.class);
        
        RequestDispatcher rd = mock(RequestDispatcher.class);
        
        when(request.getParameter("rankvalue")).thenReturn("1");
        //when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("display.jsp")).thenReturn(rd);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new RankController().doPost(request, response);
        writer.flush(); 
        
        //String result = stringWriter.getBuffer().toString();
       
         //assertEquals("Done", result);
        Mockito.verify(rd).forward(request, response);
	}
	
	
	@Test
	public void testRankServletFail() throws Exception {
     HttpServletRequest request = mock(HttpServletRequest.class);       
     HttpServletResponse response = mock(HttpServletResponse.class);    

     when(request.getParameter("rankvalue")).thenReturn("100");
     
     

     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     when(response.getWriter()).thenReturn(writer);

     new RankController().doPost(request, response);

     writer.flush();
     assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\"INVALID RANK\");location='studentaccess.jsp';</script>");
	}

}
