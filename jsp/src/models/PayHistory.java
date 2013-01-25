package models;

import java.util.Date;

public class PayHistory {

	private int employeeID;
	private float rate;
	private Date rateChangeDate;
	private int payFrequency;
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Date getRateChangeDate() {
		return rateChangeDate;
	}
	public void setRateChangeDate(Date rateChangeDate) {
		this.rateChangeDate = rateChangeDate;
	}
	public int getPayFrequency() {
		return payFrequency;
	}
	public void setPayFrequency(int payFrequency) {
		this.payFrequency = payFrequency;
	}
}

