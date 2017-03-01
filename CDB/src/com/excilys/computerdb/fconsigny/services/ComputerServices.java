package com.excilys.computerdb.fconsigny.services;

import com.excilys.computerdb.fconsigny.dao.ComputerDaoImpl;
import com.excilys.computerdb.fconsigny.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.model.Computer;
import java.time.ZoneId;
import java.util.List;

public class ComputerServices {
	private ComputerDaoImpl cDao = new ComputerDaoImpl();

	public Computer checkComputer(long id) throws ComputerException{
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
	}
	
	public List<Computer> getAllComputers(){
		return cDao.findAll();
	}

}
