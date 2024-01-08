package com.kannanrameshrk.flowersales.Customer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.kannanrameshrk.flowersales.dto.Flowers;
import com.kannanrameshrk.flowersales.dto.Garland;
import com.kannanrameshrk.flowersales.dto.User;

public class CustomerView {
	private CustomerViewModel customerViewModel;
	private static boolean customerIsLoggedIn = false;
	private static int currentCustomerId = -1;
	static boolean isValid = false;

	public CustomerView() {
		customerViewModel = new CustomerViewModel(this);
	}

	public void init(Scanner input) {
		LocalDate currentDate = LocalDate.now();
		boolean loop = true;
		while (loop) {
			if (!customerIsLoggedIn) {
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");
				int choice = input.nextInt();
				input.nextLine();

				switch (choice) {
				case 1: {
					System.out.println("\t\tRegister Page");
					System.out.println("\t\t--------------");
					System.out.println("Enter your Name:");
					String customerName = input.nextLine();
					System.out.println("Enter Contact Number:");
					String contactNumber = input.next();
					System.out.println("Enter Your Password:");
					String password = input.next();
					input.nextLine();
					System.out.println("Enter Address:");
					String address = input.nextLine();
					User user = new User(customerName, contactNumber, password, address);
					try {
						customerViewModel.validateUser(user);
						onSuccess("Insert Data Successfully:");
					} catch (SQLException e) {
						showErr("Failed Insert Data");
					}
					break;
				}
				case 2: {
					System.out.println("\t\tLogin Page");
					System.out.println("\t\t-----------");
					System.out.println("Enter user Name:");
					String userName = input.nextLine();
					System.out.println("Enter Password:");
					String pass = input.next();
					User user = new User(userName, pass);
					try {
						isValid = customerViewModel.checkUser(user);
					} catch (SQLException e) {
						System.out.println(e);
					}
					if (isValid) {
						customerIsLoggedIn = true;
						System.out.println("User logged in successfully!");

						currentCustomerId = customerViewModel.getCurrentCustomerId(userName);
						System.out.println("Your User Id is:"+currentCustomerId  );
						List<Flowers> availableFlowers = customerViewModel.getAvailableFlowers();
						List<Garland> availableGarlands = customerViewModel.getAvailableGarlands();

						System.out.println("\t\tAvailable Flowers:");
						System.out.println("\t\t-------------------");
						System.out.printf("%2s %9s %18s %10s%n", "FlowerID", "Flower", "Price", "Quantity");
						System.out.println("*********************************************************");
						for (Flowers flower : availableFlowers) {
							System.out.printf("%-10d %-20s %-10.2f %-10d%n", flower.getFlowerID(),
									flower.getFlowerName(), flower.getPrice(), flower.getQuantity());
						}
						System.out.println("*********************************************************");
						System.out.println();

						System.out.printf("%-10s %-20s %-13s %-7s %-10s %-8s%n", "GarlandID", "Garland", "Type", "Size",
								"Price", "Quantity");
						System.out
								.println("**************************************************************************");
						for (Garland garland : availableGarlands) {
							System.out.printf("%-10d %-20s %-13s %-7s %-10.2f %-8d%n", garland.getGarlandID(),
									garland.getGarlandName(), garland.getType(), garland.getSize(), garland.getPrice(),
									garland.getQuantity());
						}

					} else {
						System.out.println("Please Check userName Password...");
					}
					break;
				}
				case 3: {
					System.out.println("Exit");
					loop = false;
					break;
				}
				default: {
					System.out.println("Invalid Option...");
					break;
				}
				}
			} else {
				System.out.println();
				System.out.println("Welcome! What would you like to purchase?");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("^ 1. Purchase Flower         ^");
				System.out.println("^ 2. Purchase Garland        ^");
				System.out.println("^ 3. Logout                  ^");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.print("Enter your choice: ");
				int purchaseChoice = input.nextInt();
				input.nextLine();

				switch (purchaseChoice) {
				case 1:
					// Purchase flower logic
					LocalDate currentDate1 = LocalDate.now();
					System.out.println("Enter flower Id:");
					int flowerId=input.nextInt();
					System.out.println("Enter quantity:");
					int quantity=input.nextInt();
					Flowers flower =new Flowers(flowerId,quantity,currentDate1);
					try {
						customerViewModel.purchaseFlower(currentCustomerId, flower);
						System.out.println("Successfully Purchased..");
					} catch (SQLException e) {
						System.out.println("Not purchase in flower"+e);
					}
					break;
				case 2:
					// Purchase garland logic
					//customerViewModel.purchaseGarland(currentCustomerId, input);
					break;
				case 3:
					customerIsLoggedIn = false;
					currentCustomerId = -1;
					System.out.println("Logged out successfully!");
					break;
				default:
					System.out.println("Invalid option.");
					break;
				}
			}

		}
	}

	public void showErr(String errMessage) {
		System.out.println(errMessage);
	}

	public void onSuccess(String onSuccess) {
		System.out.println(onSuccess);
	}
}
