package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTokenService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public UserTokenService() {
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

	public String RetrieveFromPRD() throws Throwable {

		Connection("PROD_CENTRAL");
		String token = null;
		if (connection != null) {

			try {
				String Query = "SELECT \r\n" + "  `user_otp`.`otp`\r\n" + "FROM\r\n" + "  `user_otp` \r\n"
						+ "  LEFT JOIN `users` \r\n" + "    ON `user_otp`.`otp_request_email` = `users`.`email` \r\n"
						+ "  LEFT JOIN `user_geo_location` \r\n"
						+ "    ON `users`.`id` = `user_geo_location`.`user_id` \r\n"
						+ "WHERE `users`.email LIKE '%reuel@axadra.com%' \r\n"
						+ "ORDER BY `user_otp`.`otp_request_expire_date` DESC LIMIT 1";

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);

				while (resultSet.next()) {
					token = resultSet.getString("otp");

					// print the results
					System.out.println("OTP Token: "+ token);
				}
				statement.close();
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			System.out.println("Failed to RETRIEVE token");
		}

		return token;

	}

}
