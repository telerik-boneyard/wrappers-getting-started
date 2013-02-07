package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepository extends RepositoryBase {

	public ProductsRepository(String path) {
		super(path);
	}
	
	public int getProductCount() throws SQLException {
		
		int total = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT COUNT(*) AS Total FROM Products";
			
			stmt = super.connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				total = rs.getInt("Total");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
			rs.close();
		}
		
		return total;
		
	}
	
	public List<models.Product> listProducts(int take, int skip) throws SQLException {
		
		List<models.Product> products = new ArrayList<models.Product>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT p.ProductID, p.ProductName, p.SupplierID, s.CompanyName, " +
						 "p.CategoryID, c.CategoryName, p.UnitPrice, p.UnitsInStock, p.Discontinued " +
						 "FROM Products p " +
						 "JOIN Suppliers s ON p.SupplierID = s.SupplierID " +
						 "JOIN Categories c ON p.CategoryID = c.CategoryID " + 
						 "LIMIT ?,?";
			
			stmt = super.connection.prepareStatement(sql);
		
			stmt.setInt(1, skip);
			stmt.setInt(2, take);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				models.Product product = new models.Product();
				
				product.setProductID(rs.getInt("ProductID"));
				product.setProductName(rs.getString("ProductName"));
				product.setSupplierID(rs.getInt("SupplierID"));
				product.setSupplierName(rs.getString("CompanyName"));
				product.setCategoryID(rs.getInt("CategoryID"));
				product.setCategoryName(rs.getString("CategoryName"));
				product.setUnitPrice(rs.getFloat("UnitPrice"));
				product.setUnitsInStock(rs.getInt("UnitsInStock"));
				product.setDiscontinued(rs.getBoolean("Discontinued"));
				
				products.add(product);				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			rs.close();
			stmt.close();
		}
		
		return products;
	}

	public void doUpdateProduct(models.Product product) throws Exception {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE Products SET SupplierID = ?, CategoryID = ?, ProductName = ?, " +
						 "UnitPrice = ?, UnitsInStock = ?, Discontinued = ? " +
						 "WHERE ProductID = ?";

			stmt = super.connection.prepareStatement(sql);

			stmt.setInt(1, product.getSupplierID());
			stmt.setInt(2, product.getCategoryID());
			stmt.setString(3, product.getProductName());
			stmt.setFloat(4, product.getUnitPrice());
			stmt.setInt(5, product.getUnitsInStock());
			stmt.setBoolean(6, product.getDiscontinued());
			stmt.setInt(7, product.getProductID());
			
			stmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			stmt.close();
		}
	}
	
	public int doCreateProduct(models.Product product) throws Exception {
		
		int id = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "INSERT INTO Products (SupplierID, CategoryID, " +
						 "ProductName, UnitPrice, UnitsInStock, Discontinued ) " +
						 "VALUES (?, ?, ?, ?, ?, ?)";

			stmt = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, product.getSupplierID());
			stmt.setInt(2, product.getCategoryID());
			stmt.setString(3, product.getProductName());
			stmt.setFloat(4, product.getUnitPrice());
			stmt.setInt(5, product.getUnitsInStock());
			stmt.setBoolean(6, product.getDiscontinued());

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new SQLException("Unable to create product");
			}

			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			else {
				throw new SQLException("Unable to create product. No auto-genereated key obtained");
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		
		return id;
	}
	
}
