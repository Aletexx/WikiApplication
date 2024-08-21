<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Categories</title>
    <link rel="stylesheet" type="text/css" href="styleHome.css">
</head>
<body>
<!-- side bar and menu  -->
	<div class="sidenav">
		<a href="ArticleServlet">Home</a> 
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=showAll">Articles</a>
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=showCategoriesPub">Categories</a>
		<a href="Login.jsp">Admin</a>
	</div>


	<div class="main">
		<h1 class="title">Wiki Portal</h1>
		<!-- Page similar to categoryMange.jsp but for users see list of categories-->
		<div align="center">
				
			<ul id="myUL">
	<div align="center">
    <h1> Categories</h1>
    <c:forEach var="cat" items="${categories}">
        <div class="category">
            <h2 id="titleArticle">${cat.getCategoryName()}</h2>
        </div>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/ArticleServlet">Back to Home</a>
   </div>
    
    </ul>
		</div>
	</div>

