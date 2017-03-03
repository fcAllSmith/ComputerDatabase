package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;

public interface ComputerDao {
	public Computer findById(long id)  throws SQLException;
	
	public List findAll()  throws SQLException;
	
	public List findAllWithLimiter(int limit,int offset);
}