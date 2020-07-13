
<%@page import="com.project.awinas.model.StudentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DISPLAY STUDENT RECORD</title>
</head>
<body>
<%
 StudentModel sm = (StudentModel)request.getAttribute("result");

		//List<StudentModel> ms =new ArrayList<>();

//ms= (ArrayList<StudentModel>)request.getAttribute("result");

//for(StudentModel sm:ms )
//{
out.println("NAME  -  " +sm.getName());
out.println("ID  -  " +sm.getId());
out.println("MARK1  -  "+sm.getMark1());
out.println("MARK2  -  "+sm.getMark2());
out.println("MARK3  -  "+sm.getMark3());
out.println("TOTAL  -  "+sm.getTotal());
out.println("RANK  -  " +sm.getRank());

//}
 %>
</body>
</html>