package com.excilys.computerdb.fconsigny.business.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletRequest;

import com.excilys.computerdb.fconsigny.storage.connection.DatabaseHandler;

public class ServletFactory {

	public static final String ATT_NAME = "CURRENT_CONNECTION";

	public static Connection getSession() throws SQLException{
		Connection connection = DatabaseHandler.getSession();
		connection.setAutoCommit(false);
		return connection;
	}

	public static void storeConnection(ServletRequest request, Connection connection){
		request.setAttribute(ATT_NAME, connection);
	}

	public static Connection getStoredConnection(ServletRequest request){
		Connection connection = (Connection) request.getAttribute(ATT_NAME);
		return connection;
	}

}
