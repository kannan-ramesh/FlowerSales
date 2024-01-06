package com.kannanrameshrk.flowersales.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kannanrameshrk.flowersales.dto.Admin;
import com.kannanrameshrk.flowersales.dto.Flowers;
import com.kannanrameshrk.flowersales.dto.Garland;
import com.kannanrameshrk.flowersales.dto.User;

public class Repository {
	private static Repository repository;
	
	private Repository() {
		
	}
	
	public static Repository getInstance() {
		if(repository==null) {
			repository=new Repository();
		}
		return repository;	
	}

	public boolean adminLogin(Admin admin) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Connection con=DataConnection.getConnection();
		
		String query="select * from Admins where username=? AND password=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, admin.getUserName());
			pstmt.setString(2, admin.getPassword());
			rs=pstmt.executeQuery();
			boolean isEmpty = false;
			while(rs.next()) {
				isEmpty = true;
			}
			return isEmpty;
		} catch (SQLException e) {
			throw new SQLException("Not match your data please Check");
		}finally {
			DataConnection.closeConnection();
			rs.close();
			pstmt.close();
		}
	}

	public void insertFlowerData(Flowers flower) throws SQLException {
		PreparedStatement pstmt=null;
		Connection con=DataConnection.getConnection();
		
		String quary="insert into Flower(name,price,quantity) values(?,?,?)";
		
		try {
			pstmt=con.prepareStatement(quary);
			pstmt.setString(1, flower.getFlowerName());
			pstmt.setDouble(2, flower.getPrice());
			pstmt.setInt(3, flower.getQuantity());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			 throw new SQLException("Failed to insert Flower data: ");
		}
	}

	public void insertGralandData(Garland garland) throws SQLException {
		PreparedStatement pstmt=null;
		Connection con=DataConnection.getConnection();
		
		String query="insert into garland(garlandName,type,size,price,quantity) values(?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, garland.getGarlandName());
			pstmt.setString(2, garland.getType());
			pstmt.setString(3, garland.getSize());
			pstmt.setDouble(4, garland.getPrice());
			pstmt.setInt(5,garland.getQuantity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			 throw new SQLException("Failed to insert Flower data: ");
		}
	}

	public void insertUserData(User user) throws SQLException {
		PreparedStatement pstmt=null;
		Connection con=DataConnection.getConnection();
		
		String query="insert into customer(name,contact_number,password,address) values(?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, user.getCustomerName());
			pstmt.setString(2, user.getContactNumber());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			 throw new SQLException(e);
		}
	}

	public boolean checkUser(User user) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Connection con=DataConnection.getConnection();
		
		String query="select * from customer where name=? AND password=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, user.getCustomerName());
			pstmt.setString(2, user.getPassword());
			rs=pstmt.executeQuery();
			boolean isEmpty = false;
			while(rs.next()) {
				isEmpty = true;
			}
			return isEmpty;
		} catch (SQLException e) {
			throw new SQLException("Not match your data please Check");
		}finally {
			DataConnection.closeConnection();
			rs.close();
			pstmt.close();
		}
	}

	public List<Flowers> getAvailableFlowers() throws SQLException {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con=DataConnection.getConnection();
		List<Flowers> flowerList = new ArrayList<>();
		
		String query="select * from flower";
		
		try {
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Flowers flower=new Flowers();
				flower.setFlowerID(rs.getInt("flowerID"));
				flower.setFlowerName(rs.getString("name"));
				flower.setPrice(rs.getDouble("price"));
				flower.setQuantity(rs.getInt("quantity"));
				flowerList.add(flower);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return flowerList;
	}

	public List<Garland> getAvailableGarlands() throws SQLException {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con=DataConnection.getConnection();
		List<Garland> garlandList = new ArrayList<>();
		
		String query="select * from garland";
		
		try {
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Garland garland=new Garland();
				garland.setGarlandID(rs.getInt("garlandID"));
				garland.setGarlandName(rs.getString("garlandName"));
				garland.setType(rs.getString("type"));
				garland.setSize(rs.getString("size"));
				garland.setPrice(rs.getDouble("price"));
				garland.setQuantity(rs.getInt("quantity"));
				garlandList.add(garland);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return garlandList;
	}
}
