package mvccrudpackage.model.dao;

import java.sql.*; 
import java.util.*;
import mvccrudpackage.model.bean.Article;

public class ArticleDAO {
//Define instance variables
	private String DBURL = "jdbc:mysql://localhost:3306/wikiportal?serverTimezone=Australia/Melbourne";
	private String DBUsername = "root";
	private String DBPassword = "bit235mysql";
	private String INSERTARTICLESQL = "INSERT INTO Wiki (Title, CategoryID, Article) VALUES (?, ?, ?)";
	private String DELETEARTICLESQL = "delete from Wiki where Wiki_id = ?;";
    private String UPDATEARTICLESQL = "update Wiki set Title = ?, CategoryID = ?, Article = ? where Wiki_id = ?"; 
    private String HIDEARTICLESQL = "UPDATE Wiki SET hidden = true WHERE Wiki_id = ?";
    private String UNHIDEARTICLESQL = "UPDATE Wiki SET hidden = false WHERE Wiki_id = ?";
    private  String SELECTARTICLEID = "SELECT w.Wiki_id, w.Title, w.Article, w.Date_added, w.hidden, c.CategoryID, c.CategoryName "
            + "FROM Wiki w "
            + "LEFT JOIN Category c ON w.CategoryID = c.CategoryID "
            + "WHERE Wiki_id = ?";
    private  String SELECTALLARTICLES = "SELECT w.Wiki_id, w.Title, w.Article, w.Date_added, w.hidden, c.CategoryID, c.CategoryName "
            + "FROM Wiki w "
            + "LEFT JOIN Category c ON w.CategoryID = c.CategoryID "
            + "WHERE w.hidden = false"; // if false articles will display
private String SELECTHIDDENARTICLES = "SELECT w.Wiki_id, w.Title, w.Article, w.Date_added, w.hidden, c.CategoryID, c.CategoryName "
            + "FROM Wiki w "
            + "LEFT JOIN Category c ON w.CategoryID = c.CategoryID "
            + "WHERE w.hidden = true "; // if true articles wont be visible to public
private String SELECTLATESTARTICLES = "SELECT w.Wiki_id, w.Title, w.Article, w.Date_added, w.hidden, c.CategoryID, c.CategoryName "
			+ "FROM Wiki w "
			+ "LEFT JOIN Category c ON w.CategoryID = c.CategoryID "
			+ "WHERE w.hidden = false ORDER BY Date_added DESC LIMIT ?";
//constructor
	public ArticleDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	
	public Article selectArticle(int id) {
        Article article = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTARTICLEID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String title = rs.getString("Title");
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                String content = rs.getString("Article");
                String dateAdded = rs.getString("Date_added");
                boolean hidden = rs.getBoolean("hidden");
                article = new Article(id, title, categoryID,categoryName, content, dateAdded, hidden);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

	  public List<Article> selectAllArticles() {
	        List<Article> articles = new ArrayList<>();
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECTALLARTICLES)) {
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("Wiki_id");
	                String title = rs.getString("Title");
	                int categoryID = rs.getInt("CategoryID");
	                String categoryName = rs.getString("CategoryName");
	                String content = rs.getString("Article");
	                String dateAdded = rs.getString("Date_added");
	                boolean hidden = rs.getBoolean("hidden");
	                articles.add(new Article(id, title, categoryID, categoryName, content, dateAdded, hidden));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return articles;
	    }
	  
		public List<Article> selectLatestArticles(int limit) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet rs = null;
		    List<Article> arts = new ArrayList<>();
		    try {
		        connection = getConnection();
		        preparedStatement = connection.prepareStatement(SELECTLATESTARTICLES);
		        preparedStatement.setInt(1, limit);
		        rs = preparedStatement.executeQuery();
		        while(rs.next()) {
		            int articleID = rs.getInt("Wiki_id");
		            String titleName = rs.getString("Title");
		            String categoryName = rs.getString("categoryName");
		            String articleContent = rs.getString("Article");
		            String dateAdded = rs.getString("Date_added");
		            int categoryID = rs.getInt("CategoryID");
		            
		            boolean hidden = rs.getBoolean("hidden");

		            arts.add(new Article(articleID, titleName, categoryID, categoryName, articleContent, dateAdded, hidden));
		        }
		    } catch (SQLException e) {
		        printSQLException(e);
		    } finally {
		        finallySQLException(connection, preparedStatement, rs);
		    }
		    return arts;
		}
	public void insertArticle(Article art) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = getConnection();
	        preparedStatement = connection.prepareStatement(INSERTARTICLESQL);
	        preparedStatement.setString(1, art.getTitleName());
	        preparedStatement.setInt(2, art.getCategoryID());
	        preparedStatement.setString(3, art.getArticleContent());
	        
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        printSQLException(e);
	        throw e; 
	    } finally {
	        finallySQLException(connection, preparedStatement, null);
	    }
	}
	
	public boolean deleteArticle(int id) throws SQLException {
        boolean articleDeleted = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	try {
        	 connection = getConnection(); 
        	 preparedStatement = connection.prepareStatement(DELETEARTICLESQL);
        	 preparedStatement.setInt(1, id);
        	 articleDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return articleDeleted;
    }
	
	 public boolean updateArticle (Article art) throws SQLException {
	        boolean articleUpdated = false;
	        Connection connection = null; 
	      	PreparedStatement preparedStatement = null;
	      	try {
	        	connection = getConnection(); 
	        	preparedStatement = connection.prepareStatement(UPDATEARTICLESQL);
	        	preparedStatement.setString(1, art.getTitleName());
	        	preparedStatement.setInt(2, art.getCategoryID());
	        	preparedStatement.setString(3, art.getArticleContent());
	        	preparedStatement.setInt(4, art.getArticleID());

	        	articleUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
	        }
	        catch (SQLException e) {
	        	printSQLException (e);
	        }     
	      	finally {
	        	finallySQLException(connection,preparedStatement,null);
	        }
	        return articleUpdated;
	    }
	 
	 public List<Article> selectHiddenArticles() {
	        List<Article> articles = new ArrayList<>();
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECTHIDDENARTICLES)) {
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("Wiki_id");
	                String title = rs.getString("Title");
	                int categoryID = rs.getInt("CategoryID");
	                String categoryName = rs.getString("CategoryName");
	                String content = rs.getString("Article");
	                String dateAdded = rs.getString("Date_added");
	                boolean hidden = rs.getBoolean("hidden");
	                articles.add(new Article(id, title, categoryID, categoryName, content, dateAdded, hidden));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return articles;
	    }

	    public void hideArticle(int articleId) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(HIDEARTICLESQL)) {
	            preparedStatement.setInt(1, articleId);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void unhideArticle(int articleId) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(UNHIDEARTICLESQL)) {
	            preparedStatement.setInt(1, articleId);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	private void finallySQLException(Connection c, PreparedStatement p, ResultSet r) {
		if (r != null) {
			try {
				r.close();
			} catch (Exception e) {
			}
			r = null;
		}
		if (p != null) {
			try {
				p.close();
			} catch (Exception e) {
			}
			p = null;
		}
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				c = null;
			}
		}
	}
}