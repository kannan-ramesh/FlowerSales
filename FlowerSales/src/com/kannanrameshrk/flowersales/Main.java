package com.kannanrameshrk.flowersales;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kannanrameshrk.flowersales.Customer.CustomerView;
import com.kannanrameshrk.flowersales.admin.AdminView;

public class Main {

	public static void main(String[] args) {
		Main main=new Main();
		main.start();
	}

	private void start() {
		Scanner input=new Scanner(System.in);
		System.out.println("\t\tRK FLOWERS SHOP");
		System.out.println("\t\t*******************");
		
		boolean loop=true;
		
		 do{
			System.out.println(" 1.Admin\n 2.User\n 3.Exit");
			System.out.println(" Enter Your Option:");
			System.out.println(" ******************");
			int choice=0;
			try {
				choice=input.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Enter option is wrong please enter number only..");
				input.next();
			}
			switch(choice) {
			case 1:{
				System.out.println("\t\t--------------");
				System.out.println("\t\t| Admin Page  |");
				System.out.println("\t\t--------------");
				AdminView adminView=new AdminView();
				adminView.init(input);
				break;
			}
			case 2:{
				System.out.println("\t\t-----------");
				System.out.println("\t\t User Page");
				System.out.println("\t\t-----------");
				CustomerView customerView = new CustomerView();
				customerView.init(input);
				break;
			}case 3:{
				System.out.println("Exit the application......");
				loop=false;
				break;
			}
			default:{
				System.out.println("Invalid Choice ..");
				break;
			}
			}
		}while(loop);
	}

}
