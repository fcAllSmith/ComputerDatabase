package com.excilys.computerdb.fconsigny.storage.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class Database {

  private static Database instance;
  private static DataSource datasource;
  private ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>();

  private static Logger logger = Logger.getLogger(Database.class);

  private Connection connection;
  private static final String JDB_DRIVER = "com.mysql.jdbc.Driver";

  private Database() {

  }

  public static Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }

  public Connection getConnection() {
    if (this.connection != null) {
      return this.connection;
    }

    return setConnection();
  }

  public Connection setConnection() {
    try {
      this.connection = getDataSource().getConnection();
      return this.connection;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println(e);
      return null;
    }
  }

  public static DataSource getDataSource() {
    if (datasource == null) {
      Properties properties = new Properties();
      FileInputStream file = null;
      try {
        Class.forName(JDB_DRIVER);
        HikariConfig conf = new HikariConfig();
        conf.setJdbcUrl(properties.getProperty("DB_URL"));
        conf.setUsername(properties.getProperty("DB_USERNAME"));
        conf.setPassword(properties.getProperty("DB_PASSWORD"));
        conf.setAutoCommit(false);
        conf.setMaximumPoolSize(10);
        datasource = new HikariDataSource(conf);
      } catch (ClassNotFoundException e) {
        logger.error(e);
      }
    }
    return datasource;
  }
}
