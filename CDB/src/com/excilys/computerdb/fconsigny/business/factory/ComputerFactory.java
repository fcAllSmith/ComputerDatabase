package com.excilys.computerdb.fconsigny.business.factory;

import java.sql.SQLException;

import com.excilys.computerdb.fconsigny.storage.connection.DatabaseHandler;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDaoImpl;

public class ComputerFactory {

	public ComputerFactory(){
		super();
	}
  public static ComputerDao getComputerDao() throws SQLException{
    return new ComputerDaoImpl(DatabaseHandler.getSession());
  } 
}
