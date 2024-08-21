<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="styleReg.css">
</head>
<body>
<%-- Form to register admin username and password into the database --%>
<div class="my_form">

	<form action="RegisterVerify.jsp" method="post">
	<div class="my_border"> 
		<h1>Register</h1>
		<P id="p1"> Please choose a username and password </P>
		<p>
			<label for="userRegister"> <b>Username</b>
			</label> <input type="text" placeholder="" name="userRegister" required>
		</p>

		<p>
			<label for="password"> <b>Password</b>
			</label> <input type="password" placeholder="" name="pswRegister" required>
		</p>

		<button type="submit" id="button">Register</button>
		<br> <a href="Login.jsp">Back to Login</a>
		</div>
	</form>
	</div>
</body>


</html>