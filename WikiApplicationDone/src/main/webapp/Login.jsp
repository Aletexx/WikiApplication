<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WikiLogin</title>
<link rel="stylesheet" href="styleLogin.css">
</head>
<body>
<%-- Form to get username and password and sent to Verification.jsp to validate --%>
<div class="my_form">
	<form action="Verification.jsp" method="post">
		
		<div class="my_border"> 
		<h1>Log in admin console</h1>
		
		<p>
			<label for="user" class="block_labels"> <b>Username</b></label> 
		
			<input type="text"  name="user" required id="user">
		</p>

		<p>
			<label for="password" class="block_labels"> <b>Password</b></label> 
			
			<input type="password"  name="password" required  id="password">
		</p>
			<button type="submit" id="button">Login</button>
		
		
		<p ID="POR"> OR </p>
		<a href="Register.jsp">
			<button type="button" id="buttonReg">Register here</button>
		</a>
		<br>
		<a href="ArticleServlet" id="homePage">back to home</a>
		</div>
	</form>
	
	</div>
</body>


</html>
