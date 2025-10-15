package restaurant_Package;

import java.sql.*;
import java.util.*;

public class Insert_inv {
    public static void update_inv(int rest_id) {
        PreparedStatement pstm;
        ResultSet rs;

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Insert into Inventory ");
                System.out.println("2. EXIT ");
                int choice1 = sc.nextInt();
                sc.nextLine();

                if (choice1 == 1) {
                    System.out.print("Enter dish name: ");
                    String dish = sc.nextLine();

                    try (Connection conn = JDBC_Connection.getConnection()) {
                        String checkMenu = """
                            SELECT item_name FROM rest_menu
                            WHERE rest_id = ? AND item_name = ?
                        """;
                        pstm = conn.prepareStatement(checkMenu);
                        pstm.setInt(1, rest_id);
                        pstm.setString(2, dish);
                        rs = pstm.executeQuery();

                        if (rs.next()) {
                            System.out.print("Enter quantity: ");
                            int qty = sc.nextInt();
                            sc.nextLine();

                            String insertSQL = """
                                INSERT INTO rest_inventory (item_name, rest_id, quantity)
                                VALUES (?, ?, ?)
                            """;
                            pstm = conn.prepareStatement(insertSQL);
                            pstm.setString(1, dish);
                            pstm.setInt(2, rest_id);
                            pstm.setInt(3, qty);
                            pstm.execute();
                            System.out.println("Dish added to inventory.");
                        } else {
                            System.out.println("Dish does not exist in the menu. Please insert it into the menu first.");
                        }
                    }

                } else if (choice1 == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            }

            System.out.println("Exited inventory insertion.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
