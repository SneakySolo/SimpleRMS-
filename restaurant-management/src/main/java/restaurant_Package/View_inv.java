package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class View_inv {
	public static void view_inv(int rest_id) {
		PreparedStatement pstm;
		ResultSet rs;
		
		try {
			Scanner sc = new Scanner(System.in);
			
			while (true) {
				System.out.println("1. View a specific item ");
				System.out.println("2. View all ");
				System.out.println("3. EXIT ");
				int choice1 = sc.nextInt();
				sc.nextLine();
				
				if (choice1 == 1) {
					System.out.println("Enter dish name : ");
	                String dish = sc.nextLine();
	                
	                Connection conn = JDBC_Connection.getConnection();
	                String view_specific = "SELECT * FROM rest_inventory WHERE item_name = ? AND rest_id = ?";
	                pstm = conn.prepareStatement(view_specific);
	                pstm.setString(1, dish);
	                pstm.setInt(2, rest_id);
	                
	                rs = pstm.executeQuery();
	    			while (rs.next() ) {
	    				int qty = rs.getInt("quantity");
	    				String name = rs.getString("item_name");
	    				System.out.println(name + " --> " + qty + "units");
	    			}
				}
				else if (choice1 == 2) {
					Connection conn = JDBC_Connection.getConnection();
	                String view_all = "SELECT * FROM rest_inventory WHERE rest_id = ?";
	                pstm = conn.prepareStatement(view_all);
	                pstm.setInt(1, rest_id);
	                
	                rs = pstm.executeQuery();
	    			while (rs.next() ) {
	    				int qty = rs.getInt("quantity");
	    				String name = rs.getString("item_name");
	    				System.out.println(name + " --> " + qty + " units");
	    			}
				}
				else if (choice1 == 3) {
					break;
				}
				else {
					System.out.println("Invalid Choice");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
