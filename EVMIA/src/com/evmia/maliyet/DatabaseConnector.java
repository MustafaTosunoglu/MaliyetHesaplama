package com.evmia.maliyet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	public static Connection connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:C:/evmiaDB/evmiaDB.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
