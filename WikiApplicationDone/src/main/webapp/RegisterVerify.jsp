<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register admin</title>
</head>
<body>
	<%-- Register JDBC driver name and database --%>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/EMP?serverTimezone=Australia/Melbourne"
		user="root" password="bit235mysql" />
		
	<%-- Getting parameters from Register.jsp --%>
	<c:set var="userRegister" scope="session" value="${param.userRegister}" />
	<c:set var="pswRegister" scope="session" value="${param.pswRegister}" />
	
	
	<%-- Database connection and query execution --%>
	<sql:query dataSource="${snapshot}" var="existingUser">
    SELECT username FROM usersWikiPortal WHERE username = ?
    <sql:param value="${userRegister}" />
	</sql:query>

	<c:choose>
		<c:when test="${existingUser.rowCount > 0}">
			<c:out
				value="Error: Username '${userRegister}' already exists. Please choose a different username." />
			<br>
			<a href="Register.jsp">Try again</a>
		</c:when>
		
		<%-- Database connection and SQL update --%>
		<c:otherwise>
			<sql:update dataSource="${snapshot}">
			insert into  usersWikiPortal (username, password) 
			values (?, MD5(?))
				<sql:param value="${userRegister}" />
				<sql:param value="${pswRegister}" />
			</sql:update>
			<c:out value="User ${userRegister} registered successfully" />
			<br>
			<a href="Login.jsp">Back to Login</a>
		</c:otherwise>
	</c:choose>

</body>
</html>