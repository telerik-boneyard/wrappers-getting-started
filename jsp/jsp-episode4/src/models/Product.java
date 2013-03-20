package models;

// define a Product model object
public class Product {

	private int ProductID;
	private String ProductName;
	private models.Supplier Supplier;
	private models.Category Category;
	private int UnitsInStock;
	private float UnitPrice;
	private boolean Discontinued;
	
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public models.Supplier getSupplier() {
		return Supplier;
	}
	public void setSupplier(models.Supplier supplier) {
		Supplier = supplier;
	}
	public models.Category getCategory() {
		return Category;
	}
	public void setCategory(models.Category category) {
		Category = category;
	}
	public int getUnitsInStock() {
		return UnitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		UnitsInStock = unitsInStock;
	}
	public float getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		UnitPrice = unitPrice;
	}
	public boolean isDiscontinued() {
		return Discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		Discontinued = discontinued;
	}
	
}
