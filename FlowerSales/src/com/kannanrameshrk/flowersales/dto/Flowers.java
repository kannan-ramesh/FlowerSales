package com.kannanrameshrk.flowersales.dto;

import java.sql.Date;
import java.time.LocalDate;

public class Flowers {
	private int flowerID;
	private String flowerName;
	private double price;
	private int quantity;
	private LocalDate purchaseDate;

	public Flowers(String flowerName, double price, int quantity) {
		this.flowerName = flowerName;
		this.price = price;
		this.quantity = quantity;
	}

	public Flowers() {
		// TODO Auto-generated constructor stub
	}

	public Flowers(int flowerId, int quantity, LocalDate currentDate) {
		this.flowerID = flowerId;
		this.quantity = quantity;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	
}
