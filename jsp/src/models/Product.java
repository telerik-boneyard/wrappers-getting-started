package models;

public class Product {

	private int ProductID;
	private String ProductName;
	private models.Supplier Supplier;
	private models.Category Category;
	private String QuantityPerUnit;
	private float UnitPrice;
	private int UnitsInStock;
	private Boolean Discontinued;
	
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
	public String getQuantityPerUnit() {
		return QuantityPerUnit;
	}
	public void setQuantityPerUnit(String quantityPerUnit) {
		QuantityPerUnit = quantityPerUnit;
	}
	public float getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		UnitPrice = unitPrice;
	}
	public int getUnitsInStock() {
		return UnitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		UnitsInStock = unitsInStock;
	}
	public Boolean getDiscontinued() {
		return Discontinued;
	}
	public void setDiscontinued(Boolean discontinued) {
		Discontinued = discontinued;
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
	
}
