package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("computerService")
public class ComputerServices implements IComputerServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();
  
  @Autowired
  IMysqlDatasource datasource; 

  @Autowired
  public ComputerServices() {}

  public Computer getUniqueComputer(final long id) throws ServiceException {
    try {
      return computerDao.findById(datasource.getConnection(),id);
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

  public List<Computer> getAllComputers() throws ServiceException{
    try {
      return computerDao.findAll(datasource.getConnection());
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

  public List<Computer> getAllComputersWithLimiter (final int offset, final int limit, final String name) throws ServiceException {
    try {
      return computerDao.findAllWithLimiter(datasource.getConnection(),name, limit, offset);
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

  /**
   * 
   * @param computerDto
   * @return true if successful.
   */
  public boolean saveComputer(final Computer computer) throws ServiceException {
    try {
      return computerDao.addComputer(datasource.getConnection(),computer);
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

  public boolean editComptuter(final Computer computer) throws ServiceException {
    try {
      return computerDao.updateComputer(datasource.getConnection(),computer);
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

  public boolean deleteComputer(final long id) throws ServiceException{
    try {
      return computerDao.deleteComputer(datasource.getConnection(),id);
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

}
