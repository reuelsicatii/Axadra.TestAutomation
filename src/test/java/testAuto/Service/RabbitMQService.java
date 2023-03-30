package testAuto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import testAuto.Model.RabbitMQ;

public class RabbitMQService {

	static Connection connection = null;
	static PreparedStatement prepStatement = null;
	static Statement statement = null;

	public RabbitMQService() {
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
			case "LOCAL_STG_TESTAUTO":
				connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/stg_app_testauto?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "");
				break;

			case "STAGING_STG_TESTAUTO":
				connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/stg_app_testauto?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "");
				break;
			
			case "PROD_STG_TESTAUTO":
				connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/stg_app_testauto?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC",
						"root", "");
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

	public void Insert(String Query, RabbitMQ RMQObj, String Env) throws Throwable {
		Connection(Env);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
		if (connection != null) {
			System.out.println(Query);
			prepStatement = connection.prepareStatement(Query);
			prepStatement.setString(1, RMQObj.getName());
			prepStatement.setString(2, RMQObj.getFeatures());
			prepStatement.setString(3, RMQObj.getState());
			prepStatement.setInt(4, RMQObj.getReady());
			prepStatement.setInt(5, RMQObj.getUnacked());
			prepStatement.setInt(6, RMQObj.getTotal());
			prepStatement.setString(7, RMQObj.getIncoming());
			prepStatement.setString(8, RMQObj.getDeliverget());
			prepStatement.setString(9, RMQObj.getAck());
			//prepStatement.setString(10, "0000-00-00 00:00:00");
			prepStatement.executeUpdate();			
			prepStatement.close();
			connection.close();

		} else {
			System.out.println("Failed to INSERT RMQObj object ");
		}

	}
	
	
	public ArrayList<RabbitMQ> Retrieve(String Query, String Env) throws Throwable {

		ArrayList<RabbitMQ> retobj = new ArrayList<RabbitMQ>();
		
		Connection(Env);
		if (connection != null) {
			System.out.println(Query);
			prepStatement = connection.prepareStatement(Query);			
			ResultSet resultSet = prepStatement.executeQuery();		

			
			while(resultSet.next()){
				RabbitMQ obj = new RabbitMQ();
				String name = resultSet.getString("name");
				String features = resultSet.getString("features");
				String state = resultSet.getString("state");				
				int ready = resultSet.getInt("ready");
				int unacked = resultSet.getInt("unacked");
				int total = resultSet.getInt("total");
				String incoming = resultSet.getString("incoming");
				String deliverget = resultSet.getString("deliverget");
				String ack = resultSet.getString("ack");	
				String created_at = resultSet.getString("created_at");
				String updated_at = resultSet.getString("updated_at");
				
				obj.setName(name);
				obj.setFeatures(features);
				obj.setReady(ready);
				obj.setReady(ready);
				obj.setUnacked(unacked);
				obj.setTotal(total);
				obj.setIncoming(incoming);
				obj.setDeliverget(deliverget);
				obj.setAck(ack);
				obj.setCreated_at(created_at);
				obj.setUpdated_at(updated_at);			
				
				//System.out.println(obj);
				retobj.add(obj);
				
			}  
			
			prepStatement.close();
			connection.close();	

		} else {
			System.out.println("Failed to RETRIEVE RMQObj object ");
		}
		
		return retobj;
		

	}

	
	
	
}
