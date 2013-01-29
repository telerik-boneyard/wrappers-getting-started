package models;

public class EmployeeTerritory {

	private int TerritoryID;
	private String TerritoryDescription;
	private int RegionID;
	private String RegionDescription;
	private int EmployeeID;
	
	public int getTerritoryID() {
		return TerritoryID;
	}
	public void setTerritoryID(int territoryID) {
		TerritoryID = territoryID;
	}
	public String getTerritoryDescription() {
		return TerritoryDescription;
	}
	public void setTerritoryDescription(String territoryDescription) {
		TerritoryDescription = territoryDescription;
	}
	public int getRegionID() {
		return RegionID;
	}
	public void setRegionID(int regionID) {
		RegionID = regionID;
	}
	public String getRegionDescription() {
		return RegionDescription;
	}
	public void setRegionDescription(String regionDescription) {
		RegionDescription = regionDescription;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	
}
