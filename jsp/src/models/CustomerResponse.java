package models;

import java.util.List;

public class CustomerResponse {

	private int Count;
	private List<models.Customer> Data;
	
	public List<models.Customer> getData() {
		return Data;
	}
	public void setData(List<models.Customer> data) {
		Data = data;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	
}
