package com.excilys.computerdb.fconsigny.services;

import com.excilys.computerdb.fconsigny.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.model.Computer;
import com.excilys.computerdb.fconsigny.utils.Factory;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ComputerServices {
	private ComputerDao cDao = Factory.getComputerDao();

	public Computer getUniqueComputer(long id) throws ComputerException{

		try {
			Computer computer = cDao.findById(id);
			if(computer != null){
				if(computer.getName() != null){
					return computer;
				}else{
					throw new ComputerException("The computer has no name");
				}
			}else{
				throw new ComputerException("Computer not Found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerException("Computer not Found ! Query cannot be send");
		}	
	}

	public List<Computer> getAllComputers(){
		try {
			return cDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	public Computer saveComputer(Computer entity){
		Computer savedComputer = null; 
		
		try {
			if(cDao.findById(entity.getId()) == null){
				savedComputer = cDao.makePersistent(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return savedComputer;
		
	}

}
