package com.kannanrameshrk.flowersales.dto;

public class User {
	private String customerName;
	private String contactNumber;
	private String password;
	private String address;
	
	
	public User(String customerName, String contactNumber, String password, String address) {
		this.customerName=customerName;
		this.contactNumber=contactNumber;
		this.password=password;
		this.address=address;
	}
	public User(String userName, String pass) {
		this.customerName=userName;
		this.password=pass;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
