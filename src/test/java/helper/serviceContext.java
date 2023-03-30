package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class serviceContext {

	private Connection connection;
	private PreparedStatement prepStatement;
	private Statement statement;
	private ResultSet resultSet;
	

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPrepStatement() {
		return prepStatement;
	}

	public void setPrepStatement(PreparedStatement prepStatement) {
		this.prepStatement = prepStatement;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
}
