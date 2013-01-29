package models;

public class Employee {

	private int EmployeeID;
	private int ManagerID;
	private String LastName;
	private String FirstName;
	private String FullName;
	private Boolean HasChildren;
	
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public int getMangerID() {
		return ManagerID;
	}
	public void setManagerID(int managerId) {
		ManagerID = managerId;
	}
	public Boolean getHasChildren() {
		return HasChildren;
	}
	public void setHasChildren(Boolean hasChildren) {
		HasChildren = hasChildren;
	}
	public void setFullName() {
		FullName = FirstName + " " + LastName;
	}
}
