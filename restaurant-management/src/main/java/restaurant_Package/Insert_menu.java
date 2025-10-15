package restaurant_Package;

import java.sql.*;
import java.util.Scanner;

public class Insert_menu {
	public static void insert_menu(int rest_id) {
		PreparedStatement pstm;
		ResultSet rs;
		
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter name of the dish : ");
			String dish = sc.nextLine();
			System.out.println("Enter cost : ");
			int cost = sc.nextInt();
			
			Connection conn = JDBC_Connection.getConnection();
			String insert_into = """
					INSERT INTO rest_menu (rest_id, cost, item_name)
					VALUES (?, ?, ?);
					""";
			
			pstm = conn.prepareStatement(insert_into);
			pstm.setInt(1, rest_id);
			pstm.setInt(2, cost);
			pstm.setString(3, dish);
			pstm.execute();
			
			System.out.println("New item inserted into menu");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
