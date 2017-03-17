package com.excilys.computerdb.fconsigny.business.factory;

import com.excilys.computerdb.fconsigny.storage.connection.DatabaseHandler;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDaoImpl;

public class ComputerFactory {

	public ComputerFactory(){
		
	}

	public static ComputerDao getComputerDao() {
		return new ComputerDaoImpl(DatabaseHandler.getSession());
	} 
}
