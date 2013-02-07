package models;

import java.util.List;

public class DataSourceResult {

	private List<?> Data;
	private int Total;
	
	public List<?> getData() {
		return Data;
	}
	public void setData(List<?> data) {
		Data = data;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	
}
