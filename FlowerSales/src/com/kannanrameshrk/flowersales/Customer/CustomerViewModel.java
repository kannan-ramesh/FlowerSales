package com.kannanrameshrk.flowersales.Customer;

import java.sql.SQLException;
import java.util.List;

import com.kannanrameshrk.flowersales.dto.Flowers;
import com.kannanrameshrk.flowersales.dto.Garland;
import com.kannanrameshrk.flowersales.dto.User;
import com.kannanrameshrk.flowersales.repository.Repository;

class CustomerViewModel {

	private CustomerView customerView;

	public CustomerViewModel(CustomerView customerView) {
		this.customerView = customerView;
	}

	public void validateUser(User user) throws SQLException {
		boolean name = nameIsValid(user.getCustomerName());
		boolean contact = contactIsValid(user.getContactNumber());
		boolean password = passIsValid(user.getPassword());
		
		
		if (name && contact && password) {
			Repository.getInstance().insertUserData(user);
		} else {
			customerView.showErr("Invalid Input");
		}
	}

	private boolean contactIsValid(String contactNumber) {
		if(contactNumber != null && contactNumber.matches("\\d{10}")) {
			return true;
		}
		return false;
	}

	private boolean passIsValid(String password) {
		if(password != null && (password.length()>=7&& password.length()<=15)) {
			return true;
		}
		return false;
	}

	private boolean nameIsValid(String customerName) {
		if(customerName != null && customerName.matches("[A-Za-z\\s]{3,50}")) {
			return true;
		}
		return false;
	}

	public boolean checkUser(User user) throws SQLException {
		return Repository.getInstance().checkUser(user);
	}

	public List<Flowers> getAvailableFlowers()  {
		try {
			return Repository.getInstance().getAvailableFlowers();
		} catch (SQLException e) {
			customerView.showErr("err get data"+e);
		}
		return null;
	}

	public List<Garland> getAvailableGarlands() {
		try {
			return Repository.getInstance().getAvailableGarlands();
		} catch (SQLException e) {
			customerView.showErr("err get data"+e);
		}
		return null;
	}

	public int getCurrentCustomerId(String userName) {
		int id = -1;
		try {
			id = Repository.getInstance().getUserID(userName);
		} catch (SQLException e) {
			customerView.showErr("Not Found Id"+e);
		}
		return id;
	}

	public void purchaseFlower(int currentCustomerId, Flowers flower) throws SQLException {
		 Repository.getInstance().purchaseFlower(currentCustomerId,flower);
	}
}
