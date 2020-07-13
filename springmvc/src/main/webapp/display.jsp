<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.project.awinas.StudentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display page</title>
</head>
<body>
<%
 ArrayList<StudentModel> sm = (ArrayList)request.getAttribute("result");


for(int i=0;i<sm.size();i++ )
{
	StudentModel sms;
	sms =(StudentModel)sm.get(i);
out.println("NAME  -  " +sms.getName());
out.println("ID  -  " +sms.getId());
out.println("MARK1  -  "+sms.getMark1());
out.println("MARK2  -  "+sms.getMark2());
out.println("MARK3  -  "+sms.getMark3());
out.println("TOTAL  -  "+sms.getTotal());
out.println("RANK  -  " +sms.getRank());

out.println("\n");
}
 %>
</body>
</html>