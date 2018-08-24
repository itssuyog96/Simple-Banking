package com.zycus.banking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {
		String driver = "org.apache.derby.jdbc.ClientDriver";
		String url = "jdbc:derby://localhost:1527/banking";
		String user = "user";
		String pass = "pass";
		return getConnection(driver, url, user, pass);
	}

	public static Connection getConnection(String driver, String url, String user, String pass) {
		// load JDBC driver class
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException ex) {
			System.out.println("No JDBC Driver Found");
			return null;
		}

		// Establish Connection
		try {
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Established Connection with " + con.getMetaData().getDatabaseProductName());
			return con;
		} catch (SQLException ex) {
			System.out.println("Connection not established");
			return null;
		}

	}
}