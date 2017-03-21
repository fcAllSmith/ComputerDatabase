package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

@Service("computerService")
//@Transactional
public class ComputerServices implements IComputerServices {
	
	private final ComputerDao  computerDao = ComputerFactory.getComputerDao();
	
	@Autowired
	public ComputerServices(){
		
	}
	
	// @Transactional
	public Computer getUniqueComputer(final long id) {
		return computerDao.findById(id);
	}
	// @Transactional
	public List<Computer> getAllComputers(){
		return computerDao.findAll();
	}
	// @Transactional
	public List<Computer> getAllComputersWithLimiter(final int offset,final int limit,final String name){
		return computerDao.findAllWithLimiter(name,limit, offset);
	}

	/**
	 * 
	 * @param computerDto
	 * @return true if successful.
	 */
	// @Transactional
	public boolean saveComputer(final Computer computer){
		return computerDao.addComputer(computer); 
	}
	// @Transactional
	public boolean editComptuter(final Computer computer){
		return computerDao.updateComputer(computer);
	}
	// @Transactional
	public boolean deleteComputer(final long id){
		return computerDao.deleteComputer(id);
	}

	
}
