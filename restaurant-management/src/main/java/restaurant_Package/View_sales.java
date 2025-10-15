package restaurant_Package;

import java.sql.*;
import java.util.Scanner;

public class View_sales {
	public static void view_sales() {
		PreparedStatement pstm;
        ResultSet rs;
        
        Scanner sc = new Scanner(System.in);
        
        try {
        	Connection conn = JDBC_Connection.getConnection();
        	String dates = """
        		SELECT * FROM rest_sales
        		WHERE date >= DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 7 DAY);
       		""";
        	
        	pstm = conn.prepareStatement(dates);
        	rs = pstm.executeQuery();
        	
        	while (rs.next()) {
        		int id = rs.getInt("sale_id");
        		int id1 = rs.getInt("rest_id");
        		String s1 = rs.getString("customer_name");
        		String s2 = rs.getString("phone_no");
        		String s3 = rs.getString("order_summary");
        		Double total = rs.getDouble("total");
        		
        		System.out.println(id + " " + id1 + " " + s1 + " " + s2 + " " + s3 + " " + total + " " + rs.getDate("date"));
        	}
        	
        	while (true) {
        		System.out.println("\nEnter a custom date to fetch sales (format: YYYY-MM-DD) or type 'done' to exit:");
        		String input = sc.nextLine().trim();

        		if (input.equalsIgnoreCase("done")) {
        			System.out.println("Exiting date search.");
        			break;
        		}

        		String salesOnDate = """
        			SELECT * FROM rest_sales
        			WHERE DATE(date) = ?
        			ORDER BY date;
        		""";
        		
        		pstm = conn.prepareStatement(salesOnDate);
        		pstm.setString(1, input);
        		rs = pstm.executeQuery();

        		boolean found = false;
        		while (rs.next()) {
        			found = true;
        			int id = rs.getInt("sale_id");
        			int id1 = rs.getInt("rest_id");
        			String s1 = rs.getString("customer_name");
        			String s2 = rs.getString("phone_no");
        			String s3 = rs.getString("order_summary");
        			Double total = rs.getDouble("total");

        			System.out.println(id + " " + id1 + " " + s1 + " " + s2 + " " + s3 + " ₹" + total + " " + rs.getDate("date"));
        		}

        		if (!found) {
        			System.out.println("No sales found for " + input);
        		}
        	}
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
