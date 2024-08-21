package mvccrudpackage.model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mvccrudpackage.model.bean.Category;
public class CategoryDAO {
	private String DBURL = "jdbc:mysql://localhost:3306/wikiportal?serverTimezone=Australia/Melbourne";
    private String DBUsername = "root";
    private String DBPassword = "bit235mysql";

    private String INSERT_CATEGORY_SQL = "INSERT INTO Category (CategoryName) VALUES (?)";
    private String SELECT_CATEGORY_BY_ID = "SELECT CategoryID, CategoryName FROM Category WHERE CategoryID = ?";
    private String SELECT_ALL_CATEGORIES = "SELECT * FROM Category";
    private String DELETE_CATEGORY_SQL = "DELETE FROM Category WHERE CategoryID = ?";
    private String UPDATE_CATEGORY_SQL = "UPDATE Category SET CategoryName = ? WHERE CategoryID = ?";
    //private String UPDATE_ARTICLES_TO_UNKNOWN = "UPDATE Wiki SET CategoryID = (SELECT CategoryID FROM Category WHERE CategoryName = 'Unknown') WHERE CategoryID = ?";

    
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCategory(Category category) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
        }
    }

    public Category selectCategory(int id) {
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("CategoryName");
                category = new Category(id, categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> selectAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                categories.add(new Category(categoryID, categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

   

    public boolean updateCategory(Category category) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getCategoryID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public boolean deleteCategory(int id) throws SQLException {
        boolean rowDeleted;
        int unknownCategoryID = getUnknownCategoryID(); // Get the ID of the 'Unknown' category

        try (Connection connection = getConnection();
             PreparedStatement updateStmt = connection.prepareStatement("UPDATE Wiki SET CategoryID = ? WHERE CategoryID = ?");
             PreparedStatement deleteStmt = connection.prepareStatement(DELETE_CATEGORY_SQL)) {
            updateStmt.setInt(1, unknownCategoryID);
            updateStmt.setInt(2, id);
            updateStmt.executeUpdate();

            deleteStmt.setInt(1, id);
            rowDeleted = deleteStmt.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private int getUnknownCategoryID() throws SQLException {
        int unknownCategoryID = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT CategoryID FROM Category WHERE CategoryName = 'Unknown'")) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                unknownCategoryID = rs.getInt("CategoryID");
            }
        }
        return unknownCategoryID;
    }

    
//    public Category selectCategoryByID(int categoryID) {
//        Category category = null;
//        String SELECT_CATEGORY_BY_ID = "SELECT CategoryID, CategoryName FROM Category WHERE CategoryID = ?";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
//            preparedStatement.setInt(1, categoryID);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                String categoryName = rs.getString("CategoryName");
//                category = new Category(categoryID, categoryName);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return category;
//    }
}
