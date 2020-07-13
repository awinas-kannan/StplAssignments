<%@page import="com.project.awinas.StudentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DISPLAY USING ID</title>
</head>
<body>
<%
StudentModel sms =(StudentModel)request.getAttribute("result");
out.println("NAME  -  " +sms.getName());
out.println("ID  -  " +sms.getId());
out.println("MARK1  -  "+sms.getMark1());
out.println("MARK2  -  "+sms.getMark2());
out.println("MARK3  -  "+sms.getMark3());
out.println("TOTAL  -  "+sms.getTotal());
out.println("RANK  -  " +sms.getRank());
%>

</body>
</html>