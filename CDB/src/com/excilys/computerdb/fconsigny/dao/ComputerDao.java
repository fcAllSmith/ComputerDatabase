package com.excilys.computerdb.fconsigny.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdb.fconsigny.model.Computer;

public interface ComputerDao {
	public Computer findById(long id)  throws SQLException;
	
	public List findAll()  throws SQLException;
	
	public abstract Computer makePersistent(Computer entity);
}
