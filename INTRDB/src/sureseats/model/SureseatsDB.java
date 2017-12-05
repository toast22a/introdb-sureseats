package sureseats.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SureseatsDB {
	private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static String USERNAME = "root";
	private static String PASSWORD = "1234";
	private static String DATABASE = "sureseats_db";
	
	public Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection(
					URL +
					DATABASE + "?autoReconnect=true&useSSL=false",
					USERNAME,
					PASSWORD);
			System.out.println("[MYSQL] Connection successful");
			return connection;
		} catch (SQLException e) {
			System.out.println("[MYSQL] Connection failed!");
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("[MYSQL] Connection failed!");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main (String[] args) {
		SureseatsDB db = new SureseatsDB();
		db.getConnection();
	}
}
