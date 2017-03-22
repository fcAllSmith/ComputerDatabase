package com.excilys.computerdb.fconsigny.storage.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
  private HikariDataSource dataSource;
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
      initDataSource();
      this.connection = getDataSource().getConnection();
      return this.connection;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println(e);
      return null;
    }
  }

  private void initDataSource() {
    Properties properties = new Properties();
    FileInputStream file = null;

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      file = new FileInputStream(classLoader.getResource("db.properties").getPath());
      properties.load(file);

      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setJdbcUrl(properties.getProperty("DB_URL"));
      hikariConfig.setUsername(properties.getProperty("DB_USERNAME"));
      hikariConfig.setPassword(properties.getProperty("DB_PASSWORD"));

      hikariConfig.setMaximumPoolSize(5);
      hikariConfig.setConnectionTestQuery("SELECT 1");

      hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
      hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
      hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

      dataSource = new HikariDataSource(hikariConfig);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println(e.toString());
    }
  }

  public static DataSource getDataSource() {
    if (datasource == null) {
      try {
        Class.forName(JDB_DRIVER);
        HikariConfig conf = new HikariConfig();
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db2?zeroDateTimeBehavior=convertToNull");
        conf.setUsername("root");
        conf.setPassword("kXZXLPTXMMRR13");
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
