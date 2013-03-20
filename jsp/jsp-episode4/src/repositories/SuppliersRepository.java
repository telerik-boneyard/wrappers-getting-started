package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Supplier;

public class SuppliersRepository extends Repository {

	public SuppliersRepository(String path) {

		// call the base class passing in the path to the sqlit3 database
		super(path);
	}
	
	public List<models.Supplier> listSuppliers() throws SQLException {
		
		// create an empty list of suppliers to send back as the response
		List<models.Supplier> suppliers = new ArrayList<models.Supplier>();
		
		// sql objects declared outside of the try so they can be closed in
		// the finally
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// prepare the sql string for safe execution
			stmt = super.conn.prepareStatement("SELECT SupplierID, CompanyName FROM Suppliers");
			
			// execute the query and store the results in the resultset
			rs = stmt.executeQuery();
			
			// for each item in the resultset
			while(rs.next()) {
				
				// create a new supplier model object and populate it with the values from this supplier
				// item from the database
				models.Supplier supplier = new Supplier(rs.getInt("SupplierID"),
						rs.getString("CompanyName"));
				
				// add this model item to the return list
				suppliers.add(supplier);
			}
		} finally {
			// close the sql objects
			rs.close();
			stmt.close();
		}
		
		// return the list of suppliers
		return suppliers;
	}
	
}
