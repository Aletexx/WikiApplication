<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register admin user on WikiPortal</title>
</head>
<body>
<%-- JDBC driver name and database URL STEP 2: Register JDBC driver --%>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost:3306/EMP?serverTimezone=Australia/Melbourne"
user="root" password="bit235mysql"/>
<%-- Getting Request parameters --%>
<c:set var = "userRegister" scope = "session" value="${param.userRegister}"/>
<c:set var = "pswRegister" scope = "session" value="${param.pswRegister}"/>
<%-- STEP 3: Open a connection
STEP 4: Execute a query --%>
<sql:update dataSource="${snapshot}">
insert into  usersWikiPortal (username, password) 
values (?, MD5(?))
<sql:param value="${userRegister}" />
<sql:param value="${pswRegister}" />
</sql:update>
<c:out value="User ${userRegister} registered successfully"/>
<br>
<a href="Login.jsp">Back to Login</a>
</body>
</html>