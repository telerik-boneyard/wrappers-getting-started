package models;

// the employees model defines for the application what an employee looks like
public class Employee {

	private int EmployeeID;
	private String Name;
	private boolean HasChildren;
	public boolean setHasChildren() {
		return HasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		HasChildren = hasChildren;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
}
