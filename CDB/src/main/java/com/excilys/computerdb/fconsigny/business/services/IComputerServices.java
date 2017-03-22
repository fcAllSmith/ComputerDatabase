package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.model.Computer;

public interface IComputerServices {

  public Computer getUniqueComputer(final long id) throws ServiceException;

  public List<Computer> getAllComputers() throws ServiceException;

  public List<Computer> getAllComputersWithLimiter(final int offset, final int limit, final String name) throws ServiceException;

  public boolean saveComputer(final Computer computer) throws ServiceException;

  public boolean editComptuter(final Computer computer) throws ServiceException;

  public boolean deleteComputer(final long id) throws ServiceException;
}
