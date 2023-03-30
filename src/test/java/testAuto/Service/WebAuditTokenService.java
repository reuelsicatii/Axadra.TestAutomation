package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WebAuditTokenService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public WebAuditTokenService() {
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

	public String RetrieveTokenFromPRD() throws Throwable {

		Connection("PROD_CENTRAL");
		String token = null;
		if (connection != null) {

			try {
				String Query = "SELECT \r\n" + "  tbl_tokenized_requests.`token`\r\n" + "FROM\r\n"
						+ "  tbl_tokenized_requests \r\n" + "  LEFT JOIN tbl_proposal \r\n"
						+ "    ON tbl_tokenized_requests.`object_id` = tbl_proposal.id \r\n"
						+ "  LEFT JOIN `tbl_user` \r\n" + "    ON tbl_proposal.`owner_id` = `tbl_user`.`id` \r\n"
						+ "WHERE tbl_proposal.type = \"audit\" \r\n"
						+ "  AND tbl_tokenized_requests.`status` = \"completed\" \r\n"
						+ "  AND `tbl_user`.`id` = 35505 \r\n"
						+ "  AND tbl_tokenized_requests.`when_requested` BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL 5 MINUTE)) \r\n"
						+ "  AND TIMESTAMP(NOW()) \r\n" + "ORDER BY tbl_tokenized_requests.`when_requested` DESC \r\n"
						+ "LIMIT 1 ;";

				System.out.println(Query);
				statement = connection.createStatement();

				for (int i = 0; i < 10; i++) {
					// Sleep
					Thread.sleep(60000);

					System.out.println("Attempted to retrieve WebAudit Token: " + i);
					resultSet = statement.executeQuery(Query);

					if (resultSet.next()) {
						// print the results
						token = resultSet.getString("token");
						System.out.println("WebAudit Token: " + token);

						break;
					}
				}

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			System.out.println("Failed to RETRIEVE WebAudit token");
		}

		return token;

	}

	public int RetrieveTokenCountFromPRD() throws Throwable {

		Connection("PROD_CENTRAL");
		int count = 0;
		if (connection != null) {

			try {
				String Query = "SELECT \r\n" + "  COUNT( tbl_tokenized_requests.`token`) AS Count \r\n" + "FROM\r\n"
						+ "  tbl_tokenized_requests \r\n" + "  LEFT JOIN tbl_proposal \r\n"
						+ "    ON tbl_tokenized_requests.`object_id` = tbl_proposal.id \r\n"
						+ "  LEFT JOIN `tbl_user` \r\n" + "    ON tbl_proposal.`owner_id` = `tbl_user`.`id` \r\n"
						+ "WHERE tbl_proposal.type = \"audit\" \r\n"
						+ "  AND tbl_tokenized_requests.`status` = \"completed\" \r\n"
						+ "  AND tbl_tokenized_requests.`when_requested` BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL 30 MINUTE)) \r\n"
						+ "  AND TIMESTAMP(NOW()) \r\n" + "ORDER BY tbl_tokenized_requests.`when_requested` DESC \r\n"
						+ "LIMIT 100 ;";

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);
				resultSet.next();
				count = resultSet.getInt("Count");
				System.out.println("WebAudit Token Count: " + count);

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			System.out.println("Failed to RETRIEVE WebAudit token count");
		}

		return count;

	}

}
