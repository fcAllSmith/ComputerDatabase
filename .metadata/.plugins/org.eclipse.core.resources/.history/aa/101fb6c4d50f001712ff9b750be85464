package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;

public class MysqlDatabase {

  private static MysqlDatabase instance;
  private final Connection connection;
  
 // private ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>();

  private MysqlDatabase(Connection connection) {
    this.connection = connection; 
  }

  public static MysqlDatabase getInstance(Connection connection) {
    if (instance == null) {
      instance = new MysqlDatabase(connection);
    }
    return instance;
  }
  
  public Connection getConnection() {
    return this.connection;
  }
}
