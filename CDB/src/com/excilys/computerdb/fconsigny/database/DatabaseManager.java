package com.excilys.computerdb.fconsigny.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	private Connection connection;

	public DatabaseManager() {}

	/**
	 * 
	 * @param query 
	 * @return return a ResultSet of the entity or entities selected
	 */
	public ResultSet queryGet(String query){
		Database db = Database.getInstance();
		this.connection  = db.getConnection(); 
		try {
			Statement stm = connection.createStatement();
			return stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param query
	 * @return true if entity has been updated or inserted
	 */
	public boolean queryPost(String query){
		Database db = Database.getInstance();
		this.connection = db.getConnection();
		try {
			Statement stm = connection.createStatement();
			return (stm.executeUpdate(query) > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false; 
	}

	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
