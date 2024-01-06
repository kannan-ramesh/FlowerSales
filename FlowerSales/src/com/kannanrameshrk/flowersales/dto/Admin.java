package com.kannanrameshrk.flowersales.dto;

public class Admin {
	private String userName;
	private String password;
	
	public Admin(String username, String pass) {
		this.userName=username;
		this.password =pass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
