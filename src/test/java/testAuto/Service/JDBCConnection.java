package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class JDBCConnection {
	
	
	// Properties
	Connection connection = null;
	

	// Constructor
	public JDBCConnection(String Env) {
		setConnection( Env);
	}

	

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(String Env) {

		System.out.println("-------- DB Connection will now start ------------");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No MySQL JDBC Driver was availabe");
			e.printStackTrace();

		}

		try {

			switch (Env) {
			case "LOCAL_TESTAUTO":
				this.connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/testauto_db?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "admin");
				break;

			case "STAGING_TESTAUTO":
				this.connection = DriverManager.getConnection(
						"jdbc:mysql://10.10.3.196:3306/staging_testauto?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "mySQLroot");
				break;

			case "STAGING_CENTRAL":
				this.connection = DriverManager.getConnection(
						"jdbc:mysql://10.10.3.196:3306/staging_central?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "mySQLroot");
				break;

			case "PROD_CENTRAL":
				this.connection = DriverManager.getConnection(
						"jdbc:mysql://central-db-compass.cuz0vq3xjyt0.us-west-1.rds.amazonaws.com:3306/central?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"jumer", "vC0%4yi@6ZJJ");
				break;
			case "PROD_REPORTBUILDER":
				this.connection = DriverManager.getConnection(
						"jdbc:mysql://report-builder-db.cuz0vq3xjyt0.us-west-1.rds.amazonaws.com:3306/report_builder?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"qa_tester", "6s5EgwUcXZkUjpB");
				break;

			default:
				// Code Here
				break;

			}

		} catch (SQLException e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();

		}

	}

}
