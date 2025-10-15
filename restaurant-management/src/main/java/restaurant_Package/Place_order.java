package restaurant_Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Place_order {
    public static void place_order(int rest_id) {
        PreparedStatement pstm;
        ResultSet rs;

        try {
            Connection conn = JDBC_Connection.getConnection();
            ArrayList<String> billItems = new ArrayList<>();
            ArrayList<String> orderSummaryRaw = new ArrayList<>();
            double total_cost = 0;

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Enter item name (or type 'done' to finish):");
                String dish0 = sc.nextLine();

                if (dish0.equalsIgnoreCase("done")) {
                    break;
                }

                String suggestion = "SELECT item_name FROM rest_menu WHERE rest_id = ? AND item_name LIKE ?";
                pstm = conn.prepareStatement(suggestion);
                pstm.setInt(1, rest_id);
                pstm.setString(2, dish0 + "%");
                rs = pstm.executeQuery();

                System.out.println("Suggestions:");
                while (rs.next()) {
                    System.out.println(" - " + rs.getString("item_name"));
                }

                System.out.println("Confirm selection:");
                String dish = sc.nextLine();

                String checkSql = """
                    SELECT m.cost, i.quantity
                    FROM rest_menu m
                    JOIN rest_inventory i ON m.item_name = i.item_name
                    WHERE m.rest_id = ? AND i.rest_id = ? AND m.item_name = ?
                    """;
                pstm = conn.prepareStatement(checkSql);
                pstm.setInt(1, rest_id);
                pstm.setInt(2, rest_id);
                pstm.setString(3, dish);
                rs = pstm.executeQuery();

                if (!rs.next()) {
                    System.out.println("Item not present in inventory.");
                    continue;
                }

                double cost = rs.getDouble("cost");
                int stock = rs.getInt("quantity");

                if (stock <= 0) {
                    System.out.println("Out of stock.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();

                if (qty > stock) {
                    System.out.println("Only " + stock + " are available.");
                    continue;
                }

                double lineCost = qty * cost;
                total_cost += lineCost;
                billItems.add(dish + " x" + qty + " = ₹" + lineCost);
                orderSummaryRaw.add(dish + " x" + qty);

                // Inventory Update karo
                String updateInv = "UPDATE rest_inventory SET quantity = quantity - ? WHERE item_name = ? AND rest_id = ?";
                pstm = conn.prepareStatement(updateInv);
                pstm.setInt(1, qty);
                pstm.setString(2, dish);
                pstm.setInt(3, rest_id);
                pstm.executeUpdate();
            }

            // Take customer details at last
            if (!billItems.isEmpty()) {
                System.out.print("Enter customer name: ");
                String customer_name = sc.nextLine();
                System.out.print("Enter phone number: ");
                String phone_no = sc.nextLine();

                // Prepare order_summary string
                StringBuilder summary = new StringBuilder();
                for (String line : orderSummaryRaw) {
                    summary.append(line).append(", ");
                }
                if (summary.length() > 2) {
                    summary.setLength(summary.length() - 2); // Remove last comma and space
                }

                // Sales tabl emai daalo
                String updateSales = """
                    INSERT INTO rest_sales (rest_id, customer_name, phone_no, order_summary, total)
                    VALUES (?, ?, ?, ?, ?)
                    """;
                pstm = conn.prepareStatement(updateSales);
                pstm.setInt(1, rest_id);
                pstm.setString(2, customer_name);
                pstm.setString(3, phone_no);
                pstm.setString(4, summary.toString());
                pstm.setDouble(5, total_cost);
                pstm.executeUpdate();

                // Final Bill
                System.out.println("\n------ BILL ------");
                for (String line : billItems) {
                    System.out.println(line);
                }
                System.out.println("Total: ₹" + total_cost);
                System.out.println("------------------");
            } else {
                System.out.println("No items ordered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
