package com.excilys.computerdb.fconsigny.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerdb.fconsigny.app.log.DoLogger;

public class Database {

  private static Database instance; 
  private Connection connection; 
  private static final String JDB_DRIVER = "com.mysql.jdbc.Driver"; 
  
  private Database() {
    openConnection();
  }
	
  public static Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }

  private void openConnection() {
    try {
      Class.forName(JDB_DRIVER);
      String customPDO = "jdbc:mysql://localhost:3306/computer-database-db2";
      try {
        this.connection = DriverManager.getConnection(customPDO,"root","kXZXLPTXMMRR13");
        System.out.println("Connected");
      } catch(SQLException error) {
        DoLogger.doLog(Database.class,"Enable to reach the database");
        System.out.println(error.toString());
      }
    } catch (ClassNotFoundException error) {
			error.printStackTrace();
    }
  }

  public Connection getConnection() {
    return this.connection;
  }
}