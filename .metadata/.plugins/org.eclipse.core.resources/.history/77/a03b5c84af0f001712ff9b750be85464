package com.excilys.computerdb.fconsigny.storage.connection.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.excilys.computerdb.fconsigny.storage.database.MysqlDatabase;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Service("mysqldb")
public class MysqlDatasource implements IMysqlDatasource {

  private DataSource getDataSource() throws ClassNotFoundException {
    Properties properties = new Properties();
    ClassLoader classLoader = getClass().getClassLoader();
    File configFile = new File(classLoader.getResource("db.properties").getFile());

    try {
      properties.load(new FileInputStream(configFile));
      Class.forName(properties.getProperty("DB_DRVER_CLASS"));
      HikariConfig conf = new HikariConfig();
      conf.setJdbcUrl(properties.getProperty("DB_URL"));
      conf.setUsername(properties.getProperty("DB_USERNAME"));
      conf.setPassword(properties.getProperty("DB_PASSWORD"));
      conf.setAutoCommit(false);
      conf.setMaximumPoolSize(10);
      return new HikariDataSource(conf);

    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public Connection getConnection() throws DatabaseException {

    DataSource hikariDatasource;
    try {
      hikariDatasource = getDataSource();
      MysqlDatabase mysqlDatabase = MysqlDatabase.getInstance(hikariDatasource.getConnection());
      return mysqlDatabase.getConnection();
    } catch (ClassNotFoundException | SQLException e) {
      throw new  DatabaseException ("Database Unreachable : " + e.getMessage());
    } 
  }

  @Override
  public void closeConnection(Connection connection) throws SQLException {
    connection.close();
  }



}
