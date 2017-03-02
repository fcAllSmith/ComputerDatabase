package com.excilys.computerdb.fconsigny.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.computerdb.fconsigny.app.log.DoLogger;

public class DatabaseManager {

  private Connection connection;
  
  public DatabaseManager() {}

  /**
  * 
  * @param query 
  * @return return a ResultSet of the entity or entities selected.
  */
  public ResultSet queryGet(String query) {
		Database db = Database.getInstance();
		this.connection  = db.getConnection(); 
		try {
			Statement stm = connection.createStatement();
			return stm.executeQuery(query);
		} catch (SQLException e) {
			DoLogger.doLog(DatabaseManager.class, "The query GET : " + query + " didn't work" );
			e.printStackTrace();
			return null;
		}
	}

  /**
  * 
  * @param query
  * @return true if entity has been updated or inserted
  */
  public boolean queryPost(String query) {
    Database db = Database.getInstance();
    this.connection = db.getConnection();
    try {
      Statement stm = connection.createStatement();
      return (stm.executeUpdate(query) > 0);
    } catch (SQLException error) {
      DoLogger.doLog(DatabaseManager.class, "The query POST : " + query + " didn't work" );
      error.printStackTrace();
    } 
    return false; 
  }

  public void closeConnection() {
    try {
      this.connection.close();
    } catch (SQLException error) {
      DoLogger.doLog(DatabaseManager.class, "Enable to close the connection with the database");
      error.printStackTrace();
    }
  }
}
