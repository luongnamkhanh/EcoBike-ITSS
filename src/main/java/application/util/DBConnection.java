package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.config.DatabaseConfig;

public class DBConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
			if(conn != null) {
				System.out.println("Connected to the PostgreSQL successfully");
			}
			else {
				System.out.println("Fail to the PostgreSQL");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}
}

