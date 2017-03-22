package com.excilys.computerdb.fconsigny.storage.connection;

import java.sql.Connection;

import com.excilys.computerdb.fconsigny.storage.database.Database;

public class DatabaseManager {

  private static DatabaseManager dbHandler;
  private static Connection connection;

  private DatabaseManager() {
    connection = Database.getInstance().getConnection();
  }

  public static Connection getSession() {

    if (dbHandler == null) {
      dbHandler = new DatabaseManager();
    }

    return connection;
  }

}
