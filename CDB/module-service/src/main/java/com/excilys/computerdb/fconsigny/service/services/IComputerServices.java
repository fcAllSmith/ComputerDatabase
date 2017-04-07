package src.main.java.com.excilys.computerdb.fconsigny.service.services;

import java.util.List;

import src.main.java.com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import src.main.java.com.excilys.computerdb.fconsigny.core.model.Computer;

public interface IComputerServices {

  public Computer getUniqueComputer(final long id) throws ServiceException;

  public List<Computer> getAllComputers() throws ServiceException;

  public List<Computer> getAllComputersWithLimiter(final int offset, final int limit, final String name) throws ServiceException;

  public boolean saveComputer(final Computer computer) throws ServiceException;

  public boolean editComptuter(final Computer computer) throws ServiceException;

  public boolean deleteComputer(final long id) throws ServiceException;
}
