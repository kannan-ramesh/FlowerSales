package com.kannanrameshrk.flowersales.admin;

import java.sql.SQLException;
import java.util.Scanner;

import com.kannanrameshrk.flowersales.dto.Admin;
import com.kannanrameshrk.flowersales.dto.Flowers;
import com.kannanrameshrk.flowersales.dto.Garland;

public class AdminView {
	private AdminViewModel adminViewModel;
	private static boolean isAdminLoggedIn =false;
	static boolean isValid = false;

	public AdminView() {
		adminViewModel = new AdminViewModel(this);
	}

	public void init(Scanner input) {
		boolean loop = true;
		while (loop) {
			if (!isAdminLoggedIn) {
				System.out.println("Admin Login");
				System.out.println("------------");
				System.out.println("Enter user name:");
				String username = input.next();
				System.out.println("Enter enter password:");
				String pass = input.next();
				Admin admin = new Admin(username,pass);
				try {
					isValid = adminViewModel.checkAdmin(admin);
				} catch (SQLException e) {
					System.out.println("Not match your userName Password...");
				}

				if (isValid) {
					isAdminLoggedIn = true;
					System.out.println("Admin logged in successfully!");
				} else {
					System.out.println("Please Check userName Password...");
				}
			} else {
				System.out.println("Admin Menu:");
				System.out.println("1. Add Flower");
				System.out.println("2. Add Garland");
				System.out.println("3. View Orders");
				System.out.println("4. Logout");

				System.out.print("Select an option: ");
				int choice = input.nextInt();
				input.nextLine();

				switch (choice) {
				case 1:
					System.out.println("Enter Flower Name:");
					String flowerName = input.nextLine();
					System.out.println("Enter Price:");
					double price = input.nextDouble();
					System.out.println("Enter Quantity:");
					int quantity = input.nextInt();
					Flowers flower = new Flowers(flowerName, price, quantity);
					try {
						adminViewModel.validate(flower);
						System.out.println("Data added Successfully...");
					} catch (SQLException e) {
						System.out.println("Not insert data" + e);
					}
					break;
				case 2:
					System.out.println("Enter Garland Name:");
					String garlandName =input.nextLine();
					System.out.println("Garland type:");
					String garlandType = input.nextLine();
					System.out.println("ENter garland Size:");
					String size=input.next();
					System.out.println("Enter Garland Price:");
					double garlandPrice=input.nextDouble();
					System.out.println("Garlant Quantity:");
					int garlandQuantity = input.nextInt();
					Garland garland = new Garland(garlandName,garlandType,size,garlandPrice,garlandQuantity);
					try {
						adminViewModel.validateGarland(garland);
						System.out.println("Data added Successfully...");
					} catch (SQLException e) {
						System.out.println("Not insert data" + e);
					}
					break;
				case 3:
					adminViewModel.viewOrders();
					break;
				case 4:
					isAdminLoggedIn = false;
					System.out.println("Admin logged out successfully!");
					loop = false;
					break;
				default:
					System.out.println("Invalid option.");
				}
			}

		}

	}

	public void showErr(String errMessage) {
		System.out.println(errMessage);
	}
}
