<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styleHome.css">
<title>Article</title>

</head>
<body>
<!-- side bar and menu  -->
	<div class="sidenav">
		<a href="ArticleServlet">Home</a> 
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=showAll">Articles</a>
		<a href="${pageContext.request.contextPath}/ArticleServlet?action=showCat">Categories</a>
		<a href="Login.jsp">Admin</a>
	</div>
	
	<div class="main">
	<h1 class="title">Wiki Portal</h1>
	<h2 class="h2fullArticle" >Full article</h2>

	<!-- Display Title and article content  -->
	<h2 id="titleArticle">${fullArticle.getTitleName()}</h2>
	<p>Date added: ${fullArticle.getDateAdded()}</p>
	<p>Category: ${fullArticle.getCatName()}</p>
	<p>${fullArticle.getArticleContent()}</p>
	</div>
	
</body>

<style>
 .h2fullArticle {
	text-decoration: underline;
	text-align: center;
}
</style>
</html>
