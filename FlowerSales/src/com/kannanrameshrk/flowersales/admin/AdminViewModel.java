package com.kannanrameshrk.flowersales.admin;

import java.sql.SQLException;
import java.util.List;

import com.kannanrameshrk.flowersales.dto.Admin;
import com.kannanrameshrk.flowersales.dto.Flowers;
import com.kannanrameshrk.flowersales.dto.Garland;
import com.kannanrameshrk.flowersales.dto.OrderDetails;
import com.kannanrameshrk.flowersales.repository.Repository;

class AdminViewModel {
	private AdminView adminView;

	public AdminViewModel(AdminView adminView) {
		this.adminView = adminView;
	}

	public boolean checkAdmin(Admin admin) throws SQLException {
		boolean valid = Repository.getInstance().adminLogin(admin);
		return valid;
	}

	public List<OrderDetails> viewOrders() {
		List<OrderDetails> orderList=Repository.getInstance().viewOrder();
		if(orderList.isEmpty()) {
			adminView.showErr("No data found....");
		}
		return orderList;
	}

	public void validate(Flowers flower) throws SQLException {
		boolean name=nameIsvalid(flower.getFlowerName());
		boolean price=numberIsValid(flower.getPrice());
		boolean quantity=numberIsValid(flower.getQuantity());
		
		if(name && price && quantity) {
			Repository.getInstance().insertFlowerData(flower);
		}else {
			adminView.showErr("Please check your input..");
		}
	}

	private boolean numberIsValid(double price) {
		 boolean isValid = price >= 0;
		    if (!isValid) {
		        adminView.showErr("Invalid number format!");
		    }
		    return isValid;
	}

	private boolean nameIsvalid(String flowerName) {
		 boolean isValid = flowerName.matches("[A-Za-z ]{2,30}");
		    if (!isValid) {
		        adminView.showErr("Invalid name format!");
		    }
		    return isValid;
	}

	public void validateGarland(Garland garland) throws SQLException {
		boolean name=nameIsvalid(garland.getGarlandName());
		boolean type=nameIsvalid(garland.getType());
		boolean size=nameIsvalid(garland.getSize());
		boolean price=numberIsValid(garland.getPrice());
		boolean quantity=numberIsValid(garland.getQuantity());
		
		if(name && type && size && price && quantity) {
			Repository.getInstance().insertGralandData(garland);
		}else {
			adminView.showErr("Please check your input..");
		}
	}
}
