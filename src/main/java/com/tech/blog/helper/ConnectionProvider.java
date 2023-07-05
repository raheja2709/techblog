package com.tech.blog.helper;

import java.sql.*;

public class ConnectionProvider {

	private static Connection con;

	public static Connection getConnection() {
		try {

			if (con == null) {
				// driver class load
				Class.forName("org.postgresql.Driver");
				// create a Connection
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techblog", "postgres", "jatin");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
