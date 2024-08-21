<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
        <a href="ArticleServlet">Home</a> 
        <a href="${pageContext.request.contextPath}/ArticleServlet?action=showAll">Articles</a>
        <a href="${pageContext.request.contextPath}/ArticleServlet?action=showCategoriesPub">Categories</a>
        <a href="Login.jsp">Admin</a>
    </div>

    <div class="main">
        <h1 class="title">Wiki Portal</h1>
        <div align="center">
            <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search here..">
            <ul id="myUL">
                <h2 id="pageName">All Articles</h2>
                <c:forEach var="arts" items="${allArticles}">
                    <div class="article">
                        <h2 id="titleArticle">${arts.getTitleName()}</h2>
                        <p id="cat">Category: ${arts.getCatName()}</p>
                        <p class="maxText">${arts.getArticleContent()}</p>
                        <a id="readMore" href="ArticleServlet?action=showFullArticle&articleId=${arts.getArticleID()}">Read more</a>
                        <br><br>
                    </div>
                </c:forEach>
            </ul>
        </div>
    </div>

    <script>
        function myFunction() {
            var input, filter, ul, li, h2, p, i, txtValue, txtValue2;
            input = document.getElementById('myInput');
            filter = input.value.toUpperCase();
            ul = document.getElementById("myUL");
            li = ul.getElementsByClassName('article');

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
