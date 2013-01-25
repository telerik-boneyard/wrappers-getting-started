package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.PayHistory;

public class PayHistoryRepository {

	Connection _conn;
	
	public PayHistoryRepository() {
		
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
	
	public List<PayHistory> listPayHistory(int id) throws SQLException {
	
		List<PayHistory> payHistories = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		
			// create a prepared statement to get the PayHistory for this employee
			stmt = _conn.prepareStatement("SELECT EmployeeID, RateChangeDate, Rate, PayFrequency " +
										  "FROM employeepayhistory " +
										  "WHERE EmployeeID = ?");
			
			// add the parameter to the query
			stmt.setInt(1, id);
			
			// execute the query to a ResultSet
			rs = stmt.executeQuery();
			
			// initialize the return list with a size
			payHistories = new ArrayList<PayHistory>();
			
			// iterate through the results and build the JSON data list
			while (rs.next()) {
				
				// create a new PayHistory class
				PayHistory payHistory = new PayHistory();
				
				// populate it's properties
				payHistory.setEmployeeID(rs.getInt("EmployeeID"));
				payHistory.setRate(rs.getFloat("Rate"));
				payHistory.setRateChangeDate(rs.getDate("RateChangeDate"));
				payHistory.setPayFrequency(rs.getInt("PayFrequency"));
			
				// add the object to the return collection
				payHistories.add(payHistory);
			}
			
			// close out the ResultSet and PreparedStatement
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			throw e;
		}
		
		return payHistories;
 	}
	
	public void updatePayHistory(int payHistoryId, String rateChangeDate, float rate, int payFrequency) throws SQLException {
		
		try {
			
			// prepare the update query
			PreparedStatement stmt = _conn.prepareStatement("UPDATE employeepayhistory SET Rate = ?, PayFrequency = ?, RateChangeDate = ?, WHERE PayHistoryID = ?");
			stmt.setFloat(1, rate);
			stmt.setInt(2, payFrequency);
			stmt.setInt(3, payHistoryId);
			
			// execute the statement
			stmt.executeUpdate();
		
			stmt.close();
			
		} catch (SQLException e) {			
			throw e;
		}
	
	}
	
//	public void createPayHistory(int employeeId, String rateChangeDate, float rate, int payFrequency) throws SQLException {
//	
//		try {
//			
//			// create the pay history record
//			PreparedStatement stmt = _conn.prepareStatement("INSERT INTO employeepayhistory (EmployeeID, Rate, RateChangeDate, PayFrequency) VALUES (?, ?, ?, ?)");
//				
//			// add the parameters to the prepared statement
//			stmt.setInt(1, employeeId);
//			stmt.setFloat(2, rate);
//			stmt.setDate(3, java.sql.Date.valueOf(rateChangeDate));
//			stmt.setInt(4, payFrequency);
//				
//			stmt.executeUpdate();
//			
//			stmt.close();
//				
//		} catch (SQLException e) {
//			throw e;
//		}
//	}
	
}
