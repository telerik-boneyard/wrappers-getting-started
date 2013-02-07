package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends RepositoryBase {

	public CustomerRepository() {
		super();
	}
	
	public CustomerRepository(String path) {
		super(path);
	}
	
	public int getCustomerCount(int employeeId) {
		
		int count = 0;
		
		try {
			
			PreparedStatement stmt = null;
			
			String sql = "SELECT COUNT(*) as Total " + 
						 "FROM Employees e " +
						 "JOIN Orders o ON e.EmployeeID = o.EmployeeID " +
						 "JOIN Customers c ON o.CustomerID = c.CustomerID " + 
						 "WHERE e.EmployeeID = ?";
			
			stmt = super.connection.prepareStatement(sql);
			
			stmt.setInt(1, employeeId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("Total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<models.Customer> listCustomers(int employeeId, int take, int skip) {
		
		List<models.Customer> customers = new ArrayList<models.Customer>();
		
		try {
			
			PreparedStatement stmt = null;
			
			String sql = "SELECT c.CustomerID, c.CompanyName, c.ContactName, c.ContactTitle, " +
						 "c.Address, c.City, c.Region, c.PostalCode " +
						 "FROM Employees e " +
						 "JOIN Orders o ON e.EmployeeID = o.EmployeeID " +
						 "JOIN Customers c ON o.CustomerID = c.CustomerID " +
						 "WHERE e.EmployeeID = ? LIMIT ?,?";
			
			stmt = super.connection.prepareStatement(sql);
			
			stmt.setInt(1, employeeId);
			stmt.setInt(2, skip);
			stmt.setInt(3, take);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				models.Customer customer = new models.Customer();
				
				customer.setCustomerID(rs.getString("CustomerID"));
				customer.setCompanyName(rs.getString("CompanyName"));
				customer.setContactName(rs.getString("ContactName"));
				customer.setCity(rs.getString("City"));
				
				customers.add(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
		
	}
	
}
