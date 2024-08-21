<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Categories</title>
    <link rel="stylesheet" href="styleHome.css">
</head>
<body>
<div class="sidenav">
	
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=newArtForm">Create New Article</a> 
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=newCat">Create New Category</a>
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=showCategories">Manage Categories</a>
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=showHidden">Hidden Article List</a>
		<a href="Login.jsp">Logout</a>

	</div>

	<div class="main">
		<h1 class="title">Wiki Portal Admin</h1>
		<!-- Page  to display list of Categories and for ADMINs to perform CRUD operations -->
		<div align="center">
			
    <h1>Manage Categories</h1>
    <c:forEach var="cat" items="${categories}">
        <div class="category">
            <h2 id="titleArticle">${cat.getCategoryName()}</h2>
            <a onclick="return confirmDelete();" href="ArticleServlet?action=deleteCategory&categoryId=${cat.getCategoryID()}">Delete</a>
            <a href="${pageContext.request.contextPath}/ArticleServlet?action=editCat&CategoryID=${cat.getCategoryID()}">Update</a>
            <br>
        </div>
    </c:forEach>
    <br>
    <a href="${pageContext.request.contextPath}/ArticleServlet?action=showAllLogin">Back to All Articles - ADMIN</a>
    </div>
    </div>
    	<script>
	
	 function confirmDelete() {
		    return confirm('Are you sure you want to delete this Category?');
		}
	 </script>
</body>
</html>
