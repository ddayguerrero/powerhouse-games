package org.soen387.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Handler
 * @author Darrel Guerrero 27352409
 *
 */
public class DatabaseConnection {
		private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
		private static final String DB_CONNECTION = "jdbc:mysql://0.0.0.0/soen387";
		private static final String DB_USER = "dramos";
		private static final String DB_PASSWORD = "password";
		private static Connection instance = null;
		
		public static Connection getInstance() {
			if (instance == null) {
				try {
					Class.forName(DB_DRIVER);
					instance = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				} catch (ClassNotFoundException cnfe) {
					System.out.println("Class not found: " + cnfe.getMessage());
					cnfe.printStackTrace();
				} catch (SQLException se) {
					System.out.println("Error with SQL: " + se.getMessage());
					se.printStackTrace();
				} finally {
					if (instance == null) {
						System.out.println("Unable to connect to database");
						System.exit(1);
					}
				}
			}
			
			return DatabaseConnection.instance;
		}
		
		public static void clearConnection() {
			if(instance != null) {
				try {
					instance.close();
				} catch (SQLException se) {
					System.out.println("Problem with closing connection... " + se.getMessage());
				}
			}
		}
}
