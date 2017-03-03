package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.exceptions.ComputerException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ComputerServices {
  private ComputerDao cDao = ComputerFactory.getComputerDao();
  
  public Computer getUniqueComputer(long id) throws ComputerException {
    try {
      Computer computer = cDao.findById(id);
      if (computer != null) {
        if(computer.getName() != null){
          return computer;
        } else {
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

  public List<Computer> getAllComputersWithLimiter(int limit, int offset){
    return cDao.findAllWithLimiter(limit, offset);
  }

  public Computer saveComputer(Computer entity){
    Computer savedComputer = null; 

    try {
      if (cDao.findById(entity.getId()) == null) {
		 //TODO : CREATE COMPUTER
		 //savedComputer = cDao.makePersistent(entity);
      } else {
		//TODO upload computer
	  }
    } catch (SQLException error) {
      error.printStackTrace();
    }
    return savedComputer;
  }

}
