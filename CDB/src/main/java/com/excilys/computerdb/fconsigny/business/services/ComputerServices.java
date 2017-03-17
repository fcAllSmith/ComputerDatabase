package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;

import java.sql.SQLException;
import java.util.List;

public class ComputerServices {

	public ComputerServices() {
		super();
	}

	private ComputerDao cDao = ComputerFactory.getComputerDao();

	public Computer getUniqueComputer(long id) {
		return cDao.findById(id);
	}

	public List<Computer> getAllComputers(){
		return cDao.findAll();
	}

	public List<Computer> getAllComputersWithLimiter(int offset,int limit, String name){
		return cDao.findAllWithLimiter(name,limit, offset);
	}

	/**
	 * 
	 * @param computerDto
	 * @return true if successful.
	 */
	public boolean saveComputer(Computer computer){
		return cDao.addComputer(computer); 
	}

	public boolean editComptuter(Computer computer){
		return cDao.updateComputer(computer);
	}

	public boolean deleteComputer(long id){
		return cDao.deleteComputer(id);
	}

}
