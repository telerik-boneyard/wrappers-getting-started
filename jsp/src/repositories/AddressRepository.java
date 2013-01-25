package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Address;

public class AddressRepository {

	Connection _conn;
	
	public AddressRepository() {
		
		// initialize the database driver
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// set the connection instance
			_conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/adventureworks", "root", "root");
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Address> listAddresses (int employeeId) throws SQLException {
		
		List<Address> addresses = null;
		
		try {
			
			addresses = new ArrayList<Address>();
			
			PreparedStatement stmt = _conn.prepareStatement("SELECT a.AddressID, a.AddressLine1, a.AddressLine2, a.City, a.StateProvinceID, a.PostalCode " +
															"FROM address a " +
															"JOIN employeeaddress e ON e.AddressID = a.AddressID " +
															"WHERE e.EmployeeID = ?");
			
			stmt.setInt(1, employeeId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Address address = new Address();
				
				address.setAddressID(rs.getInt("AddressID"));
				address.setAddressLine1(rs.getString("AddressLine1"));
				address.setAddressLine2(rs.getString("AddressLine2"));
				address.setCity(rs.getString("City"));
				address.setStateProvinceID(rs.getInt("StateProvinceID"));
				address.setPostalCode(rs.getString("PostalCode"));
			
				addresses.add(address);
			}
		
		} catch (SQLException e) {
			throw e;
		}
		
		return addresses;
		
	}
	
}
