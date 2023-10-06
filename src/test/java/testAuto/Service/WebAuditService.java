package testAuto.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebAuditService {

	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public String RetrieveURLFrom(String Env) throws Throwable {

		JDBCConnection jDBCConnection = new JDBCConnection(Env);
		Connection connection = jDBCConnection.getConnection();
		String url = null;

		if (connection != null) {

			try {

				// Previous WebAuditURL
				// ===================================================
				String Query = "SELECT \r\n" + "  `tbl_proposal`.`url` \r\n" + "FROM\r\n" + "  `tbl_proposal` \r\n"
						+ "WHERE `tbl_proposal`.`status_code` = 'done' \r\n"
						+ "  AND `tbl_proposal`.`created_date` < DATE_SUB(NOW(), INTERVAL 30 DAY) \r\n"
						+ "ORDER BY `tbl_proposal`.`created_date` DESC \r\n" + "LIMIT 1000 ;";

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);

				Random generator = new Random();
				int randomIndex = generator.nextInt(1000);

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

	public List<String[]> RetrieveGeneratedReportsBetweenAndFrom(String fromDate, String toDate, String Env)
			throws Throwable {

		JDBCConnection jDBCConnection = new JDBCConnection(Env);
		Connection connection = jDBCConnection.getConnection();
		List<String[]> reportList = new ArrayList<>();

		if (connection != null) {

			try {

				String Query = null;
				if (toDate.contains("NOW")) {
					Query = "SELECT CONCAT('https://www.accesshub.co/web-audit-view/1/',token) AS reportUrl, date_posted, url AS websiteUrl FROM tbl_proposal WHERE `type` = 'audit' AND date_posted BETWEEN '"
							+ fromDate + "' AND NOW() ORDER BY date_posted DESC ";
				} else {

					Query = "SELECT CONCAT('https://www.accesshub.co/web-audit-view/1/',token) AS reportUrl, date_posted, url AS websiteUrl FROM tbl_proposal WHERE `type` = 'audit' AND date_posted BETWEEN '"
							+ fromDate + "' AND '" + toDate + "' ORDER BY date_posted DESC ";

				}

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);

				// Iterate through the result set
				while (resultSet.next()) {
					String[] rowData = new String[3];
					rowData[0] = resultSet.getString("reportUrl");
					rowData[1] = resultSet.getString("date_posted");
					rowData[2] = resultSet.getString("websiteUrl");
					reportList.add(rowData);
				}

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Failed to RETRIEVE generated Report List");
			}

		} else {
			System.out.println("Failed to CONNECT to Database");
		}

		return reportList;

	}

}
