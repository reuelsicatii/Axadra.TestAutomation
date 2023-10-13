package testAuto.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProposalBuilderService {

	static PreparedStatement prepStatement = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public String RetrieveProductIdFrom(String env, String productName) throws Throwable {

		JDBCConnection jDBCConnection = new JDBCConnection(env);
		Connection connection = jDBCConnection.getConnection();
		String productId = null;

		if (connection != null) {

			try {

				// Previous WebAuditURL
				// ===================================================
				String Query = "SELECT `tbl_products`.`id` FROM `tbl_products` WHERE `tbl_products`.`name` = '"+ productName +"' LIMIT 1";

				System.out.println(Query);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(Query);		

				resultSet.next();
				productId = resultSet.getString("id");
				System.out.println("ProductName + ID: " + productName + " -- " + productId);

				statement.close();
				connection.close();

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Failed to RETRIEVE ProductID");
			}

		} else {
			System.out.println("Failed to CONNECT to Database");
		}

		return productId;

	}	

}
