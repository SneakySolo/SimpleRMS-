package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Manual_insert_restaurant {
    public static void manual_ins_res() {
        try {
            Connection conn = JDBC_Connection.getConnection();
            String sql = "INSERT INTO Restaurant1 (rest_id, rest_name, rest_capacity, rest_location) VALUES (?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Restaurant ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // So that the scanner will read what I will write next

            System.out.print("Enter Restaurant Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // wohi upar wala logic

            System.out.print("Enter Location: ");
            String location = scanner.nextLine();

            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setInt(3, capacity);
            pstm.setString(4, location);
            
            System.out.println("Press Y to insert or N to discard the data");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("Y")) {
            	pstm.executeUpdate();
            	System.out.println("Inserted Successfully");
            }
            else {
            	System.out.println("Discarded");
            }
        
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
