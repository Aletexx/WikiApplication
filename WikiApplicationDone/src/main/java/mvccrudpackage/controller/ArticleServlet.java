package mvccrudpackage.controller;

import java.io.IOException;  


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException; 
import java.util.*; 
import javax.servlet.RequestDispatcher;
import mvccrudpackage.model.bean.Article;
import mvccrudpackage.model.bean.Category;
import mvccrudpackage.model.dao.ArticleDAO;
import mvccrudpackage.model.dao.CategoryDAO;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDAO artDAO;
	private CategoryDAO catDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
    	artDAO = new ArticleDAO();
    	catDAO = new CategoryDAO();
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");
	    System.out.println("Action received: " + action);
	    
	    if (action != null) {
	        try {
	            switch (action) {
	                case "showAll":
	                    showAllArticles(request, response);
	                    break;
	                case "showAllLogin":
	                    showAllArticlesLogin(request, response);
	                    break;
	                case "showFullArticle":
	                    showFullArticle(request, response);
	                    break;
	                case "insert":
	                    insertArticle(request, response);
	                    break;
	                case "delete":
	                    deleteArticle(request, response);
	                    break;
	                case "edit":
	                    showEditArticle(request, response);
	                    break;
	                case "update":
	                    updateArticle(request, response);
	                    break;
	                case "hide":
	                    hideArticle(request, response);
	                    break;
	                case "unhide":
	                    unhideArticle(request, response);
	                    break;
	                case "showHidden":
	                    showHiddenArticles(request, response);
	                    break;
	                case "createCategory":
	                    createCategory(request, response);
	                    break;
	                case "deleteCategory":
	                    deleteCategory(request, response);
	                    break;
	                case "updateCategory":
	                    updateCategory(request, response);
	                    break;
	                case "showCategories":
	                    showCategories(request, response);
	                    break;
	                case "showCategoriesPub":
	                	showCategoriesPublic(request, response);
	                    break;
	                case "newCat":
	                	newCategory(request, response);
	                    break;
	                case "editCat":
	                	showEditCategory(request, response);
	                    break;
	                case "newArtForm":
	                	newArticle(request, response);
	                    break;
	                case "editArtForm":
	                	showArticleForm(request, response);
	                    break;
	            }
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        }
	    } else {
	        listLatestArticles(request, response);
	    }
	}

    
	private void listLatestArticles(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException { // list the latest articles added
	    List<Article> listArticle = artDAO.selectLatestArticles(3); // get the latest 3 articles
	    request.setAttribute("listArticles", listArticle);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
	    dispatcher.forward(request, response);
	}
	
    private void showAllArticles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //action: showAll / show all articles to user
        List<Article> allArticles = artDAO.selectAllArticles();
		request.setAttribute("allArticles", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
		dispatcher.forward(request, response);
    }
    
    
    private void showAllArticlesLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //action: showAllLogin show all articles in the ADMIN area 
        List<Article> allArticles = artDAO.selectAllArticles();
		request.setAttribute("allArticlesLogin", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("homeLogin.jsp");
		dispatcher.forward(request, response);
    }
    
    
    private void showFullArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //action: showFullArticle / show individual article page 
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        Article fullArticle = artDAO.selectArticle(articleId);
        request.setAttribute("fullArticle", fullArticle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("fullArticle.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("Title"); // insert a new article
        int categoryID = Integer.parseInt(request.getParameter("CategoryID"));
        String content = request.getParameter("Article");
        Article newArticle = new Article(title, categoryID, content);
        artDAO.insertArticle(newArticle);
        response.sendRedirect("ArticleServlet?action=showAllLogin");
    }
    

	private void showEditArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException { // action: edit
		int wikiID = Integer.parseInt(request.getParameter("Wiki_id"));
		Article existingArticle = artDAO.selectArticle( wikiID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ArticleForm.jsp");
		request.setAttribute("article", existingArticle);
		dispatcher.forward(request, response);
	}

    private void updateArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("Wiki_id")); //update article
        String title = request.getParameter("Title");
        int categoryID = Integer.parseInt(request.getParameter("CategoryID"));;
        String content = request.getParameter("Article");
        Article article = new Article(id, title, categoryID, content);
        artDAO.updateArticle(article);
        response.sendRedirect("ArticleServlet?action=showAllLogin");
    }

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int articleId = Integer.parseInt(request.getParameter("Wiki_id")); // delete article
		artDAO.deleteArticle(articleId);
	      response.sendRedirect(request.getContextPath() +"/ArticleServlet?action=showAllLogin");
	}
	
	private void hideArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int articleId = Integer.parseInt(request.getParameter("Wiki_id")); // hide article 
        artDAO.hideArticle(articleId);
        response.sendRedirect("ArticleServlet?action=showAllLogin");
    }

    private void unhideArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId")); // un-hide an article and make it visible again
        artDAO.unhideArticle(articleId);
        response.sendRedirect("ArticleServlet?action=showHidden");
    }

    private void showHiddenArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> hiddenArticles = artDAO.selectHiddenArticles(); // page to show all articles hidden 
        request.setAttribute("hiddenArticles", hiddenArticles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hiddenArticles.jsp");
        dispatcher.forward(request, response);
    }

    private void newArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = catDAO.selectAllCategories(); //action: newArtForm /  page to add new article
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ArticleForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showArticleForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); //action: editArtForm / page to edit Article
        Article existingArticle = artDAO.selectArticle(id);
        List<Category> categories = catDAO.selectAllCategories();
        request.setAttribute("article", existingArticle);
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ArticleForm.jsp");
        dispatcher.forward(request, response);
    }
    
    
    // methods related to Categories CRUD Operations
    
	private void newCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//action:newCat / page to add a new category
		RequestDispatcher dispatcher = request.getRequestDispatcher("categoryForm.jsp");
		dispatcher.forward(request, response);
	}
    
	private void showEditCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException { // action: editCat / page to edit category
		int categoryID = Integer.parseInt(request.getParameter("CategoryID"));
		Category existingCategory = catDAO.selectCategory( categoryID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("categoryForm.jsp");
		request.setAttribute("category", existingCategory);
		dispatcher.forward(request, response);
	}
	
    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String categoryName = request.getParameter("categoryName"); // create category
        Category newCategory = new Category(categoryName);
        catDAO.insertCategory(newCategory);
        response.sendRedirect("ArticleServlet?action=showCategories");
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId")); //update a category name
        String categoryName = request.getParameter("categoryName");
        Category category = new Category(categoryId, categoryName);
        catDAO.updateCategory(category);
        response.sendRedirect("ArticleServlet?action=showCategories");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId")); //delete category
        catDAO.deleteCategory(categoryId);
        response.sendRedirect("ArticleServlet?action=showCategories");
    }

    private void showCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = catDAO.selectAllCategories(); // categories list to admin do part of CRUD
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("categoryManage.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showCategoriesPublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = catDAO.selectAllCategories(); // show categories list to public
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("categoryList.jsp");
        dispatcher.forward(request, response);
    }
}