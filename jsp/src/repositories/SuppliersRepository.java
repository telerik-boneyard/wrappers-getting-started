package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersRepository extends RepositoryBase {

	public SuppliersRepository(String path) {
		super(path);
	}
	
	public List<models.Supplier> listSuppliers() throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<models.Supplier> suppliers = new ArrayList<models.Supplier>();
		
		try {
			
			String sql = "SELECT SupplierID, CompanyName AS SupplierName FROM Suppliers";
			
			stmt = super.connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				models.Supplier supplier = new models.Supplier();
				
				supplier.setSupplierID(rs.getInt("SupplierID"));
				supplier.setSupplierName(rs.getString("SupplierName"));
				
				suppliers.add(supplier);
			}
	 	}
		finally {
			stmt.close();
			rs.close();
		}
		
		return suppliers;
		
	}
}
