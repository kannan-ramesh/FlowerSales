package com.kannanrameshrk.flowersales.dto;

public class Flowers {
	private int  flowerID;
	private String flowerName;
	private double price;
	private int quantity;
	
	
	public Flowers(String flowerName, double price, int quantity) {
		this.flowerName=flowerName;
		this.price=price;
		this.quantity=quantity;
	}
	public Flowers() {
		// TODO Auto-generated constructor stub
	}
	public int getFlowerID() {
		return flowerID;
	}
	public void setFlowerID(int flowerID) {
		this.flowerID = flowerID;
	}
	public String getFlowerName() {
		return flowerName;
	}
	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
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
	public void setQuantity( int quantity) {
		this.quantity = quantity;
	}
}
