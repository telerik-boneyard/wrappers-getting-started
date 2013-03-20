package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository extends Repository {
	
	public CategoriesRepository(String path) {
		
		// call the base class passing in the path
		super(path);
	}
	
	public List<models.Category> listCategories() throws SQLException {
		
		// create an empty list of categories to send back as the response
		List<models.Category> categories = new ArrayList<models.Category>();
		
		// sql objects declared outside of the try so they can be closed
		// in the finally
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// prepare the sql string for safe execution
			stmt = super.conn.prepareStatement("SELECT CategoryID, CategoryName FROM Categories");
			
			// execute the query
			rs = stmt.executeQuery();
			
			// for each item in the result set
			while(rs.next()) {
			
				// create a new category model object, passing the fields to it's constructor
				models.Category category = new models.Category(rs.getInt("CategoryID"),
						rs.getString("CategoryName"));
				
				// add this category to the list to be returned
				categories.add(category);
			}
		} finally {
			// close sql objects
			rs.close();
			stmt.close();
		}
		
		// return categories
		return categories;
	}
	
}
