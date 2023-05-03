package webOther.AppName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.testng.annotations.Test;

import testAuto.Service.GbpScorerService;
import testAuto.Service.WebAuditService;

public class DBConnection {

	WebAuditService webAuditService = new WebAuditService();
	GbpScorerService gbpScorerService = new GbpScorerService();

	@Test
	public void main() throws Throwable {

		// Step Definition
		//String url = webAuditService.RetrieveURLFrom("PROD_CENTRAL");
		//System.out.println("URL from SERVICE:" + url);

		// Step Definition
		String BusinessName = gbpScorerService.RetrieveBusinessNameFrom("PROD_REPORTBUILDER");
		System.out.println("BusinessName from SERVICE:" + BusinessName);

	}

	public void retrieveFrom() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No MySQL JDBC Driver was availabe");
			e.printStackTrace();
			return;
		}

		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://10.10.2.208:3306/central?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
				"jumer", "vC0%4yi@6ZJJ");

		if (connection != null) {

			try {
				String Query = "SELECT \r\n" + "  `tbl_proposal`.`url` \r\n" + "FROM\r\n" + "  `tbl_proposal` \r\n"
						+ "WHERE `tbl_proposal`.`status_code` = 'done' \r\n"
						+ "  AND `tbl_proposal`.`created_date` < DATE_SUB(NOW(), INTERVAL 30 DAY) \r\n"
						+ "ORDER BY `tbl_proposal`.`created_date` DESC \r\n" + "LIMIT 60 ;";

				System.out.println(Query);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(Query);

				Random generator = new Random();
				int randomIndex = generator.nextInt(60);

				for (int i = 0; i < randomIndex; i++) {
					resultSet.next();
				}

				String url = resultSet.getString("url");
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
	}

}
