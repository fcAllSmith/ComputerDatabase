package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.computerdb.fconsigny.utils.log.DoLogger;

public class DatabaseHelper {

	public DatabaseHelper() {}

	/**
	 * 
	 * @param query 
	 * @return return a ResultSet of the entity or entities selected.
	 */
	public ResultSet queryGet(Connection connection,String query) {
		Database db = Database.getInstance();
		connection  = db.getConnection(); 
		try {
			Statement stm = connection.createStatement();
			return stm.executeQuery(query);
		} catch (SQLException e) {
			DoLogger.doLog(DatabaseHelper.class, "The query GET : " + query + " didn't work" );
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param query
	 * @return true if entity has been updated or inserted
	 */
	public boolean queryPost(Connection connection ,String query) {
		Database db = Database.getInstance();
		connection = db.getConnection();
		try {
			Statement stm = connection.createStatement();
			return (stm.executeUpdate(query) > 0);
		} catch (SQLException error) {
			DoLogger.doLog(DatabaseHelper.class, "The query POST : " + query + " didn't work" );
			error.printStackTrace();
		} 
		return false; 
	}
}
