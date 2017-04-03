package com.excilys.computerdb.fconsigny.storage.connection.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

public interface IMysqlDatasource {

  public Connection getConnection() throws DatabaseException; 
  
  public void closeConnection(Connection connection) throws SQLException;
}