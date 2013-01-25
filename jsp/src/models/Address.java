package models;

public class Address {
	
	private int addressID;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private int stateProvinceID;
	private String postalCode;
	
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getStateProvinceID() {
		return stateProvinceID;
	}
	public void setStateProvinceID(int stateProvinceID) {
		this.stateProvinceID = stateProvinceID;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}
