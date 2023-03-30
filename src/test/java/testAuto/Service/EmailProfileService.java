package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;

import testAuto.Model.EmailProfile;

public class EmailProfileService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;

	public EmailProfileService() {
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
						"email_app", "qtAmKyBa6MxjTE9N");
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

	public void InsertToDEV(EmailProfile emailProfile, String Env) throws Throwable {
		Connection(Env);
		if (connection != null) {
			String Query = "INSERT INTO `" + Env.toLowerCase()
					+ "`.`tbl_emailprofile` (EmaillAdd,PasswordOne,PasswordTwo,RecoveryEmail,VerificationEmail,VerificationCode,TimeStamp) VALUES(?,?,?,?,?,?,?)";
			System.out.println(Query);
			prepStatement = connection.prepareStatement(Query);

			prepStatement.setString(1, emailProfile.getEmaillAdd());
			prepStatement.setString(2, emailProfile.getPasswordOne());
			prepStatement.setString(3, emailProfile.getPasswordTwo());
			prepStatement.setString(4, emailProfile.getRecoveryEmail());
			prepStatement.setString(5, emailProfile.getVerificationEmail());
			prepStatement.setString(6, emailProfile.getVerificationCode());
			prepStatement.setString(7, emailProfile.getTimeStamp());
			prepStatement.addBatch();

			prepStatement.executeBatch();
			prepStatement.close();
			connection.close();

		} else {
			Gson gson = new Gson();
			System.out.println("Failed to INSERT JSON object: " + gson.toJson(emailProfile));
		}

	}

	public void InsertToSTG(EmailProfile emailProfile, String Env) throws Throwable {
		Connection(Env);
		if (connection != null) {
			
			try {
				String Query = "INSERT INTO `" + Env.toLowerCase()
				+ "`.`email_profile` (email_address, password_primary, password_secondary, recovery_email_address, verification_email_address, verification_code, email_profile_status_code,created_date,modified_date) VALUES(?,?,?,?,?,?,?,?,?)";
				System.out.println(Query);
				prepStatement = connection.prepareStatement(Query);
		
					prepStatement.setString(1, emailProfile.getEmaillAdd());
					prepStatement.setString(2, emailProfile.getPasswordOne());
					prepStatement.setString(3, emailProfile.getPasswordTwo());
					prepStatement.setString(4, emailProfile.getRecoveryEmail());
					prepStatement.setString(5, emailProfile.getVerificationEmail());
					prepStatement.setString(6, emailProfile.getVerificationCode());
					prepStatement.setString(7, "created");
					prepStatement.setString(8, emailProfile.getTimeStamp());
					prepStatement.setString(9, emailProfile.getTimeStamp());
					prepStatement.addBatch();
		
				prepStatement.executeBatch();
				prepStatement.close();
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			


		} else {
			Gson gson = new Gson();
			System.out.println("Failed to INSERT JSON object: " + gson.toJson(emailProfile));
		}

	}
	
	public void InsertToPRD(EmailProfile emailProfile, String Env) throws Throwable {
		Connection(Env);
		if (connection != null) {
			
			try {
				String Query = "INSERT INTO `email_profile` (email_address, password_primary, password_secondary, recovery_email_address, verification_email_address, verification_code, email_profile_status_code,created_date,modified_date) VALUES(?,?,?,?,?,?,?,?,?)";
				System.out.println(Query);
				prepStatement = connection.prepareStatement(Query);
		
					prepStatement.setString(1, emailProfile.getEmaillAdd());
					prepStatement.setString(2, emailProfile.getPasswordOne());
					prepStatement.setString(3, emailProfile.getPasswordTwo());
					prepStatement.setString(4, emailProfile.getRecoveryEmail());
					prepStatement.setString(5, emailProfile.getVerificationEmail());
					prepStatement.setString(6, emailProfile.getVerificationCode());
					prepStatement.setString(7, "created");
					prepStatement.setString(8, emailProfile.getTimeStamp());
					prepStatement.setString(9, emailProfile.getTimeStamp());
					prepStatement.addBatch();
		
				prepStatement.executeBatch();
				prepStatement.close();
				connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			


		} else {
			Gson gson = new Gson();
			System.out.println("Failed to INSERT JSON object: " + gson.toJson(emailProfile));
		}

	}

}
