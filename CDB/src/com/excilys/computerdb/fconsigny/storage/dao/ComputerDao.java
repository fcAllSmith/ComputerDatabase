package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;

public interface ComputerDao {
	public Computer findById(long id);
	
	public List<Computer> findAll();
	
	public List<Computer> findAllWithLimiter(int limit,int offset);
}
