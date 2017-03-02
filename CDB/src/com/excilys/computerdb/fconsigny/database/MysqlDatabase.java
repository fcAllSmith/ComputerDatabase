package com.excilys.computerdb.fconsigny.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDatabase {

	/* DB Constant  **/
	private static final String DB_HOST = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME ="computer-database-db2";
	private static final String JDB_DRIVER = "com.mysql.jdbc.Driver"; 

	/* Unique DB instance  */
	private static MysqlDatabase _instance;
	private MysqlDatabase(){}
	
	public static MysqlDatabase getInstance(){
		if(_instance == null){
			_instance = new MysqlDatabase();
		}
		
		return _instance;
	}

	public static Connection getMySQLConnection(){

		try {
			Class.forName(JDB_DRIVER);
			String customPDO = "jdbc:mysql://localhost:3306/computer-database-db2";
			return  DriverManager.getConnection(customPDO,"root","pwd");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet querySelect(String tableName){
		try {
			Statement stmt = this.prepareQuery();		
			stmt = getMySQLConnection().createStatement();
			return this.execQuery(stmt,"SELECT * FROM " + tableName + ";");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 	
		return null;
	}

	public ResultSet querySelectById(String tableName , int id){
		try {
			Statement stmt = this.prepareQuery();
			return this.execQuery(stmt, "SELECT * FROM " + tableName + " WHERE ID =  " + id +";");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return null;
	}

	public ResultSet doQuery(String query) throws SQLException{
		Statement stmt = this.prepareQuery();		
		return this.execQuery(stmt,query);
	}
	
	public int modifyData(String query) throws SQLException{
		Statement stmt = this.prepareQuery();
		return stmt.executeUpdate(query);
	}
	
	private Statement prepareQuery() throws SQLException{
		return getMySQLConnection().createStatement(); 
	}

	private ResultSet execQuery(Statement stmt ,String str_query) throws SQLException{
		return stmt.executeQuery(str_query);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
