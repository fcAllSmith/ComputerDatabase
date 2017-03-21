package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;

public interface IComputerServices {

	public Computer getUniqueComputer(final long id); 
	
	public List<Computer> getAllComputers();
	
	public List<Computer> getAllComputersWithLimiter(final int offset,final int limit,final String name); 
	
	public boolean saveComputer(final Computer computer);
	
	public boolean editComptuter(final Computer computer);
	
	public boolean deleteComputer(final long id);
}
