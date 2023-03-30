package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.google.gson.Gson;

import testAuto.Model.TestSuite;

public class TestSuiteService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;

	public TestSuiteService() {
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

			default:
				// Code Here
				break;

			}

		} catch (Exception e) {
			System.out.println("Failed to make connection!");
			e.printStackTrace();
			return;
		}

	}

	public void Insert(String Query, TestSuite TestSuite, String Env) throws Throwable {
		Connection(Env);
		if (connection != null) {
			System.out.println(Query);
			prepStatement = connection.prepareStatement(Query);

			prepStatement.setString(1, TestSuite.getTestSuiteName());
			prepStatement.setString(2, TestSuite.getTestSuiteStatusDesc());
			prepStatement.setInt(3, TestSuite.getTestSuiteStatusValue());
			prepStatement.setString(4, TestSuite.getTestSuiteTimeStamp());
			prepStatement.addBatch();
			prepStatement.executeBatch();
			prepStatement.close();
			connection.close();

		} else {
			Gson gson = new Gson();
			System.out.println("Failed to INSERT JSON object: " + gson.toJson(TestSuite));
		}

	}

}
