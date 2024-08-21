<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>Wiki Admin - Category Form</title>
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
		<!-- Page to create or update an Category but for ADMINs only -->
    <div align="center">
       
    </div>
    <div align="center">
        <c:if test="${category != null}">
            <form action="${pageContext.request.contextPath}/ArticleServlet" method="post">
                <input type="hidden" name="action" value="updateCategory">
                <input type="hidden" name="categoryId" value="${category.getCategoryID()}">
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>Edit Category</h2>
                    </caption>
                    <tr>
                        <th>Category Name:</th>
                        <td><input type="text" name="categoryName" size="45" required value="<c:out value='${category.getCategoryName()}' />" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </tr>
                </table>
            </form>
        </c:if>

        <c:if test="${category == null}">
            <form action="${pageContext.request.contextPath}/ArticleServlet?action=createCategory" method="post">
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>Create New Category</h2>
                    </caption>
                    <tr>
                        <th>Category Name:</th>
                        <td><input type="text" name="categoryName" size="45" required /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </tr>
                </table>
            </form>
        </c:if>
        
    </div>
    </div>
</body>
</html>
