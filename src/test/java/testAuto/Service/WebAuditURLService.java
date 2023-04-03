package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class WebAuditURLService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public WebAuditURLService() {
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
						"jdbc:mysql://central-db-compass-replica.cuz0vq3xjyt0.us-west-1.rds.amazonaws.com:3306/central?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"jumer", "vC0%4yi@6ZJJ");
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
		String url = null;
		if (connection != null) {

			try {

				// Previous WebAuditURL
				// ===================================================
				String Query = "SELECT \r\n" + "  `tbl_proposal`.`url` \r\n" + "FROM\r\n" + "  `tbl_proposal` \r\n"
						+ "WHERE `tbl_proposal`.`status_code` = 'done' \r\n"
						+ "  AND `tbl_proposal`.`created_date` < DATE_SUB(NOW(), INTERVAL 30 DAY) \r\n"
						+ "ORDER BY `tbl_proposal`.`created_date` DESC \r\n" + "LIMIT 1000 ;";
				

				/*/ Axadra Property Site
				// ===================================================
				String Query = "SELECT \r\n" + "  property_url AS url \r\n" + "FROM\r\n" + "  `tbl_properties` \r\n"
						+ "WHERE is_active = 1 \r\n" + "  AND date_added >= '2015-01-01 00:00:00' \r\n"
						+ "ORDER BY RAND() \r\n" + "LIMIT 1000 ;\r\n" + "";
				*/

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);

				Random generator = new Random();
				int randomIndex = generator.nextInt(60);

				for (int i = 0; i < randomIndex; i++) {
					resultSet.next();
				}

				url = resultSet.getString("url");
				System.out.println("WebAudit URL: " + url);

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Failed to RETRIEVE WebAudit URL");
			}

		} else {
			System.out.println("Failed to CONNECT to Database");
		}

		return url;

	}

}
