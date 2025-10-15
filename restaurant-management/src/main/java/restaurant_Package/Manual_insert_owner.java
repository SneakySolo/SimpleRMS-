package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Manual_insert_owner {
	public static void manual_ins_owner() {
		try {
			Connection conn = JDBC_Connection.getConnection();
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter Restaurant ID (rest_id): ");
			int rest_id = sc.nextInt();
			sc.nextLine();

			System.out.print("Enter Owner User ID: ");
			int user_id = sc.nextInt();
			sc.nextLine();

			System.out.print("Enter Password: ");
			String password = sc.nextLine();

			String sql = "INSERT INTO owner_login (rest_id, user_id, pass_word) VALUES (?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, rest_id);
			pstm.setInt(2, user_id);
			pstm.setString(3, password);
			pstm.executeUpdate();

			System.out.println("Owner added successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}