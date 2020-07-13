<%@page import="com.project.awinas.StudentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		StudentModel sms = (StudentModel) request.getAttribute("result");
	%>
	
	<form action="update" method="post">
		<table>
					<tr>
						<td><label>ID</label></td>
						<td><input type="number" id="stuid" readonly name="stuid" value="<%=sms.getId()%>"></td>
					</tr>
					<tr>
						<td><label>NAME</label></td>
						<td><input type="text" id="stuname" name="stuname" value="<%=sms.getName() %>"></td>
					</tr>
					<tr>
						<td><label>MARK1</label></td>
						<td><input type="number" id="stumark1" name="stumark1" value="<%=sms.getMark1() %>"></td>
					</tr>
					<tr>
						<td><label>MARK2</label></td>
						<td><input type="number" id="stumark2" name="stumark2" value="<%=sms.getMark2() %>"></td>
					</tr>
					<tr>
						<td><label>MARK3</label></td>
						<td><input type="number" id="stumark3" name="stumark3" value="<%=sms.getMark3() %>"></td>
					</tr>
					<tr>
						<th colspan="2"><input type="submit" value="UPDATE"></th>
					</tr>
				</table>
	</form>
	</div>


</body>
</html>