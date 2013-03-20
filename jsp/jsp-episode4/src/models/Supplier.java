package models;

// define a supplier model object
public class Supplier {

	private int SupplierID;
	private String SupplierName;
	
	public int getSupplierID() {
		return SupplierID;
	}
	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}
	
	public Supplier() { }
	
	public Supplier(int supplierID, String supplierName) {
		super();
		SupplierID = supplierID;
		SupplierName = supplierName;
	}
}
