package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Owner_login {
	public static void owner_login() {
		try {
			Connection conn = JDBC_Connection.getConnection();
			String sql = "SELECT * FROM owner_login WHERE user_id = ? AND pass_word = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter your id - ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Enter password - ");
			String password = sc.nextLine();
			
			pstm.setInt(1, id);
			pstm.setString(2, password);
			
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				int rest_id = rs.getInt("rest_id");
			    System.out.println("Login successful!");
			    Owner_menu.owner_menu(rest_id);
			} else {
			    System.out.println("Invalid credentials.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}