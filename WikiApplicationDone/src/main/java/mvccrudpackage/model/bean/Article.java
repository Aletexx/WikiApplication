package mvccrudpackage.model.bean;

public class Article {
	
	//variables
	protected int ArticleID;
	protected String ArticleContent;
	protected String CategoryName;
	protected String DateAdded;
	protected String TitleName;
	protected int CategoryID;
	protected boolean hidden;
	private String categoryName;
	

	public Article(String titleName, String categoryName, String articleContent) { 
	    this.TitleName = titleName;
	    this.CategoryName = categoryName;
	    this.ArticleContent = articleContent;

	}
	
	public Article(String titleName, int categoryID, String articleContent) { 
	    this.TitleName = titleName;
	    this.CategoryID = categoryID;
	    this.ArticleContent = articleContent;

	}
	
	public Article(int articleID, String titleName, String categoryName, String articleContent) {
	    this.ArticleID = articleID;
	    this.TitleName = titleName;
	    this.CategoryName = categoryName;
	    this.ArticleContent = articleContent;
	}
	
	public Article(int articleID, String titleName, int categoryID, String articleContent) {
	    this.ArticleID = articleID;
	    this.TitleName = titleName;
	    this.CategoryID = categoryID;
	    this.ArticleContent = articleContent;
	}
	
	public Article(int articleID, String titleName, String categoryName, String articleContent, String dateAdded) {
	    this.ArticleID = articleID;
	    this.TitleName = titleName;
	    this.CategoryName = categoryName;
	    this.ArticleContent = articleContent;
	    this.DateAdded = dateAdded;
	}
	
    public Article(int articleID, String titleName, String categoryName, String articleContent, String dateAdded, boolean hidden) {
        this.ArticleID = articleID;
        this.TitleName = titleName;
        this.CategoryName = categoryName;
        this.ArticleContent = articleContent;
        this.DateAdded = dateAdded;
        this.hidden = hidden;
    }
    
    public Article(int articleID, String titleName, int categoryID, String articleContent, String dateAdded, boolean hidden) {
        this.ArticleID = articleID;
        this.TitleName = titleName;
        this.CategoryID = categoryID;
        this.ArticleContent = articleContent;
        this.DateAdded = dateAdded;
        this.hidden = hidden;
    }
    
    public Article(int articleID, String titleName, int categoryID, String categoryName, String articleContent, String dateAdded, boolean hidden) {
        this.ArticleID = articleID;
        this.TitleName = titleName;
        this.CategoryID = categoryID;
        this.categoryName = categoryName;
        this.ArticleContent = articleContent;
        this.DateAdded = dateAdded;
        this.hidden = hidden;
    }
    
	public int getArticleID() {
		return ArticleID;
	}
	public void setArticleID(int articleID) {
		ArticleID = articleID;
	}
	public String getArticleContent() {
		return ArticleContent;
	}
	public void setArticleContent(String articleContent) {
		ArticleContent = articleContent;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getDateAdded() {
		return DateAdded;
	}
	public void setDateAdded(String dateAdded) {
		DateAdded = dateAdded;
	}
	public String getTitleName() {
		return TitleName;
	}
	public void setTitleName(String titleName) {
		TitleName = titleName;
	}
	
    public boolean isHidden() {
        return hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    public String getCatName() { 
        return categoryName;
    }

    public void setCatName(String categoryName) {
        this.categoryName = categoryName;
    }
    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        this.CategoryID = categoryID;
    }
}
