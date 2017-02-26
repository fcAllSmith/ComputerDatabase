package com.excilys.computerdb.fconsigny;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Database instance; 
	private Connection connection; 
	private static final String JDB_DRIVER = "com.mysql.jdbc.Driver"; 
	
	private Database() {
		// TODO Auto-generated constructor stub
		openConnection();
	}
	
	public static Database getInstance(){
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}

	private void openConnection(){
		try {
			Class.forName(JDB_DRIVER);
			String customPDO = "jdbc:mysql://localhost:3306/computer-database-db2";
			try{
				this.connection = DriverManager.getConnection(customPDO,"root","kXZXLPTXMMRR13");
			}catch(SQLException error){
				System.out.println(error.toString());
			}finally{
				System.out.println("Connected");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
