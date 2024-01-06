package com.kannanrameshrk.flowersales.dto;

public class Garland {
	private int garlandID;
	private String garlandName;
	private String type;
	private String size;
	private double price;
	private int quantity;
	
	public Garland(String garlandName, String garlandType, String size, double garlandPrice, int garlandQuantity) {
		this.garlandName=garlandName;
		this.type=garlandType;
		this.size=size;
		this.price=garlandPrice;
		this.quantity=garlandQuantity;
	}
	public Garland() {
		// TODO Auto-generated constructor stub
	}
	public int getGarlandID() {
		return garlandID;
	}
	public void setGarlandID(int garlandID) {
		this.garlandID = garlandID;
	}
	public String getGarlandName() {
		return garlandName;
	}
	public void setGarlandName(String garlandName) {
		this.garlandName = garlandName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
