<%@page import="java.util.ArrayList"%>
<%@page import="com.project.awinas.StudentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DISPLAY USING RANK VALUE</title>
</head>
<body>


<table border>
<tr>
<td>ID</td>
<td>NAME</td>
<td>MARK1</td>
<td>MARK2</td>
<td>MARK3</td>
<td>TOTAL</td>
<td>RANK</td>
</tr>
<%
 ArrayList<StudentModel> sm = (ArrayList)request.getAttribute("result");


for(int i=0;i<sm.size();i++ )
{
	StudentModel sms;
	sms =(StudentModel)sm.get(i);%>
			<tr>
				
				<td><%=sms.getId()%></td>
			
			
				
				<td><%=sms.getName() %></td>
			
				<td><%=sms.getMark1() %></td>
				<td><%=sms.getMark2() %></td>
				<td><%=sms.getMark3() %></td>
				<td><%=sms.getTotal() %></td>
				<td><%=sms.getRank() %></td>
			</tr>
		

<%} %>
 
</table>
</body>
</html>