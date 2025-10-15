package restaurant_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {
	 private static final String URL = "jdbc:mysql://localhost:3306/restaurant"; //SQL database change kar lena if needed 
	 private static final String USER = "root";
	 private static final String PASSWORD = "Manisha@2285"; 

	 public static Connection getConnection() throws SQLException {
		 return DriverManager.getConnection(URL, USER, PASSWORD);
	 }
}
