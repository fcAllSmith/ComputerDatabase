package com.excilys.computerdb.fconsigny.storage.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;

public class DatabaseManager {

  private static Connection connection;

}
