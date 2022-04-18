package fpt.fa.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection cn;

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}

	private String serverName = "localhost";
	private String dbName = "QuizApp";
	private String portNumber = "1433";
	private String instance = "";// LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private String userID = "sa";
	private String password = "123";

	
}
