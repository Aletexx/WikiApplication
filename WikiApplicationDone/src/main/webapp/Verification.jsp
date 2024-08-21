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
<title>Verify Admin</title>
</head>
<body>
	<%-- Register JDBC driver name and database --%>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/EMP?serverTimezone=Australia/Melbourne"
		user="root" password="bit235mysql" />
		
	<%-- Getting parameters from Login.jsp --%>
	<c:set var="user" scope="session" value="${param.user}" />
	<c:set var="password" scope="session" value="${param.password}" />
	
	<%-- Database connection and query execution --%>
	<sql:query dataSource="${snapshot}" var="result">
	select count(*) as kount from usersWikiPortal where username = ? and password = MD5(?)
		<sql:param value="${user}" />
		<sql:param value="${password}" />
	</sql:query>
	
	<%--Extract data from result set --%>
	<c:forEach items="${result.rows}" var="r">
		<c:choose>
		<%-- > Condition checks if the number of rows returned by the query is greater than 0, 
		indicating that the username already exists in the database. --%>
			<c:when test="${r.kount > 0}">
				<c:redirect url = "ArticleServlet?action=showAllLogin"/>
			</c:when>
			<c:otherwise>
				<c:out
					value="Admin user:  ${user} does not exists or wrong password" />
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<br>
	<a href="Login.jsp">Back</a>
</body>
</html>