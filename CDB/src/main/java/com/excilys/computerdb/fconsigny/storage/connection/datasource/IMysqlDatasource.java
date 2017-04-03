package com.excilys.computerdb.fconsigny.storage.connection.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

public interface IMysqlDatasource {

  public DataSource getDataSource() throws ClassNotFoundException; 
  public Connection getConnection() throws DatabaseException; 
  
  public void closeConnection(Connection connection) throws SQLException;
}
