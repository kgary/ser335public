package com.journaldev.jdbc.statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionKG {

	private static Properties props = KGProperties.getProps();

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = null;

		// load the Driver Class
		Class.forName(props.getProperty("db.driver"));

		// create the connection now
		con = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.passwd"));

		System.out.println("DB Connection created successfully");
		return con;
	}
}
