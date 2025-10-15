package restaurant_Package;

import java.util.Scanner;

public class Admin_menu {

	public static void admin_menu() {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("Press 1 to enter data from a csv file");
			System.out.println("Press 2 to enter data manually");
			System.out.println("Press 3 to Exit");
			
			System.out.println("Enter your choice : ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
				case 1 :System.out.println("Press 1 to enter data as a new restaurat");
						System.out.println("Press 2 to enter data as a new menu item");
						System.out.println("Press 3 to go back");
						
						int choice1 = sc.nextInt();
						sc.nextLine();
						
						if (choice1 == 1) {
							Insert_automatic_restaurant.insert_auto_res();
							break;
						}
						else if (choice1 == 2) {
							Insert_automatic_menu.insert_auto_menu();
							break;
						}
						else {
							break;
						}
				
				case 2 :while (true) {
					System.out.println("Press 1 to enter data as a new restaurat");
					System.out.println("Press 2 to enter data as a new menu item");
					System.out.println("Press 3 to enter a new restaurant owner");
					System.out.println("Press 4 to go back");
					
					int choice2 = sc.nextInt();
					sc.nextLine();
					
					if (choice2 == 1) {
						while (true) {
							Manual_insert_restaurant.manual_ins_res();
							System.out.println("To add another one press C or press Q to exit");
							String option = sc.nextLine();
							if (option.equalsIgnoreCase("Q")) {
								break;
							}
						}
					}
					else if (choice2 == 2) {
						while (true) {
							Manual_insert_menu.manual_ins_menu();
							System.out.println("To add another one press C or press Q to exit");
							String option = sc.nextLine();
							if (option.equalsIgnoreCase("Q")) {
								break;
							}
						}
					}
					else if (choice2 == 3) {
						while (true) {
							Manual_insert_owner.manual_ins_owner();
							System.out.println("To add another owner press C or press Q to exit");
							String option = sc.nextLine();
							if (option.equalsIgnoreCase("Q")) {
								break;
							}
						}
					}
					else {
						break;
					}
				}
				break;
				
				case 3 :System.out.println("Exiting................");
						System.out.println("Exited");
                		return;
                		
				default :System.out.println("Please enter a valid choice");
                		 System.out.println();
                		 break;
			}
		}

	}

}
