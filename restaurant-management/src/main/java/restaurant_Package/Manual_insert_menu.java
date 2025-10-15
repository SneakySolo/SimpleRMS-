package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Manual_insert_menu {

	public static void manual_ins_menu() {
		try {
			Connection conn = JDBC_Connection.getConnection();
			String sql = "INSERT INTO rest_menu(rest_id, item_name, cost) VALUES (?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the Restaurant ID - ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Enter the item name - ");
			String name = sc.nextLine();
			
			System.out.println("Enter the cost - ");
			int cost = sc.nextInt();
			sc.nextLine();
			
			pstm.setInt(1,id);
			pstm.setString(2, name);
			pstm.setInt(3, cost);
			
			System.out.println("Press Y to insert or N to discard the data");
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("Y")) {
            	pstm.executeUpdate();
            	System.out.println("Inserted Successfully");
            }
            else {
            	System.out.println("Discarded");
            }
		}
		catch (Exception E) {
			E.printStackTrace();		}
	}

}
