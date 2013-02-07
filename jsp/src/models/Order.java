package models;

import java.util.Date;

public class Order {

	Date OrderDate;
	Date RequiredDate;
	Date ShippedDate;
	String ShipVia;
	float Freight;
	
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public Date getRequiredDate() {
		return RequiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		RequiredDate = requiredDate;
	}
	public Date getShippedDate() {
		return ShippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		ShippedDate = shippedDate;
	}
	public String getShipVia() {
		return ShipVia;
	}
	public void setShipVia(String shipVia) {
		ShipVia = shipVia;
	}
	public float getFreight() {
		return Freight;
	}
	public void setFreight(float freight) {
		Freight = freight;
	}
	
}
