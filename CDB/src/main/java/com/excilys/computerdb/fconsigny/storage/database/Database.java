package com.excilys.computerdb.fconsigny.storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		if(this.connection != null){
			return this.connection;	
		}

		return setConnection();
	}

	public Connection setConnection(){
		/*try {
			Class.forName(JDB_DRIVER);
			String customPDO = "jdbc:mysql://localhost:3306/computer-database-db2";
			try {
				this.connection = DriverManager.getConnection(customPDO,"root","pwd");
				return this.connection;
			} catch(SQLException error) {
				DoLogger.doLog(Database.class,"Enable to reach the database");
				return null;
			}
		} catch (ClassNotFoundException error) {
			error.printStackTrace();
			return null;
		}*/
		Connection connection = null; 
		try {
			Class.forName(JDB_DRIVER);
			String customPDO = "jdbc:mysql://172.17.0.2:3306/computer-database-db";
			try {
				//this.connection = DriverManager.getConnection(customPDO,"root","pwd");
				localConnection.set(DriverManager.getConnection(customPDO,"root","pwd"));
				this.connection = localConnection.get();
				return this.connection;
			} catch(SQLException error) {
				logger.error(error);
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/*try {
			//connection = getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			DoLogger.doLog(Database.class,"Enable to reach the database or connection POOL MAX");
		}*/
		return connection ;
	}

	public static DataSource getDataSource(){
		if(datasource == null){
			try {
				Class.forName(JDB_DRIVER);
				HikariConfig conf = new HikariConfig();
				conf.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db2");
				conf.setUsername("root");
				conf.setPassword("pwd");
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