package com.excilys.computerdb.fconsigny.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	public DatabaseManager() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet queryGet(String query){
		Database db = Database.getInstance();
		Connection connection  = db.getConnection(); 
		try {
			Statement stm = connection.createStatement();
			return stm.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	

}
