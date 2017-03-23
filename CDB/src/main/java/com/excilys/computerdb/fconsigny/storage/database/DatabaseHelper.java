package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseHelper {

  private static Logger logger = Logger.getLogger(DatabaseHelper.class);

  public DatabaseHelper() {
    
  }

  /**
   * 
   * @param query
   *          : query send by the dao
   * @return return a ResultSet of the entity or entities selected.
   */
  public static ResultSet queryGet(Connection connection, final String query) {
    try {
      Statement stm = connection.createStatement();
      return stm.executeQuery(query);
    } catch (SQLException e) {
      logger.error(e);
      System.out.print(e);
      return null;
    }
  }

  /**
   * 
   * @param query
   *          : query send by the dao
   * @return true if entity has been updated or inserted.
   */
  public static boolean queryPost(Connection connection, final String query) {
    try {
      Statement stm = connection.createStatement();
      return (stm.executeUpdate(query) > 0);
    } catch (SQLException error) {
      logger.error(error);
    }
    return false;
  }
}
