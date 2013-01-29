package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;

public class EmployeeRepository {

	Connection _conn;
	
	public EmployeeRepository() { }
	
	public EmployeeRepository(String path) {

		// initialize the database driver
        try {
        	Class.forName("org.sqlite.JDBC");
        	
			// set the connection instance
			_conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Employee> listEmployees(int managerId) {
		
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			
			PreparedStatement stmt = null;
			
			String query = "SELECT e.EmployeeID, e.FirstName, e.LastName, e.ReportsTo AS ManagerID, "+
					   "(SELECT COUNT(*) FROM Employees WHERE ReportsTo = e.EmployeeID) AS DirectReports " +
					   "From Employees e ";
		
			if (managerId != 0) {
				query += "WHERE e.ReportsTo = ?";
				stmt = _conn.prepareStatement(query);
				stmt.setInt(1, managerId);
			}
			else {
				query += "WHERE e.ReportsTo IS NULL";
				stmt = _conn.prepareStatement(query);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Employee employee = new Employee();
				
				employee.setEmployeeID(rs.getInt("EmployeeID"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setManagerID(rs.getInt("ManagerID"));
				employee.setHasChildren(rs.getInt("DirectReports") > 0);
				employee.setFullName();
				
				employees.add(employee);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
		
	}
}
