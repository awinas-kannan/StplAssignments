<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>STUDENT DATABASE THROUGH SERVLET</title>
</head>
<body>
	<center>
		<div id="accessdiv" style="display: block">
			<table>
				<tr>
					<th><input type="button" id="addformbutton"
						name="addformbutton" value="ADD NEW STUDENT"
						onclick="showaddform()"></th>

					<th><input type="button" id="displayformbutton"
						name="displayformbutton" value="DISPLAY STUDENT DETAIL"
						onclick="showdisplayform()"></th>

					<th><input type="button" id="deleteformbutton"
						name="deleteformbutton" value="DELETE STUDENT DETAIL"
						onclick="showdeleteform()"></th>

					<th><input type="button" id="rankformbutton"
						name="rankformbutton" value="SHOW STUDENT RANK"
						onclick="showrankform()"></th>
				</tr>
			</table>
		</div>
	</center>
	<br>
	<br>
	<br>
	<br>
	<br>
	<center>
		<div id="adddiv" style="display: none">
		<form action="add" method="post"  >
			<table>
				<tr>
					<td><label>ID</label></td>
					<td><input type="number" id="stuid" name="stuid"></td>
				</tr>
				<tr>
					<td><label>NAME</label></td>
					<td><input type="text" id="stuname" name="stuname"></td>
				</tr>
				<tr>
					<td><label>MARK1</label></td>
					<td><input type="number" id="stumark1" name="stumark1"></td>
				</tr>
				<tr>
					<td><label>MARK2</label></td>
					<td><input type="number" id="stumark2" name="stumark2"></td>
				</tr>
				<tr>
					<td><label>MARK3</label></td>
					<td><input type="number" id="stumark3" name="stumark3"></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="ADD"></th>
				</tr>
			</table>
			</form>
		</div>

		<div id="displaydiv" style="display: none">
			<form action="display" method="post">
				<table>
					<tr>
						<td><label>ENTER STUDENT ID</label></td>
						<td><input type="number" id="dispid" name="dispid"></td>
					</tr>
					<tr>
						<th colspan="2"><input type="submit"
							value="SHOW STUDENT DETAIL"></th>
					</tr>
				</table>
			</form>
		</div>

		<div id="deletediv" style="display: none">
			<form action="delete" method="post">
				<table>
					<tr>
						<td><label>ENTER STUDENT ID</label></td>
						<td><input type="number" id="deleteid" name="deleteid"></td>
					</tr>
					<tr>
						<th colspan="2"><input type="submit"
							value="DELETE STUDENT DETAIL"></th>
					</tr>
				</table>
			</form>
		</div>
		<div id="rankdiv" style="display: none">
			<form action="rank" method="post">
				<table>
					<tr>
						<td><label>ENTER RANK</label></td>
						<td><input type="number" id="rankvalue" name="rankvalue"></td>
					</tr>
					<tr>
						<th colspan="2"><input type="submit" value="SHOW DETAIL"></th>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
</html>




<script type="text/javascript">
	function showaddform() {
		document.getElementById("adddiv").style.display = 'block';
		document.getElementById("displaydiv").style.display = 'none';
		document.getElementById("deletediv").style.display = 'none';
		document.getElementById("rankdiv").style.display = 'none';
	}
	function showdisplayform() {
		document.getElementById("adddiv").style.display = 'none';
		document.getElementById("displaydiv").style.display = 'block';
		document.getElementById("deletediv").style.display = 'none';
		document.getElementById("rankdiv").style.display = 'none';
	}
	function showdeleteform() {
		document.getElementById("adddiv").style.display = 'none';
		document.getElementById("displaydiv").style.display = 'none';
		document.getElementById("deletediv").style.display = 'block';
		document.getElementById("rankdiv").style.display = 'none';
	}
	function showrankform() {
		document.getElementById("adddiv").style.display = 'none';
		document.getElementById("displaydiv").style.display = 'none';
		document.getElementById("deletediv").style.display = 'none';
		document.getElementById("rankdiv").style.display = 'block';
	}
</script>