package com.kannanrameshrk.flowersales.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kannanrameshrk.flowersales.dto.Admin;
import com.kannanrameshrk.flowersales.dto.FlowerDetails;
import com.kannanrameshrk.flowersales.dto.Flowers;
import com.kannanrameshrk.flowersales.dto.Garland;
import com.kannanrameshrk.flowersales.dto.OrderDetails;
import com.kannanrameshrk.flowersales.dto.User;

public class Repository {
	private static Repository repository;

	private Repository() {

	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean adminLogin(Admin admin) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = DataConnection.getConnection();

		String query = "select * from Admins where username=? AND password=?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, admin.getUserName());
			pstmt.setString(2, admin.getPassword());
			rs = pstmt.executeQuery();
			boolean isEmpty = false;
			while (rs.next()) {
				isEmpty = true;
			}
			return isEmpty;
		} catch (SQLException e) {
			throw new SQLException("Not match your data please Check");
		} finally {
			DataConnection.closeConnection();
			rs.close();
			pstmt.close();
		}
	}

	public void insertFlowerData(Flowers flower) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = DataConnection.getConnection();

		String quary = "insert into Flower(name,price,quantity) values(?,?,?)";

		try {
			pstmt = con.prepareStatement(quary);
			pstmt.setString(1, flower.getFlowerName());
			pstmt.setDouble(2, flower.getPrice());
			pstmt.setInt(3, flower.getQuantity());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException("Failed to insert Flower data: ");
		}
	}

	public void insertGralandData(Garland garland) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = DataConnection.getConnection();

		String query = "insert into garland(garlandName,type,size,price,quantity) values(?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, garland.getGarlandName());
			pstmt.setString(2, garland.getType());
			pstmt.setString(3, garland.getSize());
			pstmt.setDouble(4, garland.getPrice());
			pstmt.setInt(5, garland.getQuantity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Failed to insert Flower data: ");
		}
	}

	public void insertUserData(User user) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = DataConnection.getConnection();

		String query = "insert into customer(name,contact_number,password,address) values(?,?,?,?)";

		try {
			pstmt = con.prepareStatement(query);
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
		ResultSet rs = null;
		Connection con = DataConnection.getConnection();

		String query = "select * from customer where name=? AND password=?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getCustomerName());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			boolean isEmpty = false;
			while (rs.next()) {
				isEmpty = true;
			}
			return isEmpty;
		} catch (SQLException e) {
			throw new SQLException("Not match your data please Check");
		} finally {
			DataConnection.closeConnection();
			rs.close();
			pstmt.close();
		}
	}

	public List<Flowers> getAvailableFlowers() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = DataConnection.getConnection();
		List<Flowers> flowerList = new ArrayList<>();

		String query = "select * from flower";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Flowers flower = new Flowers();
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = DataConnection.getConnection();
		List<Garland> garlandList = new ArrayList<>();

		String query = "select * from garland";

		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Garland garland = new Garland();
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

	public int getUserID(String userName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection con = DataConnection.getConnection();

		String query = "select customer_id from customer where name=?";

		int customerId = -1;

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				customerId = rs.getInt("customer_id");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return customerId;
	}

	public void purchaseFlower(int currentCustomerId, Flowers flower) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = DataConnection.getConnection();

		FlowerDetails details = fetchFlowerDetails(flower.getFlowerID()); 
		int availableQuantity = details.getQuantity();
		double flowerPrice = details.getPrice();
		double totalCost = flowerPrice * flower.getQuantity();

		if (availableQuantity < flower.getQuantity()) {
			System.out.println("Requested quantity exceeds available stock.");
			return;
		}

		String type = "flower";

		String query = "INSERT INTO order_details(customer_id, item_id, item_type, quantity, subTotal, purchase_date) VALUES (?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, currentCustomerId);
			pstmt.setInt(2, flower.getFlowerID());
			pstmt.setString(3, type);
			pstmt.setInt(4, flower.getQuantity());
			pstmt.setDouble(5, totalCost);
			LocalDate currentDate = LocalDate.now();
			Date sqlDate = Date.valueOf(currentDate);
			pstmt.setDate(6, sqlDate);
			pstmt.setDate(6, sqlDate); 
			pstmt.executeUpdate();

			updateFlowerQuantity(flower.getFlowerID(), availableQuantity-flower.getQuantity());
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}

	
	private FlowerDetails fetchFlowerDetails(int flowerID) throws SQLException {
		FlowerDetails details = new FlowerDetails();
		Connection con = DataConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT price, quantity FROM flower WHERE flowerID = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, flowerID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				details.setPrice(rs.getDouble("price"));
				details.setQuantity(rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return details;
	}

	public void updateFlowerQuantity(int flowerId, int newQuantity) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = DataConnection.getConnection();

		String updateQuery = "UPDATE flower SET quantity = ? WHERE flowerID = ?";
		try {
			pstmt = con.prepareStatement(updateQuery);
			pstmt.setInt(1, newQuantity);
			pstmt.setInt(2, flowerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}

	public List<OrderDetails> viewOrder() {
		 List<OrderDetails> orderList = new ArrayList<>();
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    Connection con = DataConnection.getConnection();
		    String query = "SELECT o.order_id, c.name AS customer_name, c.contact_number, f.name AS flower_name, o.quantity, o.subtotal, o.purchase_date " +
		                   "FROM order_details o " +
		                   "JOIN customer c ON o.customer_id = c.customer_id " +
		                   "JOIN flower f ON o.item_id = f.flowerID";

		    try {
		        pstmt = con.prepareStatement(query);
		        rs = pstmt.executeQuery();
		        while (rs.next()) {
		            OrderDetails order = new OrderDetails();
		            order.setOrderId(rs.getInt("order_id"));
		            order.setCustomerName(rs.getString("customer_name"));
		            order.setContactNumber(rs.getString("contact_number"));
		            order.setFlowerName(rs.getString("flower_name"));
		            order.setQuantity(rs.getInt("quantity"));
		            order.setSubtotal(rs.getDouble("subtotal"));
		            order.setPurchaseDate(rs.getDate("purchase_date"));
		            orderList.add(order);
		        }
		    } catch (SQLException e) {
		       System.out.println(e);
		    } 
		    return orderList;
	}

}
