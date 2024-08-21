<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styleHome.css">
<title>All Articles</title>
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
		<div align="center">
			<input type="text" id="myInput" onkeyup="myFunction()"
				placeholder="Search here..">

	    <h1>Hidden Articles</h1>
    <c:forEach var="art" items="${hiddenArticles}">
        <div class="article">
            <h2>${art.getTitleName()}</h2>
            <p><em>Category: ${art.getCatName()}</em></p>
            <p class=maxText>${art.getArticleContent()}</p>
            <a id="readMore" href="ArticleServlet?action=showFullArticle&articleId=${art.getArticleID()}">Read
								more</a>
								<br>
            <a href="ArticleServlet?action=unhide&articleId=${art.getArticleID()}">Unhide</a>
        </div>
    </c:forEach>
    
    <br>
    <a href="${pageContext.request.contextPath}/ArticleServlet?action=showAllLogin">Back to Home</a>

	<script>
	 function myFunction() {
		
		    var input, filter, ul, li, h2, p, i, txtValue, txtValue2;
		    input = document.getElementById('myInput');
		    filter = input.value.toUpperCase();
		    ul = document.getElementById("myUL");
		    li = ul.getElementsByClassName('article');

		    // Loop through all articles, and hide those who don't match the search query
		    for (i = 0; i < li.length; i++) {
		        title = li[i].getElementsByTagName("h2")[0];
		        content = li[i].getElementsByTagName("p")[0];
		        txtValue = title.textContent || title.innerText;
		        txtValue2 = content.textContent || content.innerText;
		        if (txtValue.toUpperCase().indexOf(filter) > -1 || txtValue2.toUpperCase().indexOf(filter) > -1) {
		            li[i].style.display = "";
		        } else {
		            li[i].style.display = "none";
		        }
		    }
		    }
	 function clicked() {
		    return confirm('Are you sure you want to delete this Article?');
		}</script>
</body>

<style>
body {
	width: 75%;
	max-width: 600px;
	margin: 0 auto 32px auto;
}

#cat {
	font-size: large;
	font-style: italic;
}

p {
	text-align: left;
	align: left;
}

#readMore {
text-align: left;
}
</style>
</html>