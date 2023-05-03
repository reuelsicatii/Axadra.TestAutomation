package testAuto.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.sql.Connection;

public class GbpScorerService {

	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public String RetrieveBusinessNameFrom(String Env) throws Throwable {

		JDBCConnection jDBCConnection = new JDBCConnection(Env);
		Connection connection = jDBCConnection.getConnection();
		String BusinessName = null;

		if (connection != null) {

			try {

				// Previous WebAuditURL
				// ===================================================
				String Query = "SELECT \r\n" + 
						"  `business_info`.`business_name` \r\n" + 
						"FROM\r\n" + 
						"  `business_info` \r\n" + 
						"WHERE `business_info`.`created_date` < DATE_SUB(NOW(), INTERVAL 30 DAY) \r\n" + 
						"ORDER BY `business_info`.`created_date` DESC \r\n" + 
						"LIMIT 110 ;";

			
				System.out.println(Query);
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				resultSet = statement.executeQuery(Query);

				Random generator = new Random();
				int randomIndex = generator.nextInt(100);
				
				resultSet.beforeFirst();
				resultSet.relative(randomIndex);
				BusinessName = resultSet.getString("business_name");
				System.out.println("GBPScorer BusinessName: " + BusinessName);

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Failed to RETRIEVE GBPScorer BusinessName");
			}

		} else {
			System.out.println("Failed to CONNECT to Database");
		}

		return BusinessName;

	}

}
