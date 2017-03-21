package com.excilys.computerdb.fconsigny.storage.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.utils.log.DoLogger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
    Properties properties = new Properties();
    FileInputStream file = null;
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      file = new FileInputStream(classLoader.getResource("db.properties").getPath());
      properties.load(file);
      Class.forName(properties.getProperty("DB_DRVER_CLASS"));
      localConnection.set(DriverManager.getConnection(properties.getProperty("DB_URL"),
          properties.getProperty("DB_USERNAME"), properties.getProperty("DB_PASSWORD")));
      this.connection = localConnection.get();
      return this.connection;
    } catch (IOException | ClassNotFoundException | SQLException error) {
      logger.error(error);
      System.out.println(error);
      return null;
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
