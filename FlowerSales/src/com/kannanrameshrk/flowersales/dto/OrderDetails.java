package com.kannanrameshrk.flowersales.dto;

import java.sql.Date;

public class OrderDetails {
	 	private int orderId;
	    private String customerName;
	    private String contactNumber;
	    private String flowerName;
	    private int quantity;
	    private double subtotal;
	    
	    public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getFlowerName() {
			return flowerName;
		}
		public void setFlowerName(String flowerName) {
			this.flowerName = flowerName;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getSubtotal() {
			return subtotal;
		}
		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}
		public Date getPurchaseDate() {
			return purchaseDate;
		}
		public void setPurchaseDate(Date purchaseDate) {
			this.purchaseDate = purchaseDate;
		}
		private Date purchaseDate;
}
