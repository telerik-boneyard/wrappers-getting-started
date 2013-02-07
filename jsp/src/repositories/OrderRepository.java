package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends RepositoryBase {

	public OrderRepository(String path) {
		super(path);
	}
	
	public List<models.Order> listOrders(String customerId) {
		
		List<models.Order> orders = new ArrayList<models.Order>();
		
		try {
			
			PreparedStatement stmt = null;
			
			String sql = "SELECT o.CustomerID, o.EmployeeID, o.OrderDate, o.RequiredDate, " +
						 "o.ShippedDate, o.ShipVia, o.Freight " +
						 "FROM Orders o " +
						 "JOIN Customers c ON o.CustomerID = c.CustomerID " +
						 "WHERE o.CustomerID = ?";
			
			stmt = super.connection.prepareStatement(sql);
			
			stmt.setString(1, customerId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				models.Order order = new models.Order();
				
				order.setOrderDate(rs.getDate("RequiredDate"));
				order.setShippedDate(rs.getDate("ShippedDate"));
				order.setFreight(rs.getFloat("Freight"));
				order.setShipVia(rs.getString("ShipVia"));
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
}
