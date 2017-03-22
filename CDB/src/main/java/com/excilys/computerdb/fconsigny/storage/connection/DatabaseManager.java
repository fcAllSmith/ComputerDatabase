package com.excilys.computerdb.fconsigny.storage.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;

public class DatabaseManager {

  private static Connection connection;
  
  @Autowired
  IMysqlDatasource iMysqlDatasource; 
 
  public Connection getSession()  {

    try {
      connection = iMysqlDatasource.getConnection();
      return connection = iMysqlDatasource.getConnection();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

}
