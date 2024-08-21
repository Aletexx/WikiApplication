<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styleHome.css">
<title>Wiki Portal</title>
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
		<div align="center">
			<input type="text" id="myInput" onkeyup="myFunction()"
				placeholder="Search here..">
				
				
			<ul id="myUL">
				<h2 id="pageName">Recent Articles</h2>
				<!-- Display Title and article content  -->
				<c:forEach var="art" items="${listArticles}">
					<div class="article" >
						<h2 id="titleArticle">${art.getTitleName()}</h2>
						<p  class="maxText">${art.getArticleContent()}</p>
						<p>
						<a href="ArticleServlet?action=showFullArticle&articleId=${art.getArticleID()}">Read more</a>
						</p>
					</div>
				</c:forEach>
			</ul>
		</div>
	</div>

<!-- JS function to search keywords on home page -->
	<script>
	 function myFunction() {
		    // Declare variables
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
	 </script>
</body>
</html>