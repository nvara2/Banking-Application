package com.bank.bankapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static String connectionString = "jdbc:postgresql://localhost:5432/postgres";

	public static Connection getConnection() {
		try {
            //System.out.println(System.getenv("BANKER_ROLE") + ", " +
				//	System.getenv("BANKER_PASSWORD"));
			return DriverManager.getConnection(connectionString, 
					System.getenv("BANKER_ROLE"), 
					System.getenv("BANKER_PASSWORD"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database connection has failed, please check values, and try again");

		}
		return null;
	}
}