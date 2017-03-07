package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerdb.fconsigny.utils.log.DoLogger;

public class Database {

	private static Database instance; 
	private Connection connection; 
	private static final String JDB_DRIVER = "com.mysql.jdbc.Driver"; 

	private Database() {
		
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Connection getConnection() {
		if(this.connection != null){
			return this.connection;	
		}
		
		return setConnection();
	}
	
	public Connection setConnection(){
		try {
			Class.forName(JDB_DRIVER);
			String customPDO = "jdbc:mysql://localhost:3306/computer-database-db2";
			try {
				this.connection = DriverManager.getConnection(customPDO,"root","pwd");
				return this.connection;
			} catch(SQLException error) {
				DoLogger.doLog(Database.class,"Enable to reach the database");
				return null;
			}
		} catch (ClassNotFoundException error) {
			error.printStackTrace();
			return null;
		}
	}
}
