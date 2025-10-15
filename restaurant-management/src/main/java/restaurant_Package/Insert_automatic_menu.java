package restaurant_Package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Insert_automatic_menu {
	private static final String file = "C:\\Users\\vishu\\Downloads\\menu_items.csv";
	
	private static boolean isString(String str) {
		return str.matches("[a-zA-Z0-9'.,& ()\\[\\]-]+");
	}
	
	private static boolean isInt(String str) {
		return str.matches("-?\\d+");
	}
	
	public static void insert_auto_menu() {
		// WOHI Insert_automatic_restaurant ka logic hai same to same 
		try {
			int lineNum=0;
			Connection conn = JDBC_Connection.getConnection();
			String sql = "INSERT INTO rest_menu(rest_id, item_name, cost) VALUES (?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			conn.setAutoCommit(false);
			
			String line = "";
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				lineNum++;
				String data[] = line.split(",");
				
				if (data.length != 3) {
            		System.out.println("skipped line number " + lineNum + " as it has incorrect no.of coloumns");            		
					continue;
				}
				
				String id = data[0].trim();
				String name = data[1].trim();
				String cost = data[2].trim();
				
				if (!isInt(id) || !isString(name) || !isInt(cost)) {
					System.out.println("skipped line number " + lineNum + " as it has incorrect value" + "\n");
					continue;
				}
				
				pstm.setString(1, id);
				pstm.setString(2, name);
				pstm.setString(3, cost);
				
				pstm.addBatch();
			}
			pstm.executeBatch();
			conn.commit();
			System.out.println("inserted");
		}
		catch (Exception E) {
			E.printStackTrace();
		}

	}

}
