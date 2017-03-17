package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.utils.log.DoLogger;

public class DatabaseHelper {

	private static Logger logger = Logger.getLogger(DatabaseHelper.class);

	public DatabaseHelper() {}

	/**
	 * 
	 * @param query 
	 * @return return a ResultSet of the entity or entities selected.
	 */
	public ResultSet queryGet(Connection connection,final String query) {
		Database db = Database.getInstance();
		connection  = db.getConnection(); 
		try {
			Statement stm = connection.createStatement();
			return stm.executeQuery(query);
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 
	 * @param query
	 * @return true if entity has been updated or inserted
	 */
	public boolean queryPost(Connection connection ,final String query) {
		Database db = Database.getInstance();
		connection = db.getConnection();
		try {
			Statement stm = connection.createStatement();
			return (stm.executeUpdate(query) > 0);
		} catch (SQLException error) {
			logger.error(error);
		} 
		return false; 
	}
}
