package testAuto.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

public class LeadFinderService {

	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public HashMap<String, String> RetrieveKeywordLocationFrom(String Env) throws Throwable {

		JDBCConnection jDBCConnection = new JDBCConnection(Env);
		Connection connection = jDBCConnection.getConnection();
		HashMap<String, String> searchDetails = new HashMap<>();

		if (connection != null) {

			try {

				// Previous WebAuditURL
				// ===================================================
				String Query = "SELECT \r\n" + "  searched_keywords.`keyword`,\r\n"
						+ "  searched_keywords.`location`\r\n" + "FROM\r\n" + "  searched_keywords \r\n"
						+ "  JOIN tbl_tokenized_requests \r\n"
						+ "    ON tbl_tokenized_requests.`btoken` = searched_keywords.`btoken` \r\n"
						+ "WHERE `tbl_tokenized_requests`.`when_requested` < DATE_SUB(NOW(), INTERVAL 30 DAY) \r\n"
						+ "ORDER BY `tbl_tokenized_requests`.`when_requested` DESC ;";

				System.out.println(Query);
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				resultSet = statement.executeQuery(Query);

				Random generator = new Random();
				int randomIndex = generator.nextInt(100);

				resultSet.beforeFirst();
				resultSet.relative(randomIndex);

				searchDetails.put("keyword", resultSet.getString("keyword"));
				searchDetails.put("location", resultSet.getString("location"));

				System.out.println("LeadFinder Keyword: " + searchDetails.get("keyword"));
				System.out.println("LeadFinder Keyword: " + searchDetails.get("location"));

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Failed to RETRIEVE GBPScorer BusinessName");
			}

		} else {
			System.out.println("Failed to CONNECT to Database");
		}

		return searchDetails;

	}

}
