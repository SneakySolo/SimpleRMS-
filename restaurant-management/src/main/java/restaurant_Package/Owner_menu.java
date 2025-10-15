package restaurant_Package;

import java.util.Scanner;

public class Owner_menu {
	
	public static void initail_menu() {
		System.out.println();
		System.out.println("1. Place Order");
		System.out.println("2. Insert Menu Item");
		System.out.println("3. View Menu");
		System.out.println("4. View Sales");
		System.out.println("5. Manage Inventory");
		System.out.println("6. EXIT");
	}
	
	public static void owner_menu(int rest_id) {
		
		while (true) {
			Scanner sc = new Scanner(System.in);
			initail_menu();
			int choice = sc.nextInt();
			
			if (choice == 1) {
				Place_order.place_order(rest_id);
			}
			else if (choice == 2) {
				Insert_menu.insert_menu(rest_id);
			}
			else if (choice == 3) {
				View_menu.view_menu(rest_id);
			}
			else if (choice == 4) {
				View_sales.view_sales();
			}
			else if (choice == 5) {
				System.out.println("1. View Inventory");
				System.out.println("2. Update Inventory");
				System.out.println("3. Add into Inventory");
				System.out.println("4. Go back");
				
				while (true) {
					int choice1 = sc.nextInt();
					
					if (choice1 == 1) {
						View_inv.view_inv(rest_id);
					}
					else if (choice1 == 2) {
						Update_inv.update_inv(rest_id);
					}
					else if (choice1 == 3) {
						Insert_inv.update_inv(rest_id);
					}
					else if (choice1 == 4) {
						break;
					}
					else {
						System.out.println("Invalid choice");
					}
				}
			}
			else if (choice == 6) {
				System.out.println("Exited");
				break;
			}
			else {
				System.out.println("Invalid input");
			}
		}
	}
}