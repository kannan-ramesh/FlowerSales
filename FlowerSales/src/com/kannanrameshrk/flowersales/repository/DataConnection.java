package com.kannanrameshrk.flowersales.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
	private static String url="jdbc:mysql://localhost:3306/flowershop";
	private static String user="root";
	private static String pass="15410198";
	
	private static Connection con;
	
	private DataConnection() {
		
	}
	
	public static Connection getConnection() {
		if(con==null) {
			try {
				con=DriverManager.getConnection(url,user,pass);
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("Data is not connected..");
			}
		}
		return con;
	}
	
	public static void closeConnection() {
		if(con!=null) {
			try {
				con.close();
				con=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
