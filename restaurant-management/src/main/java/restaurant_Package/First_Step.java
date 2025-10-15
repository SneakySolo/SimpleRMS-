package restaurant_Package;

import java.util.Scanner;

public class First_Step {

	public static void main(String[] args) {
		System.out.println("Greetings, select role - ");
		System.out.println("1. Admin");
		System.out.println("2. Restaurant Owner");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		if (choice == 1) {
			Admin_menu.admin_menu();
		}
		else if (choice == 2) {
			Owner_login.owner_login();
		}
		else {
			System.out.println("Enter a valid input");
		}
	}

}
