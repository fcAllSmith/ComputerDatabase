package com.excilys.computerdb.fconsigny.dao;

import java.util.List;

import com.excilys.computerdb.fconsigny.model.Computer;

public interface ComputerDao {
	public Computer findById(long id);
	public List findAll();
}
