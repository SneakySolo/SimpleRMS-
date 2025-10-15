package restaurant_Package;

import java.io.*;
import java.sql.*;

public class Insert_automatic_restaurant {
    private static final String file = "C:\\Users\\vishu\\Downloads\\Restaurants.csv"; // the loaction jaha file hai
    
    private static boolean notString (String str) {
    	 return str.matches("[a-zA-Z0-9'.,& -]+");
    }
    
    private static boolean notInt (String str) {
    	return str.matches("-?\\d+");
    }
    
	public static void insert_auto_res() {
		int lineNum=0;
		try {
			Connection conn = JDBC_Connection.getConnection();
			String sql = "INSERT INTO Restaurant1 (rest_id, rest_name, rest_location, rest_capacity) VALUES (?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            conn.setAutoCommit(false); // Disable auto-commit as it will insert each row then move ahead which slows things 
            
            String line = "";
            bufferedReader.readLine(); // this will skip the upar wala part
            
            while ((line = bufferedReader.readLine()) != null) {
            	lineNum++;
            	String[] data = line.split(",");
            	
            	if (data.length != 4) { // data.length and not line.length() as the later will count the columns and the former will check the characters
            		System.out.println("skipped line number " + lineNum + " as it has incorrect no.of coloumns");            		
            		continue; // skip kar do woh sab
            	}
            		String rest_id =data[0].trim(); 
            		String rest_name = data[1].trim();
            		String rest_location = data[2].trim();
            		String rest_capacity = data[3].trim(); 
            		
            		if (!notString(rest_name) || !notString(rest_location) || !notInt(rest_id) || !notInt(rest_capacity)) {
            			System.out.println("skipped line number " + lineNum + " as it has incorrect value" + "\n");
            			continue; // skip if data fails validation
            		}
            		
            		pstm.setString(1, rest_id);
                	pstm.setString(2, rest_name);
                	pstm.setString(3, rest_location);
                	pstm.setString(4, rest_capacity);
                	
                	pstm.addBatch(); // as just pstm.executeUpdate will make it slow by a lot
            }
            pstm.executeBatch(); // finally insert those rows
            conn.commit(); // this will finally commit in bulk;
            System.out.println("Succesfully inserted" + "\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
