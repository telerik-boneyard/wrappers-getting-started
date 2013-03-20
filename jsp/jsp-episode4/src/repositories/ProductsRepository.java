package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductsRepository extends Repository {
	
	public ProductsRepository(String path) {

		// call the base class passing in the path
		super(path);
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

	public void doUpdate(models.Product product) throws SQLException {
	
		// declare sql object outside of the try so it can be closed in
		// the finally
		PreparedStatement stmt = null;
		
		try {
			
			// build the sql string
			String sql = "Update Products SET ProductName = ?, " +
					"SupplierID = ?, " +
					"CategoryID = ?, " +
					"UnitsInStock = ?, " +
					"UnitPrice = ?, " +
					"Discontinued = ? " +
					"WHERE ProductID = ?";
			
			// prepare the statement for safe execution
			stmt = conn.prepareStatement(sql);
			
			// set the query parameter values from the product model object passed in
			stmt.setString(1, product.getProductName());
			stmt.setInt(2, product.getSupplier().getSupplierID());
			stmt.setInt(3,  product.getCategory().getCategoryID());
			stmt.setInt(4, product.getUnitsInStock());
			stmt.setFloat(5, product.getUnitPrice());
			stmt.setBoolean(6, product.isDiscontinued());
			stmt.setInt(7, product.getProductID());
			
			// execute the update
			stmt.executeUpdate();
		
		} finally {
			// close sql objects
			stmt.close();
		}
		
		// this method has no return value.  it either succeeds or fails.  this is all the information
		// that we need for this method
	}
	
	public void doDelete(models.Product product) throws SQLException {
		
		// create the sql objects outisde the try so they can be closed in the finally
		PreparedStatement stmt = null;
		
		try {
			
			// prepare the sql for safe execution
			stmt = conn.prepareStatement("DELETE FROM Products WHERE ProductID = ?");
			
			// add the parameter to the query
			stmt.setInt(1, product.getProductID());
			
			// execute the update
			stmt.executeUpdate();
		
		} finally {
			// close sql objects
			stmt.close();
		}
		
		// this method has no return value.  it either succeeds or fails.  this is all the information
		// that we need for this method
	}
	
	public int doCreate(models.Product product) throws SQLException {
	
		// create a default variable to send back as the result
		int id = 0;
		
		// sql objects created outside the try so they can be closed in
		// the finally
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// build the sql insert statement
			String sql = "INSERT INTO Products (ProductName, SupplierID, CategoryID, " +
					"UnitsInStock, UnitPrice, Discontinued) VALUES (?,?,?,?,?,?)";
			
			// prepare the sql string for safe execution. specify that we expect
			// the statement to return the generated key.
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// add the parameters to the sql
			stmt.setString(1, product.getProductName());
			stmt.setInt(2, product.getSupplier().getSupplierID());
			stmt.setInt(3, product.getCategory().getCategoryID());
			stmt.setInt(4, product.getUnitsInStock());
			stmt.setFloat(5, product.getUnitPrice());
			stmt.setBoolean(6, product.isDiscontinued());
			
			// execute the create statement
			int rows = stmt.executeUpdate();
			
			// if no rows were returned, it failed
			if (rows == 0) {
				throw new SQLException("Unable to create product");
			}
			
			// get the generated key for this item
			rs = stmt.getGeneratedKeys();
			
			// pull the key off the result set
			if (rs.next()) {
				id = rs.getInt(1);
			}
			else {
				throw new SQLException("Unable to create product. No auto-genereated key obtained");
			}
		}
		catch (SQLException e) {
			// throw a new exception
			e.printStackTrace();
			throw e;
		}
		finally {
			// close sql objects
			rs.close();
			stmt.close();
		}
		
		return id;
	}
}