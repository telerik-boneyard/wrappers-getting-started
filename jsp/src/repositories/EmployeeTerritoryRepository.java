package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import models.EmployeeTerritory;

public class EmployeeTerritoryRepository {

	Connection _conn;
	
	public EmployeeTerritoryRepository() { }
	
	public EmployeeTerritoryRepository(String path) {

		// initialize the database driver
        try {
        	Class.forName("org.sqlite.JDBC");
        	
			// set the connection instance
			_conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<EmployeeTerritory> listEmployeeTerritories(int employeeId) throws Exception{
		
		List<EmployeeTerritory> employeeTerritories = new ArrayList<EmployeeTerritory>();
		
		try {
			
			String sql = "SELECT et.EmployeeID, t.TerritoryID, t.TerritoryDescription, r.RegionID, r.RegionDescription " +
						 "FROM EmployeeTerritories et " +
						 "JOIN Territories t ON et.TerritoryID = t.TerritoryID " +
						 "JOIN Regions r ON t.RegionID = r.RegionID " +
						 "WHERE et.EmployeeID = ?";
			
			PreparedStatement stmt = _conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				EmployeeTerritory employeeTerritory = new EmployeeTerritory();
				
				employeeTerritory.setEmployeeID(rs.getInt("EmployeeID"));
				employeeTerritory.setTerritoryID(rs.getInt("TerritoryID"));
				employeeTerritory.setTerritoryDescription(rs.getString("TerritoryDescription"));
				employeeTerritory.setRegionID(rs.getInt("RegionID"));
				employeeTerritory.setRegionDescription(rs.getString("RegionDescription"));
				
				employeeTerritories.add(employeeTerritory);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		
		return employeeTerritories;
	}
}
