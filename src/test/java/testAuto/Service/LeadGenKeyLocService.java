package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class LeadGenKeyLocService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public LeadGenKeyLocService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void Connection(String Env) throws Throwable {
		System.out.println("-------- DB Connection will now start ------------");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No MySQL JDBC Driver was availabe");
			e.printStackTrace();
			return;
		}

		try {

			switch (Env) {
			case "LOCAL_TESTAUTO":
				connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/testauto_db?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "admin");
				break;

			case "STAGING_TESTAUTO":
				connection = DriverManager.getConnection(
						"jdbc:mysql://10.10.3.196:3306/staging_testauto?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "mySQLroot");
				break;

			case "STAGING_CENTRAL":
				connection = DriverManager.getConnection(
						"jdbc:mysql://10.10.3.196:3306/staging_central?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "mySQLroot");
				break;

			case "PROD_CENTRAL":
				connection = DriverManager.getConnection(
						"jdbc:mysql://central-db-compass.cuz0vq3xjyt0.us-west-1.rds.amazonaws.com:3306/central?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"reuel", "reuel123");
				break;

			default:
				// Code Here
				break;

			}

		} catch (SQLException e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();
			return;
		}

	}

	public String RetrieveKeywordFromPRD() throws Throwable {

		Connection("PROD_CENTRAL");
		String keyword = null;
		if (connection != null) {

			try {
				String Query = 
						" SELECT \r\n" + 
						" `searched_keywords`.`keyword`, \r\n" + 
						" `searched_keywords`.`location` \r\n" + 
						" FROM `searched_keywords` \r\n" + 
						" WHERE `searched_keywords`.`searched_count` >= 60 \r\n" + 
						" LIMIT 1000 ;";

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);
				
				Random generator = new Random();
				int randomIndex = generator.nextInt(1000);
				
				for (int i = 0; i <randomIndex; i++) {
					resultSet.next();
				}
				
				keyword = resultSet.getString("keyword");	
				System.out.println("LeadGen keyword: "+ keyword);
	
				
				
				statement.close();
				connection.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			System.out.println("Failed to RETRIEVE LeadGen token");
		}

		return keyword;

	}
	
	public String RetrieveLocationFromPRD() throws Throwable {

		Connection("PROD_CENTRAL");
		String location = null;
		if (connection != null) {

			try {
				String Query = 
						" SELECT \r\n" + 
						" `searched_keywords`.`keyword`, \r\n" + 
						" `searched_keywords`.`location` \r\n" + 
						" FROM `searched_keywords` \r\n" + 
						" WHERE `searched_keywords`.`searched_count` >= 60 \r\n" + 
						" LIMIT 1000 ;";

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);
				
				Random generator = new Random();
				int randomIndex = generator.nextInt(1000);
				
				for (int i = 0; i <randomIndex; i++) {
					resultSet.next();
				}
				
		
				location = resultSet.getString("location");		
				System.out.println("LeadGen location: "+ location);
				
				
				statement.close();
				connection.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			System.out.println("Failed to RETRIEVE LeadGen token");
		}

		return location;

	}

}
