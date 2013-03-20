package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductsRepository {

	// class level sql object
	private Connection conn = null;
	
	public ProductsRepository(String path) {

		// initialize the database driver
        try {
        	Class.forName("org.sqlite.JDBC");
        	
			// set the connection instance
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// returns a count of all the rows in the products table
	public int getCount() throws SQLException {
		
		// default value to send back
		int total = 0;
		
		// define sql objects outside of the try so they can be
		// closed in the finally
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// build the sql string
			String sql = "SELECT COUNT(*) AS Total FROM Products";
			
			// prepare the sql for safe execution
			stmt = conn.prepareStatement(sql);
			
			// execute the statement
			rs = stmt.executeQuery();
			
			// for each row (should be just one)
			while(rs.next()) {
				// set the total variable
				total = rs.getInt("Total");
			}
		} finally {
			// close out sql objects
			rs.close();
			stmt.close();
		}
		
		// return the total from the database or 0
		return total;
	}
	
	// this method returns a list of products
	public List<models.Product> listProducts(int skip, int take) throws SQLException {
		
		// create an empty list of products to send back as the result
		List<models.Product> products = new ArrayList<models.Product>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		
			// build up the sql string
			String sql = "SELECT p.ProductID, p.ProductName, s.SupplierID, " +
					"s.CompanyName as SupplierName, c.CategoryID, " +
					"c.CategoryName, p.UnitsInStock, p.UnitPrice, p.Discontinued " +
					"FROM Products p " +
					"JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
					"JOIN Categories c ON p.CategoryID = c.CategoryID " +
					"LIMIT ?,?";
			
			// prepare it for safe execution
			stmt = conn.prepareStatement(sql);
						
			// apply the take and skip parameters to the query
			stmt.setInt(1, skip);
			stmt.setInt(2, take);
			
			// execute the query
			rs = stmt.executeQuery();
			
			// for each item in the result set...
			while(rs.next()) {
			
				// create a new product
				models.Product product = new Product();
				
				// set the model values
				product.setProductID(rs.getInt("ProductID"));
				product.setProductName(rs.getString("ProductName"));
				product.setSupplier(new models.Supplier(rs.getInt("SupplierID"), 
						rs.getString("SupplierName")));
				product.setCategory(new models.Category(rs.getInt("CategoryID"),
						rs.getString("CategoryName")));
				product.setUnitsInStock(rs.getInt("UnitsInStock"));
				product.setUnitPrice(rs.getFloat(("UnitPrice")));
				product.setDiscontinued(rs.getBoolean("Discontinued"));
				
				// add the product to the return list
				products.add(product);
			}
		} finally {
			// close out sql objects
			rs.close();
			stmt.close();
		}
		
		// return the list of projects
		return products;
	}
	
	
	
	
	
	
}
