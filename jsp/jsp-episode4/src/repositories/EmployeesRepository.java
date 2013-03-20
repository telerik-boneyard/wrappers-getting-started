package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesRepository extends Repository {
	
	public EmployeesRepository(String path) {

		// call the base class passing in the path
		super(path);
	}
	
	// returns a list of all employees who report to the person
	// with the employee id that matches the id parameter
	public List<models.Employee> listEmployees(int id) throws SQLException {
		
		// create an empty list to send back as a result
		List<models.Employee> employees = new ArrayList<models.Employee>();
		
		// create necessary sql objects outside the try so we can close them 
		// in the finally
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// build the sql string
			String sql = "SELECT EmployeeID, FirstName || ' ' || LastName as Name, " +	 
					"(SELECT COUNT(*) FROM Employees WHERE ReportsTo = e.EmployeeID) AS Children " +	 
					"FROM Employees e ";
			 
			// if a number greater than 0 was passed as the id
			if (id > 0) {
				// set the where clause to that id
				sql += "WHERE ReportsTo = ?";
				
				// prepare the statement for safe execution
				stmt = conn.prepareStatement(sql);
				
				// pass the id parameter to the sql
				stmt.setInt(1, id);
			} 
			// otherwise...
			else {
				// select where reports to is null which are top level elements
				sql += "WHERE ReportsTo IS NULL";
				// prepare the statement for safe execution
				stmt = conn.prepareStatement(sql);
			}
			
			// execute the statement into a record set
			rs = stmt.executeQuery();
			
			// for each employee in the result set
			while(rs.next()) {
			
				// create a new employee model object
				models.Employee employee = new models.Employee();
				
				// populate the model object with values from the database
				employee.setEmployeeID(rs.getInt("EmployeeID"));
				employee.setName(rs.getString("Name"));
				employee.setHasChildren(rs.getInt("Children") > 0 ? true : false);
				
				// add the employee to the return collection
				employees.add(employee);
			}
		} finally {
			// close the sql objects
			rs.close();
			stmt.close();
		}
		
		// return the list of employees
		return employees;
	}
}
