package restaurant_Package;

import java.sql.*;

public class View_menu {
	public static void view_menu(int rest_id) {
		PreparedStatement pstm;
		ResultSet rs;
		
		try {
			Connection conn = JDBC_Connection.getConnection();
			String viewMenu = "SELECT * FROM rest_menu WHERE rest_id = ?";
			
			pstm = conn.prepareStatement(viewMenu);
			pstm.setInt(1, rest_id);
			
			rs = pstm.executeQuery();
			while (rs.next() ) {
				int id = rs.getInt("rest_id");
				int cost = rs.getInt("cost");
				String name = rs.getString("item_name");
				System.out.println(id + " " + cost + " " + name);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
