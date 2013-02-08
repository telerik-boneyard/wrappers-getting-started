package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends RepositoryBase {

	public CategoryRepository(String path) {
		super(path);
	}
	
	public List<models.Category> listCategories() throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<models.Category> categories = new ArrayList<models.Category>();
		
		try {
			
			String sql = "SELECT CategoryID, CategoryName FROM Categories";
			
			stmt = super.connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				models.Category category = new models.Category();
				
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				
				categories.add(category);
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		
		return categories;
		
	}
}
