package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Update_inv {
	public static void update_inv(int rest_id) {
		PreparedStatement pstm;
		ResultSet rs;
		
		try {
			Scanner sc = new Scanner(System.in);
			
			while (true) {
				System.out.println("1. Update Inventory ");
				System.out.println("2. EXIT ");
				int choice1 = sc.nextInt();
				sc.nextLine();
				
				if (choice1 == 1) {
					System.out.println("Enter dish name : ");
	                String dish = sc.nextLine();
					
					Connection conn = JDBC_Connection.getConnection();
					String sql = "SELECT * FROM rest_inventory WHERE item_name = ? AND rest_id = ?";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, dish);
					pstm.setInt(2, rest_id);
					
					rs = pstm.executeQuery();
					
					if (rs.next()) {
						System.out.println("Enter additional quantity : ");
		                int qty = sc.nextInt();
		                sc.nextLine();
						
						String upd = """
								UPDATE rest_inventory
								SET quantity = quantity + ?
								WHERE rest_id = ? AND item_name = ?
								""";
						pstm = conn.prepareStatement(upd);
						pstm.setInt(1, qty);
						pstm.setInt(2, rest_id);
						pstm.setString(3, dish);
						pstm.executeUpdate();
					}
					else {
						System.out.println("no such item exists");
					}
				}
				else if (choice1 == 2) {
					break;
				}
				else {
					System.out.println("Invalid choice");
				}
			}
			System.out.println("Updated inventory");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
