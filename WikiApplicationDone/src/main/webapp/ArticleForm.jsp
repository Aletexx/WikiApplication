<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>Wiki Admin</title>
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
		<!-- Page to create or edit an Article but for ADMINs only -->
    <div align="center">
    
    </div>
    <div align="center">
        <c:if test="${article != null}">
            <form action="${pageContext.request.contextPath}/ArticleServlet" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="Wiki_id" value="${article.getArticleID()}">
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>Edit Article</h2>
                    </caption>
                    <tr>
                        <th>Article Name:</th>
                        <td><input type="text" name="Title" size="45" required value="<c:out value='${article.getTitleName()}' />" /></td>
                    </tr>
                    <tr>
                        <th>Category:</th>
                        <td>
                            <select name="CategoryID" required> 
                                <c:forEach var="category" items="${categories}">
                                      <option value="${category.getCategoryID()}"><c:out value="${category.getCategoryName()}" /></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>Content:</th>
                        <td><input type="text" name="Article" size="45" required value="<c:out value='${article.getArticleContent()}' />" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </tr>
                </table>
            </form>
        </c:if>

        <c:if test="${article == null}">
            <form action="${pageContext.request.contextPath}/ArticleServlet?action=insert" method="post">
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>Add New Article</h2>
                    </caption>
                    <tr>
                        <th>Article Name:</th>
                        <td><input type="text" name="Title" size="45" required /></td>
                    </tr>
                    <tr>
                        <th>Category:</th>
                        <td>
                            <select name="CategoryID" required>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.getCategoryID()}"><c:out value="${category.getCategoryName()}" /></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>Content:</th>
                        <td><input type="text" name="Article" size="45" required /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </tr>
                </table>
            </form>
        </c:if>
        
           <h4> <br>
            <a href="${pageContext.request.contextPath}/ArticleServlet?action=showAllLogin">Back to all Articles - ADMIN</a>
        </h4>
    </div>
    </div>
</body>
</html>
