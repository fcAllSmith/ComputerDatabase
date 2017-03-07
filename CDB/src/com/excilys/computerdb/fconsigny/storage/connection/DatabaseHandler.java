package com.excilys.computerdb.fconsigny.storage.connection;

import java.sql.Connection;

import com.excilys.computerdb.fconsigny.storage.database.Database;

public class DatabaseHandler {

	private static DatabaseHandler dbHandler;

	private static Connection connection; 

	private DatabaseHandler(){
		connection = Database.getInstance().getConnection(); 
	}

	public static Connection getSession(){
		if(dbHandler == null){
			dbHandler = new DatabaseHandler();
		}

		return connection;
	}
}
